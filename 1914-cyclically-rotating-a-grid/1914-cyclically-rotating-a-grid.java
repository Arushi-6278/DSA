class Solution {
    public int[][] rotateGrid(int[][] grid, int k) {
        int m = grid.length;
        int n = grid[0].length;
        int numLayers = Math.min(m, n) / 2;

        for (int layer = 0; layer < numLayers; layer++) {
       
            List<Integer> elements = new ArrayList<>();
            
           
            for (int j = layer; j < n - layer - 1; j++) elements.add(grid[layer][j]);
           
            for (int i = layer; i < m - layer - 1; i++) elements.add(grid[i][n - layer - 1]);
        
            for (int j = n - layer - 1; j > layer; j--) elements.add(grid[m - layer - 1][j]);
        
            for (int i = m - layer - 1; i > layer; i--) elements.add(grid[i][layer]);

           
            int size = elements.size();
            int netRotation = k % size;

           
            int index = 0;
            
            // Top row
            for (int j = layer; j < n - layer - 1; j++) 
                grid[layer][j] = elements.get((index++ + netRotation) % size);
        
            for (int i = layer; i < m - layer - 1; i++) 
                grid[i][n - layer - 1] = elements.get((index++ + netRotation) % size);
          
            for (int j = n - layer - 1; j > layer; j--) 
                grid[m - layer - 1][j] = elements.get((index++ + netRotation) % size);
          
            for (int i = m - layer - 1; i > layer; i--) 
                grid[i][layer] = elements.get((index++ + netRotation) % size);
        }

        return grid;
    }
}