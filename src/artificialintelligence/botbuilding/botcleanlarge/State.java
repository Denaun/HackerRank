package artificialintelligence.botbuilding.botcleanlarge;

import artificialintelligence.botbuilding.Coordinates;

import java.util.ArrayList;

class State implements Comparable<State> {
    private final int cost;
    private final int estimatedRemainingCost;
    private ArrayList<Coordinates> history;
    private ArrayList<Coordinates> remaining;

    State(Coordinates start, ArrayList<Coordinates> history,
          ArrayList<Coordinates> remaining, int estimatedRemainingCost) {
        this.history = history;
        this.remaining = remaining;

        this.cost = getCost(start, history);
        this.estimatedRemainingCost = estimatedRemainingCost;
    }

    static int getCost(Coordinates from, Iterable<Coordinates> steps) {
        int cost = 0;
        Coordinates current = from;
        for (Coordinates step : steps) {
            cost += current.getL1DistanceFrom(step) + 1;
            current = step;
        }
        return cost;
    }

    ArrayList<Coordinates> getHistory() {
        return history;
    }

    ArrayList<Coordinates> getRemaining() {
        return remaining;
    }

    int getWeight() {
        return cost + estimatedRemainingCost;
    }

    @Override
    public int compareTo(State o) {
        return this.getWeight() - o.getWeight();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        State state = (State) o;

        return history.equals(state.history) && remaining.equals(state.remaining);
    }

    @Override
    public int hashCode() {
        int result = history.hashCode();
        result = 31 * result + remaining.hashCode();
        return result;
    }
}
