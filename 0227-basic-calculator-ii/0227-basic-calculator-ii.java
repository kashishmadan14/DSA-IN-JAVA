class Solution {
    public int calculate(String s) {
        if (s == null || s.isEmpty()) return 0;
        
        int total = 0;
        int prev = 0;
        int num = 0;
        char sign = '+';
        
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            
            if (Character.isDigit(c)) {
                num = num * 10 + (c - '0');
            }
            
            if ((!Character.isDigit(c) && c != ' ') || i == s.length() - 1) {
                if (sign == '+') {
                    total += prev;
                    prev = num;
                } else if (sign == '-') {
                    total += prev;
                    prev = -num;
                } else if (sign == '*') {
                    prev = prev * num;
                } else if (sign == '/') {
                    prev = prev / num;
                }
                sign = c;
                num = 0;
            }
        }
        
        total += prev;
        return total;
    }
}
