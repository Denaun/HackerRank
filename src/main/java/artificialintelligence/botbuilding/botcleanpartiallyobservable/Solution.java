package artificialintelligence.botbuilding.botcleanpartiallyobservable;

import artificialintelligence.botbuilding.Coordinates;

import java.io.*;
import java.util.Scanner;

public class Solution {
    private static final String FILE_NAME = "data.ser";

    private static void nextMove(int r, int c, String[] grid) {
        Map map;
        try {
            FileInputStream fileIn = new FileInputStream(FILE_NAME);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            map = (Map) in.readObject();
            if (map.size() != grid.length) throw new AssertionError();
            in.close();
            fileIn.close();
        } catch (FileNotFoundException e) {
            map = new Map(grid.length);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return;
        }

        for (int y = 0; y < grid.length; y++) {
            for (int x = 0; x < grid[y].length(); x++) {
                switch (grid[y].charAt(x)) {
                case '-':
                    map.notifyClean(x, y);
                    break;
                case 'd':
                    map.notifyDirty(x, y);
                    break;
                case 'o':
                case 'b':
                    break;
                default:
                    throw new AssertionError();
                }
            }

        }

        Solver solver = new Solver(new Coordinates(c, r), map);
        solver.solve();

        try {
            FileOutputStream fileOut = new FileOutputStream(FILE_NAME);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(map);
            out.close();
            fileOut.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(solver.getNextMove().toString());
    }

    public static void main(String[] args) {
        final int N = 5;
        Scanner in = new Scanner(System.in);
        int r = in.nextInt();
        int c = in.nextInt();
        in.useDelimiter("\n");
        String[] grid = new String[N];
        for (int i = 0; i < N; i++) {
            grid[i] = in.next();
        }

        nextMove(r, c, grid);
    }
}
