class Solution {
    public String stoneGameIII(int[] stoneValue) {
        int n = stoneValue.length;
       
        int[] dp = new int[4]; 
        
        for (int i = n - 1; i >= 0; i--) {
            int take = 0;
            int maxDiff = Integer.MIN_VALUE;
            
           
            for (int k = 0; k < 3 && i + k < n; k++) {
                take += stoneValue[i + k];
                
                int currentDiff = take - dp[(i + k + 1) % 4];
                maxDiff = Math.max(maxDiff, currentDiff);
            }
            dp[i % 4] = maxDiff;
        }
        
        int finalDiff = dp[0];
        if (finalDiff > 0) return "Alice";
        if (finalDiff < 0) return "Bob";
        return "Tie";
    }
}