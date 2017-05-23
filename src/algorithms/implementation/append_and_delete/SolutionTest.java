package algorithms.implementation.append_and_delete;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class SolutionTest {
    @Test
    void example1() {
        boolean canTransform = Solution.canTransform("hackerhappy", "hackerrank", 9);
        assertTrue(canTransform);
    }

    @Test
    void example2() {
        boolean canTransform = Solution.canTransform("aba", "aba", 7);
        assertTrue(canTransform);
    }
}
