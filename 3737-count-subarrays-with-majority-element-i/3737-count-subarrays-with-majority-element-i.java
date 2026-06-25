import java.util.HashMap;

class Solution {
    public int countMajoritySubarrays(int[] nums, int target) {
        int n = nums.length;
        int count = 0;
        int prefixSum = 0;
        
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        
        for (int i = 0; i < n; i++) {
            if (nums[i] == target) {
                prefixSum += 1;
            } else {
                prefixSum -= 1;
            }
            
            for (int sum : map.keySet()) {
                if (sum < prefixSum) {
                    count += map.get(sum);
                }
            }
            
            map.put(prefixSum, map.getOrDefault(prefixSum, 0) + 1);
        }
        
        return count;
    }
}