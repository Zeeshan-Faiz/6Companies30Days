package Adobe;

/*
Given the root of a binary search tree and the lowest and highest boundaries as low and high, 
trim the tree so that all its elements lies in [low, high]. Trimming the tree should not change 
the relative structure of the elements that will remain in the tree (i.e., any node's descendant 
should remain a descendant). It can be proven that there is a unique answer.
Return the root of the trimmed binary search tree. Note that the root may change depending on the 
given bounds.

Example 1:
Input: root = [1,0,2], low = 1, high = 2
Output: [1,null,2]

Example 2:
Input: root = [3,0,4,null,2,null,null,1], low = 1, high = 3
Output: [3,2,null,1]
*/

public class Q669TrimABST {

    public TreeNode trimBST(TreeNode root, int low, int high) {

        if (root == null)
            return null;

        if (root.val < low) {
            // answer lies in right subtree
            return trimBST(root.right, low, high);
        } else if (root.val > high) {
            // answer lies in left subtree
            return trimBST(root.left, low, high);
        }

        // check if answer should include left or right of the current root
        root.left = trimBST(root.left, low, high);
        root.right = trimBST(root.right, low, high);
        return root;
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