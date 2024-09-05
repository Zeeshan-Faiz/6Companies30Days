package Microsoft;

import java.util.*;

/*
Given an integer array nums, reorder it such that nums[0] < nums[1] > nums[2] < nums[3]....
You may assume the input array always has a valid answer.

Example 1:
Input: nums = [1,5,1,1,6,4]
Output: [1,6,1,5,1,4]
Explanation: [1,4,1,5,1,6] is also accepted.

Example 2:
Input: nums = [1,3,2,2,3,1]
Output: [2,3,1,3,1,2]
*/

public class Q324WiggleSortII {
    
    public void wiggleSortII(int[] nums) {
        
        int n = nums.length - 1;
        int[] newarr = Arrays.copyOf(nums, nums.length);
        Arrays.sort(newarr);// sort new array
        
        // now lets add larger elements at odd position and smaller elements at even position 
        for (int i = 1; i < nums.length; i += 2)// odd position
            nums[i] = newarr[n--];

        for (int i = 0; i < nums.length; i += 2)// even position
            nums[i] = newarr[n--];
    }
}