import java.util.Arrays;

class Solution {
    public long gcdSum(int[] nums) {
        int n = nums.length;
        long[] prefixGcd = new long[n];
        long maxSoFar = 0;
        
        for (int i = 0; i < n; i++) {
            if (nums[i] > maxSoFar) {
                maxSoFar = nums[i];
            }
            prefixGcd[i] = gcd(nums[i], maxSoFar);
        }
        
        Arrays.sort(prefixGcd);
        
        long sum = 0;
        int left = 0;
        int right = n - 1;
        
        while (left < right) {
            sum += gcd(prefixGcd[left], prefixGcd[right]);
            left++;
            right--;
        }
        
        return sum;
    }
    
    private long gcd(long a, long b) {
        while (b != 0) {
            long temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }
}