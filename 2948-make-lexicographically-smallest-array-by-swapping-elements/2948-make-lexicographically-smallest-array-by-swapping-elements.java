class Solution {
    public int[] lexicographicallySmallestArray(int[] nums, int limit) {
        int n = nums.length;
        int[][] sortedWithIndices = new int[n][2];
        for (int i = 0; i < n; i++) {
            sortedWithIndices[i][0] = nums[i];
            sortedWithIndices[i][1] = i;
        }

        java.util.Arrays.sort(sortedWithIndices, (a, b) -> Integer.compare(a[0], b[0]));

        int[] result = new int[n];
        int groupStart = 0;

        for (int i = 0; i < n; i++) {
            if (i == n - 1 || sortedWithIndices[i + 1][0] - sortedWithIndices[i][0] > limit) {
                int groupSize = i - groupStart + 1;
                int[] indices = new int[groupSize];

                for (int j = 0; j < groupSize; j++) {
                    indices[j] = sortedWithIndices[groupStart + j][1];
                }

                java.util.Arrays.sort(indices);

                for (int j = 0; j < groupSize; j++) {
                    result[indices[j]] = sortedWithIndices[groupStart + j][0];
                }

                groupStart = i + 1;
            }
        }

        return result;
    }
}