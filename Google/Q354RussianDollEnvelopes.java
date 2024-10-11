package Google;

import java.util.Arrays;

/*
You are given a 2D array of integers envelopes where envelopes[i] = [wi, hi] represents the width 
and the height of an envelope.
One envelope can fit into another if and only if both the width and height of one envelope are 
greater than the other envelope's width and height.
Return the maximum number of envelopes you can Russian doll (i.e., put one inside the other).
Note: You cannot rotate an envelope.

Example 1:
Input: envelopes = [[5,4],[6,4],[6,7],[2,3]]
Output: 3
Explanation: The maximum number of envelopes you can Russian doll is 3 ([2,3] => [5,4] => [6,7]).

Example 2:
Input: envelopes = [[1,1],[1,1],[1,1]]
Output: 1
*/

public class Q354RussianDollEnvelopes {

    public int maxEnvelopes(int[][] envelopes) {

        Arrays.sort(envelopes, (a, b) -> {
            return a[0] == b[0] ? b[1] - a[1] : a[0] - b[0];
        });
        int n = envelopes.length;
        int[] d = new int[n + 1];
        d[1] = envelopes[0][1];
        int size = 1;
        for (int i = 1; i < n; ++i) {
            int x = envelopes[i][1];
            if (x > d[size]) {
                d[++size] = x;
            } else {
                int left = 1, right = size;
                while (left < right) {
                    int mid = (left + right) >> 1;
                    if (d[mid] >= x) {
                        right = mid;
                    } else {
                        left = mid + 1;
                    }
                }
                int p = d[left] >= x ? left : 1;
                d[p] = x;
            }
        }
        return size;
    }
}