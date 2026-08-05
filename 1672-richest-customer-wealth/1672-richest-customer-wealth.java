class Solution {
    public int maximumWealth(int[][] accounts) {
        int maxwealth = 0;

        for(int person = 0; person<accounts.length; person++){
            int wealth =0;
            
            for(int account = 0; account<accounts[person].length; account++){
                wealth += accounts[person][account];
            }
            maxwealth = Math.max(maxwealth,wealth);
        }
        return maxwealth;

    }
}