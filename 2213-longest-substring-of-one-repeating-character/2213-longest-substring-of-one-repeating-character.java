class Solution {
    private int[] maxLen;
    private int[] prefLen;
    private char[] prefChar;
    private int[] suffLen;
    private char[] suffChar;

    public int[] longestRepeating(String s, String queryCharacters, int[] queryIndices) {
        int n = s.length();
        int k = queryIndices.length;
        maxLen = new int[4 * n];
        prefLen = new int[4 * n];
        prefChar = new char[4 * n];
        suffLen = new int[4 * n];
        suffChar = new char[4 * n];

        char[] sArr = s.toCharArray();
        build(sArr, 1, 0, n - 1);

        int[] ans = new int[k];
        for (int i = 0; i < k; i++) {
            update(1, 0, n - 1, queryIndices[i], queryCharacters.charAt(i));
            ans[i] = maxLen[1];
        }
        return ans;
    }

    private void build(char[] s, int node, int l, int r) {
        if (l == r) {
            maxLen[node] = 1;
            prefLen[node] = 1;
            suffLen[node] = 1;
            prefChar[node] = s[l];
            suffChar[node] = s[l];
            return;
        }
        int mid = l + (r - l) / 2;
        build(s, node * 2, l, mid);
        build(s, node * 2 + 1, mid + 1, r);
        merge(node, node * 2, node * 2 + 1, mid - l + 1, r - mid);
    }

    private void update(int node, int l, int r, int idx, char ch) {
        if (l == r) {
            prefChar[node] = ch;
            suffChar[node] = ch;
            return;
        }
        int mid = l + (r - l) / 2;
        if (idx <= mid) {
            update(node * 2, l, mid, idx, ch);
        } else {
            update(node * 2 + 1, mid + 1, r, idx, ch);
        }
        merge(node, node * 2, node * 2 + 1, mid - l + 1, r - mid);
    }

    private void merge(int node, int leftChild, int rightChild, int lenL, int lenR) {
        maxLen[node] = Math.max(maxLen[leftChild], maxLen[rightChild]);

        if (suffChar[leftChild] == prefChar[rightChild]) {
            maxLen[node] = Math.max(maxLen[node], suffLen[leftChild] + prefLen[rightChild]);
        }

        prefChar[node] = prefChar[leftChild];
        prefLen[node] = prefLen[leftChild];
        if (prefLen[leftChild] == lenL && prefChar[leftChild] == prefChar[rightChild]) {
            prefLen[node] += prefLen[rightChild];
        }

        suffChar[node] = suffChar[rightChild];
        suffLen[node] = suffLen[rightChild];
        if (suffLen[rightChild] == lenR && suffChar[rightChild] == suffChar[leftChild]) {
            suffLen[node] += suffLen[leftChild];
        }
    }
}