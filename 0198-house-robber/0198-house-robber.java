class Solution {

    public int solve(int n, int[] nums, int[] dp, int size) {

        // Base case
        if (n >= size) {
            return 0;
        }

        // Already calculated
        if (dp[n] != -1) {
            return dp[n];
        }

        // Don't pick current house
        int notPick = solve(n + 1, nums, dp, size);

        // Pick current house
        int pick = nums[n] + solve(n + 2, nums, dp, size);

        // Store and return maximum
        dp[n] = Math.max(pick, notPick);

        return dp[n];
    }

    public int rob(int[] nums) {
        int n = nums.length;

        int[] dp = new int[n + 1];
        Arrays.fill(dp, -1);

        return solve(0, nums, dp, n);
    }
}