class Solution {
    public String countAndSay(int n) {
        // Base case for n = 1
        StringBuilder currentString = new StringBuilder("1");
        
        // Generate the sequence iteratively up to the nth term
        while (--n > 0) {
            StringBuilder nextString = new StringBuilder();
            int length = currentString.length();
            
            for (int i = 0; i < length; i++) {
                int count = 1;
                
                // Count consecutive identical characters
                while (i + 1 < length && currentString.charAt(i) == currentString.charAt(i + 1)) {
                    count++;
                    i++;
                }
                
                // Append frequency followed by the character itself
                nextString.append(count).append(currentString.charAt(i));
            }
            
            // Move to the next sequence item
            currentString = nextString;
        }
        
        return currentString.toString();
    }
}
