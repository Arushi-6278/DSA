import java.util.*;

class Solution {
    
    private int[] tree;
    private int n;

    private void update(int node, int start, int end, int idx, int val) {
        if (start == end) {
            tree[node] = val;
            return;
        }
        int mid = start + (end - start) / 2;
        if (idx <= mid) {
            update(2 * node, start, mid, idx, val);
        } else {
            update(2 * node + 1, mid + 1, end, idx, val);
        }
        tree[node] = Math.max(tree[2 * node], tree[2 * node + 1]);
    }

    private int query(int node, int start, int end, int l, int r) {
        if (r < start || end < l) {
            return 0;
        }
        if (l <= start && end <= r) {
            return tree[node];
        }
        int mid = start + (end - start) / 2;
        int p1 = query(2 * node, start, mid, l, r);
        int p2 = query(2 * node + 1, mid + 1, end, l, r);
        return Math.max(p1, p2);
    }

    public List<Boolean> getResults(int[][] queries) {
        
        int max_x = 0;
        for (int[] q : queries) {
            max_x = Math.max(max_x, q[1]);
        }
        
      
        n = max_x + 1;
        tree = new int[4 * n];
        
        TreeSet<Integer> obstacles = new TreeSet<>();
        obstacles.add(0); 
        
        List<Boolean> results = new ArrayList<>();
        
        for (int[] q : queries) {
            int type = q[0];
            if (type == 1) {
                int x = q[1];
                
               
                Integer prev = obstacles.lower(x);
                Integer next = obstacles.higher(x);
                
               
                update(1, 0, n - 1, x, x - prev);
                
                
                if (next != null) {
                    update(1, 0, n - 1, next, next - x);
                }
                
                obstacles.add(x);
            } else {
                int x = q[1];
                int sz = q[2];
                
               
                int prev = obstacles.floor(x);
                
              
                int maxGapInObstacles = query(1, 0, n - 1, 0, prev);
                
                
                int trailingGap = x - prev;
                
                int maxPossibleBlock = Math.max(maxGapInObstacles, trailingGap);
                
                results.add(maxPossibleBlock >= sz);
            }
        }
        
        return results;
    }
}