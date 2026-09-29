class Solution {
    public boolean hasValidPath(char[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        if ((m + n - 1) % 2 != 0 || grid[0][0] == ')' || grid[m - 1][n - 1] == '(') {
            return false;
        }

        boolean[][][] visited = new boolean[m][n][(m + n) / 2 + 1];
        return dfs(grid, 0, 0, 0, visited, m, n);
    }

    private boolean dfs(char[][] grid, int r, int c, int open, boolean[][][] visited, int m, int n) {
        if (grid[r][c] == '(') {
            open++;
        } else {
            open--;
        }

        if (open < 0 || open > (m + n) / 2) {
            return false;
        }

        if (r == m - 1 && c == n - 1) {
            return open == 0;
        }

        if (visited[r][c][open]) {
            return false;
        }
        visited[r][c][open] = true;

        if (r + 1 < m && dfs(grid, r + 1, c, open, visited, m, n)) {
            return true;
        }

        if (c + 1 < n && dfs(grid, r, c + 1, open, visited, m, n)) {
            return true;
        }

        return false;
    }
}