class Solution {
    public boolean stoneGameIX(int[] stones) {
        int[] count = new int[3];
        for (int stone : stones) {
            count[stone % 3]++;
        }
        
        // Scenario 1: Even number of type 0 stones
        if (count[0] % 2 == 0) {
            return Math.min(count[1], count[2]) > 0;
        }
        
        // Scenario 2: Odd number of type 0 stones
        return Math.abs(count[1] - count[2]) > 2;
    }
}
