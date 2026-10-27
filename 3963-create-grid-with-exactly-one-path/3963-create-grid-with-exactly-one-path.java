import java.util.Arrays;

class Solution {
    public String[] createGrid(int m, int n) {
        // Initialize an m x n character grid
        char[][] grid = new char[m][n];
        
        // Step 1: Fill the entire grid with walls (#)
        for (int i = 0; i < m; i++) {
            Arrays.fill(grid[i], '#');
        }
        
        // Step 2: Open up the entire first row with free cells (.)
        Arrays.fill(grid[0], '.');
        
        // Step 3: Open up the entire last column with free cells (.)
        for (int i = 0; i < m; i++) {
            grid[i][n - 1] = '.';
        }
        
        // Step 4: Convert the character array into an array of Strings
        String[] result = new String[m];
        for (int i = 0; i < m; i++) {
            result[i] = new String(grid[i]);
        }
        
        return result;
    }
}
