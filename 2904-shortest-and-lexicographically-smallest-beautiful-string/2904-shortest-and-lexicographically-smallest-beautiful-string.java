class Solution {
    public String shortestBeautifulSubstring(String s, int k) {
        int n = s.length();
        String result = "";
        int minLength = n + 1;
        int countOnes = 0;
        int left = 0;

        for (int right = 0; right < n; right++) {
            if (s.charAt(right) == '1') {
                countOnes++;
            }

            
            while (countOnes == k) {
                
                if (s.charAt(left) == '1') {
                    int currentLength = right - left + 1;
                    String currentStr = s.substring(left, right + 1);

              
                    if (currentLength < minLength) {
                        minLength = currentLength;
                        result = currentStr;
                    } else if (currentLength == minLength && currentStr.compareTo(result) < 0) {
                        result = currentStr;
                    }

                
                    countOnes--;
                }
                left++;
            }
        }

        return result;
    }
}
