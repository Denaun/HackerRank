package artificialintelligence.botbuilding.botsavesprincess2;

import artificialintelligence.botbuilding.Coordinates;
import artificialintelligence.botbuilding.Direction;
import org.junit.Assert;
import org.junit.Test;

public class SolutionTest {
    public SolutionTest() {
    }

    @Test
    public void example() {
        Coordinates mario = new Coordinates(3, 2);
        Coordinates princess = new Coordinates(0, 2);
        Assert.assertEquals(Direction.LEFT, Solution.nextDirection(mario, princess));
    }
}