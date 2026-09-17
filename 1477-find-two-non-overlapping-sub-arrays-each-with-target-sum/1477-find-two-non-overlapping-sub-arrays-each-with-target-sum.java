import java.util.Arrays;

public class Solution {
    public int minSumOfLengths(int[] arr, int target) {
        int n = arr.length;
        int[] best = new int[n];
        Arrays.fill(best, Integer.MAX_VALUE);
        
        int ans = Integer.MAX_VALUE;
        int sum = 0;
        int l = 0;
        
        for (int r = 0; r < n; r++) {
            sum += arr[r];
          
            while (sum > target) {
                sum -= arr[l];
                l++;
            }
     
            if (sum == target) {
                int currentLength = r - l + 1;
                
                if (l > 0 && best[l - 1] != Integer.MAX_VALUE) {
                    ans = Math.min(ans, best[l - 1] + currentLength);
                }
           
                if (r > 0) {
                    best[r] = Math.min(best[r - 1], currentLength);
                } else {
                    best[r] = currentLength;
                }
            } else {
                
                if (r > 0) {
                    best[r] = best[r - 1];
                }
            }
        }
        
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }
}
