class Solution {
    public long findKthSmallest(int[] coins, int k) {
        int n = coins.length;
        long low = 1;
        long high = (long) k * 25;
        long ans = high;

        while (low <= high) {
            long mid = low + (high - low) / 2;
            if (countAmounts(mid, coins, n) >= k) {
                ans = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return ans;
    }

    private long countAmounts(long target, int[] coins, int n) {
        long count = 0;
        int totalSubsets = 1 << n;

        for (int mask = 1; mask < totalSubsets; mask++) {
            long lcmVal = 1;
            int setBits = 0;
            boolean overflow = false;

            for (int i = 0; i < n; i++) {
                if ((mask & (1 << i)) != 0) {
                    setBits++;
                    lcmVal = lcm(lcmVal, coins[i]);
                    if (lcmVal > target) {
                        overflow = true;
                        break;
                    }
                }
            }

            if (!overflow) {
                if (setBits % 2 == 1) {
                    count += target / lcmVal;
                } else {
                    count -= target / lcmVal;
                }
            }
        }
        return count;
    }

    private long gcd(long a, long b) {
        while (b != 0) {
            long temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }

    private long lcm(long a, long b) {
        if (a == 0 || b == 0) return 0;
        return (a / gcd(a, b)) * b;
    }
}