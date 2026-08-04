import java.util.HashSet;

class Solution {
    public boolean containsDuplicate(int[] nums) {
        HashSet<Integer> seen = new HashSet<>();
        for (int num : nums) {
            // If the number is already in the set, we found a duplicate
            if (!seen.add(num)) {
                return true;
            }
        }
        return false;
    }
}