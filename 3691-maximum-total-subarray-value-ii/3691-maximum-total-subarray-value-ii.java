import java.util.*;

class Solution {
    public long maxTotalValue(int[] nums, int k) {
        int n = nums.length;
        int logn = (int)(Math.log(n) / Math.log(2)) + 1;
        int[][] stMax = new int[n][logn];
        int[][] stMin = new int[n][logn];
        
        for (int i = 0; i < n; i++) {
            stMax[i][0] = nums[i];
            stMin[i][0] = nums[i];
        }
        
        for (int j = 1; j < logn; j++) {
            for (int i = 0; i + (1 << j) <= n; i++) {
                stMax[i][j] = Math.max(stMax[i][j-1], stMax[i + (1 << (j-1))][j-1]);
                stMin[i][j] = Math.min(stMin[i][j-1], stMin[i + (1 << (j-1))][j-1]);
            }
        }
        
        PriorityQueue<long[]> pq = new PriorityQueue<>((a, b) -> Long.compare(b[0], a[0]));
        
        for (int l = 0; l < n; l++) {
            int r = n - 1;
            int len = r - l + 1;
            int j = (int)(Math.log(len) / Math.log(2));
            int maxVal = Math.max(stMax[l][j], stMax[r - (1 << j) + 1][j]);
            int minVal = Math.min(stMin[l][j], stMin[r - (1 << j) + 1][j]);
            long val = maxVal - minVal;
            pq.offer(new long[]{val, l, r});
        }
        
        long total = 0;
        while (k-- > 0) {
            long[] top = pq.poll();
            long val = top[0];
            int l = (int) top[1];
            int r = (int) top[2];
            total += val;
            if (r > l) {
                int nr = r - 1;
                int len = nr - l + 1;
                int j = (int)(Math.log(len) / Math.log(2));
                int maxVal = Math.max(stMax[l][j], stMax[nr - (1 << j) + 1][j]);
                int minVal = Math.min(stMin[l][j], stMin[nr - (1 << j) + 1][j]);
                long nval = maxVal - minVal;
                pq.offer(new long[]{nval, l, nr});
            }
        }
        
        return total;
    }
}