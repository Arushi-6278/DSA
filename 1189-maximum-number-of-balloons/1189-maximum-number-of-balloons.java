class Solution {
    public int maxNumberOfBalloons(String text) {
        int[] counts = new int[26];
        for (int i = 0; i < text.length(); i++) {
            counts[text.charAt(i) - 'a']++;
        }

        int b = counts['b' - 'a'];
        int a = counts['a' - 'a'];
        int l = counts['l' - 'a'] / 2;
        int o = counts['o' - 'a'] / 2;
        int n = counts['n' - 'a'];

        int min = b;
        if (a < min) min = a;
        if (l < min) min = l;
        if (o < min) min = o;
        if (n < min) min = n;

        return min;
    }
}