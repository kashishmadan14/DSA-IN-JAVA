class Solution {
    public int longestValidParentheses(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }

        int left = 0, right = 0;
        int maxLength = 0;

        // 1. Pass from Left to Right
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(') {
                left++;
            } else {
                right++;
            }

            if (left == right) {
                maxLength = Math.max(maxLength, 2 * right);
            } else if (right > left) {
                // Reset if there are more closing than opening brackets
                left = 0;
                right = 0;
            }
        }

        // Reset counters for the reverse pass
        left = 0;
        right = 0;

        // 2. Pass from Right to Left (Catches leftover unclosed open brackets like "(()")
        for (int i = s.length() - 1; i >= 0; i--) {
            char c = s.charAt(i);
            if (c == '(') {
                left++;
            } else {
                right++;
            }

            if (left == right) {
                maxLength = Math.max(maxLength, 2 * left);
            } else if (left > right) {
                // Reset if there are more opening than closing brackets
                left = 0;
                right = 0;
            }
        }

        return maxLength;
    }
}
