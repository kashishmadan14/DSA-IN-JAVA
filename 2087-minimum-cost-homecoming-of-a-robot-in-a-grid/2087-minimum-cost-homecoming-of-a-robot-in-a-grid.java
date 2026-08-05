class Solution {
    public int minCost(int[] startPos, int[] homePos, int[] rowCosts, int[] colCosts) {
        int totalCost = 0;
        int r = startPos[0];
        int c = startPos[1];
        int targetR = homePos[0];
        int targetC = homePos[1];

        // Move along rows
        while (r != targetR) {
            r += (r < targetR) ? 1 : -1;
            totalCost += rowCosts[r];
        }

        // Move along columns
        while (c != targetC) {
            c += (c < targetC) ? 1 : -1;
            totalCost += colCosts[c];
        }

        return totalCost;
    }
}