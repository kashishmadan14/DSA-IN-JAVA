class Solution {
    public int maxProfit(int[] prices) {
        int minPrice = Integer.MAX_VALUE;
        int maxProfit = 0;
        
        for (int price : prices) {
            if (price < minPrice) {
                minPrice = price; // Update the lowest buying price found so far
            } else {
                maxProfit = Math.max(maxProfit, price - minPrice); // Check if selling today yields a new max profit
            }
        }
        
        return maxProfit;
    }
}