package Walmart;

/*
Given two integer arrays nums1 and nums2, return the maximum length of a subarray that appears 
in both arrays.

Example 1:
Input: nums1 = [1,2,3,2,1], nums2 = [3,2,1,4,7]
Output: 3
Explanation: The repeated subarray with maximum length is [3,2,1].

Example 2:
Input: nums1 = [0,0,0,0,0], nums2 = [0,0,0,0,0]
Output: 5
Explanation: The repeated subarray with maximum length is [0,0,0,0,0].
*/

public class Q718MaxLengthOfRepeatedSubarray {
 
    public int findLength(int[] nums1, int[] nums2) {

        int[][] dp = new int[nums1.length + 1][nums2.length + 1];
        int max = 0;

        // Iterate over each position in the matrix
        for (int i = 1; i <= nums1.length; i++) {
            for (int j = 1; j <= nums2.length; j++) {

                // If numbers are equal
                if (nums1[i - 1] == nums2[j - 1]) {
                    // Get the number from diagonally opposite and add 1
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                    max = Math.max(dp[i][j], max);
                }
            }
        }
        return max;
    }
}