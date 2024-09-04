package Microsoft;

/*
Given an array of integers nums and an integer k. A continuous subarray is called nice if there 
are k odd numbers on it. Return the number of nice sub-arrays.

Example 1:
Input: nums = [1,1,2,1,1], k = 3
Output: 2
Explanation: The only sub-arrays with 3 odd numbers are [1,1,2,1] and [1,2,1,1].

Example 2:
Input: nums = [2,4,6], k = 1
Output: 0
Explanation: There are no odd numbers in the array.

Example 3:
Input: nums = [2,2,2,1,2,2,1,2,2,2], k = 2
Output: 16
*/

public class Q1248CountNoOfNiceSubarrays {

    public int numberOfSubarrays(int[] nums, int k) {

        // convert array elements to 1(for odd) or 0(for even)
        for (int n : nums) {
            if (n % 2 == 0)
                n = 0;
            else if (n % 2 != 0)
                n = 1;
        }
        int a = ans(nums, k);
        int b = ans(nums, k - 1);

        return a - b;
    }

    public int ans(int[] nums, int goal) {

        int left = 0, right = 0, sum = 0, count = 0;
        // edge case
        if (goal < 0)
            return 0;

        while (right < nums.length) {
            sum = sum + (nums[right] % 2); // Add the current element to sum
            while (sum > goal) {
                sum = sum - (nums[left] % 2);
                left++;
            }
            count += (right - left + 1); // Count the number of valid subarrays ending at right
            right++;
        }
        return count;
    }
}