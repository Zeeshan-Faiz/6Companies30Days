package Adobe;

/*
Given an integer array nums and an integer k, return the maximum sum of a non-empty subsequence 
of that array such that for every two consecutive integers in the subsequence, nums[i] and nums[j], 
where i < j, the condition j - i <= k is satisfied.
A subsequence of an array is obtained by deleting some number of elements (can be zero) from the 
array, leaving the remaining elements in their original order.

Example 1:
Input: nums = [10,2,-10,5,20], k = 2
Output: 37
Explanation: The subsequence is [10, 2, 5, 20].

Example 2:
Input: nums = [-1,-2,-3], k = 1
Output: -1
Explanation: The subsequence must be non-empty, so we choose the largest number.

Example 3:
Input: nums = [10,-2,-10,-5,20], k = 2
Output: 23
Explanation: The subsequence is [10, -2, -5, 20].
*/

public class Q1425ConstrainedSubsequenceSum {
    
    public int constrainedSubsetSum(int[] nums, int k) {
        int[] sum = new int[nums.length];
        int[] index = new int[nums.length];
        int head = 0;
        int tail = 0;
        sum[0] = nums[0];
        index[0] = 0;
        int maxSum = nums[0];
        for (int i = 1; i < nums.length; ++i) {
            while (tail <= head && index[tail] < i - k) {
                ++tail;
            }
            int currentSum = nums[i];
            if (tail <= head && sum[tail] > 0) {
                currentSum += sum[tail];
            }
            maxSum = Math.max(maxSum, currentSum);
            while (tail <= head && sum[head] <= currentSum) {
                --head;
            }
            ++head;
            sum[head] = currentSum;
            index[head] = i;
        }
        return maxSum;
    }
}