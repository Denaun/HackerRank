package artificialintelligence.botbuilding.botcleanpartiallyobservable;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class MapTest {
    @Test
    void testClean() {
        Map map = new Map(1);
        map.notifyClean(0, 0);
        Assertions.assertTrue(map.isClean(0, 0));
        Assertions.assertFalse(map.isDirty(0, 0));
    }

    @Test
    void testDirty() {
        Map map = new Map(1);
        map.notifyDirty(0, 0);
        Assertions.assertTrue(map.isDirty(0, 0));
        Assertions.assertFalse(map.isClean(0, 0));
    }

    @Test
    void testSize() {
        Map map = new Map(5);
        Assertions.assertEquals(5, map.size());
    }
}