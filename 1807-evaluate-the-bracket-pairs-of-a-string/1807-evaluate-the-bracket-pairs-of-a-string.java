class Solution {
    public String evaluate(String s, List<List<String>> knowledge) {
        Map<String, String> map = new HashMap<>();
        for (List<String> k : knowledge) {
            map.put(k.get(0), k.get(1));
        }
        
        StringBuilder result = new StringBuilder();
        StringBuilder key = new StringBuilder();
        boolean inBracket = false;
        
        for (char c : s.toCharArray()) {
            if (c == '(') {
                inBracket = true;
            } else if (c == ')') {
                inBracket = false;
                result.append(map.getOrDefault(key.toString(), "?"));
                key.setLength(0);
            } else {
                if (inBracket) {
                    key.append(c);
                } else {
                    result.append(c);
                }
            }
        }
        
        return result.toString();
    }
}