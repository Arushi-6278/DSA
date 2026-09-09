class Solution {
    public long countCommas(long n) {
        long count = 0;
        long start = 1000;
        while (start <= n) {
            count += n - start + 1;
            start *= 1000;
        }
        return count;
    }
}