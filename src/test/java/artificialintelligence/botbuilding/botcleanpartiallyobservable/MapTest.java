package artificialintelligence.botbuilding.botcleanpartiallyobservable;

import org.junit.Assert;
import org.junit.Test;

import junit.framework.TestCase;

public class MapTest {
    public MapTest() {
    }

    @Test
    public void testClean() {
        Map map = new Map(1);
        map.notifyClean(0, 0);
        Assert.assertTrue(map.isClean(0, 0));
        Assert.assertFalse(map.isDirty(0, 0));
    }

    @Test
    public void testDirty() {
        Map map = new Map(1);
        map.notifyDirty(0, 0);
        Assert.assertTrue(map.isDirty(0, 0));
        Assert.assertFalse(map.isClean(0, 0));
    }

    @Test
    public void testSize() {
        Map map = new Map(5);
        Assert.assertEquals(5, map.size());
        map.isClean(2, 3);
        try {
            map.isClean(6, 6);
            TestCase.fail("Expected exception was not occured.");
        } catch(IndexOutOfBoundsException ignored) {
        }
        try {
            map.isDirty(1, 8);
            TestCase.fail("Expected exception was not occured.");
        } catch(IndexOutOfBoundsException ignored) {
        }
        try {
            map.notifyClean(9, 2);
            TestCase.fail("Expected exception was not occured.");
        } catch(IndexOutOfBoundsException ignored) {
        }
        try {
            map.notifyDirty(9, 8);
            TestCase.fail("Expected exception was not occured.");
        } catch(IndexOutOfBoundsException ignored) {
        }
    }
}