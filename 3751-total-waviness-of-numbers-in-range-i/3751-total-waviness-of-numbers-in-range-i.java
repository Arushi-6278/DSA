class Solution {
    public int totalWaviness(int num1, int num2) {
        int totalWavinessSum = 0;
        for (int i = num1; i <= num2; i++) {
            totalWavinessSum += countWaviness(i);
        }
        return totalWavinessSum;
    }

    private int countWaviness(int num) {
        if (num < 100) {
            return 0;
        }
        
        String s = Integer.toString(num);
        int length = s.length();
        int waviness = 0;
        
        for (int i = 1; i < length - 1; i++) {
            char prev = s.charAt(i - 1);
            char curr = s.charAt(i);
            char next = s.charAt(i + 1);
            
            if (curr > prev && curr > next) {
                waviness++;
            } else if (curr < prev && curr < next) {
                waviness++;
            }
        }
        
        return waviness;
    }
}