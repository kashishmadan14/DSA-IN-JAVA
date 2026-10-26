class Solution {
    public int countIslands(int[][] grid, int k) {
        int m = grid.length;
        int n = grid[0].length;
        int count = 0;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                // Found an unvisited land cell
                if (grid[i][j] > 0) {
                    long islandSum = dfs(grid, i, j);
                    // Check if total island value is divisible by k
                    if (islandSum % k == 0) {
                        count++;
                    }
                }
            }
        }
        return count;
    }

    private long dfs(int[][] grid, int r, int c) {
        // Base case: check bounds and if cell is water (0)
        if (r < 0 || r >= grid.length || c < 0 || c >= grid[0].length || grid[r][c] == 0) {
            return 0;
        }

        // Add current cell value
        long sum = grid[r][c];
        
        // Mark cell as visited by turning it into water
        grid[r][c] = 0; 

        // Explore 4-directional neighbors
        sum += dfs(grid, r + 1, c);
        sum += dfs(grid, r - 1, c);
        sum += dfs(grid, r, c + 1);
        sum += dfs(grid, r, c - 1);

        return sum;
    }
}
