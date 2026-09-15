class Solution {
    public int maxPalindromes(String s, int k) {
        int n = s.length();
        int count = 0;
        int lastEnded = -1; 

        for (int i = 0; i < n; i++) {
          
            int l1 = i - k / 2;
            int r1 = i + k / 2 - (k % 2 == 0 ? 1 : 0);
            
            int l2 = i - (k + 1) / 2;
            int r2 = i + (k + 1) / 2 - ((k + 1) % 2 == 0 ? 1 : 0);

            if (isValidPalindrome(s, l1, r1, lastEnded)) {
                count++;
                lastEnded = r1;
            } 
           
            else if (isValidPalindrome(s, l2, r2, lastEnded)) {
                count++;
                lastEnded = r2;
            }
        }

        return count;
    }

    private boolean isValidPalindrome(String s, int l, int r, int lastEnded) {
      
        if (l <= lastEnded || r >= s.length()) {
            return false;
        }
        
        while (l < r) {
            if (s.charAt(l) != s.charAt(r)) {
                return false;
            }
            l++;
            r--;
        }
        return true;
    }
}
