package artificialintelligence.botbuilding.botcleanpartiallyobservable;

import artificialintelligence.botbuilding.*;
import org.junit.Assert;
import org.junit.Test;

public class SolverTest {
    public SolverTest() {
    }

    @Test
    public void testSolve() {
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
    public void testSolve1() {
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
        Assert.assertTrue(move.getDirection() == Direction.UP);
    }

    @Test
    public void testSolve2() {
        Map map = new Map(5);
        map.notifyClean(0, 0);
        map.notifyClean(1, 0);
        map.notifyClean(2, 0);
        map.notifyClean(0, 1);
        map.notifyClean(1, 1);
        map.notifyClean(2, 1);
        Solver solver = new Solver(new Coordinates(1, 0), map);
        boolean found = solver.solve();
        Assert.assertTrue(found);
        Action action = solver.getNextMove();
        Assert.assertTrue(action instanceof Move);
        Move move = (Move) action;
        Assert.assertTrue(move.getDirection() == Direction.RIGHT);
    }
}