import java.util.Arrays;

class Solution {
    public int[] gcdValues(int[] nums, long[] queries) {
        int maxVal = 0;
        for (int num : nums) {
            maxVal = Math.max(maxVal, num);
        }

        int[] count = new int[maxVal + 1];
        for (int num : nums) {
            count[num]++;
        }

        long[] gcdCount = new long[maxVal + 1];
        for (int g = maxVal; g >= 1; g--) {
            long totalMultiples = 0;
            for (int multiple = g; multiple <= maxVal; multiple += g) {
                totalMultiples += count[multiple];
            }

            long totalPairs = totalMultiples * (totalMultiples - 1) / 2;

            for (int multiple = 2 * g; multiple <= maxVal; multiple += g) {
                totalPairs -= gcdCount[multiple];
            }
            gcdCount[g] = totalPairs;
        }

        long[] prefixSums = new long[maxVal + 1];
        for (int i = 1; i <= maxVal; i++) {
            prefixSums[i] = prefixSums[i - 1] + gcdCount[i];
        }

        int[] answer = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            long target = queries[i] + 1;
            int low = 1, high = maxVal;
            int ansIdx = maxVal;

            while (low <= high) {
                int mid = (low + high) >>> 1;
                if (prefixSums[mid] >= target) {
                    ansIdx = mid;
                    high = mid - 1;
                } else {
                    low = mid + 1;
                }
            }
            answer[i] = ansIdx;
        }

        return answer;
    }
}