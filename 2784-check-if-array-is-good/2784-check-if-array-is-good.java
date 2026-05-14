class Solution {
    public boolean isGood(int[] nums) {
        int n = nums.length - 1;
        
        
        if (n < 1) {
            return false;
        }

        
        int[] counts = new int[201];
        int maxVal = 0;

        for (int num : nums) {
            counts[num]++;
            if (num > maxVal) {
                maxVal = num;
            }
        }

   
        if (maxVal != n) {
            return false;
        }

        for (int i = 1; i < n; i++) {
            if (counts[i] != 1) {
                return false;
            }
        }

        return counts[n] == 2;
    }
}