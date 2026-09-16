class Solution {
    public int numberOfSets(int n, int k) {
        long MOD = 1000000007;
        long totalPoints = n + k - 1;
        long totalChoose = 2 * k;

        if (totalChoose > totalPoints) {
            return 0;
        }

        long numerator = 1;
        long denominator = 1;

        for (long i = 1; i <= totalChoose; i++) {
            numerator = (numerator * (totalPoints - i + 1)) % MOD;
            denominator = (denominator * i) % MOD;
        }

        return (int) (numerator * power(denominator, MOD - 2, MOD) % MOD);
    }

    private long power(long base, long exp, long mod) {
        long res = 1;
        base %= mod;
        while (exp > 0) {
            if (exp % 2 == 1) res = (res * base) % mod;
            base = (base * base) % mod;
            exp /= 2;
        }
        return res;
    }
}