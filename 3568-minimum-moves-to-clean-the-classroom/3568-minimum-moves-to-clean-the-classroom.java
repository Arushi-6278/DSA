class Solution {
    public int minMoves(String[] classroom, int maxEnergy) {
        int m = classroom.length;
        int n = classroom[0].length();
        int startX = -1, startY = -1;
        int litterCount = 0;
        int[][] litterIndex = new int[m][n];
        
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                char c = classroom[i].charAt(j);
                if (c == 'S') {
                    startX = i;
                    startY = j;
                } else if (c == 'L') {
                    litterIndex[i][j] = litterCount++;
                }
            }
        }
        
        if (litterCount == 0) return 0;
        
        boolean[][][] visited = new boolean[m * n][1 << litterCount][maxEnergy + 1];
        java.util.Queue<int[]> queue = new java.util.LinkedList<>();
        
        int startCell = startX * n + startY;
        queue.add(new int[]{startCell, 0, maxEnergy, 0});
        visited[startCell][0][maxEnergy] = true;
        
        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        int targetMask = (1 << litterCount) - 1;
        
        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int cell = current[0];
            int mask = current[1];
            int e = current[2];
            int moves = current[3];
            
            if (mask == targetMask) {
                return moves;
            }
            
            if (e == 0) continue;
            
            int r = cell / n;
            int c = cell % n;
            
            for (int[] dir : directions) {
                int nr = r + dir[0];
                int nc = c + dir[1];
                
                if (nr >= 0 && nr < m && nc >= 0 && nc < n) {
                    char nextChar = classroom[nr].charAt(nc);
                    if (nextChar == 'X') continue;
                    
                    int nextMask = mask;
                    if (nextChar == 'L') {
                        nextMask |= (1 << litterIndex[nr][nc]);
                    }
                    
                    int nextEnergy = e - 1;
                    if (nextChar == 'R') {
                        nextEnergy = maxEnergy;
                    }
                    
                    int nextCell = nr * n + nc;
                    if (!visited[nextCell][nextMask][nextEnergy]) {
                        visited[nextCell][nextMask][nextEnergy] = true;
                        queue.add(new int[]{nextCell, nextMask, nextEnergy, moves + 1});
                    }
                }
            }
        }
        
        return -1;
    }
}