class Solution {
    public int maxLength(int[] nums) {
        int n = nums.length;
        int maxVal = 0;
        int maxLcm = 1;

        // Calculate the upper bound for any valid product
        for (int num : nums) {
            maxVal = Math.max(maxVal, num);
            maxLcm = lcm(maxLcm, num);
        }
        int maxAllowedProd = maxLcm * maxVal;

        int ans = 0;

        for (int i = 0; i < n; i++) {
            int prod = 1;
            int g = 0;
            int l = 1;

            for (int j = i; j < n; j++) {
                prod *= nums[j];
                
                // Prune early: product grows strictly faster than lcm * gcd
                if (prod > maxAllowedProd) {
                    break;
                }

                g = gcd(g, nums[j]);
                l = lcm(l, nums[j]);

                if (prod == l * g) {
                    ans = Math.max(ans, j - i + 1);
                }
            }
        }

        return ans;
    }

    private int gcd(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }

    private int lcm(int a, int b) {
        return (a / gcd(a, b)) * b;
    }
}
