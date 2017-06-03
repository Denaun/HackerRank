package artificialintelligence.botbuilding.botcleanpartiallyobservable;

import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Test;

import java.io.*;

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
        } catch (IndexOutOfBoundsException ignored) {
        }
        try {
            map.isDirty(1, 8);
            TestCase.fail("Expected exception was not occured.");
        } catch (IndexOutOfBoundsException ignored) {
        }
        try {
            map.notifyClean(9, 2);
            TestCase.fail("Expected exception was not occured.");
        } catch (IndexOutOfBoundsException ignored) {
        }
        try {
            map.notifyDirty(9, 8);
            TestCase.fail("Expected exception was not occured.");
        } catch (IndexOutOfBoundsException ignored) {
        }
    }

    @Test
    public void testSerializable() throws IOException, ClassNotFoundException {
        Map originalMap = new Map(1);
        originalMap.notifyClean(0, 0);

        String pathname = "test.ser";
        FileOutputStream fileOut = new FileOutputStream(pathname);
        ObjectOutputStream out = new ObjectOutputStream(fileOut);
        out.writeObject(originalMap);
        out.close();
        fileOut.close();

        FileInputStream fileIn = new FileInputStream(pathname);
        ObjectInputStream in = new ObjectInputStream(fileIn);
        Map readMap = (Map) in.readObject();
        in.close();
        fileIn.close();

        Assert.assertTrue(new File(pathname).delete());

        Assert.assertEquals(originalMap.size(), readMap.size());
        Assert.assertTrue(readMap.isClean(0, 0));
    }
}