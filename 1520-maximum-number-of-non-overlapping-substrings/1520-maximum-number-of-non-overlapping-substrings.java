class Solution {
    public List<String> maxNumOfSubstrings(String s) {
        int n = s.length();
        int[] first = new int[26];
        int[] last = new int[26];
        java.util.Arrays.fill(first, -1);
        java.util.Arrays.fill(last, -1);

        for (int i = 0; i < n; i++) {
            int ch = s.charAt(i) - 'a';
            if (first[ch] == -1) {
                first[ch] = i;
            }
            last[ch] = i;
        }

        List<int[]> intervals = new java.util.ArrayList<>();
        for (int i = 0; i < 26; i++) {
            if (first[i] != -1) {
                int left = first[i];
                int right = last[i];
                boolean valid = true;

                for (int j = left; j <= right; j++) {
                    int ch = s.charAt(j) - 'a';
                    if (first[ch] < left) {
                        valid = false;
                        break;
                    }
                    right = Math.max(right, last[ch]);
                }

                if (valid) {
                    intervals.add(new int[]{left, right});
                }
            }
        }

        intervals.sort((a, b) -> Integer.compare(a[1], b[1]));

        List<String> result = new java.util.ArrayList<>();
        int prevRight = -1;

        for (int[] interval : intervals) {
            if (interval[0] > prevRight) {
                result.add(s.substring(interval[0], interval[1] + 1));
                prevRight = interval[1];
            }
        }

        return result;
    }
}