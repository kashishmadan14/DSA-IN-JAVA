class Solution {
    public int largestOverlap(int[][] img1, int[][] img2) {
        int n = img1.length;

        int[][] counts = new int[2 * n + 1][2 * n + 1];
        int maxOverlap = 0;

        for (int i1 = 0; i1 < n; i1++) {
            for (int j1 = 0; j1 < n; j1++) {
                if (img1[i1][j1] == 1) { 
                 
                    for (int i2 = 0; i2 < n; i2++) {
                        for (int j2 = 0; j2 < n; j2++) {
                            if (img2[i2][j2] == 1) {
                               
                                int rowShift = i2 - i1;
                                int colShift = j2 - j1;
                                
                                counts[rowShift + n][colShift + n]++;
                            }
                        }
                    }
                    
                }
            }
        }

        for (int i = 0; i < counts.length; i++) {
            for (int j = 0; j < counts[0].length; j++) {
                maxOverlap = Math.max(maxOverlap, counts[i][j]);
            }
        }

        return maxOverlap;
    }
}
