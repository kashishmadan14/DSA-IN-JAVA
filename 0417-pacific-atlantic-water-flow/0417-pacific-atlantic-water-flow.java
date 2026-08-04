import java.util.ArrayList;
import java.util.List;

class Solution {
    private int m, n;
    private int[][] dirs = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    public List<List<Integer>> pacificAtlantic(int[][] heights) {
        List<List<Integer>> result = new ArrayList<>();
        if (heights == null || heights.length == 0 || heights[0].length == 0) {
            return result;
        }

        m = heights.length;
        n = heights[0].length;
        boolean[][] pacific = new boolean[m][n];
        boolean[][] atlantic = new boolean[m][n];

        // Move inward from Pacific borders (top row and left column)
        for (int i = 0; i < m; i++) {
            dfs(heights, pacific, i, 0, heights[i][0]);
        }
        for (int j = 0; j < n; j++) {
            dfs(heights, pacific, 0, j, heights[0][j]);
        }

        // Move inward from Atlantic borders (bottom row and right column)
        for (int i = 0; i < m; i++) {
            dfs(heights, atlantic, i, n - 1, heights[i][n - 1]);
        }
        for (int j = 0; j < n; j++) {
            dfs(heights, atlantic, m - 1, j, heights[m - 1][j]);
        }

        // Find cells that reach both oceans
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (pacific[i][j] && atlantic[i][j]) {
                    List<Integer> coord = new ArrayList<>();
                    coord.add(i);
                    coord.add(j);
                    result.add(coord);
                }
            }
        }

        return result;
    }

    private void dfs(int[][] heights, boolean[][] visited, int r, int c, int prevHeight) {
        if (r < 0 || r >= m || c < 0 || c >= n || visited[r][c] || heights[r][c] < prevHeight) {
            return;
        }
        visited[r][c] = true;
        for (int[] d : dirs) {
            dfs(heights, visited, r + d[0], c + d[1], heights[r][c]);
        }
    }
}