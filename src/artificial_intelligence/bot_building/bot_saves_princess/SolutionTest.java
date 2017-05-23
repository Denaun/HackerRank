package artificial_intelligence.bot_building.bot_saves_princess;

import artificial_intelligence.bot_building.Coordinates;
import artificial_intelligence.bot_building.Direction;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SolutionTest {
    private void move(Coordinates from, Iterable<Direction> steps) {
        for (Direction step : steps) {
            from.move(step);
        }
    }

    @Test
    void example() {
        Coordinates mario = new Coordinates(1, 1);
        Coordinates princess = new Coordinates(0, 2);
        Iterable<Direction> directions = Solution.navigate(mario, princess);
        assertEquals(2, directions.spliterator().getExactSizeIfKnown());

        move(mario, directions);
        assertEquals(princess, mario);
    }

    @Test
    void test1() {
        Coordinates mario = new Coordinates(1, 1);
        Coordinates princess = new Coordinates(2, 0);
        Iterable<Direction> directions = Solution.navigate(mario, princess);
        assertEquals(2, directions.spliterator().getExactSizeIfKnown());

        move(mario, directions);
        assertEquals(princess, mario);
    }
}