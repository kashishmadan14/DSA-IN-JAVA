class Solution {
    public int climbStairs(int n, int[] costs) {
        // dp[i] stores the minimum cost to reach step i
        int[] dp = new int[n + 1];
        
        // Base case: starting point costs 0
        dp[0] = 0;
        
        // Iterate through each step from 1 to n
        for (int i = 1; i <= n; i++) {
            int minCost = Integer.MAX_VALUE;
            
            // Check all valid backward jumps of size 1, 2, or 3
            for (int jump = 1; jump <= 3; jump++) {
                int prevStep = i - jump;
                
                if (prevStep >= 0) {
                    // Cost = cost to reach previous step + cost of current step + quadratic jump penalty
                    int currentJumpCost = dp[prevStep] + costs[i - 1] + (jump * jump);
                    minCost = Math.min(minCost, currentJumpCost);
                }
            }
            dp[i] = minCost;
        }
        
        return dp[n];
    }
}
