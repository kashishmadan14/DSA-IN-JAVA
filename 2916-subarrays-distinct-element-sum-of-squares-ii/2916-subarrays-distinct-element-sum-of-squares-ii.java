class Solution {
    static final int MOD = 1_000_000_007;

    long[] sum, sqSum, lazy;
    int n;

    public int sumCounts(int[] nums) {
        n = nums.length;
        sum = new long[4 * n];
        sqSum = new long[4 * n];
        lazy = new long[4 * n];

        java.util.Map<Integer, Integer> last = new java.util.HashMap<>();
        long ans = 0;

        for (int i = 0; i < n; i++) {
            int prev = last.getOrDefault(nums[i], -1);

            update(1, 0, n - 1, prev + 1, i);

            ans = (ans + sqSum[1]) % MOD;
            last.put(nums[i], i);
        }

        return (int) ans;
    }

    void apply(int node, int l, int r, long val) {
        long len = r - l + 1L;

        sqSum[node] = (
            sqSum[node]
            + 2L * val % MOD * sum[node] % MOD
            + val * val % MOD * len % MOD
        ) % MOD;

        sum[node] = (sum[node] + val * len) % MOD;
        lazy[node] = (lazy[node] + val) % MOD;
    }

    void pushDown(int node, int l, int r) {
        if (lazy[node] == 0 || l == r) return;

        int mid = l + (r - l) / 2;

        apply(node * 2, l, mid, lazy[node]);
        apply(node * 2 + 1, mid + 1, r, lazy[node]);

        lazy[node] = 0;
    }

    void update(int node, int l, int r, int ql, int qr) {
        if (ql > r || qr < l) return;

        if (ql <= l && r <= qr) {
            apply(node, l, r, 1);
            return;
        }

        pushDown(node, l, r);

        int mid = l + (r - l) / 2;

        update(node * 2, l, mid, ql, qr);
        update(node * 2 + 1, mid + 1, r, ql, qr);

        sum[node] = (sum[node * 2] + sum[node * 2 + 1]) % MOD;
        sqSum[node] = (sqSum[node * 2] + sqSum[node * 2 + 1]) % MOD;
    }
}