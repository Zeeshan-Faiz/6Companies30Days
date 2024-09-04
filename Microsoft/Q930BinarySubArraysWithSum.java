package Microsoft;

/*
Given a binary array nums and an integer goal, return the number of non-empty subarrays with a sum 
goal. A subarray is a contiguous part of the array.

Example 1:
Input: nums = [1,0,1,0,1], goal = 2
Output: 4
Explanation: The 4 subarrays are bolded and underlined below:
[1,0,1,0,1]
[1,0,1,0,1]
[1,0,1,0,1]
[1,0,1,0,1]

Example 2:
Input: nums = [0,0,0,0,0], goal = 0
Output: 15
*/

public class Q930BinarySubArraysWithSum {

    public int numSubarraysWithSum(int[] nums, int goal) {

        int a = ans(nums, goal);
        int b = ans(nums, goal - 1);

        return a - b;
    }

    public int ans(int[] nums, int goal) {

        int left = 0, right = 0, sum = 0, count = 0;
        //edge case
        if (goal < 0) 
            return 0;

        while (right < nums.length) 
        {
            sum += nums[right]; // Add the current element to sum
            while (sum > goal) {
                sum = sum - nums[left];
                left++;
            }
            count += (right - left + 1); // Count the number of valid subarrays ending at right
            right++;
        }
        return count;
    }
}