class Solution {
    public int minMoves(int[] nums, int limit) {
      
        int[] diff = new int[2 * limit + 2];
        int n = nums.length;

        for (int i = 0; i < n / 2; i++) {
            int a = nums[i];
            int b = nums[n - 1 - i];

            
            int minVal = Math.min(a, b);
            int maxVal = Math.max(a, b);

           
            diff[2] += 2;
            diff[2 * limit + 1] -= 2;

            diff[minVal + 1] -= 1;
            diff[maxVal + limit + 1] += 1;

         
            diff[a + b] -= 1;
            diff[a + b + 1] += 1;
        }

        int minMoves = n; 
        int currentMoves = 0;
        
      
        for (int i = 2; i <= 2 * limit; i++) {
            currentMoves += diff[i];
            minMoves = Math.min(minMoves, currentMoves);
        }

        return minMoves;
    }
}