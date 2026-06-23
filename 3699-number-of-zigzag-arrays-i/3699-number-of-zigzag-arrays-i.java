class Solution {
    public int zigZagArrays(int n, int l, int r) {
        int m = r - l + 1;
        if (m <= 0) return 0;
        long MOD = 1000000007;

        long[] dpUp = new long[m];
        long[] dpDown = new long[m];

        for (int i = 0; i < m; i++) {
            dpUp[i] = 1;
            dpDown[i] = 1;
        }

        long[] nextUp = new long[m];
        long[] nextDown = new long[m];

        for (int j = 1; j < n; j++) {
            long prefixSumUp = 0;
            for (int i = 0; i < m; i++) {
                nextUp[i] = prefixSumUp;
                prefixSumUp = (prefixSumUp + dpDown[i]) % MOD;
            }

            long suffixSumDown = 0;
            for (int i = m - 1; i >= 0; i--) {
                nextDown[i] = suffixSumDown;
                suffixSumDown = (suffixSumDown + dpUp[i]) % MOD;
            }

            System.arraycopy(nextUp, 0, dpUp, 0, m);
            System.arraycopy(nextDown, 0, dpDown, 0, m);
        }

        long total = 0;
        for (int i = 0; i < m; i++) {
            total = (total + dpUp[i] + dpDown[i]) % MOD;
        }

        return (int) total;
    }
}