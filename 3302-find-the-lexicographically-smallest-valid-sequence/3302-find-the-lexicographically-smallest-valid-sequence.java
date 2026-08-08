class Solution {
    public int[] validSequence(String word1, String word2) {
        int n = word1.length();
        int m = word2.length();
        
        int[] last = new int[n];
        int j = m - 1;
        for (int i = n - 1; i >= 0; i--) {
            if (j >= 0 && word1.charAt(i) == word2.charAt(j)) {
                j--;
            }
            last[i] = j;
        }

        int[] result = new int[m];
        int rIdx = 0;
        j = 0; 
        boolean usedDiff = false;

        for (int i = 0; i < n && j < m; i++) {
            if (word1.charAt(i) == word2.charAt(j)) {
                result[rIdx++] = i;
                j++;
            } else if (!usedDiff) {
                int nextSuffixLenNeeded = m - (j + 1);
                if (nextSuffixLenNeeded == 0 || (i + 1 < n && (m - 1 - last[i + 1]) >= nextSuffixLenNeeded)) {
                    usedDiff = true;
                    result[rIdx++] = i;
                    j++;
                }
            }
        }

        if (j == m) {
            return result;
        }
        return new int[0];
    }
}
