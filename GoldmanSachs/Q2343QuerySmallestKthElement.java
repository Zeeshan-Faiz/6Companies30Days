package GoldmanSachs;

/*
You are given a 0-indexed array of strings nums, where each string is of equal length and consists 
of only digits.
You are also given a 0-indexed 2D integer array queries where queries[i] = [ki, trimi]. For each 
queries[i], you need to:
    Trim each number in nums to its rightmost trimi digits.
    Determine the index of the kith smallest trimmed number in nums. If two trimmed numbers are 
    equal, the number with the lower index is considered to be smaller.
    Reset each number in nums to its original length.
Return an array answer of the same length as queries, where answer[i] is the answer to the ith query.

Note:
    To trim to the rightmost x digits means to keep removing the leftmost digit, until only x digits remain.
    Strings in nums may contain leading zeros.

Example 1:
Input: nums = ["102","473","251","814"], queries = [[1,1],[2,3],[4,2],[1,2]]
Output: [2,2,1,0]
Explanation:
1. After trimming to the last digit, nums = ["2","3","1","4"]. The smallest number is 1 at index 2.
2. Trimmed to the last 3 digits, nums is unchanged. The 2nd smallest number is 251 at index 2.
3. Trimmed to the last 2 digits, nums = ["02","73","51","14"]. The 4th smallest number is 73.
4. Trimmed to the last 2 digits, the smallest number is 2 at index 0.
   Note that the trimmed number "02" is evaluated as 2.

Example 2:
Input: nums = ["24","37","96","04"], queries = [[2,1],[2,2]]
Output: [3,0]
Explanation:
1. Trimmed to the last digit, nums = ["4","7","6","4"]. The 2nd smallest number is 4 at index 3.
   There are two occurrences of 4, but the one at index 0 is considered smaller than the one at index 3.
2. Trimmed to the last 2 digits, nums is unchanged. The 2nd smallest number is 24.
*/

public class Q2343QuerySmallestKthElement {
    
    public int[] smallestTrimmedNumbers(String[] nums, int[][] queries) {
        
        int l = nums[0].length();
        int[][] levelSort = new int[l + 1][nums.length];
        for (int i = 0; i < nums.length; i++) {
            levelSort[0][i] = i;
        }
        for (int i = 0; i < l; i++) {
            levelSort[i + 1] = countSort(nums, levelSort[i], l - i - 1);
        }
        int n = queries.length;
        int[] ans = new int[n];
        for (int i = 0; i < n; i++) {
            int k = queries[i][0];
            int trim = queries[i][1];
            ans[i] = levelSort[trim][k - 1];
        }
        return ans;
    }

    private int[] countSort(String[] nums, int[] curr, int index) {
        
        int[] count = new int[10];
        for (String num : nums) {
            int d = num.charAt(index) - '0';
            count[d]++;
        }
        for (int i = 1; i < 10; i++) {
            count[i] += count[i - 1];
        }
        int[] sorted = new int[curr.length];
        for (int i = curr.length - 1; i >= 0; i--) {
            int d = nums[curr[i]].charAt(index) - '0';
            sorted[--count[d]] = curr[i];
        }
        return sorted;
    }
}