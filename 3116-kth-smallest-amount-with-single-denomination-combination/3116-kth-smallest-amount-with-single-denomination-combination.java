class Solution {
    private int[] coins;
    private int targetK;

    public long findKthSmallest(int[] coins, int k) {
        this.coins = coins;
        this.targetK = k;
        long left = 1, right = (long) 1e11;
        
        while (left < right) {
            long mid = (left + right) >> 1;
            if (check(mid)) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    private boolean check(long mx) {
        long count = 0;
        int n = coins.length;
        
        for (int i = 1; i < (1 << n); ++i) {
            long lcmVal = 1;
            for (int j = 0; j < n; ++j) {
                if ((i >> j & 1) == 1) {
                    lcmVal = lcm(lcmVal, coins[j]);
                    if (lcmVal > mx) {
                        break;
                    }
                }
            }
            if (lcmVal > mx) continue;
            
            int bits = Integer.bitCount(i);
            if ((bits & 1) == 1) {
                count += mx / lcmVal;
            } else {
                count -= mx / lcmVal;
            }
        }
        return count >= targetK;
    }

    private long gcd(long a, long b) {
        return b == 0 ? a : gcd(b, a % b);
    }

    private long lcm(long a, long b) {
        return (a / gcd(a, b)) * b;
    }
}
