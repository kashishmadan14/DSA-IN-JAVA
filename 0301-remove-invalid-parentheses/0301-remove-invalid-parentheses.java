import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Solution {
    public List<String> removeInvalidParentheses(String s) {
        int leftRem = 0;
        int rightRem = 0;

        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (ch == '(') {
                leftRem++;
            } else if (ch == ')') {
                if (leftRem > 0) {
                    leftRem--; 
                } else {
                    rightRem++; 
                }
            }
        }

        Set<String> result = new HashSet<>();
        dfs(s, 0, leftRem, rightRem, 0, new StringBuilder(), result);
        return new ArrayList<>(result);
    }

    private void dfs(String s, int index, int leftRem, int rightRem, int balance, StringBuilder sb, Set<String> result) {
      
        if (index == s.length()) {
            if (leftRem == 0 && rightRem == 0 && balance == 0) {
                result.add(sb.toString());
            }
            return;
        }

        if (balance < 0) {
            return;
        }

        char ch = s.charAt(index);
        int len = sb.length();

        if (ch == '(') {
          
            if (leftRem > 0) {
                dfs(s, index + 1, leftRem - 1, rightRem, balance, sb, result);
            }
          
            sb.append(ch);
            dfs(s, index + 1, leftRem, rightRem, balance + 1, sb, result);
            sb.setLength(len); 
            
        } else if (ch == ')') {
           
            if (rightRem > 0) {
                dfs(s, index + 1, leftRem, rightRem - 1, balance, sb, result);
            }
            
            sb.append(ch);
            dfs(s, index + 1, leftRem, rightRem, balance - 1, sb, result);
            sb.setLength(len); 
            
        } else {
            sb.append(ch);
            dfs(s, index + 1, leftRem, rightRem, balance, sb, result);
            sb.setLength(len); 
        }
    }
}
