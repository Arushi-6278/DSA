class Solution {
    public int countCommas(int n) {
        int count = 0;
        for (int i = 1000; i <= n; i *= 1000) {
            count += n - i + 1;
        }
        return count;
    }
}