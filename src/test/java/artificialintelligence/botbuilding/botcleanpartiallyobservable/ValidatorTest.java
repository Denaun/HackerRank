package artificialintelligence.botbuilding.botcleanpartiallyobservable;

import artificialintelligence.botbuilding.Clean;
import artificialintelligence.botbuilding.Coordinates;
import artificialintelligence.botbuilding.Direction;
import artificialintelligence.botbuilding.Move;
import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Test;

import java.io.InvalidObjectException;
import java.util.Arrays;

public class ValidatorTest {
    public ValidatorTest() {
    }

    @Test
    public void testMap() {
        Validator validator = new Validator(5, new Coordinates(0, 0),
                                            Arrays.asList(new Coordinates(0, 0),
                                                          new Coordinates(1, 1)));
        Map expected = new Map(5);
        expected.notifyDirty(0, 0);
        expected.notifyClean(0, 1);
        expected.notifyClean(1, 0);
        expected.notifyDirty(1, 1);
        Assert.assertEquals(expected, validator.asMap(1));

        expected.notifyClean(0, 2);
        expected.notifyClean(1, 2);
        expected.notifyClean(2, 0);
        expected.notifyClean(2, 1);
        expected.notifyClean(2, 2);
        Assert.assertEquals(expected, validator.asMap(2));
    }

    @Test
    public void testAction_clean() throws InvalidObjectException {
        Validator validator = new Validator(5, new Coordinates(0, 0),
                                            Arrays.asList(new Coordinates(0, 0),
                                                          new Coordinates(1, 1)));
        Assert.assertFalse(validator.isFinished());

        validator.performAction(new Clean());
        Assert.assertTrue(validator.asMap(0).isClean(0, 0));

        try {
            validator.performAction(new Clean());
            TestCase.fail("Expected exception was not occured.");
        } catch (InvalidObjectException ignored) {
        }

        validator.performAction(new Move(Direction.RIGHT));
        validator.performAction(new Move(Direction.DOWN));
        validator.performAction(new Clean());
        Assert.assertTrue(validator.isFinished());
    }

    @Test
    public void testAction_move() throws InvalidObjectException {
        Validator validator = new Validator(5, new Coordinates(0, 0),
                                            Arrays.asList(new Coordinates(0, 0),
                                                          new Coordinates(1, 1)));
        Assert.assertTrue(validator.asMap(0).isDirty(0, 0));
        Assert.assertFalse(validator.asMap(0).isClean(1, 0));
        Assert.assertFalse(validator.asMap(0).isDirty(1, 0));
        Assert.assertEquals(new Coordinates(0, 0), validator.getBot());

        validator.performAction(new Move(Direction.RIGHT));
        Assert.assertTrue(validator.asMap(0).isClean(1, 0));
        Assert.assertEquals(new Coordinates(1, 0), validator.getBot());

        try {
            validator.performAction(new Move(Direction.UP));
            TestCase.fail("Expected exception was not occured.");
        } catch (InvalidObjectException ignored) {
        }
    }
}