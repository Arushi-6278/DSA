class Solution {
    private static final int MOD = 1000000007;

    public int zigZagArrays(int n, int l, int r) {
        int K = r - l + 1;
        int size = 2 * K;
        long[][] T = new long[size][size];

        for (int i = 0; i < K; i++) {
            for (int j = 0; j < K; j++) {
                if (i < j) {
                    T[j][i + K] = 1;
                }
                if (i > j) {
                    T[j + K][i] = 1;
                }
            }
        }

        long[][] T_pow = matrixPower(T, n - 2);

        long[][] base = new long[size][1];
        for (int i = 0; i < K; i++) {
            for (int j = 0; j < K; j++) {
                if (i < j) {
                    base[j][0] = (base[j][0] + 1) % MOD;
                } else if (i > j) {
                    base[j + K][0] = (base[j + K][0] + 1) % MOD;
                }
            }
        }

        long[][] result = multiply(T_pow, base);

        long total = 0;
        for (int i = 0; i < size; i++) {
            total = (total + result[i][0]) % MOD;
        }

        return (int) total;
    }

    private long[][] matrixPower(long[][] base, int exp) {
        int n = base.length;
        long[][] res = new long[n][n];
        for (int i = 0; i < n; i++) {
            res[i][i] = 1;
        }
        while (exp > 0) {
            if ((exp & 1) == 1) {
                res = multiply(res, base);
            }
            base = multiply(base, base);
            exp >>= 1;
        }
        return res;
    }

    private long[][] multiply(long[][] A, long[][] B) {
        int rA = A.length;
        int cA = A[0].length;
        int cB = B[0].length;
        long[][] C = new long[rA][cB];
        for (int i = 0; i < rA; i++) {
            for (int k = 0; k < cA; k++) {
                if (A[i][k] == 0) continue;
                for (int j = 0; j < cB; j++) {
                    C[i][j] = (C[i][j] + A[i][k] * B[k][j]) % MOD;
                }
            }
        }
        return C;
    }
}