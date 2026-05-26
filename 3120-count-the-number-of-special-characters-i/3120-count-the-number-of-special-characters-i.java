class Solution {
    public int numberOfSpecialChars(String word) {
        boolean[] hasLower = new boolean[26];
        boolean[] hasUpper = new boolean[26];
        
        
        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            if (Character.isLowerCase(ch)) {
                hasLower[ch - 'a'] = true;
            } else {
                hasUpper[ch - 'A'] = true;
            }
        }
        
        
        int specialCount = 0;
        for (int i = 0; i < 26; i++) {
            if (hasLower[i] && hasUpper[i]) {
                specialCount++;
            }
        }
        
        return specialCount;
    }
}