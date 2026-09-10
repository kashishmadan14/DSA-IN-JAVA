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
class Solution {
   
    private int matchingNodeCount = 0;

    public int averageOfSubtree(TreeNode root) {
        calculateSubtreeDetails(root);
        return matchingNodeCount;
    }


    private int[] calculateSubtreeDetails(TreeNode node) {
      
        if (node == null) {
            return new int[]{0, 0};
        }

        int[] leftSubtree = calculateSubtreeDetails(node.left);
        int[] rightSubtree = calculateSubtreeDetails(node.right);

        int currentSum = node.val + leftSubtree[0] + rightSubtree[0];
        int currentNodeCount = 1 + leftSubtree[1] + rightSubtree[1];

        
        int currentAverage = currentSum / currentNodeCount;

        if (node.val == currentAverage) {
            matchingNodeCount++;
        }

        return new int[]{currentSum, currentNodeCount};
    }
}
