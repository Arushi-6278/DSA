class Solution {
    public boolean sumGame(String num) {
        int n = num.length();
        int sumDiff = 0;
        int qDiff = 0;

        for (int i = 0; i < n / 2; i++) {
            if (num.charAt(i) == '?') {
                qDiff++;
            } else {
                sumDiff += num.charAt(i) - '0';
            }
        }

        for (int i = n / 2; i < n; i++) {
            if (num.charAt(i) == '?') {
                qDiff--;
            } else {
                sumDiff -= num.charAt(i) - '0';
            }
        }

        if ((qDiff & 1) != 0) {
            return true;
        }

        return sumDiff + qDiff / 2 * 9 != 0;
    }
}