class Solution {
    public char processStr(String s, long k) {
        java.util.List<Long> lengths = new java.util.ArrayList<>();
        java.util.List<Character> ops = new java.util.ArrayList<>();
        
        long len = 0;
        for (char c : s.toCharArray()) {
            if (c >= 'a' && c <= 'z') {
                len++;
                ops.add(c);
                lengths.add(len);
            } else if (c == '*') {
                if (len > 0) len--;
                ops.add('*');
                lengths.add(len);
            } else if (c == '#') {
                len *= 2;
                ops.add('#');
                lengths.add(len);
            } else if (c == '%') {
                ops.add('%');
                lengths.add(len);
            }
        }
        
        if (k >= len) return '.';
        
        long idx = k;
        for (int i = ops.size() - 1; i >= 0; i--) {
            char op = ops.get(i);
            long prevLen = (i > 0) ? lengths.get(i - 1) : 0;
            
            if (op >= 'a' && op <= 'z') {
                if (idx == prevLen) return op;
            } else if (op == '*') {
                if (idx >= prevLen) {
                    idx = prevLen;
                }
            } else if (op == '#') {
                if (idx >= prevLen) {
                    idx -= prevLen;
                }
            } else if (op == '%') {
                idx = prevLen - 1 - idx;
            }
        }
        return '.';
    }
}