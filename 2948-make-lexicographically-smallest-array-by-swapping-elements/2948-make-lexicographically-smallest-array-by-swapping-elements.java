import java.util.*;

class Solution {
    public int[] lexicographicallySmallestArray(int[] nums, int limit) {
        int n = nums.length;
        
        // Step 1: Pair each element with its original index and sort by value
        int[][] sortedPairs = new int[n][2];
        for (int i = 0; i < n; i++) {
            sortedPairs[i][0] = nums[i];
            sortedPairs[i][1] = i;
        }
        
        // Sort based on the element values
        Arrays.sort(sortedPairs, (a, b) -> Integer.compare(a[0], b[0]));
        
        // Step 2: Group elements using a List of Queues
        List<Queue<Integer>> groups = new ArrayList<>();
        // Map to quickly find which group index an element's original index belongs to
        int[] groupMapping = new int[n];
        
        int groupIndex = 0;
        // Create the first group and add the smallest element value
        groups.add(new LinkedList<>());
        groups.get(groupIndex).offer(sortedPairs[0][0]);
        groupMapping[sortedPairs[0][1]] = groupIndex;
        
        for (int i = 1; i < n; i++) {
            // If the difference exceeds the limit, create a new connected component/group
            if (sortedPairs[i][0] - sortedPairs[i - 1][0] > limit) {
                groupIndex++;
                groups.add(new LinkedList<>());
            }
            // Add the value to the current sorted group queue
            groups.get(groupIndex).offer(sortedPairs[i][0]);
            // Map the original index to this group identifier
            groupMapping[sortedPairs[i][1]] = groupIndex;
        }
        
        // Step 3: Reconstruct the answer array
        int[] result = new int[n];
        for (int i = 0; i < n; i++) {
            int gIdx = groupMapping[i];
            // Poll the smallest remaining element for this group
            result[i] = groups.get(gIdx).poll();
        }
        
        return result;
    }
}

