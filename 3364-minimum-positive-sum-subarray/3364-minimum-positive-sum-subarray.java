import java.util.List;

class Solution {
    public int minimumSumSubarray(List<Integer> nums, int l, int r) {
        int n = nums.size();
        int minSum = Integer.MAX_VALUE;

        // Fix the starting index of the subarray
        for (int i = 0; i < n; i++) {
            int currentSum = 0;
            
            // Extend the ending index of the subarray
            for (int j = i; j < n; j++) {
                currentSum += nums.get(j);
                int length = j - i + 1;
                
                // Check if the subarray length is within [l, r] and sum is positive
                if (length >= l && length <= r && currentSum > 0) {
                    minSum = Math.min(minSum, currentSum);
                }
            }
        }

        // Return -1 if no valid positive subarray was found
        return minSum == Integer.MAX_VALUE ? -1 : minSum;
    }
}
