import java.util.ArrayList;
import java.util.List;

public class Solution {
    public List<String> removeInvalidParentheses(String s) {
        int leftRem = 0;
        int rightRem = 0;

        // 1. Calculate the exact number of invalid '(' and ')' to remove
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (ch == '(') {
                leftRem++;
            } else if (ch == ')') {
                if (leftRem > 0) {
                    leftRem--; // Valid pair found
                } else {
                    rightRem++; // Misplaced closing parenthesis
                }
            }
        }

        List<String> result = new ArrayList<>();
        dfs(s, 0, leftRem, rightRem, result);
        return result;
    }

    private void dfs(String s, int startIndex, int leftRem, int rightRem, List<String> result) {
        // Base case: if no more removals are needed, check if the string is valid
        if (leftRem == 0 && rightRem == 0) {
            if (isValid(s)) {
                result.add(s);
            }
            return;
        }

        for (int i = startIndex; i < s.length(); i++) {
            // Pruning: Skip duplicate adjacent characters to avoid duplicate branches
            if (i > startIndex && s.charAt(i) == s.charAt(i - 1)) {
                continue;
            }

            char ch = s.charAt(i);
            // Try removing the current character and recurse
            if (leftRem > 0 && ch == '(') {
                String nextStr = s.substring(0, i) + s.substring(i + 1);
                dfs(nextStr, i, leftRem - 1, rightRem, result);
            }
            if (rightRem > 0 && ch == ')') {
                String nextStr = s.substring(0, i) + s.substring(i + 1);
                dfs(nextStr, i, leftRem, rightRem - 1, result);
            }
        }
    }

    // Helper method to check if the parentheses structure is currently balanced
    private boolean isValid(String s) {
        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (ch == '(') {
                count++;
            } else if (ch == ')') {
                count--;
                if (count < 0) {
                    return false; // More closing than opening at this point
                }
            }
        }
        return count == 0;
    }
}
