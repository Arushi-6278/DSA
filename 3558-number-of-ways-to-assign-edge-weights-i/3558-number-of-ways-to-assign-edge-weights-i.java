import java.util.*;

class Solution {
    public int assignEdgeWeights(int[][] edges) {
        int n = edges.length + 1;
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            adj.add(new ArrayList<>());
        }
        for (int[] edge : edges) {
            adj.get(edge[0]).add(edge[1]);
            adj.get(edge[1]).add(edge[0]);
        }

        int maxDepth = 0;
        Queue<int[]> queue = new LinkedList<>();
        boolean[] visited = new boolean[n + 1];
        
        queue.offer(new int[]{1, 0});
        visited[1] = true;

        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            int node = curr[0];
            int depth = curr[1];
            maxDepth = Math.max(maxDepth, depth);

            for (int neighbor : adj.get(node)) {
                if (!visited[neighbor]) {
                    visited[neighbor] = true;
                    queue.offer(new int[]{neighbor, depth + 1});
                }
            }
        }

        long ans = 1;
        long base = 2;
        int power = maxDepth - 1;
        long mod = 1000000007;

        while (power > 0) {
            if ((power & 1) == 1) {
                ans = (ans * base) % mod;
            }
            base = (base * base) % mod;
            power >>= 1;
        }

        return (int) ans;
    }
}