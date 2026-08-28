class Solution {
    public String lexPalindromicPermutation(String s, String target) {
        int n = s.length();
        int[] count = new int[26];
        for (char c : s.toCharArray()) {
            count[c - 'a']++;
        }
        
        int oddCount = 0;
        char midChar = 0;
        for (int i = 0; i < 26; i++) {
            if (count[i] % 2 != 0) {
                oddCount++;
                midChar = (char) ('a' + i);
            }
        }
        
        if (n % 2 == 0 && oddCount != 0) return "";
        if (n % 2 != 0 && oddCount != 1) return "";
        
        int[] halfCount = new int[26];
        for (int i = 0; i < 26; i++) {
            halfCount[i] = count[i] / 2;
        }
        
        int halfLen = n / 2;
        String tHalf = target.substring(0, halfLen);
        
        for (int L = halfLen; L >= 0; L--) {
            int[] remCount = halfCount.clone();
            boolean possible = true;
            for (int i = 0; i < L; i++) {
                char c = tHalf.charAt(i);
                if (remCount[c - 'a'] > 0) {
                    remCount[c - 'a']--;
                } else {
                    possible = false;
                    break;
                }
            }
            if (!possible) continue;
            
            int startChar = (L == halfLen) ? 26 : (tHalf.charAt(L) - 'a' + 1);
            
            for (int c = (L == halfLen ? 0 : startChar); c < 26; c++) {
                if (L < halfLen && remCount[c] == 0) continue;
                
                int[] curRem = remCount.clone();
                StringBuilder firstHalf = new StringBuilder();
                firstHalf.append(tHalf, 0, L);
                
                if (L < halfLen) {
                    firstHalf.append((char) ('a' + c));
                    curRem[c]--;
                }
                
                for (int i = 0; i < 26; i++) {
                    while (curRem[i] > 0) {
                        firstHalf.append((char) ('a' + i));
                        curRem[i]--;
                    }
                }
                
                StringBuilder full = new StringBuilder(firstHalf);
                if (n % 2 != 0) {
                    full.append(midChar);
                }
                for (int i = halfLen - 1; i >= 0; i--) {
                    full.append(firstHalf.charAt(i));
                }
                
                String cand = full.toString();
                if (cand.compareTo(target) > 0) {
                    return cand;
                }
            }
        }
        
        return "";
    }
}