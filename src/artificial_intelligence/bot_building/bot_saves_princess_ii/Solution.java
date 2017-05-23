package artificial_intelligence.bot_building.bot_saves_princess_ii;


import artificial_intelligence.bot_building.Coordinates;
import artificial_intelligence.bot_building.Direction;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Scanner;

public class Solution {
    static Direction nextDirection(Coordinates from, Coordinates to) {
        class State {
            private Coordinates position;
            private Direction origin;

            private State(Coordinates position, Direction origin) {
                this.position = position;
                this.origin = origin;
            }
        }

        Queue<State> frontier = new ArrayDeque<>();
        for (Direction direction : Direction.values()) {
            frontier.add(new State(new Coordinates(from).move(direction), direction));
        }
        while (!frontier.isEmpty()) {
            State current = frontier.remove();
            if (current.position.equals(to)) {
                return current.origin;
            }
            for (Direction direction : Direction.values()) {
                frontier.add(new State(new Coordinates(current.position).move(direction), current.origin));
            }
        }
        assert false;
        return Direction.valueOf("unreachable");
    }

    @SuppressWarnings("WeakerAccess")
    static void nextMove(int n, int r, int c, String[] grid) {
        Coordinates princess = null;
        for (int y = 0; y < grid.length; y++) {
            int x = grid[y].indexOf('p');
            if (x >= 0) {
                princess = new Coordinates(x, y);
                break;
            }
        }

        System.out.println(nextDirection(new Coordinates(c, r), princess).toString());
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n, r, c;
        n = in.nextInt();
        r = in.nextInt();
        c = in.nextInt();
        in.useDelimiter("\n");
        String grid[] = new String[n];


        for (int i = 0; i < n; i++) {
            grid[i] = in.next();
        }

        nextMove(n, r, c, grid);

    }
}
