package artificialintelligence.botbuilding.botclean;

import artificialintelligence.botbuilding.Action;
import artificialintelligence.botbuilding.Coordinates;
import artificialintelligence.botbuilding.Move;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SolutionTest {
    private void move(Coordinates from, Iterable<Action> steps, Collection<Coordinates> dirt) {
        for (Action step : steps) {
            if (step instanceof Move) {
                from.move(((Move) step).getDirection());
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
        Iterable<Action> plan = Solution.generatePlan(bot, dirt);
        move(bot, plan, dirt);
        assertEquals(0, dirt.size());
    }
}