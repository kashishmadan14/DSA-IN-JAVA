import java.util.*;

public class Solution {
    public String smallestNumber(String num, long t) {
        // Step 1: Validate prime factors of t
        long temp = t;
        int[] targetPrimes = {2, 3, 5, 7};
        for (int p : targetPrimes) {
            while (temp % p == 0) {
                temp /= p;
            }
        }
        if (temp > 1) return "-1"; // Contains invalid primes like 11, 13, etc.

        int n = num.length();
        
        // Track the greatest common divisor reduction through the prefix
        long[] rem = new long[n + 1];
        rem[0] = t;
        int firstZero = n;
        
        for (int i = 0; i < n; i++) {
            int d = num.charAt(i) - '0';
            if (d == 0) {
                firstZero = i;
                break;
            }
            rem[i + 1] = rem[i] / gcd(rem[i], d);
        }

        // If num is zero-free and its digit product is already divisible by t
        if (firstZero == n && rem[n] == 1) {
            return num;
        }

        // Step 2: Try to find a larger number with the same length using backtracking
        char[] arr = num.toCharArray();
        for (int i = Math.min(n - 1, firstZero); i >= 0; i--) {
            int currentDigit = arr[i] - '0';
            
            for (int d = currentDigit + 1; d <= 9; d++) {
                long requiredT = rem[i] / gcd(rem[i], d);
                StringBuilder suffix = new StringBuilder();
                long curr = requiredT;
                
                // Greedily build the smallest required tail matching remaining factors
                for (int v = 9; v >= 2; v--) {
                    while (curr % v == 0) {
                        suffix.append(v);
                        curr /= v;
                    }
                }
                
                // If factors fit within the remaining suffix space
                if (suffix.length() <= n - 1 - i) {
                    StringBuilder result = new StringBuilder();
                    // Append unchanged prefix
                    for (int j = 0; j < i; j++) {
                        result.append(arr[j]);
                    }
                    // Append the incremented digit
                    result.append(d);
                    // Pad with '1's to fill empty middle spots
                    int onesNeeded = (n - 1 - i) - suffix.length();
                    for (int o = 0; o < onesNeeded; o++) {
                        result.append('1');
                    }
                    // Append suffix digits in ascending order
                    result.append(suffix.reverse());
                    return result.toString();
                }
            }
        }

        // Step 3: Same length impossible, scale up to length n + 1 or what factors dictate
        StringBuilder suffix = new StringBuilder();
        long curr = t;
        for (int v = 9; v >= 2; v--) {
            while (curr % v == 0) {
                suffix.append(v);
                curr /= v;
            }
        }
        
        int reqLen = Math.max(n + 1, suffix.length());
        StringBuilder result = new StringBuilder();
        int onesNeeded = reqLen - suffix.length();
        for (int i = 0; i < onesNeeded; i++) {
            result.append('1');
        }
        result.append(suffix.reverse());
        return result.toString();
    }

    private long gcd(long a, long b) {
        while (b != 0) {
            long temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }
}
