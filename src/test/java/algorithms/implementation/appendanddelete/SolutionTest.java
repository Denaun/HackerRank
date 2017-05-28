package algorithms.implementation.appendanddelete;

import org.junit.Assert;
import org.junit.Test;

public class SolutionTest {
    public SolutionTest() {
    }

    @Test
    public void example1() {
        boolean canTransform = Solution.canTransform("hackerhappy", "hackerrank", 9);
        Assert.assertTrue(canTransform);
    }

    @Test
    public void example2() {
        boolean canTransform = Solution.canTransform("aba", "aba", 7);
        Assert.assertTrue(canTransform);
    }
}
