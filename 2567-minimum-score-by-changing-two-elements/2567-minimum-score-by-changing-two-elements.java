import java.util.Arrays;

class Solution {
    public int minimizeSum(int[] nums) {
        int n = nums.length;
        
        // If there are 3 or fewer elements, changing 2 makes all elements equal
        if (n <= 3) {
            return 0;
        }
        
        // Sort the array to easily access the smallest and largest extremes
        Arrays.sort(nums);
        
        // Case 1: Change the two smallest elements
        int option1 = nums[n - 1] - nums[2];
        
        // Case 2: Change the smallest and the largest element
        int option2 = nums[n - 2] - nums[1];
        
        // Case 3: Change the two largest elements
        int option3 = nums[n - 3] - nums[0];
        
        return Math.min(option1, Math.min(option2, option3));
    }
}
