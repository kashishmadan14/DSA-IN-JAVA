class Solution {
    public int totalNumbers(int[] digits) {
        int n = digits.length;
        int ans = 0;

        int[] mp = new int[10];

        for (int d : digits) {
            mp[d]++;
        }

        for (int i = 1; i <= 9; i++) {
            if (mp[i] == 0) continue;

            mp[i]--;

            for (int j = 0; j <= 9; j++) {
                if (mp[j] == 0) continue;

                mp[j]--;

                for (int k = 0; k <= 9; k += 2) {
                    if (mp[k] == 0) continue;

                    mp[k]--;
                    ans++;
                    mp[k]++;
                }

                mp[j]++;
            }

            mp[i]++;
        }

        return ans;
    }
}