import java.util.Arrays;
import java.util.Comparator;

public class Solution {
    public int eraseOverlapIntervals(int[][] intervals) {
        if (intervals == null || intervals.length == 0) {
            return 0;
        }

        Arrays.sort(intervals, Comparator.comparingInt(a -> a[1]));

        int removals = 0;
    
        int currentEnd = intervals[0][1];

        for (int i = 1; i < intervals.length; i++) {
           
            if (intervals[i][0] < currentEnd) {
                removals++; 
            } else {
              
                currentEnd = intervals[i][1];
            }
        }

        return removals;
    }
}
