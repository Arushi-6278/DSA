import java.util.*;

class Solution {
    public int[] maximumWeight(List<List<Integer>> intervals) {
        int n = intervals.size();
        long[][] sorted = new long[n][4];
        for (int i = 0; i < n; i++) {
            sorted[i][0] = intervals.get(i).get(0);
            sorted[i][1] = intervals.get(i).get(1);
            sorted[i][2] = intervals.get(i).get(2);
            sorted[i][3] = i;
        }

        Arrays.sort(sorted, (a, b) -> Long.compare(a[0], b[0]));

        long[] starts = new long[n];
        for (int i = 0; i < n; i++) {
            starts[i] = sorted[i][0];
        }

        long[][][] dp = new long[5][n + 1][];
        for (int k = 0; k <= 4; k++) {
            for (int i = 0; i <= n; i++) {
                dp[k][i] = null;
            }
        }

        for (int i = 0; i <= n; i++) {
            dp[0][i] = new long[]{0};
        }

        for (int k = 1; k <= 4; k++) {
            for (int i = n - 1; i >= 0; i--) {
                long[] skip = dp[k][i + 1];

                long[] take = null;
                int nextIdx = binarySearch(starts, sorted[i][1] + 1);
                if (dp[k - 1][nextIdx] != null) {
                    long[] prev = dp[k - 1][nextIdx];
                    take = new long[prev.length + 1];
                    take[0] = prev[0] + sorted[i][2];
                    take[1] = sorted[i][3];
                    System.arraycopy(prev, 1, take, 2, prev.length - 1);
                    sortIndices(take);
                }

                dp[k][i] = best(skip, take);
            }
        }

        long[] res = null;
        for (int k = 1; k <= 4; k++) {
            res = best(res, dp[k][0]);
        }

        if (res == null || res.length == 1) {
            return new int[0];
        }

        int[] ans = new int[res.length - 1];
        for (int i = 1; i < res.length; i++) {
            ans[i - 1] = (int) res[i];
        }
        return ans;
    }

    private int binarySearch(long[] starts, long target) {
        int low = 0, high = starts.length;
        while (low < high) {
            int mid = (low + high) >>> 1;
            if (starts[mid] >= target) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }
        return low;
    }

    private void sortIndices(long[] arr) {
        int len = arr.length - 1;
        for (int i = 1; i <= len; i++) {
            for (int j = i + 1; j <= len; j++) {
                if (arr[i] > arr[j]) {
                    long temp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;
                }
            }
        }
    }

    private long[] best(long[] a, long[] b) {
        if (a == null) return b;
        if (b == null) return a;

        if (a[0] > b[0]) return a;
        if (b[0] > a[0]) return b;

        int minLen = Math.min(a.length, b.length);
        for (int i = 1; i < minLen; i++) {
            if (a[i] < b[i]) return a;
            if (b[i] < a[i]) return b;
        }

        if (a.length <= b.length) return a;
        return b;
    }
}