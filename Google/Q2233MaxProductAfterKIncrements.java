package Google;

import java.util.Arrays;

/*
You are given an array of non-negative integers nums and an integer k. In one operation, you may 
choose any element from nums and increment it by 1.
Return the maximum product of nums after at most k operations. Since the answer may be very large, 
return it modulo 109 + 7. Note that you should maximize the product before taking the modulo. 

Example 1:
Input: nums = [0,4], k = 5
Output: 20
Explanation: Increment the first number 5 times.
Now nums = [5, 4], with a product of 5 * 4 = 20.
It can be shown that 20 is maximum product possible, so we return 20.
Note that there may be other ways to increment nums to have the maximum product.

Example 2:
Input: nums = [6,3,3,2], k = 2
Output: 216
Explanation: Increment the second number 1 time and increment the fourth number 1 time.
Now nums = [6, 4, 3, 3], with a product of 6 * 4 * 3 * 3 = 216.
It can be shown that 216 is maximum product possible, so we return 216.
Note that there may be other ways to increment nums to have the maximum product.
*/

public class Q2233MaxProductAfterKIncrements {

    public int maximumProduct(int[] nums, int k) {
        Arrays.sort(nums);
        int n = nums.length;
        long product = 1;
        long mod = (long) (1e9 + 7);
        int count = 1;
        for (int i = 1; i < n; i++) {
            if (nums[i] == nums[i - 1]) {
                count++;
            } else {
                int diff = nums[i] - nums[i - 1];
                if (diff * count <= k) {
                    for (int j = 0; j < i; j++) {
                        nums[j] += diff;
                    }
                    k -= count * diff;
                    count++;
                } else {
                    int x = k / count;
                    int y = k % count;
                    for (int j = 0; j < i; j++) {
                        nums[j] += x;
                    }
                    for (int j = 0; j < y; j++) {
                        nums[i - 1 - j]++;
                    }
                    count = 0;
                    k = 0;
                }
            }
            if (k == 0) {
                break;
            }
        }
        if (count >= 1 && k != 0) {
            int x = k / n;
            int y = k % n;
            for (int j = 0; j < n; j++) {
                if (j < y) {
                    nums[j]++;
                }
                nums[j] += x;
            }
        }
        for (int i : nums) {
            product = (product * ((long) i)) % mod;
        }
        return (int) (product);
    }
}