class Solution {
    public String digitSum(String s, int k) {
        // Keep processing rounds until the string length is at most k
        while (s.length() > k) {
            StringBuilder nextString = new StringBuilder();
            
            // Step through the string in chunks of size k
            for (int i = 0; i < s.length(); i += k) {
                int groupSum = 0;
                
                // Sum the digits within the current chunk
                for (int j = i; j < Math.min(i + k, s.length()); j++) {
                    groupSum += s.charAt(j) - '0';
                }
                
                // Append the sum of this chunk to the new string
                nextString.append(groupSum);
            }
            
            // Update s for the next round
            s = nextString.toString();
        }
        
        return s;
    }
}
