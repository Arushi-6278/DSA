class Solution {
    public int countCompleteComponents(int n, int[][] edges) {
        int[] parent = new int[n];
        int[] rank = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
            rank[i] = 0;
        }
        
        for (int[] edge : edges) {
            union(parent, rank, edge[0], edge[1]);
        }
        
        int[] vertexCount = new int[n];
        int[] edgeCount = new int[n];
        
        for (int i = 0; i < n; i++) {
            int root = find(parent, i);
            vertexCount[root]++;
        }
        
        for (int[] edge : edges) {
            int root = find(parent, edge[0]);
            edgeCount[root]++;
        }
        
        int result = 0;
        for (int i = 0; i < n; i++) {
            if (parent[i] == i) {
                int v = vertexCount[i];
                int expectedEdges = v * (v - 1) / 2;
                if (edgeCount[i] == expectedEdges) {
                    result++;
                }
            }
        }
        return result;
    }
    
    private int find(int[] parent, int x) {
        if (parent[x] != x) {
            parent[x] = find(parent, parent[x]);
        }
        return parent[x];
    }
    
    private void union(int[] parent, int[] rank, int x, int y) {
        int rootX = find(parent, x);
        int rootY = find(parent, y);
        if (rootX == rootY) return;
        if (rank[rootX] < rank[rootY]) {
            parent[rootX] = rootY;
        } else if (rank[rootX] > rank[rootY]) {
            parent[rootY] = rootX;
        } else {
            parent[rootY] = rootX;
            rank[rootX]++;
        }
    }
}