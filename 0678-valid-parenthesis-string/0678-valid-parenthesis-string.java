class Solution {
    public boolean checkValidString(String s) {
        // minOpen tracks the minimum possible open '(' remaining
        // maxOpen tracks the maximum possible open '(' remaining
        int minOpen = 0;
        int maxOpen = 0;

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if (c == '(') {
                minOpen++;
                maxOpen++;
            } else if (c == ')') {
                minOpen--;
                maxOpen--;
            } else { // c == '*'
                // '*' can be ')', empty string "", or '('
                minOpen--; // If treated as ')'
                maxOpen++; // If treated as '('
            }

            // More closing parentheses than possible opening ones/asterisks
            if (maxOpen < 0) {
                return false;
            }

            // minOpen cannot drop below 0 because we cannot have negative open brackets
            if (minOpen < 0) {
                minOpen = 0;
            }
        }

        // The string is valid if we can successfully close all open parentheses
        return minOpen == 0;
    }
}
