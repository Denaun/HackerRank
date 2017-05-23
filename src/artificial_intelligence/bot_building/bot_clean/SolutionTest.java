package artificial_intelligence.bot_building.bot_clean;

import artificial_intelligence.bot_building.Coordinates;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

class SolutionTest {
    private void move(Coordinates from, Iterable<Solution.Action> steps, Collection<Coordinates> dirt) {
        for (Solution.Action step : steps) {
            if (step instanceof Solution.Move) {
                from.move(((Solution.Move) step).direction);
            } else {
                dirt.remove(from);
            }
        }
    }

    @Test
    void generatePlan() {
        Coordinates bot = new Coordinates(0, 0);
        Collection<Coordinates> dirt = new ArrayList<>(Arrays.asList(new Coordinates(4, 0),
                                                                     new Coordinates(1, 1),
                                                                     new Coordinates(4, 1),
                                                                     new Coordinates(2, 2),
                                                                     new Coordinates(3, 2),
                                                                     new Coordinates(2, 3),
                                                                     new Coordinates(4, 4)));
        Iterable<Solution.Action> plan = Solution.generatePlan(bot, dirt);
        move(bot, plan, dirt);
        Assertions.assertEquals(0, dirt.size());
    }
}