package artificialintelligence.botbuilding.botsavesprincess;

import artificialintelligence.botbuilding.Coordinates;
import artificialintelligence.botbuilding.Direction;
import org.junit.Assert;
import org.junit.Test;

public class SolutionTest {
    public SolutionTest() {
    }

    private void move(Coordinates from, Iterable<Direction> steps) {
        for (Direction step : steps) {
            from.move(step);
        }
    }

    @Test
    public void example() {
        Coordinates mario = new Coordinates(1, 1);
        Coordinates princess = new Coordinates(0, 2);
        Iterable<Direction> directions = Solution.navigate(mario, princess);
        Assert.assertEquals(2, directions.spliterator().getExactSizeIfKnown());

        move(mario, directions);
        Assert.assertEquals(princess, mario);
    }

    @Test
    public void test1() {
        Coordinates mario = new Coordinates(1, 1);
        Coordinates princess = new Coordinates(2, 0);
        Iterable<Direction> directions = Solution.navigate(mario, princess);
        Assert.assertEquals(2, directions.spliterator().getExactSizeIfKnown());

        move(mario, directions);
        Assert.assertEquals(princess, mario);
    }
}