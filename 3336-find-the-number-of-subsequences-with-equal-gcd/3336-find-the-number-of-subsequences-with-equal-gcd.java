class Solution {
    public int subsequencePairCount(int[] nums) {
        int n = nums.length;
        int maxVal = 0;
        for (int num : nums) {
            if (num > maxVal) {
                maxVal = num;
            }
        }
        
        long[][][] dp = new long[maxVal + 1][maxVal + 1][2];
        dp[0][0][0] = 1;
        
        long MOD = 1000000007;
        
        for (int num : nums) {
            for (int g1 = 0; g1 <= maxVal; g1++) {
                for (int g2 = 0; g2 <= maxVal; g2++) {
                    long ways = dp[g1][g2][0];
                    if (ways == 0) continue;
                    
                    int ng1 = (g1 == 0) ? num : gcd(g1, num);
                    dp[ng1][g2][1] = (dp[ng1][g2][1] + ways) % MOD;
                    
                    int ng2 = (g2 == 0) ? num : gcd(g2, num);
                    dp[g1][ng2][1] = (dp[g1][ng2][1] + ways) % MOD;
                }
            }
            
            for (int g1 = 0; g1 <= maxVal; g1++) {
                for (int g2 = 0; g2 <= maxVal; g2++) {
                    if (dp[g1][g2][1] > 0) {
                        dp[g1][g2][0] = (dp[g1][g2][0] + dp[g1][g2][1]) % MOD;
                        dp[g1][g2][1] = 0;
                    }
                }
            }
        }
        
        long ans = 0;
        for (int g = 1; g <= maxVal; g++) {
            ans = (ans + dp[g][g][0]) % MOD;
        }
        
        return (int) ans;
    }
    
    private int gcd(int a, int b) {
        while (b != 0) {
            int tmp = a % b;
            a = b;
            b = tmp;
        }
        return a;
    }
}