package artificial_intelligence.bot_building.bot_clean_stochastic;


import artificial_intelligence.bot_building.Coordinates;
import artificial_intelligence.bot_building.Direction;

import java.util.Scanner;

public class Solution {
    static Action generatePlan(Coordinates start, Coordinates dirt) {
        if (start.equals(dirt)) {
            return new Clean();
        }
        if (start.getX() > dirt.getX()) {
            return new Move(Direction.LEFT);
        }
        if (start.getX() < dirt.getX()) {
            return new Move(Direction.RIGHT);
        }
        if (start.getY() > dirt.getY()) {
            return new Move(Direction.UP);
        }
        if (start.getY() < dirt.getY()) {
            return new Move(Direction.DOWN);
        }
        assert false;
        return null;
    }

    @SuppressWarnings("WeakerAccess")
    static void nextMove(int r, int c, String[] grid) {
        Coordinates bot = new Coordinates(c, r);
        Coordinates dirt = null;
        for (int y = 0; y < grid.length; y++) {
            for (int x = 0; x < grid[y].length(); x++) {
                if (grid[y].charAt(x) == 'd') {
                    dirt = new Coordinates(x, y);
                    break;
                }
            }
        }
        Action plan = generatePlan(bot, dirt);
        assert plan != null;
        System.out.println(plan.toString());
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

    interface Action {
    }

    static class Move implements Action {
        Direction direction;

        Move(Direction direction) {
            this.direction = direction;
        }

        @Override
        public String toString() {
            return direction.toString();
        }
    }

    static class Clean implements Action {
        @Override
        public String toString() {
            return "CLEAN";
        }
    }
}
