import java.util.HashSet;
import java.util.Set;

class Solution {
    public int missingMultiple(int[] nums, int k) {
        
        Set<Integer> existingNumbers = new HashSet<>();
        for (int num : nums) {
            existingNumbers.add(num);
        }
        
        int multiplier = 1;
        while (true) {
            int currentMultiple = k * multiplier;
        
            if (!existingNumbers.contains(currentMultiple)) {
                return currentMultiple;
            }
            multiplier++;
        }
    }
}
