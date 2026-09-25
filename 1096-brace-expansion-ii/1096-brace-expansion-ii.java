import java.util.*;

class Solution {
    public List<String> braceExpansionII(String expression) {
        Set<String> set = parse(expression, 0, expression.length() - 1);
        List<String> res = new ArrayList<>(set);
        Collections.sort(res);
        return res;
    }

    private Set<String> parse(String s, int start, int end) {
        Set<String> res = new HashSet<>();
        List<Set<String>> currentGroups = new ArrayList<>();
        
        int i = start;
        while (i <= end) {
            if (s.charAt(i) == '{') {
                int braceCount = 1;
                int j = i + 1;
                while (j <= end && braceCount > 0) {
                    if (s.charAt(j) == '{') braceCount++;
                    else if (s.charAt(j) == '}') braceCount--;
                    j++;
                }
                currentGroups.add(parse(s, i + 1, j - 2));
                i = j;
            } else if (Character.isLowerCase(s.charAt(i))) {
                Set<String> temp = new HashSet<>();
                temp.add(String.valueOf(s.charAt(i)));
                currentGroups.add(temp);
                i++;
            } else if (s.charAt(i) == ',') {
                res.addAll(combine(currentGroups));
                currentGroups.clear();
                i++;
            }
        }
        
        res.addAll(combine(currentGroups));
        return res;
    }

    private Set<String> combine(List<Set<String>> groups) {
        Set<String> res = new HashSet<>();
        if (groups.isEmpty()) return res;
        res.add("");
        
        for (Set<String> group : groups) {
            Set<String> next = new HashSet<>();
            for (String prefix : res) {
                for (String str : group) {
                    next.add(prefix + str);
                }
            }
            res = next;
        }
        
        return res;
    }
}