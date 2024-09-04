package Microsoft;

import java.util.ArrayList;
import java.util.List;

/*
Given a string s, find two disjoint palindromic subsequences of s such that the product of their 
lengths is maximized. The two subsequences are disjoint if they do not both pick a character at 
the same index. Return the maximum possible product of the lengths of the two palindromic subsequences.

A subsequence is a string that can be derived from another string by deleting some or no characters 
without changing the order of the remaining characters. A string is palindromic if it reads the 
same forward and backward.

Example 1:
Input: s = "leetcodecom"
Output: 9
Explanation: An optimal solution is to choose "ete" for the 1st subsequence and "cdc" for the 2nd subsequence.
The product of their lengths is: 3 * 3 = 9.

Example 2:
Input: s = "bb"
Output: 1
Explanation: An optimal solution is to choose "b" (the first character) for the 1st subsequence and "b" (the second character) for the 2nd subsequence.
The product of their lengths is: 1 * 1 = 1.

Example 3:
Input: s = "accbcaxxcxx"
Output: 25
Explanation: An optimal solution is to choose "accca" for the 1st subsequence and "xxcxx" for the 2nd subsequence.
The product of their lengths is: 5 * 5 = 25.
*/

public class Q2002MaxProductOfTwoPalindromicSubSeq {

    int max = 0;
    public int maxProduct(String s) {

        char[] c = s.toCharArray();
        dfs(c, 0, new ArrayList<>(), new ArrayList<>());

        return max;
    }

    public void dfs(char[] c, int index, List<Character> s1, List<Character> s2) {

        // base case
        if (index >= c.length) {
            if (isPalin(s1) && isPalin(s2))
                max = Math.max(max, s1.size() * s2.size());
            return;
        }

        // add a character each time to s1 and then s2 and don't add the current character to both
        s1.add(c[index]);
        dfs(c, index + 1, s1, s2);
        s1.remove(s1.size() - 1);// backtrack
        s2.add(c[index]);
        dfs(c, index + 1, s1, s2);// backtrack
        s2.remove(s2.size() - 1);
        dfs(c, index + 1, s1, s2);
    }

    public boolean isPalin(List<Character> str) {

        int i = 0, j = str.size() - 1;
        while (i < j) {
            if (str.get(i) != str.get(j))
                return false;
            i++;
            j--;
        }
        return true;
    }
}