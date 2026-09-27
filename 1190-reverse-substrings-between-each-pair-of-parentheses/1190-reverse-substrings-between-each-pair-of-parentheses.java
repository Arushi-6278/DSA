class Solution {
    public String reverseParentheses(String s) {
        int n = s.length();
        int[] pair = new int[n];
        int[] stack = new int[n];
        int top = -1;

        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            if (c == '(') {
                stack[++top] = i;
            } else if (c == ')') {
                int j = stack[top--];
                pair[i] = j;
                pair[j] = i;
            }
        }

        StringBuilder sb = new StringBuilder();
        int index = 0;
        int direction = 1;

        while (index < n) {
            char c = s.charAt(index);
            if (c == '(' || c == ')') {
                index = pair[index];
                direction = -direction;
            } else {
                sb.append(c);
            }
            index += direction;
        }

        return sb.toString();
    }
}