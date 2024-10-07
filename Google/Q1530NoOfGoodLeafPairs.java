package Google;

/*
You are given the root of a binary tree and an integer distance. A pair of two different leaf 
nodes of a binary tree is said to be good if the length of the shortest path between them is 
less than or equal to distance. Return the number of good leaf node pairs in the tree.

Example 1:
Input: root = [1,2,3,null,4], distance = 3
Output: 1
Explanation: The leaf nodes of the tree are 3 and 4 and the length of the shortest path between them is 3. This is the only good pair.

Example 2:
Input: root = [1,2,3,4,5,6,7], distance = 3
Output: 2
Explanation: The good pairs are [4,5] and [6,7] with shortest path = 2. The pair [4,6] is not good because the length of ther shortest path between them is 4.

Example 3:
Input: root = [7,1,4,6,null,5,3,null,null,null,null,null,2], distance = 3
Output: 1
Explanation: The only good pair is [2,5].
*/

public class Q1530NoOfGoodLeafPairs {

    public int countPairs(TreeNode root, int distance) {
        int pairs[] = new int[1];
        dfs(root, distance, pairs);
        return pairs[0];
    }

    public int[] dfs(TreeNode root, int distance, int[] pairs) {

        if (root == null) {
            return new int[distance + 1];
        }

        if (root.left == null && root.right == null) {
            int[] res = new int[distance + 1];
            res[1] = 1;
            return res;
        }

        int left[] = dfs(root.left, distance, pairs);
        int right[] = dfs(root.right, distance, pairs);

        int prefixsum[] = new int[right.length];
        prefixsum[0] = right[0];
        for (int i = 1; i < prefixsum.length; i++) {
            prefixsum[i] = prefixsum[i - 1] + right[i];
        }

        for (int i = 1; i < left.length; i++) {
            pairs[0] = pairs[0] + (left[i] * prefixsum[distance - i]);
        }

        int[] res = new int[distance + 1];
        for (int i = 1; i < res.length - 1; i++) {
            res[i + 1] = left[i] + right[i];
        }

        return res;
    }

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
}