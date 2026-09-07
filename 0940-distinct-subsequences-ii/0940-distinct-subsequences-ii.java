class Solution {
    public int distinctSubseqII(String s) {
        int MOD = 1000000007;
        int n = s.length();
        
        // dp[i] stores the total number of distinct subsequences using the first i characters
        // This includes the empty subsequence ""
        int[] dp = new int[n + 1];
        dp[0] = 1; // Base case: the empty string ""
        
        // last[ch] stores the 1-based DP index of the last occurrence of character ch
        int[] last = new int[26];
        java.util.Arrays.fill(last, -1);
        
        for (int i = 1; i <= n; i++) {
            char ch = s.charAt(i - 1);
            int charIdx = ch - 'a';
            
            // If we append the current character to all previous subsequences, 
            // the number of options initially doubles.
            dp[i] = (dp[i - 1] * 2) % MOD;
            
            // If this character appeared before, we have generated duplicate subsequences.
            // We subtract the number of distinct subsequences that existed right before 
            // the previous occurrence of this character.
            if (last[charIdx] != -1) {
                int prevIndex = last[charIdx];
                dp[i] = (dp[i] - dp[prevIndex - 1] + MOD) % MOD;
            }
            
            // Update the last seen position of the character
            last[charIdx] = i;
        }
        
        // Subtract 1 to exclude the empty subsequence from our final answer
        return (dp[n] - 1 + MOD) % MOD;
    }
}
