class Solution {
    public boolean canJump(int[] nums) {
        // Track the farthest index we can currently reach
        int maxReachable = 0;
        
        for (int i = 0; i < nums.length; i++) {
            // If the current index is beyond the maximum reachable index, 
            // it means we are stuck and cannot proceed further.
            if (i > maxReachable) {
                return false;
            }
            
            // Update the farthest index reachable from the current position
            maxReachable = Math.max(maxReachable, i + nums[i]);
            
            // Optimization: If we can already reach or pass the last index, return true
            if (maxReachable >= nums.length - 1) {
                return true;
            }
        }
        
        return true;
    }
}