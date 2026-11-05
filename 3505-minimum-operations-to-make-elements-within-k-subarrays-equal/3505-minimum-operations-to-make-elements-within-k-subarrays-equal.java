import java.util.*;

class Solution {
    // Utility custom Balanced Map structure to track median elements & running sums
    private static class BalancedMultiSet {
        private final TreeMap<Integer, Integer> left = new TreeMap<>();
        private final TreeMap<Integer, Integer> right = new TreeMap<>();
        private int leftSize = 0;
        private int rightSize = 0;
        private long leftSum = 0;
        private long rightSum = 0;
        private final int totalCapacity;
        private final int leftCapacity;

        public BalancedMultiSet(int x) {
            this.totalCapacity = x;
            this.leftCapacity = (x + 1) / 2;
        }

        public void add(int val) {
            if (leftSize < leftCapacity || val <= left.lastKey()) {
                left.put(val, left.getOrDefault(val, 0) + 1);
                leftSum += val;
                leftSize++;
            } else {
                right.put(val, right.getOrDefault(val, 0) + 1);
                rightSum += val;
                rightSize++;
            }
            balance();
        }

        public void remove(int val) {
            if (left.containsKey(val)) {
                left.put(val, left.get(val) - 1);
                if (left.get(val) == 0) left.remove(val);
                leftSum -= val;
                leftSize--;
            } else {
                right.put(val, right.get(val) - 1);
                if (right.get(val) == 0) right.remove(val);
                rightSum -= val;
                rightSize--;
            }
            balance();
        }

        private void balance() {
            // Shift elements from left to right if left exceeds its capacity
            while (leftSize > leftCapacity) {
                int maxLeft = left.lastKey();
                removeValueFromMap(left, maxLeft);
                leftSum -= maxLeft;
                leftSize--;
                
                right.put(maxLeft, right.getOrDefault(maxLeft, 0) + 1);
                rightSum += maxLeft;
                rightSize++;
            }
            // Shift elements from right to left if left needs more elements
            while (leftSize < leftCapacity && rightSize > 0) {
                int minRight = right.firstKey();
                removeValueFromMap(right, minRight);
                rightSum -= minRight;
                rightSize--;
                
                left.put(minRight, left.getOrDefault(minRight, 0) + 1);
                leftSum += minRight;
                leftSize++;
            }
        }

        private void removeValueFromMap(TreeMap<Integer, Integer> map, int val) {
            map.put(val, map.get(val) - 1);
            if (map.get(val) == 0) map.remove(val);
        }

        public long getCost() {
            long median = left.lastKey();
            // Total operations = cost to bring left up to median + cost to bring right down to median
            long cost = (median * leftSize - leftSum) + (rightSum - median * rightSize);
            return cost;
        }
    }

    public long minOperations(int[] nums, int x, int k) {
        int n = nums.length;
        long[] minOps = new long[n];
        Arrays.fill(minOps, Long.MAX_VALUE);

        // Phase 1: Sliding Window Precomputations
        BalancedMultiSet window = new BalancedMultiSet(x);
        for (int i = 0; i < n; i++) {
            window.add(nums[i]);
            if (i >= x - 1) {
                minOps[i - x + 1] = window.getCost();
                window.remove(nums[i - x + 1]);
            }
        }

        // Phase 2: Dynamic Programming to choose k non-overlapping intervals
        // dp[i][j] = minimum cost using elements starting from index i onwards, needing j intervals
        long[][] dp = new long[n + 1][k + 1];
        for (int i = 0; i <= n; i++) {
            Arrays.fill(dp[i], Long.MAX_VALUE / 2); // Avoid overflow
        }
        
        // Base Case: 0 intervals left requires 0 operations
        for (int i = 0; i <= n; i++) {
            dp[i][0] = 0;
        }

        // Fill bottom-up DP
        for (int j = 1; j <= k; j++) {
            for (int i = n - x; i >= 0; i--) {
                // Choice 1: Skip the current index
                long skip = dp[i + 1][j];
                
                // Choice 2: Pick the current window of size x
                long pick = minOps[i] + dp[i + x][j - 1];
                
                dp[i][j] = Math.min(skip, pick);
            }
        }

        return dp[0][k];
    }
}
