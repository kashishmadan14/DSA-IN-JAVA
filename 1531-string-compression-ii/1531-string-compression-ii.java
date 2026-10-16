import java.util.Arrays;

public class Solution {
   
    private Integer[][][][] memo;

    public int getLengthOfOptimalCompression(String s, int k) {
        int n = s.length();
      
        memo = new Integer[n][k + 1][27][n + 1];
        return dp(s, 0, k, (char) ('a' + 26), 0);
    }

    private int dp(String s, int i, int k, char prev, int count) {
       
        if (k < 0) return 999999; 
        
        if (i == s.length()) return 0;
        
        
        int prevIdx = prev - 'a';
        
       
        if (memo[i][k][prevIdx][count] != null) {
            return memo[i][k][prevIdx][count];
        }

        int res = dp(s, i + 1, k - 1, prev, count);

        if (s.charAt(i) == prev) {
            
            int incr = 0;
            if (count == 1 || count == 9 || count == 99) {
                incr = 1;
            }
            res = Math.min(res, incr + dp(s, i + 1, k, prev, count + 1));
        } else {
           
            res = Math.min(res, 1 + dp(s, i + 1, k, s.charAt(i), 1));
        }

        return memo[i][k][prevIdx][count] = res;
    }
}
