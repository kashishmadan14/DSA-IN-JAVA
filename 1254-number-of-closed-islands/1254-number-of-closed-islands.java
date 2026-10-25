class Solution {
    public int closedIsland(int[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;
        
        // Step 1: Eliminate all land (0s) connected to the boundaries
        // Left and Right boundaries
        for (int i = 0; i < rows; i++) {
            if (grid[i][0] == 0) dfs(grid, i, 0);
            if (grid[i][cols - 1] == 0) dfs(grid, i, cols - 1);
        }
        
        // Top and Bottom boundaries
        for (int j = 0; j < cols; j++) {
            if (grid[0][j] == 0) dfs(grid, 0, j);
            if (grid[rows - 1][j] == 0) dfs(grid, rows - 1, j);
        }
        
        // Step 2: Count the remaining isolated closed islands
        int closedIslandsCount = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == 0) {
                    closedIslandsCount++;
                    dfs(grid, i, j); // Flood-fill the closed island to avoid recounting
                }
            }
        }
        
        return closedIslandsCount;
    }
    
    private void dfs(int[][] grid, int r, int c) {
        // Base cases: Out of bounds or already water (1)
        if (r < 0 || c < 0 || r >= grid.length || c >= grid[0].length || grid[r][c] == 1) {
            return;
        }
        
        // Mark the current land cell as visited by converting it to water
        grid[r][c] = 1;
        
        // Explore all 4 adjacent directions
        dfs(grid, r + 1, c); // Down
        dfs(grid, r - 1, c); // Up
        dfs(grid, r, c + 1); // Right
        dfs(grid, r, c - 1); // Left
    }
}
