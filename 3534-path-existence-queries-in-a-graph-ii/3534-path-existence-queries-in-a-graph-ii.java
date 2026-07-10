import java.util.*;

class Solution {
    public int[] pathExistenceQueries(int n, int[] nums, int maxDiff, int[][] queries) {
        int maxVal = 0;
        for (int num : nums) {
            if (num > maxVal) {
                maxVal = num;
            }
        }
        
        boolean[] exists = new boolean[maxVal + 1];
        for (int num : nums) {
            exists[num] = true;
        }
        
        int uCount = 0;
        for (int i = 0; i <= maxVal; i++) {
            if (exists[i]) {
                uCount++;
            }
        }
        
        int[] uniqueVals = new int[uCount];
        int idx = 0;
        for (int i = 0; i <= maxVal; i++) {
            if (exists[i]) {
                uniqueVals[idx++] = i;
            }
        }
        
        int[] valToIdx = new int[maxVal + 1];
        Arrays.fill(valToIdx, -1);
        for (int i = 0; i < uCount; i++) {
            valToIdx[uniqueVals[i]] = i;
        }
        
        int[] comp = new int[uCount];
        int compId = 0;
        for (int i = 0; i < uCount; i++) {
            if (i > 0 && uniqueVals[i] - uniqueVals[i - 1] > maxDiff) {
                compId++;
            }
            comp[i] = compId;
        }
        
        int LOG = 18;
        int[][] up = new int[uCount][LOG];
        
        int r = 0;
        for (int i = 0; i < uCount; i++) {
            while (r < uCount && uniqueVals[r] <= uniqueVals[i] + maxDiff) {
                r++;
            }
            up[i][0] = r - 1;
        }
        
        for (int j = 1; j < LOG; j++) {
            for (int i = 0; i < uCount; i++) {
                up[i][j] = up[up[i][j - 1]][j - 1];
            }
        }
        
        int[] ans = new int[queries.length];
        for (int q = 0; q < queries.length; q++) {
            int u = queries[q][0];
            int v = queries[q][1];
            
            if (u == v) {
                ans[q] = 0;
                continue;
            }
            
            int idxU = valToIdx[nums[u]];
            int idxV = valToIdx[nums[v]];
            
            if (comp[idxU] != comp[idxV]) {
                ans[q] = -1;
                continue;
            }
            
            int start = Math.min(idxU, idxV);
            int target = Math.max(idxU, idxV);
            
            if (uniqueVals[target] - uniqueVals[start] <= maxDiff) {
                ans[q] = 1;
                continue;
            }
            
            int steps = 0;
            int curr = start;
            for (int j = LOG - 1; j >= 0; j--) {
                if (up[curr][j] < target) {
                    steps += (1 << j);
                    curr = up[curr][j];
                }
            }
            
            ans[q] = steps + 1;
        }
        
        return ans;
    }
}