package artificial_intelligence.bot_building.bot_clean_stochastic;

import artificial_intelligence.bot_building.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class SolutionTest {
    @Test
    void generatePlan() {
        Coordinates bot = new Coordinates(0, 0);
        Coordinates dirt = new Coordinates(4, 0);
        Action plan = Solution.generatePlan(bot, dirt);
        assertTrue(plan instanceof Move);
        assertEquals(Direction.RIGHT, ((Move) plan).getDirection());

        bot = new Coordinates(1, 0);
        dirt = new Coordinates(0, 0);
        plan = Solution.generatePlan(bot, dirt);
        assertTrue(plan instanceof Move);
        assertEquals(Direction.LEFT, ((Move) plan).getDirection());

        bot = new Coordinates(0, 0);
        dirt = new Coordinates(0, 4);
        plan = Solution.generatePlan(bot, dirt);
        assertTrue(plan instanceof Move);
        assertEquals(Direction.DOWN, ((Move) plan).getDirection());

        bot = new Coordinates(0, 1);
        dirt = new Coordinates(0, 0);
        plan = Solution.generatePlan(bot, dirt);
        assertTrue(plan instanceof Move);
        assertEquals(Direction.UP, ((Move) plan).getDirection());

        bot = new Coordinates(0, 0);
        dirt = new Coordinates(0, 0);
        plan = Solution.generatePlan(bot, dirt);
        assertTrue(plan instanceof Clean);
    }
}