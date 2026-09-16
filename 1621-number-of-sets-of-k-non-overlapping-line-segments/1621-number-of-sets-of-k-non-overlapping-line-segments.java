class Solution {
    public int numberOfSets(int n, int k) {
        long MOD = 1_000_000_007L;
        
        // We need to calculate (n + k - 1) Choose (2 * k)
        int totalObjects = n + k - 1;
        int chooseObjects = 2 * k;
        
        if (chooseObjects > totalObjects) {
            return 0;
        }
        
        return (int) combination(totalObjects, chooseObjects, MOD);
    }
    
    private long combination(int n, int r, long mod) {
        if (r > n - r) {
            r = n - r; // Optimize using symmetry property of nCr
        }
        
        long numerator = 1;
        long denominator = 1;
        
        for (int i = 1; i <= r; i++) {
            numerator = (numerator * (n - i + 1)) % mod;
            denominator = (denominator * i) % mod;
        }
        
        // Result = (numerator * modularInverse(denominator)) % mod
        return (numerator * modularInverse(denominator, mod)) % mod;
    }
    
    // Fermat's Little Theorem to find modular inverse
    private long modularInverse(long n, long mod) {
        return power(n, mod - 2, mod);
    }
    
    private long power(long base, long exp, long mod) {
        long res = 1;
        base = base % mod;
        while (exp > 0) {
            if ((exp & 1) == 1) {
                res = (res * base) % mod;
            }
            base = (base * base) % mod;
            exp >>= 1;
        }
        return res;
    }
}
