package artificial_intelligence.bot_building.bot_saves_princess;

import artificial_intelligence.bot_building.Coordinates;
import artificial_intelligence.bot_building.Direction;

import java.util.*;

public class Solution {
    static Iterable<Direction> navigate(Coordinates from, Coordinates to) {
        class State {
            private Coordinates position;
            private ArrayList<Direction> history;

            private State(Coordinates position, ArrayList<Direction> history) {
                this.position = position;
                this.history = history;
            }
        }

        Queue<State> frontier = new ArrayDeque<>();
        frontier.add(new State(from, new ArrayList<>()));
        while (!frontier.isEmpty()) {
            State current = frontier.remove();
            if (current.position.equals(to)) {
                return current.history;
            }
            for (Direction direction : Direction.values()) {
                State newState = new State(new Coordinates(current.position), new ArrayList<>(current.history));
                newState.position.move(direction);
                newState.history.add(direction);
                frontier.add(newState);
            }
        }
        assert false;
        return Collections.emptyList();
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int size = in.nextInt();
        Coordinates mario = new Coordinates();
        Coordinates princess = new Coordinates();
        for (int i = 0; i < size; i++) {
            String line = in.next();
            for (int j = 0; j < size; j++) {
                Coordinates coord = new Coordinates(j, i);
                switch (line.charAt(j)) {
                case 'm':
                    mario = coord;
                    break;
                case 'p':
                    princess = coord;
                    break;
                case '-':
                    break;
                default:
                    assert false;
                }
            }
        }

        Iterable<Direction> result = navigate(mario, princess);
        for (Direction d : result) {
            System.out.println(d.toString());
        }
    }
}
