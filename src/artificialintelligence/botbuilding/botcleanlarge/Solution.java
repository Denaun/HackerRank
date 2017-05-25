package artificialintelligence.botbuilding.botcleanlarge;

import artificialintelligence.botbuilding.*;

import java.util.ArrayList;
import java.util.Scanner;

public class Solution {
    private static Action generatePlan(Coordinates start, Coordinates dirt) {
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
        throw new AssertionError();
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

        if (dirt.contains(bot)) {
            System.out.println(new Clean().toString());
            return;
        }
        Solver solver = new Solver(bot, dirt);
        if (!solver.solveAStar(2_000_000_000L)) {
            solver.solveSa(100_000);
        }
        Iterable<Coordinates> plan = solver.getSolution();
        System.out.println(generatePlan(bot, plan.iterator().next()).toString());
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int r = in.nextInt();
        int c = in.nextInt();
        int h = in.nextInt();
        in.nextInt();  // Width
        in.useDelimiter("\n");
        String grid[] = new String[h];
        for (int i = 0; i < h; i++) {
            grid[i] = in.next();
        }

        nextMove(r, c, grid);
    }
}
