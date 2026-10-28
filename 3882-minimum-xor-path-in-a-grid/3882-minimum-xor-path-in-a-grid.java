import java.util.Arrays;

class Solution {

    public int solve(int i, int j, int m, int n, int[][] grid,
                     int currCost, int[][][] dp) {

        // Base case: out of bounds
        if (i < 0 || i >= m || j < 0 || j >= n) {
            return Integer.MAX_VALUE;
        }

        currCost = currCost ^ grid[i][j];

        // Reached destination
        if (i == m - 1 && j == n - 1) {
            return currCost;
        }

        // Check DP table
        if (dp[i][j][currCost] != -1) {
            return dp[i][j][currCost];
        }

        // Explore down and right
        int down = solve(i + 1, j, m, n, grid, currCost, dp);
        int right = solve(i, j + 1, m, n, grid, currCost, dp);

        // Return minimum XOR path cost
        return dp[i][j][currCost] = Math.min(down, right);
    }

    public int minCost(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        int[][][] dp = new int[m][n][1024];

        for (int[][] row : dp) {
            for (int[] cell : row) {
                Arrays.fill(cell, -1);
            }
        }

        return solve(0, 0, m, n, grid, 0, dp);
    }
}