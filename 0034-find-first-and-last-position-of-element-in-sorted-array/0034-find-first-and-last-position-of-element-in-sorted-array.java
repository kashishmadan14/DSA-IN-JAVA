class Solution {
    public int[] searchRange(int[] nums, int target) {
        int[] result = new int[2];
        result[0] = findPosition(nums, target, true);  // Find first position
        result[1] = findPosition(nums, target, false); // Find last position
        return result;
    }

    private int findPosition(int[] nums, int target, boolean findFirst) {
        int index = -1;
        int start = 0;
        int end = nums.length - 1;

        while (start <= end) {
            int mid = start + (end - start) / 2;

            if (nums[mid] == target) {
                index = mid; // Record potential answer
                if (findFirst) {
                    end = mid - 1; // Keep searching left for the first occurrence
                } else {
                    start = mid + 1; // Keep searching right for the last occurrence
                }
            } else if (nums[mid] < target) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }
        return index;
    }
}