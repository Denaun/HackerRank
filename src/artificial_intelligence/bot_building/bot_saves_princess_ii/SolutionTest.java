package artificial_intelligence.bot_building.bot_saves_princess_ii;

import artificial_intelligence.bot_building.Coordinates;
import artificial_intelligence.bot_building.Direction;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SolutionTest {
    @Test
    void example() {
        Coordinates mario = new Coordinates(3, 2);
        Coordinates princess = new Coordinates(0, 2);
        assertEquals(Direction.LEFT, Solution.nextDirection(mario, princess));
    }
}