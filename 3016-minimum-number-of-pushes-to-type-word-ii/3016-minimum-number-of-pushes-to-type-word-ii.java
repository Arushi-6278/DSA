class Solution {
    public int minimumPushes(String word) {
        int[] counts = new int[26];
        for (char c : word.toCharArray()) {
            counts[c - 'a']++;
        }

        Arrays.sort(counts);

        int totalPushes = 0;
        int mapped = 0;

        for (int i = 25; i >= 0; i--) {
            if (counts[i] == 0) break;
            
            int pushesPerChar = (mapped / 8) + 1;
            totalPushes += counts[i] * pushesPerChar;
            mapped++;
        }

        return totalPushes;
    }
}