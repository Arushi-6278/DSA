class Solution {
    public int maxActiveSectionsAfterTrade(String s) {
        int n = s.length();
        int ones = 0;
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == '1') ones++;
        }

        String t = "1" + s + "1";
        int m = t.length();

        int[] left = new int[m];
        int[] right = new int[m];

        for (int i = 0; i < m; ) {
            int j = i;
            while (j < m && t.charAt(j) == t.charAt(i)) j++;
            for (int k = i; k < j; k++) {
                left[k] = i;
                right[k] = j - 1;
            }
            i = j;
        }

        int ans = ones;

        for (int i = 1; i < m - 1; ) {
            if (t.charAt(i) == '1') {
                int l = left[i];
                int r = right[i];
                if (l > 0 && r < m - 1 && t.charAt(l - 1) == '0' && t.charAt(r + 1) == '0') {
                    int zeroLeft = l - left[l - 1];
                    int zeroRight = right[r + 1] - r;
                    int oneLen = r - l + 1;
                    ans = Math.max(ans, ones + zeroLeft + zeroRight);
                }
                i = r + 1;
            } else {
                i = right[i] + 1;
            }
        }

        return ans;
    }
}