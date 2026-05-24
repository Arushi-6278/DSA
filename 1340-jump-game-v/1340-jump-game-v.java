class Solution {
    public int maxJumps(int[] arr, int d) {
        int n = arr.length;
        int[] dp = new int[n];
        int maxVisited = 0;
        
       
        for (int i = 0; i < n; i++) {
            maxVisited = Math.max(maxVisited, dfs(arr, d, i, dp));
        }
        
        return maxVisited;
    }
    
    private int dfs(int[] arr, int d, int i, int[] dp) {
       
        if (dp[i] != 0) {
            return dp[i];
        }
        
        int currentMax = 1; 
        int n = arr.length;
        
      
        for (int x = 1; x <= d && i + x < n; x++) {
           
            if (arr[i + x] >= arr[i]) {
                break;
            }
            currentMax = Math.max(currentMax, 1 + dfs(arr, d, i + x, dp));
        }
        
       
        for (int x = 1; x <= d && i - x >= 0; x++) {
         
            if (arr[i - x] >= arr[i]) {
                break;
            }
            currentMax = Math.max(currentMax, 1 + dfs(arr, d, i - x, dp));
        }
        
        
        dp[i] = currentMax;
        return currentMax;
    }
}