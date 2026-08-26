class Solution {
    public String shortestBeautifulSubstring(String s, int k) {
        int n = s.length();
        int minLen = n + 1;
        String ans = "";

        for (int i = 0; i < n; i++) {
            int ones = 0;
            for (int j = i; j < n; j++) {
                if (s.charAt(j) == '1') {
                    ones++;
                }
                if (ones == k) {
                    int len = j - i + 1;
                    String sub = s.substring(i, j + 1);
                    if (len < minLen) {
                        minLen = len;
                        ans = sub;
                    } else if (len == minLen) {
                        if (sub.compareTo(ans) < 0) {
                            ans = sub;
                        }
                    }
                    break;
                }
            }
        }

        return ans;
    }
}