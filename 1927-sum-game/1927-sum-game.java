class Solution {
    public boolean sumGame(String num) {
        final int n = num.length();
        double balance = 0.0;
        
        for (int i = 0; i < n / 2; ++i) {
            balance += getExpectation(num.charAt(i));
        }
        
        for (int i = n / 2; i < n; ++i) {
            balance -= getExpectation(num.charAt(i));
        }
        
        
        return balance != 0.0;
    }
    
    private double getExpectation(char c) {
        
        return c == '?' ? 4.5 : c - '0';
    }
}
