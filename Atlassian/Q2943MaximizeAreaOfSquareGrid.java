package Atlassian;

import java.util.Arrays;

/*
You are given the two integers, n and m and two integer arrays, hBars and vBars. The grid has 
n + 2 horizontal and m + 2 vertical bars, creating 1 x 1 unit cells. The bars are indexed 
starting from 1.
You can remove some of the bars in hBars from horizontal bars and some of the bars in vBars from 
vertical bars. Note that other bars are fixed and cannot be removed.
Return an integer denoting the maximum area of a square-shaped hole in the grid, after removing 
some bars (possibly none)

Example 1:
Input: n = 2, m = 1, hBars = [2,3], vBars = [2]
Output: 4
Explanation:
The left image shows the initial grid formed by the bars. The horizontal bars are [1,2,3,4], and the vertical bars are [1,2,3].
One way to get the maximum square-shaped hole is by removing horizontal bar 2 and vertical bar 2.

Example 2:
Input: n = 1, m = 1, hBars = [2], vBars = [2]
Output: 4
Explanation:
To get the maximum square-shaped hole, we remove horizontal bar 2 and vertical bar 2.

Example 3:
Input: n = 2, m = 3, hBars = [2,3], vBars = [2,4]
Output: 4
Explanation:
One way to get the maximum square-shaped hole is by removing horizontal bar 3, and vertical bar 4.
*/

public class Q2943MaximizeAreaOfSquareGrid {

    public int maximizeSquareHoleArea(int n, int m, int[] hBars, int[] vBars) {
        int x = find(hBars), y = find(vBars);
        int res = Math.min(x, y) + 1;

        return res * res;
    }

    private int find(int[] arr) {
        
        Arrays.sort(arr);
        int res = 1, i = 0, n = arr.length;
        while (i < n) 
        {
            int count = 1;
            while (i + 1 < n && arr[i] + 1 == arr[i + 1]) {
                i++;
                count++;
            }
            i++;
            res = Math.max(res, count);
        }
        return res;
    }
}