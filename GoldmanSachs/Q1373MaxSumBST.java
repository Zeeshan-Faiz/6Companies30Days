package GoldmanSachs;

/*
Given a binary tree root, return the maximum sum of all keys of any sub-tree which is also a 
Binary Search Tree (BST).
Assume a BST is defined as follows:

    The left subtree of a node contains only nodes with keys less than the node's key.
    The right subtree of a node contains only nodes with keys greater than the node's key.
    Both the left and right subtrees must also be binary search trees.

Example 1:
Input: root = [1,4,3,2,4,2,5,null,null,null,null,null,null,4,6]
Output: 20
Explanation: Maximum sum in a valid Binary search tree is obtained in root node with key equal to 3.

Example 2:
Input: root = [4,3,null,1,2]
Output: 2
Explanation: Maximum sum in a valid Binary search tree is obtained in a single root node with key equal to 2.

Example 3:
Input: root = [-4,-2,-5]
Output: 0
Explanation: All values are negatives. Return an empty BST.
*/

public class Q1373MaxSumBST {
    
    int greatestBSTSum = 0;
    public int maxSumBST(TreeNode root) {

        helper(root);
        return greatestBSTSum;

    }

    public int helper(TreeNode root) {
        if (root == null)
            return 0;

        int left = helper(root.left);
        int right = helper(root.right);

        if (left == Integer.MIN_VALUE || right == Integer.MIN_VALUE)
            return Integer.MIN_VALUE;

        if (root.left != null) {
            TreeNode greatestLeft = root.left;
            while (greatestLeft.right != null) {
                greatestLeft = greatestLeft.right;
            }
            if (greatestLeft.val >= root.val)
                return Integer.MIN_VALUE;
        }
        if (root.right != null) {
            TreeNode greatestRight = root.right;
            while (greatestRight.left != null) {
                greatestRight = greatestRight.left;
            }
            if (greatestRight.val <= root.val)
                return Integer.MIN_VALUE;
        }
        int sum = left + right + root.val;
        if (sum > greatestBSTSum) {
            greatestBSTSum = sum;
        }
        return sum;
    }
}