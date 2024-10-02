package Walmart;

import java.util.List;

/*
Given a string s and a dictionary of strings wordDict, return true if s can be segmented into a 
space-separated sequence of one or more dictionary words.
Note that the same word in the dictionary may be reused multiple times in the segmentation.

Example 1:
Input: s = "leetcode", wordDict = ["leet","code"]
Output: true
Explanation: Return true because "leetcode" can be segmented as "leet code".

Example 2:
Input: s = "applepenapple", wordDict = ["apple","pen"]
Output: true
Explanation: Return true because "applepenapple" can be segmented as "apple pen apple".
Note that you are allowed to reuse a dictionary word.

Example 3:
Input: s = "catsandog", wordDict = ["cats","dog","sand","and","cat"]
Output: false
*/

public class Q139WordBreak {
    
    public boolean wordBreak(String s, List<String> wordDict) {
        //create array for memo;
        Boolean[] arr = new Boolean[s.length()];
        return helper(s, wordDict, 0, arr);
    }
    public boolean helper(String s, List<String> wordDict, int index, Boolean[] arr) {

        if(index >= s.length()) {
            return true;
        }
        if(arr[index] != null) {
            return arr[index];
        }
        //interate through wordDict
        for(int i=0; i<wordDict.size(); i++) {
            String word = wordDict.get(i);
            if(s.length()-index < word.length() || s.charAt(index) != word.charAt(0)) {
                continue;
            }
            //iterate through the word
            int j = 0;
            while(j<word.length()) {
                if(s.charAt(index+j) != word.charAt(j)) {
                    break;
                }
                j++;
            }
            //if whold word found in this portion of "s". 
            if(j == word.length()) {
                arr[index] = helper(s, wordDict, index+j, arr);
                if(arr[index] == true) {
                    return true;
                }
            }
        }
        //return false if none of the word in wordDict can be found in this portion of "s".
        return false;
    }
}