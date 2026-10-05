class Solution {
    public int scoreOfParentheses(String s) {
        int totalScore = 0;
        int depth = 0;
        
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                // Moving inside a deeper layer
                depth++;
            } else {
                // Stepping out of a layer
                depth--;
                
                // If the previous character was '(', we found a core "()" primitive
                if (s.charAt(i - 1) == '(') {
                    // 1 << depth is equivalent to 2^depth
                    totalScore += 1 << depth;
                }
            }
        }
        
        return totalScore;
    }
}
