class Solution {
    public int sumOfSquares(int[] nums) {
        final int n = nums.length;
        int sumOfSquares = 0;
        
        for (int i = 0; i < n; ++i) {
            
            if (n % (i + 1) == 0) {
                sumOfSquares += nums[i] * nums[i];
            }
        }
        
        return sumOfSquares;
    }
}
