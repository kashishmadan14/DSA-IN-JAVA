class Solution {
    public int[] validSequence(String word1, String word2) {
        int n = word1.length();
        int m = word2.length();
        
        int[] lastMatch = new int[m];
        Arrays.fill(lastMatch, -1);
        
        int j = m - 1;
        for (int i = n - 1; i >= 0; i--) {
            if (j >= 0 && word1.charAt(i) == word2.charAt(j)) {
                lastMatch[j] = i;
                j--;
            }
        }
        
        int[] result = new int[m];
        int w2Idx = 0;
        boolean changed = false; 
        
        for (int i = 0; i < n; i++) {
            if (w2Idx == m) break;
            
            
            if (word1.charAt(i) == word2.charAt(w2Idx)) {
                result[w2Idx] = i;
                w2Idx++;
            } 
     
            else if (!changed) {
                
                if (w2Idx + 1 == m || i < lastMatch[w2Idx + 1]) {
                    result[w2Idx] = i;
                    w2Idx++;
                    changed = true; 
                }
            }
        }
       
        return w2Idx == m ? result : new int[0];
    }
}