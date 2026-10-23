class Solution {
    public int sortPermutation(int[] nums) {
        // Initialize ans to -1, which represents all 1 bits in two's complement.
        // This acts as the identity multiplier for bitwise AND.
        int ans = -1;
        boolean isSorted = true;

        for (int i = 0; i < nums.length; ++i) {
            // Check if the current element is misplaced
            if (nums[i] != i) {
                ans &= nums[i];
                isSorted = false;
            }
        }

        // If the array is already sorted, return 0. Otherwise, return ans.
        return isSorted ? 0 : ans;
    }
}
