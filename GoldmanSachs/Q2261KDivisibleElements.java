package GoldmanSachs;

import java.util.HashMap;

/*
Given an integer array nums and two integers k and p, return the number of distinct subarrays, 
which have at most k elements that are divisible by p.
Two arrays nums1 and nums2 are said to be distinct if:
    They are of different lengths, or
    There exists at least one index i where nums1[i] != nums2[i].

A subarray is defined as a non-empty contiguous sequence of elements in an array.

Example 1:
Input: nums = [2,3,3,2,2], k = 2, p = 2
Output: 11
Explanation:
The elements at indices 0, 3, and 4 are divisible by p = 2.
The 11 distinct subarrays which have at most k = 2 elements divisible by 2 are:
[2], [2,3], [2,3,3], [2,3,3,2], [3], [3,3], [3,3,2], [3,3,2,2], [3,2], [3,2,2], and [2,2].
Note that the subarrays [2] and [3] occur more than once in nums, but they should each be counted only once.
The subarray [2,3,3,2,2] should not be counted because it has 3 elements that are divisible by 2.

Example 2:

Input: nums = [1,2,3,4], k = 4, p = 1
Output: 10
Explanation:
All element of nums are divisible by p = 1.
Also, every subarray of nums will have at most 4 elements that are divisible by 1.
Since all subarrays are distinct, the total number of subarrays satisfying all the constraints is 10.
*/

public class Q2261KDivisibleElements {
    
    int count = 0;

    void add(int nums[], int low, int high, Node root) {
        
        Node temp = root;
        for (int i = low; i <= high; i++) {
            Node child = temp.map.getOrDefault(nums[i], null);
            if (child == null) {
                child = new Node();
                count++;
                temp.map.put(nums[i], child);
            }
            temp = child;
        }
    }

    public int countDistinct(int[] nums, int k, int p) {

        Node root = new Node();
        int i = 0, j = 0, countP = 0;
        while (j < nums.length) {
            while (i < nums.length) {
                if (nums[i] % p == 0)
                    countP++;
                if (countP == k + 1) {
                    countP--;
                    break;
                }
                i++;

            }
            if (nums[j] % p == 0)
                countP--;
            add(nums, j, i - 1, root);
            j++;

        }
        return count;
    }

    class Node {
        HashMap<Integer, Node> map;
        Node() {
            map = new HashMap<>();
        }
    }
}