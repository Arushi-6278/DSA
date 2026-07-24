class Solution {
    public int uniqueXorTriplets(int[] nums) {
        boolean[] exists = new boolean[2048];
        int uniqueCount = 0;
        
        for (int x : nums) {
            if (!exists[x]) {
                exists[x] = true;
                uniqueCount++;
            }
        }
        
        int[] uniqueNums = new int[uniqueCount];
        int index = 0;
        for (int i = 0; i < 2048; i++) {
            if (exists[i]) {
                uniqueNums[index++] = i;
            }
        }
        
        boolean[] xorPairExists = new boolean[2048];
        for (int i = 0; i < uniqueCount; i++) {
            for (int j = i; j < uniqueCount; j++) {
                xorPairExists[uniqueNums[i] ^ uniqueNums[j]] = true;
            }
        }
        
        boolean[] resultSet = new boolean[2048];
        int result = 0;
        
        for (int i = 0; i < 2048; i++) {
            if (!xorPairExists[i]) continue;
            for (int k = 0; k < uniqueCount; k++) {
                int val = i ^ uniqueNums[k];
                if (!resultSet[val]) {
                    resultSet[val] = true;
                    result++;
                }
            }
        }
        
        return result;
    }
}