/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
public class Solution {
    // Main function to check if subRoot is a subtree of root
    public boolean isSubtree(TreeNode root, TreeNode subRoot) {
        // Base case: If the main tree is empty, it cannot contain any subtree
        if (root == null) {
            return false;
        }
        
        // If the trees match from the current node, return true
        if (isSameTree(root, subRoot)) {
            return true;
        }
        
        // Recursively look for a match in the left and right subtrees
        return isSubtree(root.left, subRoot) || isSubtree(root.right, subRoot);
    }

    // Helper function to check if two trees are structurally identical
    private boolean isSameTree(TreeNode p, TreeNode q) {
        // Both are null means they are identical
        if (p == null && q == null) {
            return true;
        }
        // One is null or values don't match means they are not identical
        if (p == null || q == null || p.val != q.val) {
            return false;
        }
        
        // Recursively check left and right subtrees
        return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }
}