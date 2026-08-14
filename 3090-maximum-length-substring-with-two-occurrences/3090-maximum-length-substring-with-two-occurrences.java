class Solution {
    public int maximumLengthSubstring(String s) {
        int[] freq = new int[26];
        int maxLen = 0;
        int left = 0;
        
        for (int right = 0; right < s.length(); right++) {
            char currentChar = s.charAt(right);
            freq[currentChar - 'a']++;
            
            while (freq[currentChar - 'a'] > 2) {
                freq[s.charAt(left) - 'a']--;
                left++;
            }
            
            maxLen = Math.max(maxLen, right - left + 1);
        }
        
        return maxLen;
    }
}