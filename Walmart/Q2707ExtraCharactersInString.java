package Walmart;

/*
You are given a 0-indexed string s and a dictionary of words dictionary. You have to break s into 
one or more non-overlapping substrings such that each substring is present in dictionary. There 
may be some extra characters in s which are not present in any of the substrings.
Return the minimum number of extra characters left over if you break up s optimally.

Example 1:
Input: s = "leetscode", dictionary = ["leet","code","leetcode"]
Output: 1
Explanation: We can break s in two substrings: "leet" from index 0 to 3 and "code" from index 5 to 8. There is only 1 unused character (at index 4), so we return 1.

Example 2:
Input: s = "sayhelloworld", dictionary = ["hello","world"]
Output: 3
Explanation: We can break s in two substrings: "hello" from index 3 to 7 and "world" from index 8 to 12. The characters at indices 0, 1, 2 are not used in any substring and thus are considered as extra characters. Hence, we return 3..
*/

public class Q2707ExtraCharactersInString {
    
    public int minExtraChar(String s, String[] dictionary) {
        Node root = new Node();
        for (String str : dictionary) {
            insert(str, root);
        }
        int n = s.length();
        int[] dp = new int[n + 1];
        for (int i = 0; i < n + 1; i++) {
            dp[i] = i;
        }

        for (int i = 0; i < n; i++) {
            search(s, root, i, dp);
            dp[i + 1] = Math.min(dp[i + 1], dp[i] + 1);
        }
        return dp[n];
    }

    public void insert(String s, Node root) {
        for (int i = 0; i < s.length(); i++) {
            int path = s.charAt(i) - 'a';
            if (root.child[path] == null) {
                Node node = new Node();
                root.child[path] = node;
            }
            root = root.child[path];
        }
        root.isEnd = true;
    }

    public void search(String s, Node root, int idx, int[] dp) {
        for (int i = idx; i < s.length(); i++) {
            int path = s.charAt(i) - 'a';
            if (root.child[path] != null) {
                root = root.child[path];
                if (root.isEnd) {
                    dp[i + 1] = Math.min(dp[i + 1], dp[idx]);
                }
            } else {
                return;
            }
        }
    }

    class Node {
        public Node[] child;
        public boolean isEnd;

        public Node() {
            child = new Node[26];
            isEnd = false;
        }
    }
}