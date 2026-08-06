class Solution {
    public int smallestNumber(int n, int t) {
        while(true){
            int n1 = n;
            int p = 1;
            while(n>0){
                p = p*(n%10);
                n/=10;
            }
            if(p%t == 0)return n1;
            n = n1+1;
        }
    }
}