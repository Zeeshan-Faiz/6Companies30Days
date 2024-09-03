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

        int n = nums.length, ans = 0, t = 0;
        int[] cnt = new int[n + 1];
        cnt[0] = 1;
        for (int num : nums) {
            t += num & 1;// t increase with every odd number encountered
            if (t - k >= 0) {
                ans += cnt[t - k];
            }
            cnt[t]++;
        }
        return ans;
    }
}