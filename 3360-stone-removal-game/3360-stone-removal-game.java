class Solution {
    public boolean canAliceWin(int n) {
        int stonesToRemove = 10;
        boolean isAliceTurn = true;
        
        while (n >= stonesToRemove) {
            n -= stonesToRemove;
            stonesToRemove--;
            isAliceTurn = !isAliceTurn;
        }
        
        // If the loop stops on Bob's turn (!isAliceTurn), Bob cannot move and Alice wins
        return !isAliceTurn;
    }
}
