import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

class Solution {
    public TreeNode createBinaryTree(int[][] descriptions) {
        Map<Integer, TreeNode> map = new HashMap<>();
        Set<Integer> children = new HashSet<>();

        for (int[] d : descriptions) {
            int pVal = d[0];
            int cVal = d[1];
            boolean isLeft = d[2] == 1;

            map.putIfAbsent(pVal, new TreeNode(pVal));
            map.putIfAbsent(cVal, new TreeNode(cVal));

            TreeNode parent = map.get(pVal);
            TreeNode child = map.get(cVal);

            if (isLeft) {
                parent.left = child;
            } else {
                parent.right = child;
            }

            children.add(cVal);
        }

        for (int[] d : descriptions) {
            int pVal = d[0];
            if (!children.contains(pVal)) {
                return map.get(pVal);
            }
        }

        return null;
    }
}