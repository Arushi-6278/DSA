class Solution {
    public long countMajoritySubarrays(int[] nums, int target) {
        int n = nums.length;
        int[] prefixSums = new int[n + 1];
        
        int minP = 0;
        int maxP = 0;
        
        for (int i = 0; i < n; i++) {
            int val = (nums[i] == target) ? 1 : -1;
            prefixSums[i + 1] = prefixSums[i] + val;
            if (prefixSums[i + 1] < minP) minP = prefixSums[i + 1];
            if (prefixSums[i + 1] > maxP) maxP = prefixSums[i + 1];
        }
        
        int offset = -minP;
        int treeSize = maxP - minP + 2;
        int[] bit = new int[treeSize];
        
        long ans = 0;
        
        for (int p : prefixSums) {
            int bitIdx = p + offset + 1;
            
            int idx = bitIdx - 1;
            while (idx > 0) {
                ans += bit[idx];
                idx -= idx & (-idx);
            }
            
            idx = bitIdx;
            while (idx < treeSize) {
                bit[idx] += 1;
                idx += idx & (-idx);
            }
        }
        
        return ans;
    }
}