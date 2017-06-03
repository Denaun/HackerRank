package artificialintelligence.botbuilding;

import org.junit.Assert;
import org.junit.Test;

public class CoordinatesTest {
    public CoordinatesTest() {
    }

    @Test
    public void testMove() {
        Coordinates zero = new Coordinates();
        Assert.assertEquals(new Coordinates(-1, 0), zero.move(Direction.LEFT));
        Assert.assertEquals(new Coordinates(0, 0), zero.move(Direction.RIGHT));
        Assert.assertEquals(new Coordinates(0, -1), zero.move(Direction.UP));
        Assert.assertEquals(new Coordinates(0, 0), zero.move(Direction.DOWN));
    }

    @Test
    public void testGetL1DistanceFrom() {
        Assert.assertEquals(0, new Coordinates(0, 0).getL1DistanceFrom(new Coordinates(0, 0)));
        Assert.assertEquals(1, new Coordinates(0, 0).getL1DistanceFrom(new Coordinates(1, 0)));
        Assert.assertEquals(1, new Coordinates(0, 0).getL1DistanceFrom(new Coordinates(0, 1)));
        Assert.assertEquals(1, new Coordinates(1, 0).getL1DistanceFrom(new Coordinates(0, 0)));
        Assert.assertEquals(2, new Coordinates(0, 0).getL1DistanceFrom(new Coordinates(1, 1)));
    }
}