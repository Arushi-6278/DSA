class Solution {
    public long sumAndMultiply(int n) {
        if (n == 0) {
            return 0;
        }

        long x = 0;
        long multiplier = 1;
        long sum = 0;

        while (n > 0) {
            int digit = n % 10;
            if (digit != 0) {
                x = digit * multiplier + x;
                multiplier *= 10;
                sum += digit;
            }
            n /= 10;
        }

        return x * sum;
    }
}