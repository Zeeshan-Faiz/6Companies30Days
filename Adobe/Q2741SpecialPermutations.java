package Adobe;

/*
You are given a 0-indexed integer array nums containing n distinct positive integers. A permutation 
of nums is called special if:
    For all indexes 0 <= i < n - 1, either nums[i] % nums[i+1] == 0 or nums[i+1] % nums[i] == 0.
Return the total number of special permutations. As the answer could be large, return it modulo 109 + 7

Example 1:
Input: nums = [2,3,6]
Output: 2
Explanation: [3,6,2] and [2,6,3] are the two special permutations of nums.

Example 2:
Input: nums = [1,4,3]
Output: 2
Explanation: [3,1,4] and [4,1,3] are the two special permutations of nums.
*/

public class Q2741SpecialPermutations {

    int MOD = (int) 1e9 + 7;
    int N;
    Integer[][] memo;
    int[] nums;

    public int specialPerm(int[] nums) {
        this.N = nums.length;
        this.memo = new Integer[N][1 << N];
        this.nums = nums;
        return backtrack(0, 0);
    }

    // mask = available element to choose from
    private int backtrack(int preIndex, int mask) {
        if (mask == (1 << N) - 1)
            return 1;
        if (memo[preIndex][mask] != null)
            return memo[preIndex][mask];
        int count = 0;
        for (int i = 0; i < N; i++)
            if ((mask & (1 << i)) == 0 &&
                    (mask == 0 || nums[i] % nums[preIndex] == 0 || nums[preIndex] % nums[i] == 0))
                count = (count + backtrack(i, mask | (1 << i))) % MOD; // used.add(i);
        return memo[preIndex][mask] = count;
    }
}