package artificialintelligence.botbuilding;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CoordinatesTest {
    @Test
    void testMove() {
        Coordinates zero = new Coordinates();
        assertEquals(new Coordinates(-1, 0), zero.move(Direction.LEFT));
        assertEquals(new Coordinates(0, 0), zero.move(Direction.RIGHT));
        assertEquals(new Coordinates(0, -1), zero.move(Direction.UP));
        assertEquals(new Coordinates(0, 0), zero.move(Direction.DOWN));
    }

    @Test
    void testGetL1DistanceFrom() {
        assertEquals(0, new Coordinates(0, 0).getL1DistanceFrom(new Coordinates(0, 0)));
        assertEquals(1, new Coordinates(0, 0).getL1DistanceFrom(new Coordinates(1, 0)));
        assertEquals(1, new Coordinates(0, 0).getL1DistanceFrom(new Coordinates(0, 1)));
        assertEquals(1, new Coordinates(1, 0).getL1DistanceFrom(new Coordinates(0, 0)));
        assertEquals(2, new Coordinates(0, 0).getL1DistanceFrom(new Coordinates(1, 1)));
    }
}