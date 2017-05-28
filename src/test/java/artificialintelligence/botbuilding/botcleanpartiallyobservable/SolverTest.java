package artificialintelligence.botbuilding.botcleanpartiallyobservable;

import artificialintelligence.botbuilding.Action;
import artificialintelligence.botbuilding.Coordinates;
import artificialintelligence.botbuilding.Direction;
import artificialintelligence.botbuilding.Move;
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
}