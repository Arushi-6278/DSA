import java.util.Arrays;

class Solution {
    public int minimumCost(int[] cost) {
       
        Arrays.sort(cost);
        
        int totalCost = 0;
        int n = cost.length;
        
       
        int count = 0;
        for (int i = n - 1; i >= 0; i--) {
            count++;
           
            if (count % 3 != 0) {
                totalCost += cost[i];
            }
        }
        
        return totalCost;
    }
}