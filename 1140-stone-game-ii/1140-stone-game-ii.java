class Solution {
    public int stoneGameII(int[] piles) {
        int n = piles.length;
        int[] suffixSum = new int[n];
        suffixSum[n - 1] = piles[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            suffixSum[i] = suffixSum[i + 1] + piles[i];
        }

        int[][] dp = new int[n][n + 1];
        return helper(0, 1, piles, suffixSum, dp);
    }

    private int helper(int i, int M, int[] piles, int[] suffixSum, int[][] dp) {
        int n = piles.length;
        if (i >= n) {
            return 0;
        }
        if (i + 2 * M >= n) {
            return suffixSum[i];
        }
        if (dp[i][M] != 0) {
            return dp[i][M];
        }

        int maxStones = 0;
        for (int X = 1; X <= 2 * M; X++) {
            int nextM = Math.max(M, X);
            int opponentStones = helper(i + X, nextM, piles, suffixSum, dp);
            maxStones = Math.max(maxStones, suffixSum[i] - opponentStones);
        }

        dp[i][M] = maxStones;
        return maxStones;
    }
}