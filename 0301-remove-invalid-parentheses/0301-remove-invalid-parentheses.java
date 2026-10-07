import java.util.*;

class Solution {
    public List<String> removeInvalidParentheses(String s) {
        int leftRem = 0, rightRem = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(') {
                leftRem++;
            } else if (c == ')') {
                if (leftRem > 0) {
                    leftRem--;
                } else {
                    rightRem++;
                }
            }
        }

        Set<String> result = new HashSet<>();
        backtrack(s, 0, 0, 0, leftRem, rightRem, new StringBuilder(), result);
        return new ArrayList<>(result);
    }

    private void backtrack(String s, int index, int open, int close, int leftRem, int rightRem, StringBuilder path, Set<String> result) {
        if (index == s.length()) {
            if (leftRem == 0 && rightRem == 0) {
                result.add(path.toString());
            }
            return;
        }

        char c = s.charAt(index);
        int len = path.length();

        if (c == '(') {
            if (leftRem > 0) {
                backtrack(s, index + 1, open, close, leftRem - 1, rightRem, path, result);
            }
            path.append(c);
            backtrack(s, index + 1, open + 1, close, leftRem, rightRem, path, result);
            path.setLength(len);
        } else if (c == ')') {
            if (rightRem > 0) {
                backtrack(s, index + 1, open, close, leftRem, rightRem - 1, path, result);
            }
            if (open > close) {
                path.append(c);
                backtrack(s, index + 1, open, close + 1, leftRem, rightRem, path, result);
                path.setLength(len);
            }
        } else {
            path.append(c);
            backtrack(s, index + 1, open, close, leftRem, rightRem, path, result);
            path.setLength(len);
        }
    }
}