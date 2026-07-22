import java.util.ArrayList;
import java.util.List;

class Solution {
    public List<Integer> maxActiveSectionsAfterTrade(String s, int[][] queries) {
        int n = s.length();
        int origOnes = 0;
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == '1') {
                origOnes++;
            }
        }

        List<Integer> stList = new ArrayList<>();
        List<Integer> edList = new ArrayList<>();
        int idx = 0;
        while (idx < n) {
            if (s.charAt(idx) == '1') {
                int st = idx;
                while (idx + 1 < n && s.charAt(idx + 1) == '1') {
                    idx++;
                }
                int ed = idx;
                stList.add(st);
                edList.add(ed);
            }
            idx++;
        }

        int M = stList.size();
        List<Integer> ans = new ArrayList<>(queries.length);
        if (M == 0) {
            for (int i = 0; i < queries.length; i++) {
                ans.add(origOnes);
            }
            return ans;
        }

        int[] blockSt = new int[M];
        int[] blockEd = new int[M];
        int[] prev1 = new int[M];
        int[] next1 = new int[M];
        int[] C = new int[M];

        for (int i = 0; i < M; i++) {
            blockSt[i] = stList.get(i);
            blockEd[i] = edList.get(i);
        }

        for (int i = 0; i < M; i++) {
            prev1[i] = (i > 0) ? blockEd[i - 1] : -1;
            next1[i] = (i < M - 1) ? blockSt[i + 1] : n;
            C[i] = next1[i] - prev1[i] - blockEd[i] + blockSt[i] - 2;
        }

        int K = 0;
        while ((1 << K) <= M) {
            K++;
        }
        int[][] stTable = new int[M][K];
        for (int i = 0; i < M; i++) {
            stTable[i][0] = C[i];
        }
        for (int j = 1; j < K; j++) {
            for (int i = 0; i + (1 << j) <= M; i++) {
                stTable[i][j] = Math.max(stTable[i][j - 1], stTable[i + (1 << (j - 1))][j - 1]);
            }
        }

        for (int[] query : queries) {
            int l = query[0];
            int r = query[1];

            int bStart = lowerBound(blockSt, l + 1);
            int bEnd = upperBound(blockEd, r - 1) - 1;

            if (bStart > bEnd) {
                ans.add(origOnes);
                continue;
            }

            int maxGain = 0;

            int st = blockSt[bStart];
            int ed = blockEd[bStart];
            int p1 = prev1[bStart];
            int n1 = next1[bStart];
            int L = Math.max(p1, l - 1);
            int R = Math.max(L, Math.min(n1, r + 1));
            int gain = (R - L - 1) - (ed - st + 1);
            if (gain > maxGain) {
                maxGain = gain;
            }

            if (bStart != bEnd) {
                st = blockSt[bEnd];
                ed = blockEd[bEnd];
                p1 = prev1[bEnd];
                n1 = next1[bEnd];
                L = Math.max(p1, l - 1);
                R = Math.max(L, Math.min(n1, r + 1));
                gain = (R - L - 1) - (ed - st + 1);
                if (gain > maxGain) {
                    maxGain = gain;
                }

                if (bStart + 1 <= bEnd - 1) {
                    int interiorMax = queryRmq(stTable, bStart + 1, bEnd - 1);
                    if (interiorMax > maxGain) {
                        maxGain = interiorMax;
                    }
                }
            }

            ans.add(origOnes + maxGain);
        }

        return ans;
    }

    private int lowerBound(int[] arr, int target) {
        int low = 0, high = arr.length;
        while (low < high) {
            int mid = (low + high) >>> 1;
            if (arr[mid] >= target) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }
        return low;
    }

    private int upperBound(int[] arr, int target) {
        int low = 0, high = arr.length;
        while (low < high) {
            int mid = (low + high) >>> 1;
            if (arr[mid] > target) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }
        return low;
    }

    private int queryRmq(int[][] stTable, int L, int R) {
        if (L > R) {
            return -1000000000;
        }
        int len = R - L + 1;
        int j = 31 - Integer.numberOfLeadingZeros(len);
        return Math.max(stTable[L][j], stTable[R - (1 << j) + 1][j]);
    }
}
