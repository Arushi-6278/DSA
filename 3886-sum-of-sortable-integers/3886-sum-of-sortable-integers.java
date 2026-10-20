import java.util.Arrays;

class Solution {
    public int sortableIntegers(int[] nums) {
        int n = nums.length;
        int[] sorted = nums.clone();
        Arrays.sort(sorted);
        
        int sum = 0;
        for (int k = 1; k <= n; k++) {
            if (n % k == 0) {
                if (check(nums, sorted, k)) {
                    sum += k;
                }
            }
        }
        return sum;
    }
    
    private boolean check(int[] nums, int[] sorted, int k) {
        int n = nums.length;
        for (int i = 0; i < n; i += k) {
            int dropCount = 0;
            int dropIdx = -1;
            for (int j = 0; j < k - 1; j++) {
                if (nums[i + j] > nums[i + j + 1]) {
                    dropCount++;
                    dropIdx = j;
                    if (dropCount > 1) return false;
                }
            }
            if (dropCount == 0) {
                for (int j = 0; j < k; j++) {
                    if (nums[i + j] != sorted[i + j]) return false;
                }
            } else {
                int sIdx = i;
                for (int j = dropIdx + 1; j < k; j++) {
                    if (nums[i + j] != sorted[sIdx++]) return false;
                }
                for (int j = 0; j <= dropIdx; j++) {
                    if (nums[i + j] != sorted[sIdx++]) return false;
                }
            }
        }
        return true;
    }
}
