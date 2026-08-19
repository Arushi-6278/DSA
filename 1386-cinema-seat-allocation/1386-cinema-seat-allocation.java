import java.util.HashMap;
import java.util.Map;

class Solution {
    public int maxNumberOfFamilies(int n, int[][] reservedSeats) {
        Map<Integer, Integer> rowMasks = new HashMap<>();
        
        for (int[] seat : reservedSeats) {
            int row = seat[0];
            int col = seat[1];
            if (col >= 2 && col <= 9) {
                rowMasks.put(row, rowMasks.getOrDefault(row, 0) | (1 << (col - 2)));
            }
        }
        
        int totalGroups = (n - rowMasks.size()) * 2;
        
        for (int mask : rowMasks.values()) {
            boolean leftAvailable = (mask & 0b00001111) == 0;
            boolean rightAvailable = (mask & 0b11110000) == 0;
            boolean middleAvailable = (mask & 0b00111100) == 0;
            
            if (leftAvailable && rightAvailable) {
                totalGroups += 2;
            } else if (leftAvailable || rightAvailable || middleAvailable) {
                totalGroups += 1;
            }
        }
        
        return totalGroups;
    }
}