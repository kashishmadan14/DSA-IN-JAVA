class Solution {
    public int minInsertions(String s) {
        int insertions = 0;
        int openNeeded = 0; // Represents how many ')' are currently needed
        
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            
            if (c == '(') {
                // Each '(' needs two ')'
                openNeeded += 2;
                
                // If we previously needed an odd number of ')', it means we had a single ')' 
                // standing alone without its consecutive pair. We must insert 1 ')' to finish it.
                if (openNeeded % 2 != 0) {
                    insertions++;   // Insert 1 ')' to fix the missing match
                    openNeeded--;   // We fulfilled that missing ')' requirement
                }
            } else { // c == ')'
                openNeeded--; // We found one closing parenthesis
                
                // If openNeeded drops below 0, it means we found a ')' without a matching '('
                if (openNeeded < 0) {
                    insertions++;   // Insert 1 '(' to match this ')'
                    openNeeded += 2; // That new '(' immediately creates a demand for 2 ')'
                                    // Since we are already on a ')', net increase is +1 (-1 + 2)
                }
            }
        }
        
        // After iterating, any remaining openNeeded requirements must be added to the total
        return insertions + openNeeded;
    }
}
