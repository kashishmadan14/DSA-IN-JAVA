import java.util.*;

// Definition for a binary tree node provided by LeetCode
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}

public class Codec {
    private static final String DELIMITER = ",";
    private static final String NULL_MARKER = "#";

    // Encodes a tree to a single string using Pre-order Traversal (DFS)
    public String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        serializeHelper(root, sb);
        return sb.toString();
    }

    private void serializeHelper(TreeNode root, StringBuilder sb) {
        if (root == null) {
            sb.append(NULL_MARKER).append(DELIMITER);
            return;
        }
        
        // Process Current Node, then Left, then Right
        sb.append(root.val).append(DELIMITER);
        serializeHelper(root.left, sb);
        serializeHelper(root.right, sb);
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        // Queue handles sequential consumption of tokens efficiently
        Queue<String> nodes = new LinkedList<>(Arrays.asList(data.split(DELIMITER)));
        return deserializeHelper(nodes);
    }

    private TreeNode deserializeHelper(Queue<String> nodes) {
        String currentVal = nodes.poll();
        
        // Base case: if marker matches, there is no node here
        if (currentVal == null || currentVal.equals(NULL_MARKER)) {
            return null;
        }

        // Reconstruct the root, then its left and right subtrees
        TreeNode root = new TreeNode(Integer.parseInt(currentVal));
        root.left = deserializeHelper(nodes);
        root.right = deserializeHelper(nodes);
        
        return root;
    }
}