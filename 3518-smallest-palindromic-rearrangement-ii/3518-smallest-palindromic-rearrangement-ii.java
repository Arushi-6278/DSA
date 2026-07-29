class Solution {
    public String smallestPalindrome(String s, int k) {
        int[] count = new int[26];
        for (int i = 0; i < s.length(); i++) {
            count[s.charAt(i) - 'a']++;
        }

        int[] halfCount = new int[26];
        int totalLen = 0;
        Character center = null;

        for (int i = 0; i < 26; i++) {
            if (count[i] % 2 != 0) {
                center = (char) ('a' + i);
            }
            halfCount[i] = count[i] / 2;
            totalLen += halfCount[i];
        }

        long totalPerms = countPermutations(halfCount, totalLen, k + 1);
        if (totalPerms < k) {
            return "";
        }

        StringBuilder half = new StringBuilder();
        long target = k;

        for (int i = 0; i < totalLen; i++) {
            for (int c = 0; c < 26; c++) {
                if (halfCount[c] > 0) {
                    halfCount[c]--;
                    long perms = countPermutations(halfCount, totalLen - 1 - i, target + 1);
                    if (target <= perms) {
                        half.append((char) ('a' + c));
                        break;
                    } else {
                        target -= perms;
                        halfCount[c]++;
                    }
                }
            }
        }

        StringBuilder result = new StringBuilder(half);
        if (center != null) {
            result.append(center);
        }
        for (int i = half.length() - 1; i >= 0; i--) {
            result.append(half.charAt(i));
        }

        return result.toString();
    }

    private long countPermutations(int[] halfCount, int remLen, long cap) {
        long total = 1;
        int rem = remLen;
        for (int i = 0; i < 26; i++) {
            if (halfCount[i] > 0) {
                long comb = nCr(rem, halfCount[i], cap);
                total = multiplyCapped(total, comb, cap);
                if (total >= cap) {
                    return cap;
                }
                rem -= halfCount[i];
            }
        }
        return total;
    }

    private long nCr(int n, int r, long cap) {
        if (r > n - r) {
            r = n - r;
        }
        long res = 1;
        for (int i = 1; i <= r; i++) {
            res = res * (n - r + i) / i;
            if (res >= cap) {
                return cap;
            }
        }
        return res;
    }

    private long multiplyCapped(long a, long b, long cap) {
        if (a == 0 || b == 0) return 0;
        if (a > cap / b) return cap;
        return Math.min(a * b, cap);
    }
}