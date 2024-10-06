package Google;

/*
Design a data structure that supports adding new words and finding if a string matches any previously 
added string.
Implement the WordDictionary class:
    WordDictionary() Initializes the object.
    void addWord(word) Adds word to the data structure, it can be matched later.
    bool search(word) Returns true if there is any string in the data structure that matches word or false otherwise. word may contain dots '.' where dots can be matched with any letter.

Example 1:
Input
["WordDictionary","addWord","addWord","addWord","search","search","search","search"]
[[],["bad"],["dad"],["mad"],["pad"],["bad"],[".ad"],["b.."]]
Output
[null,null,null,null,false,true,true,true]

Explanation
WordDictionary wordDictionary = new WordDictionary();
wordDictionary.addWord("bad");
wordDictionary.addWord("dad");
wordDictionary.addWord("mad");
wordDictionary.search("pad"); // return False
wordDictionary.search("bad"); // return True
wordDictionary.search(".ad"); // return True
wordDictionary.search("b.."); // return True
*/

public class Q211DesignWordDataStructure {

    TrieNode root;

    class TrieNode {
        TrieNode[] nodes = new TrieNode[26];
        boolean isWord;
    }

    public Q211DesignWordDataStructure() {
        root = new TrieNode();
    }

    public void addWord(String word) {
        int idx = 0;
        TrieNode n = root;
        while (idx < word.length()) {
            int c = word.charAt(idx++) - 'a';
            if (n.nodes[c] == null)
                n.nodes[c] = new TrieNode();
            n = n.nodes[c];
        }
        n.isWord = true;
    }

    public boolean search(String word) {
        return recSearch(word, 0, root);
    }

    boolean recSearch(String word, int idx, TrieNode n) {
        if (n == null)
            return false;
        if (idx == word.length())
            return n.isWord;
        char c = word.charAt(idx);
        while (c != '.') {
            if (n.nodes[c - 'a'] == null)
                return false;
            n = n.nodes[c - 'a'];

            if (++idx == word.length())
                return n.isWord;
            c = word.charAt(idx);
        }
        if (word.charAt(idx) == '.') {
            for (int i = 0; i < 26; i++) {
                if (n.nodes[i] != null) {
                    if (recSearch(word, idx + 1, n.nodes[i]))
                        return true;
                }
            }
        } else {
            return recSearch(word, idx + 1, n.nodes[word.charAt(idx) - 'a']);
        }
        return false;
    }
}