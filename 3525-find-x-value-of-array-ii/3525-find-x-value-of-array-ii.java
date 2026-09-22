class Solution {
    private int[] prod;
    private int[][] counts;
    private int currentProd;
    private int ans;

    public int[] resultArray(int[] nums, int k, int[][] queries) {
        int n = nums.length;
        prod = new int[4 * n];
        counts = new int[4 * n][5];

        build(1, 0, n - 1, nums, k);

        int q = queries.length;
        int[] res = new int[q];

        for (int i = 0; i < q; i++) {
            int idx = queries[i][0];
            int val = queries[i][1];
            int start = queries[i][2];
            int x = queries[i][3];

            update(1, 0, n - 1, idx, val, k);

            currentProd = 1 % k;
            ans = 0;
            query(1, 0, n - 1, start, n - 1, x, k);

            res[i] = ans;
        }

        return res;
    }

    private void merge(int node, int left, int right, int k) {
        prod[node] = (prod[left] * prod[right]) % k;
        for (int i = 0; i < k; i++) {
            counts[node][i] = counts[left][i];
        }
        int pL = prod[left];
        for (int j = 0; j < k; j++) {
            int rem = (pL * j) % k;
            counts[node][rem] += counts[right][j];
        }
    }

    private void build(int node, int l, int r, int[] nums, int k) {
        if (l == r) {
            int v = nums[l] % k;
            prod[node] = v;
            for (int i = 0; i < k; i++) counts[node][i] = 0;
            counts[node][v] = 1;
            return;
        }
        int mid = l + (r - l) / 2;
        build(2 * node, l, mid, nums, k);
        build(2 * node + 1, mid + 1, r, nums, k);
        merge(node, 2 * node, 2 * node + 1, k);
    }

    private void update(int node, int l, int r, int idx, int val, int k) {
        if (l == r) {
            int v = val % k;
            prod[node] = v;
            for (int i = 0; i < k; i++) counts[node][i] = 0;
            counts[node][v] = 1;
            return;
        }
        int mid = l + (r - l) / 2;
        if (idx <= mid) {
            update(2 * node, l, mid, idx, val, k);
        } else {
            update(2 * node + 1, mid + 1, r, idx, val, k);
        }
        merge(node, 2 * node, 2 * node + 1, k);
    }

    private void query(int node, int l, int r, int ql, int qr, int targetX, int k) {
        if (ql <= l && r <= qr) {
            for (int j = 0; j < k; j++) {
                if ((currentProd * j) % k == targetX) {
                    ans += counts[node][j];
                }
            }
            currentProd = (currentProd * prod[node]) % k;
            return;
        }
        int mid = l + (r - l) / 2;
        if (ql <= mid) {
            query(2 * node, l, mid, ql, qr, targetX, k);
        }
        if (qr > mid) {
            query(2 * node + 1, mid + 1, r, ql, qr, targetX, k);
        }
    }
}