class Solution {
    public int firstStableIndex(int[] nums, int k) {
        int n = nums.length;

        int[] suffixMin = new int[n];

        suffixMin[n - 1] = nums[n - 1];

        for (int i = n - 2; i >= 0; i--) {
            suffixMin[i] = Math.min(suffixMin[i + 1], nums[i]);
        }

        int prefixMax = Integer.MIN_VALUE;
        int minIdx = Integer.MAX_VALUE;

        for (int i = 0; i < n; i++) {
            prefixMax = Math.max(prefixMax, nums[i]);

            int score = prefixMax - suffixMin[i];

            if (score <= k) {
                minIdx = Math.min(minIdx, i);
            }
        }

        return minIdx == Integer.MAX_VALUE ? -1 : minIdx;
    }
}