class Solution {
    public String minWindow(String s, String t) {
        if (s == null || t == null || s.length() < t.length()) {
            return "";
        }

        int[] targetMap = new int[128];
        for (char c : t.toCharArray()) {
            targetMap[c]++;
        }

        int left = 0;
        int right = 0;
        int minLength = Integer.MAX_VALUE;
        int minStart = 0;
        int requiredCount = t.length();

        while (right < s.length()) {
            char rightChar = s.charAt(right);
        
            if (targetMap[rightChar] > 0) {
                requiredCount--;
            }
            targetMap[rightChar]--;
            right++;

            while (requiredCount == 0) {
  
                if (right - left < minLength) {
                    minLength = right - left;
                    minStart = left;
                }

                char leftChar = s.charAt(left);
              
                targetMap[leftChar]++;
                
                if (targetMap[leftChar] > 0) {
                    requiredCount++;
                }
                left++;
            }
        }

        return minLength == Integer.MAX_VALUE ? "" : s.substring(minStart, minStart + minLength);
    }
}