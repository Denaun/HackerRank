package artificialintelligence.botbuilding.botcleanlarge;

import artificialintelligence.botbuilding.Action;
import artificialintelligence.botbuilding.Coordinates;
import artificialintelligence.botbuilding.Move;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

class SolutionTest {
    private void move(Coordinates from, Iterable<Action> steps, Collection<Coordinates> dirt) {
        for (Action step : steps) {
            if (step instanceof Move) {
                from.move(((Move) step).getDirection());
            } else {
                Assertions.assertTrue(dirt.contains(from));
                dirt.remove(from);
            }
        }
    }

    @Test
    void generatePlanSparse() {
        Coordinates bot = new Coordinates(0, 0);
        Collection<Coordinates> dirt = new ArrayList<>(Arrays.asList(new Coordinates(42, 1),
                                                                     new Coordinates(10, 10),
                                                                     new Coordinates(42, 11),
                                                                     new Coordinates(20, 20),
                                                                     new Coordinates(32, 21),
                                                                     new Coordinates(33, 21),
                                                                     new Coordinates(34, 21),
                                                                     new Coordinates(35, 21),
                                                                     new Coordinates(31, 22),
                                                                     new Coordinates(31, 23),
                                                                     new Coordinates(31, 24),
                                                                     new Coordinates(31, 24),
                                                                     new Coordinates(20, 30),
                                                                     new Coordinates(42, 41)));
        Iterable<Action> plan = Solution.generatePlan(bot, dirt);

        move(bot, plan, dirt);
        Assertions.assertEquals(0, dirt.size());
        Assertions.assertEquals(155, plan.spliterator().getExactSizeIfKnown());
    }

    @Test
    void generatePlanDense() {
        Coordinates bot = new Coordinates(0, 0);
        Collection<Coordinates> dirt = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            for (int j = 0; j < 50; j++) {
                dirt.add(new Coordinates(i, j));
            }
        }

        Iterable<Action> plan = Solution.generatePlan(bot, dirt);
        move(bot, plan, dirt);
        Assertions.assertEquals(0, dirt.size());
        Assertions.assertEquals(5075, plan.spliterator().getExactSizeIfKnown());
    }
}