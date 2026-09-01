class Solution {
    public boolean isValidSudoku(char[][] board) {
        // Track seen numbers for rows, columns, and 3x3 sub-boxes
        boolean[][] rows = new boolean[9][9];
        boolean[][] cols = new boolean[9][9];
        boolean[][] boxes = new boolean[9][9];

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                char current = board[i][j];
                
                // Skip empty cells
                if (current == '.') {
                    continue;
                }
                
                // Convert char digit to 0-8 index
                int num = current - '1';
                
                // Calculate the 3x3 box index (0 to 8)
                int boxIndex = (i / 3) * 3 + (j / 3);
                
                // Check if the number has already been seen in the current row, column, or box
                if (rows[i][num] || cols[j][num] || boxes[boxIndex][num]) {
                    return false;
                }
                
                // Mark the number as seen
                rows[i][num] = true;
                cols[j][num] = true;
                boxes[boxIndex][num] = true;
            }
        }
        
        return true;
    }
}
