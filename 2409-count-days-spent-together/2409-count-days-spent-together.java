class Solution {
    public int countDaysTogether(String arriveAlice, String leaveAlice, String arriveBob, String leaveBob) {
        int arrive1 = parseDays(arriveAlice);
        int leave1 = parseDays(leaveAlice);
        int arrive2 = parseDays(arriveBob);
        int leave2 = parseDays(leaveBob);
        
        int start = Math.max(arrive1, arrive2);
        int end = Math.min(leave1, leave2);
        
        return Math.max(0, end - start + 1);
    }
    
    private int parseDays(String date) {
        int[] daysInMonths = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        int month = Integer.parseInt(date.substring(0, 2));
        int day = Integer.parseInt(date.substring(3, 5));
        int totalDays = day;
        for (int i = 0; i < month - 1; i++) {
            totalDays += daysInMonths[i];
        }
        return totalDays;
    }
}