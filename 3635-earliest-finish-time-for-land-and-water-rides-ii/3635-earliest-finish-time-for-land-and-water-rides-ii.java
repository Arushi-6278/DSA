import java.util.*;

class Solution {
    public int earliestFinishTime(int[] landStartTime, int[] landDuration, int[] waterStartTime, int[] waterDuration) {
        int n = landStartTime.length;
        int m = waterStartTime.length;
        
      
        WaterRide[] waterRides = new WaterRide[m];
        for (int j = 0; j < m; j++) {
            waterRides[j] = new WaterRide(waterStartTime[j], waterDuration[j]);
        }
        Arrays.sort(waterRides, (a, b) -> Integer.compare(a.start, b.start));
        
       
      
        long[] suffixMin = new long[m + 1];
        suffixMin[m] = Long.MAX_VALUE;
        for (int j = m - 1; j >= 0; j--) {
            suffixMin[j] = Math.min(suffixMin[j + 1], waterRides[j].start + (long)waterRides[j].duration);
        }
        
        
        long[] prefixMinDur = new long[m];
        prefixMinDur[0] = waterRides[0].duration;
        for (int j = 1; j < m; j++) {
            prefixMinDur[j] = Math.min(prefixMinDur[j - 1], waterRides[j].duration);
        }
        
        int best = Integer.MAX_VALUE;
        
       
        for (int i = 0; i < n; i++) {
            long landFinish = landStartTime[i] + (long)landDuration[i];
            
           
            int left = 0, right = m - 1;
            int idx = m;
            while (left <= right) {
                int mid = left + (right - left) / 2;
                if (waterRides[mid].start > landFinish) {
                    idx = mid;
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            }
            
            long minFinish = Long.MAX_VALUE;
           
            if (idx > 0) {
                minFinish = Math.min(minFinish, landFinish + prefixMinDur[idx - 1]);
            }
           
            if (idx < m) {
                minFinish = Math.min(minFinish, suffixMin[idx]);
            }
            
            if (minFinish < best) {
                best = (int)minFinish;
            }
        }
        
       
        LandRide[] landRides = new LandRide[n];
        for (int i = 0; i < n; i++) {
            landRides[i] = new LandRide(landStartTime[i], landDuration[i]);
        }
        Arrays.sort(landRides, (a, b) -> Integer.compare(a.start, b.start));
        
        
        long[] suffixMinLand = new long[n + 1];
        suffixMinLand[n] = Long.MAX_VALUE;
        for (int i = n - 1; i >= 0; i--) {
            suffixMinLand[i] = Math.min(suffixMinLand[i + 1], landRides[i].start + (long)landRides[i].duration);
        }
        
       
        long[] prefixMinLandDur = new long[n];
        prefixMinLandDur[0] = landRides[0].duration;
        for (int i = 1; i < n; i++) {
            prefixMinLandDur[i] = Math.min(prefixMinLandDur[i - 1], landRides[i].duration);
        }
        
        
        for (int j = 0; j < m; j++) {
            long waterFinish = waterStartTime[j] + (long)waterDuration[j];
            
            
            int left = 0, right = n - 1;
            int idx = n;
            while (left <= right) {
                int mid = left + (right - left) / 2;
                if (landRides[mid].start > waterFinish) {
                    idx = mid;
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            }
            
            long minFinish = Long.MAX_VALUE;
           
            if (idx > 0) {
                minFinish = Math.min(minFinish, waterFinish + prefixMinLandDur[idx - 1]);
            }
           
            if (idx < n) {
                minFinish = Math.min(minFinish, suffixMinLand[idx]);
            }
            
            if (minFinish < best) {
                best = (int)minFinish;
            }
        }
        
        return best;
    }
    
    class WaterRide {
        int start;
        int duration;
        
        WaterRide(int s, int d) {
            start = s;
            duration = d;
        }
    }
    
    class LandRide {
        int start;
        int duration;
        
        LandRide(int s, int d) {
            start = s;
            duration = d;
        }
    }
}