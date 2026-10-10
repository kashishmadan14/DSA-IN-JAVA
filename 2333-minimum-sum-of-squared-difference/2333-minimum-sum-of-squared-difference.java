class Solution {
    public long minSumSquareDiff(int[] nums1, int[] nums2, int k1, int k2) {
        int n = nums1.length;
        long k = (long) k1 + k2;
        
        // Bucket array to store the frequency of each difference value
        // Maximum difference can be at most 100,000 based on problem constraints
        int[] bucket = new int[100001];
        long totalDiff = 0;
        
        for (int i = 0; i < n; i++) {
            int diff = Math.abs(nums1[i] - nums2[i]);
            bucket[diff]++;
            totalDiff += diff;
        }
        
        // If our total budget can reduce all differences to 0, return 0
        if (totalDiff <= k) {
            return 0;
        }
        
        // Greedily reduce the largest differences first
        for (int i = 100000; i > 0; i--) {
            if (bucket[i] == 0) continue;
            
            // Count how many elements have the current maximum difference 'i'
            long count = bucket[i];
            
            // If we have enough operations to reduce ALL elements of value 'i' to 'i - 1'
            if (k >= count) {
                k -= count;
                bucket[i - 1] += count;
                bucket[i] = 0;
            } else {
                // If we can only reduce a portion of them
                bucket[i - 1] += k;
                bucket[i] -= k;
                k = 0; // Budget exhausted
                break;
            }
        }
        
        // Calculate the final sum of squared differences
        long minSumSquare = 0;
        for (int i = 1; i <= 100000; i++) {
            if (bucket[i] > 0) {
                minSumSquare += (long) bucket[i] * i * i;
            }
        }
        
        return minSumSquare;
    }
}
