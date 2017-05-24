package artificialintelligence.botbuilding.botsavesprincess2;

import artificialintelligence.botbuilding.Coordinates;
import artificialintelligence.botbuilding.Direction;
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