import java.util.PriorityQueue;
import java.util.Comparator;

public class Solution {
    public int[] assignTasks(int[] servers, int[] tasks) {
        int m = tasks.length;
        int[] ans = new int[m];
        
        // freeServers: sorted by weight, then by server index
        PriorityQueue<int[]> freeServers = new PriorityQueue<>(
            (a, b) -> a[0] != b[0] ? Integer.compare(a[0], b[0]) : Integer.compare(a[1], b[1])
        );
        
        // busyServers: sorted by completion time, then weight, then server index
        PriorityQueue<int[]> busyServers = new PriorityQueue<>((a, b) -> {
            if (a[2] != b[2]) return Integer.compare(a[2], b[2]); // Free time
            if (a[0] != b[0]) return Integer.compare(a[0], b[0]); // Weight
            return Integer.compare(a[1], b[1]);                  // Index
        });
        
        // Initialize all servers into the free heap
        // Format: {weight, index}
        for (int i = 0; i < servers.length; i++) {
            freeServers.offer(new int[]{servers[i], i});
        }
        
        int currTime = 0;
        
        for (int i = 0; i < m; i++) {
            // The task at index i arrives at max(currTime, i)
            currTime = Math.max(currTime, i);
            
            // 1. Release all servers whose task completes at or before currTime
            while (!busyServers.isEmpty() && busyServers.peek()[2] <= currTime) {
                int[] server = busyServers.poll();
                freeServers.offer(new int[]{server[0], server[1]});
            }
            
            // 2. If no servers are free, fast-forward time to the next available server's completion time
            if (freeServers.isEmpty()) {
                currTime = busyServers.peek()[2];
                while (!busyServers.isEmpty() && busyServers.peek()[2] <= currTime) {
                    int[] server = busyServers.poll();
                    freeServers.offer(new int[]{server[0], server[1]});
                }
            }
            
            // 3. Assign the task to the best available free server
            int[] bestServer = freeServers.poll();
            ans[i] = bestServer[1];
            
            // 4. Move the server to busy pool with updated release time
            // Format: {weight, index, freeTime}
            busyServers.offer(new int[]{bestServer[0], bestServer[1], currTime + tasks[i]});
        }
        
        return ans;
    }
}
