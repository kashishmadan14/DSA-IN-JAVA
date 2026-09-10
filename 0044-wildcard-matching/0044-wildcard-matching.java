public class Solution {
    public boolean isMatch(String s, String p) {
        int sPtr = 0, pPtr = 0;
        int starIdx = -1, matchIdx = -1;
        
        while (sPtr < s.length()) {
            if (pPtr < p.length() && (p.charAt(pPtr) == '?' || p.charAt(pPtr) == s.charAt(sPtr))) {
                sPtr++;
                pPtr++;
            } 

            else if (pPtr < p.length() && p.charAt(pPtr) == '*') {
                starIdx = pPtr;
                matchIdx = sPtr;
                pPtr++; 
            } 

            else if (starIdx != -1) {
                pPtr = starIdx + 1; 
                matchIdx++;         
                sPtr = matchIdx;    
            } 
        
            else {
                return false;
            }
        }
        
        while (pPtr < p.length() && p.charAt(pPtr) == '*') {
            pPtr++;
        }
        
        return pPtr == p.length();
    }
}
