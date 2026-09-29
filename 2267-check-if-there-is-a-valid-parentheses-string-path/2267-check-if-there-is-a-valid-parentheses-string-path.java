class Solution {
    public boolean hasValidPath(char[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        
        // Quick Pruning Optimization Checks
        // 1. Total length of path (m + n - 1) must be even to balance out ( ( and ) )
        if ((m + n - 1) % 2 != 0) return false;
        // 2. Cannot start with a closing parenthesis
        if (grid[0][0] == ')') return false;
        // 3. Cannot end with an opening parenthesis
        if (grid[m - 1][n - 1] == '(') return false;
        
        // 3D Memoization table: [row][col][balance]
        // Max possible balance value can't exceed the total steps (m + n)
        Boolean[][][] memo = new Boolean[m][n][m + n];
        
        return dfs(grid, 0, 0, 0, memo);
    }
    
    private boolean dfs(char[][] grid, int r, int c, int balance, Boolean[][][] memo) {
        // Adjust balance based on the current cell
        if (grid[r][c] == '(') {
            balance++;
        } else {
            balance--;
        }
        
        // If balance drops below 0, it means an invalid prefix sequence (e.g. "())")
        if (balance < 0) return false;
        
        // Base case: Reached the bottom-right corner
        if (r == grid.length - 1 && c == grid[0].length - 1) {
            return balance == 0;
        }
        
        // Return cached result if already calculated
        if (memo[r][c][balance] != null) {
            return memo[r][c][balance];
        }
        
        boolean pathExists = false;
        
        // Option 1: Move Down
        if (r + 1 < grid.length) {
            pathExists = pathExists || dfs(grid, r + 1, c, balance, memo);
        }
        
        // Option 2: Move Right
        if (c + 1 < grid[0].length) {
            pathExists = pathExists || dfs(grid, r, c + 1, balance, memo);
        }
        
        // Cache the result and return
        return memo[r][c][balance] = pathExists;
    }
}
