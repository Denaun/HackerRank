package algorithms.implementation.extra_long_factorials;

import org.junit.jupiter.api.Test;

import java.math.BigInteger;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SolutionTest {
    @Test
    void example() {
        BigInteger fac = Solution.factorial(25);
        assertEquals(new BigInteger("15511210043330985984000000"), fac);
    }
}
