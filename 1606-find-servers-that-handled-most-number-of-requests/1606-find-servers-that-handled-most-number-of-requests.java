import java.util.*;

class Solution {
    public List<Integer> busiestServers(int k, int[] arrival, int[] load) {
        // Track the number of requests handled by each server
        int[] requestCounts = new int[k];
        
        // TreeSet to store all currently available (idle) servers
        TreeSet<Integer> idleServers = new TreeSet<>();
        for (int i = 0; i < k; i++) {
            idleServers.add(i);
        }
        
        // Min-heap to track busy servers: stores int[] where [0] = freeTime, [1] = serverId
        PriorityQueue<int[]> busyServers = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));
        
        for (int i = 0; i < arrival.length; i++) {
            int arrivalTime = arrival[i];
            int processTime = load[i];
            
            // 1. Free up all servers that have finished processing by the current arrival time
            while (!busyServers.isEmpty() && busyServers.peek()[0] <= arrivalTime) {
                int[] freedServer = busyServers.poll();
                idleServers.add(freedServer[1]);
            }
            
            // If all servers are busy, the request is dropped
            if (idleServers.isEmpty()) {
                continue;
            }
            
            // 2. Find the preferred server using round-robin logic (i % k)
            int preferredServer = i % k;
            
            // Use ceiling() to find the lowest server ID >= preferredServer
            Integer assignedServer = idleServers.ceiling(preferredServer);
            
            // If no server >= preferredServer is free, wrap around to the first available server
            if (assignedServer == null) {
                assignedServer = idleServers.first();
            }
            
            // 3. Assign the request to the chosen server
            requestCounts[assignedServer]++;
            busyServers.offer(new int[]{arrivalTime + processTime, assignedServer});
            idleServers.remove(assignedServer);
        }
        
        // 4. Find the maximum number of requests handled by any server
        int maxRequests = 0;
        for (int count : requestCounts) {
            maxRequests = Math.max(maxRequests, count);
        }
        
        // Collect all server IDs that matched the maximum request count
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < k; i++) {
            if (requestCounts[i] == maxRequests) {
                result.add(i);
            }
        }
        
        return result;
    }
}
