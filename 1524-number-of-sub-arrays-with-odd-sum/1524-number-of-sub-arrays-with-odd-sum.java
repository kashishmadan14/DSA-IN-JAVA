class Solution {
    public int numOfSubarrays(int[] arr) {
        final int MOD = 1_000_000_007;
        long oddCount = 0;
        long evenCount = 1; // Starts at 1 to account for an empty prefix sum of 0 (even)
        long currentSum = 0;
        long totalSubarrays = 0;

        for (int num : arr) {
            currentSum += num;

            if (currentSum % 2 != 0) {
                // Current prefix sum is odd; pair it with previous even prefix sums
                totalSubarrays = (totalSubarrays + evenCount) % MOD;
                oddCount++;
            } else {
                // Current prefix sum is even; pair it with previous odd prefix sums
                totalSubarrays = (totalSubarrays + oddCount) % MOD;
                evenCount++;
            }
        }

        return (int) totalSubarrays;
    }
}
