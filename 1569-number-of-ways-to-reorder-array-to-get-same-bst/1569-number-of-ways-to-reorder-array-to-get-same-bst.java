import java.util.ArrayList;
import java.util.List;

public class Solution {
    private static final int MOD = 1_000_000_007;
    private int[][] table;

    public int numOfWays(int[] nums) {
        int n = nums.length;
        
        // Step 1: Precompute Pascal's Triangle for Combinations C(n, k)
        table = new int[n + 1][n + 1];
        for (int i = 0; i <= n; i++) {
            table[i][0] = 1;
            for (int j = 1; j <= i; j++) {
                table[i][j] = (table[i - 1][j - 1] + table[i - 1][j]) % MOD;
            }
        }

        // Convert array to List for easier sub-list partitioning
        List<Integer> list = new ArrayList<>();
        for (int num : nums) {
            list.add(num);
        }

        // Step 2: Perform DFS and subtract 1 to exclude the original ordering
        return (dfs(list) - 1 + MOD) % MOD;
    }

    private int dfs(List<Integer> nums) {
        // Base case: If the list has 2 or fewer elements, there's only 1 way to arrange them.
        if (nums.size() <= 2) {
            return 1;
        }

        int root = nums.get(0);
        List<Integer> left = new ArrayList<>();
        List<Integer> right = new ArrayList<>();

        // Partition remaining elements into left and right subtrees
        for (int i = 1; i < nums.size(); i++) {
            int current = nums.get(i);
            if (current < root) {
                left.add(current);
            } else {
                right.add(current);
            }
        }

        // Recursive calls for left and right subtrees
        long leftWays = dfs(left);
        long rightWays = dfs(right);

        // Interleave the left and right elements using precomputed combinations
        long totalWays = table[left.size() + right.size()][left.size()];

        // Combine results using modular multiplication
        return (int) (((totalWays * leftWays) % MOD * rightWays) % MOD);
    }
}
