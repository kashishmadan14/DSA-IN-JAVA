import java.util.ArrayList;
import java.util.List;

class Solution {
    public List<Integer> targetIndices(int[] nums, int target) {
        int countLessThanTarget = 0;
        int countEqualsTarget = 0;
        
        // Single pass to gather counts
        for (int num : nums) {
            if (num < target) {
                countLessThanTarget++;
            } else if (num == target) {
                countEqualsTarget++;
            }
        }
        
        List<Integer> result = new ArrayList<>();
        // Populate the sequential indices where target resides
        for (int i = 0; i < countEqualsTarget; i++) {
            result.add(countLessThanTarget + i);
        }
        
        return result;
    }
}
