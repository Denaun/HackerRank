package artificialintelligence.botbuilding.botcleanlarge;

import artificialintelligence.botbuilding.Coordinates;

import java.util.*;

class Solver {
    private final Coordinates start;
    private final Collection<Coordinates> objectives;

    private State solution;

    Solver(Coordinates start, Collection<Coordinates> objectives) {
        this.start = start;
        this.objectives = objectives;

        this.solution = new State(start, new ArrayList<>(objectives), new ArrayList<>(), 0);
    }

    private static double getTransitionProbability(int newValue, int oldValue, double temperature) {
        if (newValue < oldValue) {
            return 1;
        }
        return Math.exp((oldValue - newValue) / temperature);
    }

    /**
     * @param timeLimit Time, in nanoseconds, after which the search will stop.
     * @return `true` if the search reached the optimal solution within the time frame.
     */
    boolean solveAStar(long timeLimit) {
        final long startTime = System.nanoTime();
        Queue<State> frontier = new PriorityQueue<>();
        frontier.add(new State(start, new ArrayList<>(), new ArrayList<>(objectives),
                               getEstimate(start, objectives)));
        while (!frontier.isEmpty()) {
            State current = frontier.remove();
            if (current.getRemaining().isEmpty()) {
                solution = current;
                return true;
            }
            if (System.nanoTime() - startTime > timeLimit) {
                ArrayList<Coordinates> finalHistory = new ArrayList<>(current.getHistory());
                finalHistory.addAll(current.getRemaining());
                solution = new State(start, finalHistory, new ArrayList<>(), 0);
                return false;
            }

            for (Coordinates aNode : current.getRemaining()) {
                ArrayList<Coordinates> newHistory = new ArrayList<>(current.getHistory());
                newHistory.add(aNode);
                ArrayList<Coordinates> newRemaining = new ArrayList<>(current.getRemaining());
                newRemaining.remove(aNode);
                State newState = new State(start, newHistory, newRemaining,
                                           getEstimate(newHistory.get(newHistory.size() - 1),
                                                       newRemaining));
                frontier.offer(newState);
            }
        }
        throw new AssertionError();
    }

    void solveSa(long iterations) {
        Random generator = new Random();
        ArrayList<Coordinates> currentSolution = solution.getHistory();
        if (currentSolution.size() > 1) {
            int currentCost = State.getCost(start, currentSolution);
            for (long k = 0; k < iterations; k++) {
                double temperature = 1f - ((double) k / iterations);
                int i = generator.nextInt(currentSolution.size() - 1);
                int newCost = currentCost;
                if (i == 0) {
                    newCost -= start.getL1DistanceFrom(currentSolution.get(i));
                    newCost += start.getL1DistanceFrom(currentSolution.get(i + 1));
                } else {
                    newCost -= currentSolution.get(i - 1).getL1DistanceFrom(currentSolution.get(i));
                    newCost += currentSolution.get(i - 1)
                                              .getL1DistanceFrom(currentSolution.get(i + 1));
                }
                if (i + 2 < currentSolution.size()) {
                    newCost -= currentSolution.get(i + 1)
                                              .getL1DistanceFrom(currentSolution.get(i + 2));
                    newCost += currentSolution.get(i).getL1DistanceFrom(currentSolution.get(i + 2));
                }
                if (getTransitionProbability(newCost, currentCost, temperature) >=
                    generator.nextDouble()) {
                    Collections.swap(currentSolution, i, i + 1);
                    currentCost = newCost;
                }
            }
        }
        solution = new State(start, currentSolution, new ArrayList<>(), 0);
    }

    private int getEstimate(Coordinates substart, Collection<Coordinates> subobjectives) {
        Set<Coordinates> quadrants = new HashSet<>();
        for (Coordinates objective : subobjectives) {
            quadrants.add(new Coordinates(objective.getX() / 10, objective.getY() / 10));
        }
        Solver subsolver = new Solver(
                new Coordinates(substart.getX() / 10, substart.getY() / 10),
                quadrants);
        subsolver.solveSa(10_000);
        return (subsolver.solution.getWeight() - quadrants.size()) * 10 + subobjectives.size();
    }

    Iterable<Coordinates> getSolution() {
        return solution.getHistory();
    }
}
