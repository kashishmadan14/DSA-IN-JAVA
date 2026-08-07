class Solution {
    public int[] sortedSquares(int[] nums) {
        int n = nums.length;
        int[] result = new int[n];
        int left = 0;
        int right = n - 1;
        int insertPos = n - 1;

        while (left <= right) {
            int leftSquare = nums[left] * nums[left];
            int rightSquare = nums[right] * nums[right];

            if (leftSquare > rightSquare) {
                result[insertPos] = leftSquare;
                left++;
            } else {
                result[insertPos] = rightSquare;
                right--;
            }
            insertPos--;
        }

        return result;
    }
}