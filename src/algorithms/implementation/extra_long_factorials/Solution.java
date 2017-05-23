package algorithms.implementation.extra_long_factorials;

import java.math.BigInteger;
import java.util.Scanner;

public class Solution {
    static BigInteger factorial(int n) {
        BigInteger result = BigInteger.ONE;
        for (int i = 2; i <= n; i++) {
            result = result.multiply(BigInteger.valueOf(i));
        }
        return result;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int num = in.nextInt();
        System.out.print(factorial(num));
    }
}
