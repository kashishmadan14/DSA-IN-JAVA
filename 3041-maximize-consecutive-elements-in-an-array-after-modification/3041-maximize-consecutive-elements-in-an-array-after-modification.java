import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

class Solution {
    public int maxSelectedElements(int[] nums) {
        Arrays.sort(nums);
        Map<Integer, Integer> dp = new HashMap<>();
        int maxLen = 0;

        for (int num : nums) {
            // 1. Option: Increase num by 1 (extends sequence ending at num)
            dp.put(num + 1, dp.getOrDefault(num, 0) + 1);
            
            // 2. Option: Keep num unchanged (extends sequence ending at num - 1)
            dp.put(num, dp.getOrDefault(num - 1, 0) + 1);

            maxLen = Math.max(maxLen, Math.max(dp.get(num), dp.get(num + 1)));
        }

        return maxLen;
    }
}
