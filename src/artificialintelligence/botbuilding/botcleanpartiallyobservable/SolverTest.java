package artificialintelligence.botbuilding.botcleanpartiallyobservable;

import artificialintelligence.botbuilding.Action;
import artificialintelligence.botbuilding.Coordinates;
import artificialintelligence.botbuilding.Direction;
import artificialintelligence.botbuilding.Move;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class SolverTest {
    @Test
    void testSolve() {
        Map map = new Map(5);
        map.notifyClean(0, 0);
        map.notifyClean(1, 0);
        map.notifyClean(0, 1);
        map.notifyDirty(1, 1);
        Solver solver = new Solver(new Coordinates(0, 0), map);
        boolean found = solver.solve();
        Assertions.assertTrue(found);
        Action action = solver.getNextMove();
        Assertions.assertTrue(action instanceof Move);
        Move move = (Move) action;
        Assertions.assertTrue(
                move.getDirection() == Direction.RIGHT || move.getDirection() == Direction.DOWN);
    }
}