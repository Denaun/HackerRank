package algorithms.implementation.extralongfactorials;


import org.junit.Assert;
import org.junit.Test;

import java.math.BigInteger;

public class SolutionTest {
    public SolutionTest() {
    }

    @Test
    public void example() {
        BigInteger fac = Solution.factorial(25);
        Assert.assertEquals(new BigInteger("15511210043330985984000000"), fac);
    }
}
