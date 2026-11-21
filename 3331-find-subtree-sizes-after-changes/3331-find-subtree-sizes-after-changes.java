import java.util.*;

class Solution {
    public int[] findSubtreeSizes(int[] parent, String s) {
        int n = parent.length;
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
        }
        for (int i = 1; i < n; i++) {
            adj.get(parent[i]).add(i);
        }

        int[] newParent = Arrays.copyOf(parent, n);
        int[] lastSeen = new int[26];
        Arrays.fill(lastSeen, -1);

        // Step 1: Find new parents using DFS + backtracking
        findNewParents(0, s, adj, lastSeen, newParent);

        // Step 2: Build the modified tree
        List<List<Integer>> newAdj = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            newAdj.add(new ArrayList<>());
        }
        for (int i = 1; i < n; i++) {
            newAdj.get(newParent[i]).add(i);
        }

        // Step 3: Calculate final subtree sizes
        int[] ans = new int[n];
        countSizes(0, newAdj, ans);
        return ans;
    }

    private void findNewParents(int u, String s, List<List<Integer>> adj, int[] lastSeen, int[] newParent) {
        int charIdx = s.charAt(u) - 'a';
        int prevAncestor = lastSeen[charIdx];

        if (prevAncestor != -1) {
            newParent[u] = prevAncestor;
        }

        // Update current node as the closest ancestor for this character
        lastSeen[charIdx] = u;

        for (int v : adj.get(u)) {
            findNewParents(v, s, adj, lastSeen, newParent);
        }

        // Backtrack to restore state for sibling subtrees
        lastSeen[charIdx] = prevAncestor;
    }

    private int countSizes(int u, List<List<Integer>> newAdj, int[] ans) {
        int size = 1;
        for (int v : newAdj.get(u)) {
            size += countSizes(v, newAdj, ans);
        }
        ans[u] = size;
        return size;
    }
}
