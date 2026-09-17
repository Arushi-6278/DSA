class Solution {
    public int minSumOfLengths(int[] arr, int target) {
        int n = arr.length;
        int[] minLen = new int[n];
        for (int i = 0; i < n; i++) {
            minLen[i] = Integer.MAX_VALUE;
        }

        int left = 0, currentSum = 0;
        int bestLeft = Integer.MAX_VALUE;
        int result = Integer.MAX_VALUE;

        for (int right = 0; right < n; right++) {
            currentSum += arr[right];

            while (currentSum > target && left <= right) {
                currentSum -= arr[left];
                left++;
            }

            if (currentSum == target) {
                int currentLen = right - left + 1;
                if (left > 0 && minLen[left - 1] != Integer.MAX_VALUE) {
                    result = Math.min(result, currentLen + minLen[left - 1]);
                }
                bestLeft = Math.min(bestLeft, currentLen);
            }

            minLen[right] = bestLeft;
        }

        return result == Integer.MAX_VALUE ? -1 : result;
    }
}