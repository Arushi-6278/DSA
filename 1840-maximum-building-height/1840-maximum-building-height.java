import java.util.Arrays;

class Solution {
    public int maxBuilding(int n, int[][] restrictions) {
        int m = restrictions.length;
        if (m == 0) {
            return n - 1;
        }

        int[][] allRestrictions = new int[m + 2][2];
        allRestrictions[0] = new int[]{1, 0};
        for (int i = 0; i < m; i++) {
            allRestrictions[i + 1] = restrictions[i];
        }
        allRestrictions[m + 1] = new int[]{n, n - 1};

        Arrays.sort(allRestrictions, (a, b) -> Integer.compare(a[0], b[0]));

        int total = allRestrictions.length;

        for (int i = 1; i < total; i++) {
            allRestrictions[i][1] = Math.min(allRestrictions[i][1], allRestrictions[i - 1][1] + (allRestrictions[i][0] - allRestrictions[i - 1][0]));
        }

        for (int i = total - 2; i >= 0; i--) {
            allRestrictions[i][1] = Math.min(allRestrictions[i][1], allRestrictions[i + 1][1] + (allRestrictions[i + 1][0] - allRestrictions[i][0]));
        }

        int max_height = 0;
        for (int i = 0; i < total - 1; i++) {
            int id1 = allRestrictions[i][0];
            int h1 = allRestrictions[i][1];
            int id2 = allRestrictions[i + 1][0];
            int h2 = allRestrictions[i + 1][1];
            
            int current_max = (h1 + h2 + (id2 - id1)) / 2;
            max_height = Math.max(max_height, current_max);
        }

        return max_height;
    }
}