import java.util.Arrays;

public class Solution {
    public int[] countServers(int n, int[][] logs, int x, int[] queries) {
        int m = queries.length;
        int[] ans = new int[m];
        
        // 1. Sort logs by timestamp
        Arrays.sort(logs, (a, b) -> Integer.compare(a[1], b[1]));
        
        // 2. Store queries along with their original indices and sort chronologically
        int[][] sortedQueries = new int[m][2];
        for (int i = 0; i < m; i++) {
            sortedQueries[i][0] = queries[i]; // query time
            sortedQueries[i][1] = i;          // original index
        }
        Arrays.sort(sortedQueries, (a, b) -> Integer.compare(a[0], b[0]));
        
        // Frequency array to keep track of server request counts in the current window
        // Server IDs are 1-indexed up to n based on typical constraints
        int[] serverCount = new int[n + 1];
        int activeServers = 0;
        
        int left = 0;
        int right = 0;
        
        // 3. Process each query using a sliding window
        for (int i = 0; i < m; i++) {
            int queryTime = sortedQueries[i][0];
            int originalIndex = sortedQueries[i][1];
            
            // Expand window to include all logs up to queryTime
            while (right < logs.length && logs[right][1] <= queryTime) {
                int serverId = logs[right][0];
                if (serverCount[serverId] == 0) {
                    activeServers++;
                }
                serverCount[serverId]++;
                right++;
            }
            
            // Shrink window to remove logs older than (queryTime - x)
            while (left < logs.length && logs[left][1] < queryTime - x) {
                int serverId = logs[left][0];
                serverCount[serverId]--;
                if (serverCount[serverId] == 0) {
                    activeServers--;
                }
                left++;
            }
            
            // 4. Save the total number of inactive servers for this specific query
            ans[originalIndex] = n - activeServers;
        }
        
        return ans;
    }
}
