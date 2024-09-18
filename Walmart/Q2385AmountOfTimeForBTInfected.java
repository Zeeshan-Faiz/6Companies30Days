package Walmart;

/*
You are given the root of a binary tree with unique values, and an integer start. At minute 0, 
an infection starts from the node with value start.
Each minute, a node becomes infected if:
    The node is currently uninfected.
    The node is adjacent to an infected node.

Return the number of minutes needed for the entire tree to be infected.

Example 1:
Input: root = [1,5,3,null,4,10,6,9,2], start = 3
Output: 4
Explanation: The following nodes are infected during:
- Minute 0: Node 3
- Minute 1: Nodes 1, 10 and 6
- Minute 2: Node 5
- Minute 3: Node 4
- Minute 4: Nodes 9 and 2
It takes 4 minutes for the whole tree to be infected so we return 4.

Example 2:
Input: root = [1], start = 1
Output: 0
Explanation: At minute 0, the only node in the tree is infected so we return 0.
*/

public class Q2385AmountOfTimeForBTInfected {

    int maxDepth = 0;

    public int amountOfTime(TreeNode root, int start) {
        if (root == null)
            return 0;

        dfs(root, start);
        return maxDepth;
    }

    private int dfs(TreeNode root, int start) {
        
        if (root == null)
            return 0;

        int depth = 0;
        int left = dfs(root.left, start);
        int right = dfs(root.right, start);

        if (root.val == start) {
            depth = -1;
            maxDepth = Math.max(left, right);
        } 
        else if (left >= 0 && right >= 0) {
            depth = Math.max(left, right) + 1;
        } 
        else {
            int dist = Math.abs(left) + Math.abs(right);
            depth = Math.min(left, right) - 1;
            maxDepth = Math.max(maxDepth, dist);
        }
        return depth;
    }
}