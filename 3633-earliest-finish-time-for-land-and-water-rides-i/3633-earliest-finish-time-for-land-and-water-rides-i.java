class Solution {
    public int earliestFinishTime(int[] landStartTime, int[] landDuration, int[] waterStartTime, int[] waterDuration) {
        int minFinishTime = Integer.MAX_VALUE;
        int n = landStartTime.length;
        int m = waterStartTime.length;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                
             
                int landFinish1 = landStartTime[i] + landDuration[i];
                int waterStart1 = Math.max(landFinish1, waterStartTime[j]);
                int totalFinish1 = waterStart1 + waterDuration[j];
                
               
                int waterFinish2 = waterStartTime[j] + waterDuration[j];
                int landStart2 = Math.max(waterFinish2, landStartTime[i]);
                int totalFinish2 = landStart2 + landDuration[i];
                
                minFinishTime = Math.min(minFinishTime, Math.min(totalFinish1, totalFinish2));
            }
        }

        return minFinishTime;
    }
}