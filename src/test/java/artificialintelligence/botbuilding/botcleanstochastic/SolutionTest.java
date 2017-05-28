package artificialintelligence.botbuilding.botcleanstochastic;

import artificialintelligence.botbuilding.*;
import org.junit.Assert;
import org.junit.Test;

public class SolutionTest {
    public SolutionTest() {
    }

    @Test
    public void generatePlan() {
        Coordinates bot = new Coordinates(0, 0);
        Coordinates dirt = new Coordinates(4, 0);
        Action plan = Solution.generatePlan(bot, dirt);
        Assert.assertTrue(plan instanceof Move);
        Assert.assertEquals(Direction.RIGHT, ((Move) plan).getDirection());

        bot = new Coordinates(1, 0);
        dirt = new Coordinates(0, 0);
        plan = Solution.generatePlan(bot, dirt);
        Assert.assertTrue(plan instanceof Move);
        Assert.assertEquals(Direction.LEFT, ((Move) plan).getDirection());

        bot = new Coordinates(0, 0);
        dirt = new Coordinates(0, 4);
        plan = Solution.generatePlan(bot, dirt);
        Assert.assertTrue(plan instanceof Move);
        Assert.assertEquals(Direction.DOWN, ((Move) plan).getDirection());

        bot = new Coordinates(0, 1);
        dirt = new Coordinates(0, 0);
        plan = Solution.generatePlan(bot, dirt);
        Assert.assertTrue(plan instanceof Move);
        Assert.assertEquals(Direction.UP, ((Move) plan).getDirection());

        bot = new Coordinates(0, 0);
        dirt = new Coordinates(0, 0);
        plan = Solution.generatePlan(bot, dirt);
        Assert.assertTrue(plan instanceof Clean);
    }
}