class Solution {
    public int[][] restoreMatrix(int[] rowSum, int[] colSum) {
        int numRows = rowSum.length;
        int numCols = colSum.length;
        int[][] matrix = new int[numRows][numCols];
        
        int i = 0; // Pointer for current row
        int j = 0; // Pointer for current column
        
        // Loop until we process all rows or columns
        while (i < numRows && j < numCols) {
            // Take the minimum of the remaining row and column sum
            int value = Math.min(rowSum[i], colSum[j]);
            
            // Assign to the matrix cell
            matrix[i][j] = value;
            
            // Update the remaining sums
            rowSum[i] -= value;
            colSum[j] -= value;
            
            // If the row sum becomes 0, move to the next row
            if (rowSum[i] == 0) {
                i++;
            }
            // If the column sum becomes 0, move to the next column
            if (colSum[j] == 0) {
                j++;
            }
        }
        
        return matrix;
    }
}
