class Solution {
    public List<Integer> remainingMethods(int n, int k, int[][] invocations) {
        // Step 1: Build the directed dependency graph
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
        }
        for (int[] inv : invocations) {
            adj.get(inv[0]).add(inv[1]);
        }

        // Step 2: Use BFS to find all suspicious methods starting from k
        boolean[] isSuspicious = new boolean[n];
        Queue<Integer> queue = new LinkedList<>();
        
        queue.add(k);
        isSuspicious[k] = true;

        while (!queue.isEmpty()) {
            int current = queue.poll();
            for (int neighbor : adj.get(current)) {
                if (!isSuspicious[neighbor]) {
                    isSuspicious[neighbor] = true;
                    queue.add(neighbor);
                }
            }
        }

        // Step 3: Verify if any non-suspicious method invokes a suspicious method
        for (int[] inv : invocations) {
            int src = inv[0];
            int dest = inv[1];
            // If sender is safe, but receiver is suspicious -> Cannot remove anything
            if (!isSuspicious[src] && isSuspicious[dest]) {
                List<Integer> allMethods = new ArrayList<>();
                for (int i = 0; i < n; i++) {
                    allMethods.add(i);
                }
                return allMethods;
            }
        }

        // Step 4: Collect and return only the non-suspicious methods
        List<Integer> remaining = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (!isSuspicious[i]) {
                remaining.add(i);
            }
        }
        return remaining;
    }
}