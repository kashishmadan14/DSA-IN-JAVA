class Solution {
    public int maximumSaleItems(int[][] items, int budget) {
        int n = items.length;
        int[] dp = new int[budget + 1];
        int minPrice = Integer.MAX_VALUE;

        for (int i = 0; i < n; i++) {
            int factor = items[i][0];
            int price = items[i][1];
            minPrice = Math.min(minPrice, price);

            // Count total items gained from buying the first copy of item i
            // (1 purchased copy + all free copies j where factor_i divides factor_j)
            int totalGain = 0;
            for (int j = 0; j < n; j++) {
                if (items[j][0] % factor == 0) {
                    totalGain++;
                }
            }

            // 0/1 Knapsack: iterate backwards so each item's bonus is only claimed once
            for (int b = budget; b >= price; b--) {
                dp[b] = Math.max(dp[b], dp[b - price] + totalGain);
            }
        }

        // Spend any remaining budget on the cheapest item available
        int maxItems = 0;
        for (int b = 0; b <= budget; b++) {
            int remainingBudget = budget - b;
            maxItems = Math.max(maxItems, dp[b] + (remainingBudget / minPrice));
        }

        return maxItems;
    }
}
