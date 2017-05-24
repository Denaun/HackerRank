package artificial_intelligence.bot_building.bot_clean_stochastic;

import artificial_intelligence.bot_building.Coordinates;
import artificial_intelligence.bot_building.Direction;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class SolutionTest {
    @Test
    void generatePlan() {
        Coordinates bot = new Coordinates(0, 0);
        Coordinates dirt = new Coordinates(4, 0);
        Solution.Action plan = Solution.generatePlan(bot, dirt);
        assertTrue(plan instanceof Solution.Move);
        assertEquals(Direction.RIGHT, ((Solution.Move) plan).direction);

        bot = new Coordinates(1, 0);
        dirt = new Coordinates(0, 0);
        plan = Solution.generatePlan(bot, dirt);
        assertTrue(plan instanceof Solution.Move);
        assertEquals(Direction.LEFT, ((Solution.Move) plan).direction);

        bot = new Coordinates(0, 0);
        dirt = new Coordinates(0, 4);
        plan = Solution.generatePlan(bot, dirt);
        assertTrue(plan instanceof Solution.Move);
        assertEquals(Direction.DOWN, ((Solution.Move) plan).direction);

        bot = new Coordinates(0, 1);
        dirt = new Coordinates(0, 0);
        plan = Solution.generatePlan(bot, dirt);
        assertTrue(plan instanceof Solution.Move);
        assertEquals(Direction.UP, ((Solution.Move) plan).direction);

        bot = new Coordinates(0, 0);
        dirt = new Coordinates(0, 0);
        plan = Solution.generatePlan(bot, dirt);
        assertTrue(plan instanceof Solution.Clean);
    }
}