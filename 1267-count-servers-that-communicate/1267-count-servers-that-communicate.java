class Solution {
    public int countServers(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        
        // Trackers to store the count of servers in each row and column
        int[] rowCount = new int[m];
        int[] colCount = new int[n];
        
        // First Pass: Count the total number of servers in each row and column
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    rowCount[i]++;
                    colCount[j]++;
                }
            }
        }
        
        int communicatingServers = 0;
        
        // Second Pass: Check if a server can communicate with others
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                // If a server exists and its row or column has more than 1 server, it can communicate
                if (grid[i][j] == 1 && (rowCount[i] > 1 || colCount[j] > 1)) {
                    communicatingServers++;
                }
            }
        }
        
        return communicatingServers;
    }
}
