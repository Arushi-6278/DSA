class Solution {
    public int minAddToMakeValid(String s) {
        int openCount = 0;
        int minMoves = 0;
        
        for (char c : s.toCharArray()) {
            if (c == '(') {
                openCount++;
            } else {
                if (openCount > 0) {
                    openCount--;
                } else {
                    minMoves++;
                }
            }
        }
        
        return minMoves + openCount;
    }
}