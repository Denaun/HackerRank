package artificialintelligence.botbuilding.botcleanpartiallyobservable;

import artificialintelligence.botbuilding.*;
import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Test;

import java.io.InvalidObjectException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

public class SolverTest {
    public SolverTest() {
    }

    private int solve(Validator validator, boolean debug) throws InvalidObjectException {
        int size = validator.asMap(0).size();
        Map explored = new Map(size);
        Object serializedState = null;
        int steps;
        for (steps = 0; steps < 200; steps++) {
            if (validator.isFinished()) break;

            Map newMap = validator.asMap(1);
            for (int y = 0; y < size; y++) {
                for (int x = 0; x < size; x++) {
                    if (newMap.isClean(x, y)) {
                        explored.notifyClean(x, y);
                    } else if (newMap.isDirty(x, y)) {
                        explored.notifyDirty(x, y);
                    }
                }
            }

            Solver solver = new Solver(validator.getBot(), explored);
            solver.setSerializableState(serializedState);
            boolean found = solver.solve();
            Assert.assertTrue(found);
            Action nextMove = solver.getNextMove();
            if (debug) System.err.println(nextMove);
            validator.performAction(nextMove);
            serializedState = solver.getSerializableState();
        }
        return steps;
    }

    @Test
    public void testSerializable() {
        Direction aDirection = Direction.DOWN;
        Solver solver = new Solver(new Coordinates(), new Map(0));
        solver.setSerializableState(aDirection);
        Object anObject = solver.getSerializableState();
        Assert.assertEquals(aDirection, anObject);

        solver.setSerializableState(null);
        anObject = solver.getSerializableState();
        Assert.assertEquals(null, anObject);

        try {
            solver.setSerializableState(solver);
            TestCase.fail("Expected exception was not occured.");
        } catch (ClassCastException ignored) {
        }
    }

    @Test
    public void testSolve_example1() throws InvalidObjectException {
        int size = 5;
        Validator validator = new Validator(size, new Coordinates(0, 0),
                                            Collections.singletonList(new Coordinates(1, 1)));
        int steps = solve(validator, true);
        Assert.assertTrue(validator.isFinished());
        Assert.assertEquals(7, steps);
    }

    @Test
    public void testSolve_example2() throws InvalidObjectException {
        int size = 5;
        Validator validator = new Validator(size, new Coordinates(1, 1),
                                            Arrays.asList(new Coordinates(1, 0),
                                                          new Coordinates(1, 1)));
        int steps = solve(validator, true);
        Assert.assertTrue(validator.isFinished());
        Assert.assertEquals(11, steps);
    }

    @Test
    public void testSolve_example3() {
        Map map = new Map(5);
        map.notifyClean(0, 0);
        map.notifyClean(1, 0);
        map.notifyClean(2, 0);
        map.notifyClean(0, 1);
        map.notifyClean(1, 1);
        map.notifyClean(2, 1);
        Solver solver = new Solver(new Coordinates(1, 0), map);
        solver.setSerializableState(Direction.UP);
        boolean found = solver.solve();
        Assert.assertTrue(found);
        Action action = solver.getNextMove();
        Assert.assertTrue(action instanceof Move);
        Move move = (Move) action;
        Assert.assertEquals(Direction.RIGHT, move.getDirection());
    }

    @Test
    public void testSolve_emptyColumn() throws InvalidObjectException {
        int size = 5;
        Validator validator = new Validator(size, new Coordinates(1, 3),
                                            Collections.singletonList(new Coordinates(2, 4)));

        int steps = solve(validator, true);
        Assert.assertTrue(validator.isFinished());
        Assert.assertEquals(11, steps);
    }

    @Test
    public void testSolve_random() throws InvalidObjectException {
        int totalSteps = 0;
        int tests = 100;
        int size = 5;
        for (int count = 0; count < tests; count++) {
            Random rng = new Random();
            ArrayList<Coordinates> dirt = new ArrayList<>();
            for (int i = 0; i < rng.nextInt(size * size - 1) + 1; i++) {
                dirt.add(new Coordinates(rng.nextInt(size), rng.nextInt(size)));
            }
            Validator validator = new Validator(size,
                                                new Coordinates(rng.nextInt(size),
                                                                rng.nextInt(size)),
                                                dirt);
            System.err.println(validator.asMap(size * size));

            int steps = solve(validator, false);
            Assert.assertTrue(validator.isFinished());
            System.err.println(steps);
            totalSteps += steps;
        }
        double averageSteps = (double) totalSteps / tests;
        System.err.println(averageSteps);
        Assert.assertTrue(averageSteps < 23);
    }
}