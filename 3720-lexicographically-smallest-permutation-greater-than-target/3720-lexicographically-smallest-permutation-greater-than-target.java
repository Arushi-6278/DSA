class Solution {
    public String lexGreaterPermutation(String s, String target) {
        int n = s.length();
        int[] count = new int[26];
        for (int i = 0; i < n; i++) {
            count[s.charAt(i) - 'a']++;
        }

        for (int i = n - 1; i >= 0; i--) {
            int[] currentCount = count.clone();
            boolean validPrefix = true;
            for (int j = 0; j < i; j++) {
                int charIdx = target.charAt(j) - 'a';
                if (currentCount[charIdx] > 0) {
                    currentCount[charIdx]--;
                } else {
                    validPrefix = false;
                    break;
                }
            }

            if (!validPrefix) {
                continue;
            }

            int targetCharIdx = target.charAt(i) - 'a';
            for (int choice = targetCharIdx + 1; choice < 26; choice++) {
                if (currentCount[choice] > 0) {
                    StringBuilder result = new StringBuilder();
                    result.append(target.substring(0, i));
                    result.append((char) ('a' + choice));
                    currentCount[choice]--;

                    for (int c = 0; c < 26; c++) {
                        while (currentCount[c] > 0) {
                            result.append((char) ('a' + c));
                            currentCount[c]--;
                        }
                    }
                    return result.toString();
                }
            }
        }

        return "";
    }
}