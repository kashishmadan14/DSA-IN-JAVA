public class Solution {
    public List<Integer> findMissingElements(int[] nums) {
        List<Integer> result = new ArrayList<>();
        Set<Integer> set = new HashSet<>();
        
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        
        // Find boundaries and collect existing elements
        for (int num : nums) {
            set.add(num);
            if (num < min) min = num;
            if (num > max) max = num;
        }
        
        // Identify missing elements within the range
        for (int i = min; i <= max; i++) {
            if (!set.contains(i)) {
                result.add(i);
            }
        }
        
        return result;
    }
}