import java.util.ArrayList;
import java.util.List;

public class Solution {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        backtrack(result, new ArrayList<>(), candidates, target, 0);
        return result;
    }

    private void backtrack(List<List<Integer>> result, List<Integer> tempList, 
                           int[] candidates, int remain, int start) {
        if (remain < 0) {
            return; // Base case: target exceeded
        } else if (remain == 0) {
            result.add(new ArrayList<>(tempList)); // Base case: target found
        } else {
            for (int i = start; i < candidates.length; i++) {
                tempList.add(candidates[i]);
                // Stay at index 'i' since we can reuse the same element
                backtrack(result, tempList, candidates, remain - candidates[i], i); 
                tempList.remove(tempList.size() - 1); // Backtrack
            }
        }
    }
}