import java.util.Arrays;

class Solution {
    public long maxAlternatingSum(int[] nums) {
        int n = nums.length;
        long[] squaredNums = new long[n];
        
        for (int i = 0; i < n; ++i) {
            squaredNums[i] = (long) nums[i] * nums[i];
        }
    
        Arrays.sort(squaredNums);
        
        long maxScore = 0;
        int half = n / 2;
        
        
        for (int i = 0; i < half; ++i) {
            maxScore -= squaredNums[i];
        }
   
        for (int i = half; i < n; ++i) {
            maxScore += squaredNums[i];
        }
        
        return maxScore;
    }
}
