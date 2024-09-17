package GoldmanSachs;

/*
You are given two positive integers startPos and endPos. Initially, you are standing at position 
startPos on an infinite number line. With one step, you can move either one position to the left,
or one position to the right.
Given a positive integer k, return the number of different ways to reach the position endPos 
starting from startPos, such that you perform exactly k steps. Since the answer may be very 
large, return it modulo 109 + 7. Two ways are considered different if the order of the steps made 
is not exactly the same.
Note that the number line includes negative integers.

Example 1:
Input: startPos = 1, endPos = 2, k = 3
Output: 3
Explanation: We can reach position 2 from 1 in exactly 3 steps in three ways:
- 1 -> 2 -> 3 -> 2.
- 1 -> 2 -> 1 -> 2.
- 1 -> 0 -> 1 -> 2.
It can be proven that no other way is possible, so we return 3.

Example 2:
Input: startPos = 2, endPos = 5, k = 10
Output: 0
Explanation: It is impossible to reach position 5 from position 2 in exactly 10 steps.
*/

public class Q2400NumberOfStepsToReachPosition {

    private int MOD = 1000000007;

    public int numberOfWays(int startPos, int endPos, int k) {
        int d = Math.abs(startPos - endPos);
        if (k < d || (k - d) % 2 == 1)
            return 0;
        if (k == d)
            return 1;
        int[] res = new int[k + 1];
        res[0] = 1;
        for (int i = 1; i <= k; i++) {
            for (int j = i; j > 0; j--) {
                res[j] = (res[j] + res[j - 1]) % MOD;
            }
        }
        int turns = d + (k - d) / 2;
        return res[turns];
    }
}