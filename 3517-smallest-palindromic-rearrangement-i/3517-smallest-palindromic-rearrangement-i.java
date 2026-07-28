class Solution {
public String smallestPalindrome(String s) {
int[] count = new int[26];
int n = s.length();

    for (int i = 0; i < n; i++) {
        count[s.charAt(i) - 'a']++;
    }
    
    char[] leftHalf = new char[n / 2];
    char middleChar = 0;
    int index = 0;
    
    for (int i = 0; i < 26; i++) {
        if (count[i] % 2 != 0) {
            middleChar = (char) ('a' + i);
        }
        int halfCount = count[i] / 2;
        for (int j = 0; j < halfCount; j++) {
            leftHalf[index++] = (char) ('a' + i);
        }
    }
    
    StringBuilder sb = new StringBuilder();
    sb.append(leftHalf);
    if (n % 2 != 0) {
        sb.append(middleChar);
    }
    for (int i = leftHalf.length - 1; i >= 0; i--) {
        sb.append(leftHalf[i]);
    }
    
    return sb.toString();
}
}