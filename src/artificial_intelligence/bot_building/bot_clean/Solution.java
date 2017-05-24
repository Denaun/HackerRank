package artificial_intelligence.bot_building.bot_clean;

import artificial_intelligence.bot_building.*;

import java.util.*;

public class Solution {
    static Iterable<Action> generatePlan(Coordinates start, Collection<Coordinates> dirt) {
        class State implements Comparable<State> {
            private Coordinates position;
            private ArrayList<Action> history;
            private ArrayList<Coordinates> remainingDirt;

            private State(Coordinates position, ArrayList<Action> history, ArrayList<Coordinates> remainingDirt) {
                this.position = position;
                this.history = history;
                this.remainingDirt = remainingDirt;
            }

            /**
             * @return Simple A* weight (depth + a simple admissible heuristic).
             */
            private int weight() {
                return this.history.size() + this.remainingDirt.size();
            }

            @Override
            public int compareTo(State o) {
                return this.weight() - o.weight();
            }

            @Override
            public boolean equals(Object o) {
                if (this == o) return true;
                if (o == null || getClass() != o.getClass()) return false;

                State state = (State) o;

                return position.equals(state.position) && remainingDirt.equals(state.remainingDirt);
            }

            @Override
            public int hashCode() {
                int result = position.hashCode();
                result = 31 * result + remainingDirt.hashCode();
                return result;
            }
        }

        Queue<State> frontier = new PriorityQueue<>();
        frontier.add(new State(start, new ArrayList<>(), new ArrayList<>(dirt)));
        Collection<State> visited = new HashSet<>();
        while (!frontier.isEmpty()) {
            State current = frontier.remove();
            if (current.remainingDirt.isEmpty()) return current.history;

            if (current.remainingDirt.contains(current.position)) {
                ArrayList<Action> newActions = new ArrayList<>(current.history);
                newActions.add(new Clean());
                ArrayList<Coordinates> newDirt = new ArrayList<>(current.remainingDirt);
                newDirt.remove(current.position);
                State newState = new State(current.position, newActions, newDirt);
                if (!visited.contains(newState)) {
                    frontier.offer(newState);
                    visited.add(newState);
                }
                continue;
            }
            for (Direction direction : Direction.values()) {
                ArrayList<Action> newActions = new ArrayList<>(current.history);
                newActions.add(new Move(direction));
                State newState = new State(new Coordinates(current.position).move(direction), newActions,
                                           current.remainingDirt);
                if (!visited.contains(newState)) {
                    frontier.offer(newState);
                    visited.add(newState);
                }
            }
        }
        assert false;
        return Collections.emptyList();
    }

    @SuppressWarnings("WeakerAccess")
    static void nextMove(int r, int c, String[] grid) {
        Coordinates bot = new Coordinates(c, r);
        ArrayList<Coordinates> dirt = new ArrayList<>();
        for (int y = 0; y < grid.length; y++) {
            for (int x = 0; x < grid[y].length(); x++) {
                if (grid[y].charAt(x) == 'd') {
                    dirt.add(new Coordinates(x, y));
                }
            }
        }
        Iterable<Action> plan = generatePlan(bot, dirt);
        System.out.println(plan.iterator().next().toString());
    }

    public static void main(String[] args) {
        final int N = 5;
        Scanner in = new Scanner(System.in);
        int r = in.nextInt();
        int c = in.nextInt();
        in.useDelimiter("\n");
        String grid[] = new String[N];
        for (int i = 0; i < N; i++) {
            grid[i] = in.next();
        }

        nextMove(r, c, grid);
    }
}
