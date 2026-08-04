import java.util.ArrayList;
import java.util.List;

public class Solution {
    // Mapping table for keypad digits (0-9) to their corresponding letters
    private static final String[] KEYPAD = {
        "",     // 0
        "",     // 1
        "abc",  // 2
        "def",  // 3
        "ghi",  // 4
        "jkl",  // 5
        "mno",  // 6
        "pqrs", // 7
        "tuv",  // 8
        "wxyz"  // 9
    };

    public List<String> letterCombinations(String digits) {
        List<String> result = new ArrayList<>();
        
        // Edge case: return empty list for empty input string
        if (digits == null || digits.isEmpty()) {
            return result;
        }
        
        backtrack(result, digits, new StringBuilder(), 0);
        return result;
    }

    private void backtrack(List<String> result, String digits, StringBuilder current, int index) {
        // Base case: if current combination length matches input digits length
        if (index == digits.length()) {
            result.add(current.toString());
            return;
        }

        // Fetch letters mapped to the current digit
        String letters = KEYPAD[digits.charAt(index) - '0'];

        // Iterate through all possible letters for the current digit
        for (char letter : letters.toCharArray()) {
            current.append(letter);                  // Choose
            backtrack(result, digits, current, index + 1); // Recurse
            current.deleteCharAt(current.length() - 1);    // Backtrack (Undo)
        }
    }
}