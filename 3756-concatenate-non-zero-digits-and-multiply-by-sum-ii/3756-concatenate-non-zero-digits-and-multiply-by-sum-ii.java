class Solution {
    public int[] sumAndMultiply(String s, int[][] queries) {
        int m = s.length();
        int MOD = 1_000_000_007;
        
        long[] p10 = new long[m + 1];
        p10[0] = 1;
        for (int i = 1; i <= m; i++) {
            p10[i] = (p10[i - 1] * 10) % MOD;
        }
        
        int[] nonZeroCount = new int[m + 1];
        long[] valPrefix = new long[m + 1];
        long[] sumPrefix = new long[m + 1];
        
        for (int i = 0; i < m; i++) {
            char c = s.charAt(i);
            int digit = c - '0';
            
            if (digit != 0) {
                nonZeroCount[i + 1] = nonZeroCount[i] + 1;
                valPrefix[i + 1] = (valPrefix[i] * 10 + digit) % MOD;
                sumPrefix[i + 1] = sumPrefix[i] + digit;
            } else {
                nonZeroCount[i + 1] = nonZeroCount[i];
                valPrefix[i + 1] = valPrefix[i];
                sumPrefix[i + 1] = sumPrefix[i];
            }
        }
        
        int numQueries = queries.length;
        int[] ans = new int[numQueries];
        
        for (int i = 0; i < numQueries; i++) {
            int L = queries[i][0];
            int R = queries[i][1];
            
            int nzLeft = nonZeroCount[L];
            int nzRight = nonZeroCount[R + 1];
            int numNz = nzRight - nzLeft;
            
            if (numNz == 0) {
                ans[i] = 0;
                continue;
            }
            
            long x = (valPrefix[R + 1] - (valPrefix[L] * p10[numNz]) % MOD + MOD) % MOD;
            long sum = sumPrefix[R + 1] - sumPrefix[L];
            
            ans[i] = (int) ((x * (sum % MOD)) % MOD);
        }
        
        return ans;
    }
}