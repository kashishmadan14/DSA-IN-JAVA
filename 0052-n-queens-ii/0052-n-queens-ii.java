class Solution {
    private int count = 0;
    private boolean[] cols;      // Tracks columns occupied by queens
    private boolean[] diag1;     // Tracks primary diagonals (row + col)
    private boolean[] diag2;     // Tracks secondary diagonals (row - col + n)

    public int totalNQueens(int n) {
        cols = new boolean[n];
        diag1 = new boolean[2 * n];
        diag2 = new boolean[2 * n];
        
        backtrack(0, n);
        return count;
    }

    private void backtrack(int row, int n) {
        // Base case: All queens are placed successfully
        if (row == n) {
            count++;
            return;
        }

        for (int col = 0; col < n; col++) {
            int d1 = row + col;
            int d2 = row - col + n; // Offset by +n to avoid negative indices

            // Check if the current position is under attack
            if (cols[col] || diag1[d1] || diag2[d2]) {
                continue;
            }

            // Place the queen (Action)
            cols[col] = true;
            diag1[d1] = true;
            diag2[d2] = true;

            // Recurse to the next row (Recurse)
            backtrack(row + 1, n);

            // Remove the queen (Undo/Backtrack)
            cols[col] = false;
            diag1[d1] = false;
            diag2[d2] = false;
        }
    }
}
