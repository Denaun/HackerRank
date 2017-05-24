package artificialintelligence.botbuilding;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CoordinatesTest {
    @Test
    void move() {
        Coordinates zero = new Coordinates();
        assertEquals(new Coordinates(-1, 0), zero.move(Direction.LEFT));
        assertEquals(new Coordinates(0, 0), zero.move(Direction.RIGHT));
        assertEquals(new Coordinates(0, -1), zero.move(Direction.UP));
        assertEquals(new Coordinates(0, 0), zero.move(Direction.DOWN));
    }

}