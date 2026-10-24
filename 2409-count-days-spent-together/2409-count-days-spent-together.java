class Solution {
    // Number of days in each month for a non-leap year
    private final int[] daysInMonth = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

    public int countDaysTogether(String arriveAlice, String leaveAlice, String arriveBob, String leaveBob) {
        // Convert all MM-DD dates to absolute day of the year numbers
        int startA = dateToDayOfYear(arriveAlice);
        int endA = dateToDayOfYear(leaveAlice);
        int startB = dateToDayOfYear(arriveBob);
        int endB = dateToDayOfYear(leaveBob);

        // Find the overlapping range
        int overlapStart = Math.max(startA, startB);
        int overlapEnd = Math.min(endA, endB);

        // Calculate days if there is an overlap, otherwise return 0
        if (overlapStart <= overlapEnd) {
            return overlapEnd - overlapStart + 1;
        }
        
        return 0;
    }

    private int dateToDayOfYear(String date) {
        // Parse month and day from "MM-DD"
        int month = Integer.parseInt(date.substring(0, 2));
        int day = Integer.parseInt(date.substring(3, 5));

        int dayCount = 0;
        // Add up all the days from previous months
        for (int i = 0; i < month - 1; i++) {
            dayCount += daysInMonth[i];
        }
        
        // Add the days of the current month
        return dayCount + day;
    }
}
