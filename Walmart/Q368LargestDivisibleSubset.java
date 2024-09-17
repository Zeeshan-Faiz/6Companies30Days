package Walmart;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
Given a set of distinct positive integers nums, return the largest subset answer such that 
every pair (answer[i], answer[j]) of elements in this subset satisfies:
    answer[i] % answer[j] == 0, or
    answer[j] % answer[i] == 0

If there are multiple solutions, return any of them.
Example 1:
Input: nums = [1,2,3]
Output: [1,2]
Explanation: [1,3] is also accepted.

Example 2:
Input: nums = [1,2,4,8]
Output: [1,2,4,8]
*/

public class Q368LargestDivisibleSubset {
    
    public List<Integer> largestDivisibleSubset(int[] nums) {
        int[] dp = new int[nums.length];
        Arrays.fill(dp,-1);
        Arrays.sort(nums);
        int[] hash = new int[nums.length];
        int maxi = -1;
        int lastIndex = 0;
        for(int index=0;index<nums.length;index++){
            hash[index] = index;
            for(int col=0;col<index;col++){
                
                if(nums[index]%nums[col]==0 && 1 + dp[col] > dp[index]){
                    dp[index] = 1 + dp[col];
                    hash[index] = col;
                }
            }
            if(dp[index]>maxi){
                maxi = dp[index];
                lastIndex = index;
            }
        } 
        List<Integer> lst = new ArrayList<Integer>();
        lst.add(nums[lastIndex]);
        while(hash[lastIndex]!=lastIndex){
            lastIndex = hash[lastIndex];
            lst.add(nums[lastIndex]);
            

        }
        return lst;
    }
}