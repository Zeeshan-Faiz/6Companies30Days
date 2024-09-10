package Adobe;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/*
Given a string s and a string array dictionary, return the longest string in the dictionary that 
can be formed by deleting some of the given string characters. If there is more than one possible 
result, return the longest word with the smallest lexicographical order. If there is no possible 
result, return the empty string.

Example 1:
Input: s = "abpcplea", dictionary = ["ale","apple","monkey","plea"]
Output: "apple"

Example 2:
Input: s = "abpcplea", dictionary = ["a","b","c"]
Output: "a"
*/

public class Q524LongestWordinDictionary {

    public String findLongestWord(String s, List<String> dictionary) {

        // Sorting the dictionary in reverse Order...
        Collections.sort(dictionary, Comparator.reverseOrder());
        String longest = "";
        for (String word : dictionary) {
            // If word can be formed and the current longest is less than or equal in length...
            if (WordCanBeFormed(s, word) && longest.length() <= word.length())
                longest = word;
        }
        return longest;
    }

    public boolean WordCanBeFormed(String sequence, String word) {

        StringBuilder greedy = new StringBuilder();

        for (int i = 0, j = 0; i < sequence.length(); i++) {
            // If the characters are same at both indices
            if (j != word.length() && sequence.charAt(i) == word.charAt(j)) {
                greedy.append("" + sequence.charAt(i));
                j++;
            }
        }
        // Checking if the word can be developed as a subsequence
        return greedy.toString().equals(word);
    }
}