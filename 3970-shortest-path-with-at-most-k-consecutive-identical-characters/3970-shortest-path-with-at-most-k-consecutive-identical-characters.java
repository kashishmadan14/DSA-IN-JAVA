import java.util.*;

class Solution {
    public int shortestPath(int n, int[][] edges, String labels, int k) {
        // Build the adjacency list for the directed graph
        List<int[]>[] graph = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            int weight = edge[2];
            graph[u].add(new int[]{v, weight});
        }

        // dist[u][c] stores the minimum cost to reach node u 
        // with a trailing streak of c consecutive identical characters
        int[][] dist = new int[n][k + 1];
        for (int[] row : dist) {
            Arrays.fill(row, Integer.MAX_VALUE);
        }

        // Priority Queue stores arrays of format: {current_cost, current_node, current_streak}
        // Min-heap ordered by current_cost
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> Integer.compare(a[0], b[0]));

        // Base case: start at node 0. The initial character has a streak of 1.
        dist[0][1] = 0;
        pq.offer(new int[]{0, 0, 1});

        while (!pq.isEmpty()) {
            int[] current = pq.poll();
            int cost = current[0];
            int u = current[1];
            int count = current[2];

            // If we found a cheaper way to reach this state already, skip it
            if (cost > dist[u][count]) {
                continue;
            }

            // Early exit: First time reaching the destination guarantees the shortest path
            if (u == n - 1) {
                return cost;
            }

            // Traverse neighbors
            for (int[] edge : graph[u]) {
                int v = edge[0];
                int weight = edge[1];

                // Determine the next consecutive character streak count
                int nextCount = (labels.charAt(u) == labels.charAt(v)) ? count + 1 : 1;

                // Only proceed if the streak does not exceed the constraint k
                if (nextCount <= k) {
                    int nextCost = cost + weight;
                    // Relax the edge if a shorter path to state (v, nextCount) is found
                    if (nextCost < dist[v][nextCount]) {
                        dist[v][nextCount] = nextCost;
                        pq.offer(new int[]{nextCost, v, nextCount});
                    }
                }
            }
        }

        // Destination node n - 1 is unreachable under the given constraints
        return -1;
    }
}
