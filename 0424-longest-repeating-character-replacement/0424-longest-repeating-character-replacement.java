public class Solution {
    public int characterReplacement(String s, int k) {
        int[] counts = new int[26];
        int left = 0;
        int maxFreq = 0;
        int maxLength = 0;

        for (int right = 0; right < s.length(); right++) {
          
            counts[s.charAt(right) - 'A']++;
           
            maxFreq = Math.max(maxFreq, counts[s.charAt(right) - 'A']);

            int windowSize = right - left + 1;
            if (windowSize - maxFreq > k) {
                counts[s.charAt(left) - 'A']--;
                left++;
            }

            maxLength = Math.max(maxLength, right - left + 1);
        }

        return maxLength;
    }
}