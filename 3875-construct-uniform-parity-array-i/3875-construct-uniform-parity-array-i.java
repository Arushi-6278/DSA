class Solution {
    public boolean uniformArray(int[] nums1) {
        int n = nums1.length;
        for (int targetParity = 0; targetParity <= 1; targetParity++) {
            boolean possible = true;
            for (int i = 0; i < n; i++) {
                boolean can = false;
                if ((nums1[i] & 1) == targetParity) {
                    can = true;
                }
                for (int j = 0; j < n; j++) {
                    if (i != j) {
                        int diff = nums1[i] - nums1[j];
                        if ((diff & 1) == targetParity) {
                            can = true;
                            break;
                        }
                    }
                }
                if (!can) {
                    possible = false;
                    break;
                }
            }
            if (possible) {
                return true;
            }
        }
        return false;
    }
}