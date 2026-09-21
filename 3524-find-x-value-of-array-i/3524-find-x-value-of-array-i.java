class Solution {
    public long[] resultArray(int[] nums, int k) {
        int n = nums.length;
        long[] result = new long[k];
        
        long[][] count = new long[n][k];
        int val0 = nums[0] % k;
        count[0][val0]++;
        result[val0]++;

        for (int i = 1; i < n; i++) {
            int val = nums[i] % k;
            count[i][val]++;
            result[val]++;
            for (int r = 0; r < k; r++) {
                if (count[i - 1][r] > 0) {
                    int nextR = (r * val) % k;
                    count[i][nextR] += count[i - 1][r];
                    result[nextR] += count[i - 1][r];
                }
            }
        }
        
        return result;
    }
}