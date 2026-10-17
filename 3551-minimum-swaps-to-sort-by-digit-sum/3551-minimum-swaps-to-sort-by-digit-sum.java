import java.util.Arrays;

class Solution {
    public int minSwaps(int[] nums) {
        int n = nums.length;
        Integer[] p = new Integer[n];
        for (int i = 0; i < n; i++) {
            p[i] = i;
        }
        Arrays.sort(p, (a, b) -> {
            int sumA = digitSum(nums[a]);
            int sumB = digitSum(nums[b]);
            if (sumA != sumB) {
                return Integer.compare(sumA, sumB);
            }
            return Integer.compare(nums[a], nums[b]);
        });
        int[] pos = new int[n];
        for (int i = 0; i < n; i++) {
            pos[p[i]] = i;
        }
        boolean[] visited = new boolean[n];
        int cycles = 0;
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                if (pos[i] == i) {
                    visited[i] = true;
                    cycles++;
                } else {
                    int curr = i;
                    while (!visited[curr]) {
                        visited[curr] = true;
                        curr = pos[curr];
                    }
                    cycles++;
                }
            }
        }
        return n - cycles;
    }
    
    private int digitSum(int n) {
        int sum = 0;
        while (n > 0) {
            sum += n % 10;
            n /= 10;
        }
        return sum;
    }
}