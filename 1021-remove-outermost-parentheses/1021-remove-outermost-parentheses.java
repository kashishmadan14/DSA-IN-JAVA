public class Solution {
    public static String removeOuterParentheses(String s) {
        StringBuilder result = new StringBuilder();
        int depth = 0;

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            
            if (c == '(') {
                // If depth > 0, it means this '(' is not the outermost one
                if (depth > 0) {
                    result.append(c);
                }
                depth++;
            } else if (c == ')') {
                depth--;
                // If depth > 0 after decrement, this ')' is not the outermost one
                if (depth > 0) {
                    result.append(c);
                }
            }
        }
        
        return result.toString();
    }

    public static void main(String[] args) {
        String input1 = "(()())(())";
        System.out.println(removeOuterParentheses(input1)); // Output: "()()()"

        String input2 = "(()())(())(()(()))";
        System.out.println(removeOuterParentheses(input2)); // Output: "()()()()(())"
    }
}
