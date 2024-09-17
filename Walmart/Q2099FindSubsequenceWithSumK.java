package Walmart;

import java.util.Arrays;

/*
You are given an integer array nums and an integer k. You want to find a subsequence of nums of 
length k that has the largest sum. Return any such subsequence as an integer array of length k.
A subsequence is an array that can be derived from another array by deleting some or no elements 
without changing the order of the remaining elements.

Example 1:
Input: nums = [2,1,3,3], k = 2
Output: [3,3]
Explanation:
The subsequence has the largest sum of 3 + 3 = 6.

Example 2:
Input: nums = [-1,-2,3,4], k = 3
Output: [-1,3,4]
Explanation: 
The subsequence has the largest sum of -1 + 3 + 4 = 6.

Example 3:
Input: nums = [3,4,3,3], k = 2
Output: [3,4]
Explanation:
The subsequence has the largest sum of 3 + 4 = 7. 
Another possible subsequence is [4, 3].
*/

public class Q2099FindSubsequenceWithSumK {

    public int[] maxSubsequence(int[] nums, int k) {
        int[] resTemp = Arrays.copyOf(nums, nums.length);
        int l = 0, n = nums.length, r = n - 1;

        int pivotIndex = quickSelect(resTemp, l, r, n - k);
        int pivotValue = resTemp[pivotIndex];

        int countTop = 0;
        for (int i = pivotIndex; i < n; i++) {
            if (resTemp[i] > pivotValue)
                countTop++;
        }
        int countK = k - countTop;

        int[] res = new int[k];
        int j = 0;
        for (int num : nums) {
            if (j == k)
                break;
            if (num == pivotValue && countK > 0) {
                res[j++] = num;
                countK--;
            } else if (num > pivotValue) {
                res[j++] = num;
            }
        }
        return res;

    }

    private int quickSelect(int[] nums, int left, int right, int k) {
        if (left == right)
            return left;
        int pivotIndex = partition(nums, left, right);
        if (pivotIndex == k) {
            return pivotIndex;
        } else if (pivotIndex < k) {
            return quickSelect(nums, pivotIndex + 1, right, k);
        } else {
            return quickSelect(nums, left, pivotIndex - 1, k);
        }
    }

    private int partition(int[] nums, int left, int right) {
        int pivot = nums[right];
        int l = left, r = right - 1;
        while (l <= r) {
            if (nums[l] < pivot)
                l++;
            else if (nums[r] >= pivot)
                r--;
            else
                swap(nums, l++, r--);
        }
        swap(nums, l, right);
        return l;
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}