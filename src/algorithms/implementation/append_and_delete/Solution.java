package algorithms.implementation.append_and_delete;

import java.util.Scanner;

public class Solution {
    static boolean canTransform(String source, String objective, int steps) {
        int minLength = Math.min(source.length(), objective.length());
        int commonLength;
        for (commonLength = 0; commonLength < minLength; commonLength++) {
            if (source.charAt(commonLength) != objective.charAt(commonLength)) {
                break;
            }
        }
        int numberOfDeletes = source.length() - commonLength;
        int numberOfAppends = objective.length() - commonLength;
        int freeSteps = steps - (numberOfDeletes + numberOfAppends);
        return freeSteps >= 0 && (freeSteps == 0 || freeSteps % 2 == 0 || freeSteps > 2 * commonLength);
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String s = in.next();
        String t = in.next();
        int k = in.nextInt();
        if (canTransform(s, t, k)) {
            System.out.print("Yes");
        } else {
            System.out.print("No");
        }
    }
}

