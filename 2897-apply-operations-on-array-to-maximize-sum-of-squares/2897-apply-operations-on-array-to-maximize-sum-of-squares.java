import java.util.List;

class Solution {
    public int maxSum(List<Integer> nums, int k) {
        final int MOD = 1_000_000_007;
        // Since max value of nums[i] is up to 10^9, 31 bits are sufficient
        int[] bitCounts = new int[31];
        
        // Step 1: Count total set bits at each position across all numbers
        for (int num : nums) {
            for (int i = 0; i < 31; i++) {
                if (((num >> i) & 1) == 1) {
                    bitCounts[i]++;
                }
            }
        }
        
        long totalSumOfSquares = 0;
        
        // Step 2: Greedily construct the largest possible k numbers
        for (int i = 0; i < k; i++) {
            long currentNumber = 0;
            for (int bit = 0; bit < 31; bit++) {
                // If there is a bit available at this position, allocate it to the current number
                if (bitCounts[bit] > 0) {
                    currentNumber |= (1L << bit);
                    bitCounts[bit]--; // Consume the bit
                }
            }
            // Step 3: Add the square of the constructed number to the total sum
            long squared = (currentNumber * currentNumber) % MOD;
            totalSumOfSquares = (totalSumOfSquares + squared) % MOD;
        }
        
        return (int) totalSumOfSquares;
    }
}
