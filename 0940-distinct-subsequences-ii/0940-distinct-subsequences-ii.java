class Solution {
    public int distinctSubseqII(String s) {
        int mod = 1000000007;
        int[] last = new int[26];
        long total = 0;

        for (char c : s.toCharArray()) {
            int idx = c - 'a';
            long added = (total + 1 - last[idx] + mod) % mod;
            total = (total + added) % mod;
            last[idx] = (last[idx] + (int) added) % mod;
        }

        return (int) total;
    }
}