package Microsoft;

/*
Given an integer array nums of size n, return the minimum number of moves required to make all 
array elements equal. In one move, you can increment n - 1 elements of the array by 1.

Example 1:
Output: 3
Explanation: Only three moves are needed (remember each move increments two elements):
[1,2,3]  =>  [2,3,3]  =>  [3,4,3]  =>  [4,4,4]

Example 2:
Input: nums = [1,1,1]
Output: 0
*/

public class Q453MinMovesToEqualArrayElements {

    public int minMoves(int[] nums) {

        // find the minimum element in the array
        int min = Integer.MAX_VALUE;
        for (int n : nums) {
            min = Math.min(min, n);
        }

        // iterate the array and find the sum between the difference of
        // min element and and each element
        int ans = 0;
        for (int n : nums) {
            ans += (n - min);
        }
        return ans;
    }
}