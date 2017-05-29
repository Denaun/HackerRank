package artificialintelligence.botbuilding.botcleanpartiallyobservable;

import artificialintelligence.botbuilding.*;
import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Test;

public class SolverTest {
    public SolverTest() {
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
    public void testSolve_example1() {
        Map map = new Map(5);
        map.notifyClean(0, 0);
        map.notifyClean(1, 0);
        map.notifyClean(0, 1);
        map.notifyDirty(1, 1);
        Solver solver = new Solver(new Coordinates(0, 0), map);
        boolean found = solver.solve();
        Assert.assertTrue(found);
        Action action = solver.getNextMove();
        Assert.assertTrue(action instanceof Move);
        Move move = (Move) action;
        Assert.assertTrue(
                move.getDirection() == Direction.RIGHT || move.getDirection() == Direction.DOWN);
    }

    @Test
    public void testSolve_example2() {
        Map map = new Map(5);
        map.notifyClean(0, 0);
        map.notifyDirty(1, 0);
        map.notifyClean(2, 0);
        map.notifyClean(0, 1);
        map.notifyDirty(1, 1);
        map.notifyClean(2, 1);
        map.notifyClean(0, 2);
        map.notifyClean(1, 2);
        map.notifyClean(2, 2);
        Solver solver = new Solver(new Coordinates(1, 1), map);
        solver.setSerializableState(Direction.UP);
        boolean found = solver.solve();
        Assert.assertTrue(found);
        Action action = solver.getNextMove();
        Assert.assertTrue(action instanceof Clean);

        map.notifyClean(1, 1);
        found = solver.solve();
        Assert.assertTrue(found);
        action = solver.getNextMove();
        Assert.assertTrue(action instanceof Move);
        Move move = (Move) action;
        Assert.assertEquals(Direction.UP, move.getDirection());
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
    public void testSolve_emptyColumn() {
        Map map = new Map(5);
        for (int y = 0; y < 5; y++) {
            map.notifyClean(0, y);
            map.notifyClean(1, y);
            map.notifyClean(2, y);
        }
        map.notifyDirty(2, 4);

        Solver solver = new Solver(new Coordinates(1, 3), map);
        solver.setSerializableState(Direction.DOWN);
        solver.solve();
        Move move = (Move) solver.getNextMove();
        Assert.assertEquals(Direction.RIGHT, move.getDirection());
        Direction verticalDirection = (Direction) solver.getSerializableState();
        Assert.assertEquals(Direction.UP, verticalDirection);
        map.notifyDirty(3, 3);
        map.notifyClean(3, 4);

        solver = new Solver(new Coordinates(2, 3), map);
        solver.setSerializableState(verticalDirection);
        solver.solve();
        move = (Move) solver.getNextMove();
        Assert.assertEquals(Direction.DOWN, move.getDirection());
        verticalDirection = (Direction) solver.getSerializableState();
        Assert.assertEquals(Direction.UP, verticalDirection);

        solver = new Solver(new Coordinates(2, 4), map);
        solver.setSerializableState(verticalDirection);
        solver.solve();
        Assert.assertTrue(solver.getNextMove() instanceof Clean);
        verticalDirection = (Direction) solver.getSerializableState();
        Assert.assertEquals(Direction.UP, verticalDirection);
        map.notifyClean(2, 4);

        solver = new Solver(new Coordinates(2, 4), map);
        solver.setSerializableState(verticalDirection);
        solver.solve();
        move = (Move) solver.getNextMove();
        Assert.assertEquals(Direction.RIGHT, move.getDirection());
        verticalDirection = (Direction) solver.getSerializableState();
        Assert.assertEquals(Direction.UP, verticalDirection);
    }
}