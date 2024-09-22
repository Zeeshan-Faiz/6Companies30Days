package Walmart;

import java.util.Arrays;

/*
Given a string s, sort it in decreasing order based on the frequency of the characters. The 
frequency of a character is the number of times it appears in the string.
Return the sorted string. If there are multiple answers, return any of them.

Example 1:
Input: s = "tree"
Output: "eert"
Explanation: 'e' appears twice while 'r' and 't' both appear once.
So 'e' must appear before both 'r' and 't'. Therefore "eetr" is also a valid answer.

Example 2:
Input: s = "cccaaa"
Output: "aaaccc"
Explanation: Both 'c' and 'a' appear three times, so both "cccaaa" and "aaaccc" are valid answers.
Note that "cacaca" is incorrect, as the same characters must be together.

Example 3:
Input: s = "Aabb"
Output: "bbAa"
Explanation: "bbaA" is also a valid answer, but "Aabb" is incorrect.
Note that 'A' and 'a' are treated as two different characters.
*/

public class Q451SortCharactersByFrequency {

    public String frequencySort(String s) {

        int[][] freq = new int[75][2];
        for (int i = 0; i < s.length(); i++) {
            int x = s.charAt(i) - '0';
            freq[x][0]++;
            freq[x][1] = x;
        }
        Arrays.sort(freq, (a, b) -> b[0] - a[0]);
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < freq.length; i++) {
            int x = freq[i][1] + '0';
            while (freq[i][0] > 0) {
                result.append((char) x);
                freq[i][0]--;
            }
        }
        return result.toString();
    }
}