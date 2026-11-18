class Solution {
    public int minChanges(String s) {
        int changes = 0;
        
        // Step through the string in pairs (indices 0&1, 2&3, 4&5, ...)
        for (int i = 0; i < s.length(); i += 2) {
            if (s.charAt(i) != s.charAt(i + 1)) {
                changes++;
            }
        }
        
        return changes;
    }
}
