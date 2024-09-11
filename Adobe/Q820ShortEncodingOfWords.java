package Adobe;

/*
A valid encoding of an array of words is any reference string s and array of indices indices such that:
    words.length == indices.length
    The reference string s ends with the '#' character.
    For each index indices[i], the substring of s starting from indices[i] and up to (but not 
    including) the next '#' character is equal to words[i].

Given an array of words, return the length of the shortest reference string s possible of any valid 
encoding of words.

Example 1:
Input: words = ["time", "me", "bell"]
Output: 10
Explanation: A valid encoding would be s = "time#bell#" and indices = [0, 2, 5].
words[0] = "time", the substring of s starting from indices[0] = 0 to the next '#' is underlined in "time#bell#"
words[1] = "me", the substring of s starting from indices[1] = 2 to the next '#' is underlined in "time#bell#"
words[2] = "bell", the substring of s starting from indices[2] = 5 to the next '#' is underlined in "time#bell#"

Example 2:
Input: words = ["t"]
Output: 2
Explanation: A valid encoding would be s = "t#" and indices = [0]
*/

public class Q820ShortEncodingOfWords {
    
    TrieNode root;
    class TrieNode {
        TrieNode[] next = null;
    }

    public int minimumLengthEncoding(String[] words) {
        root = new TrieNode();
        root.next = new TrieNode[26];
        int res = 0;
        for (String w : words) {
            res += helper(w);
        }
        return res;
    }

    public int helper(String w) {
        int length = w.length();
        boolean newBranch = false;
        int create = 0;
        TrieNode current = root;
        for (int i = length - 1; i >= 0; --i) {
            boolean newLevel = false;
            int id = w.charAt(i) - 'a';
            if (current.next == null) {
                newLevel = true;
                current.next = new TrieNode[26];
            }
            if (current.next[id] == null) {
                if (!newLevel)
                    newBranch = true;
                current.next[id] = new TrieNode();
                create++;
            }
            current = current.next[id];
        }
        return newBranch ? length + 1 : create;
    }
}