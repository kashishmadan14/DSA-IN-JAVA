import java.util.ArrayList;
import java.util.List;

public class Solution {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        backtrack(result, new ArrayList<>(), nums, new boolean[nums.length]);
        return result;
    }

    private void backtrack(List<List<Integer>> result, List<Integer> tempList, int[] nums, boolean[] used) {
        // Base case: If the current permutation is complete
        if (tempList.size() == nums.length) {
            result.add(new ArrayList<>(tempList)); // Append a copy of the list
            return;
        }

        // Recursive case: Try every number for the current position
        for (int i = 0; i < nums.length; i++) {
            if (used[i]) continue; // Skip elements that are already in the current path

            // Take element
            used[i] = true;
            tempList.add(nums[i]);

            // Recurse to build the next positions
            backtrack(result, tempList, nums, used);

            // Backtrack (Undo choice)
            used[i] = false;
            tempList.remove(tempList.size() - 1);
        }
    }
}
