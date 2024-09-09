package Adobe;

/*
You want to change s to t. Changing the ith character of s to ith character of t costs 
|s[i] - t[i]| (i.e., the absolute difference between the ASCII values of the characters).
Return the maximum length of a substring of s that can be changed to be the same as the 
corresponding substring of t with a cost less than or equal to maxCost. If there is no substring 
from s that can be changed to its corresponding substring from t, return 0.

Example 1:
Input: s = "abcd", t = "bcdf", maxCost = 3
Output: 3
Explanation: "abc" of s can change to "bcd".
That costs 3, so the maximum length is 3.

Example 2:
Input: s = "abcd", t = "cdef", maxCost = 3
Output: 1
Explanation: Each character in s costs 2 to change to character in t,  so the maximum length is 1.

Example 3:
Input: s = "abcd", t = "acde", maxCost = 0
Output: 1
Explanation: You cannot make any change, so the maximum length is 1.
*/

public class Q1208GetEqualSubstrings {

    public int equalSubstring(String s, String t, int maxCost) {
        
        char[] as = s.toCharArray();
        char[] at = t.toCharArray();
        int len = as.length;

        //store the cost for converting each character of s to that of t
        int[] cost = new int[len];
        for (int i = 0; i < len; i++) {
            cost[i] = Math.abs(as[i] - at[i]);
        }

        int max = 0;
        int l = 0;
        int sum = 0;
        for (int i = 0; i < len; i++) {
            sum += cost[i];

            while (sum > maxCost) {
                sum -= cost[l++];
            }

            max = Math.max(max, i - l + 1);
        }
        return max;
    }
}