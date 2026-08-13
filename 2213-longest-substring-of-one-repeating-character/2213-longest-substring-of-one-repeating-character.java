class Solution {

    static class Node {
        char leftChar;
        char rightChar;

        int leftLen;
        int rightLen;
        int maxLen;
        int len;

        Node() {}

        Node(char c) {
            leftChar = rightChar = c;
            leftLen = rightLen = maxLen = len = 1;
        }
    }

    Node[] tree;
    char[] s;

    public int[] longestRepeating(String s, String queryCharacters,
                                  int[] queryIndices) {

        this.s = s.toCharArray();

        int n = s.length();
        tree = new Node[4 * n];

        build(1, 0, n - 1);

        int k = queryIndices.length;
        int[] ans = new int[k];

        for (int i = 0; i < k; i++) {

            int index = queryIndices[i];
            char newChar = queryCharacters.charAt(i);

            this.s[index] = newChar;

            update(1, 0, n - 1, index);

            ans[i] = tree[1].maxLen;
        }

        return ans;
    }

    // Build segment tree
    private void build(int node, int start, int end) {

        if (start == end) {
            tree[node] = new Node(s[start]);
            return;
        }

        int mid = start + (end - start) / 2;

        build(node * 2, start, mid);
        build(node * 2 + 1, mid + 1, end);

        tree[node] = merge(tree[node * 2], tree[node * 2 + 1]);
    }

    // Update one index
    private void update(int node, int start, int end, int index) {

        if (start == end) {
            tree[node] = new Node(s[index]);
            return;
        }

        int mid = start + (end - start) / 2;

        if (index <= mid) {
            update(node * 2, start, mid, index);
        } else {
            update(node * 2 + 1, mid + 1, end, index);
        }

        tree[node] = merge(tree[node * 2], tree[node * 2 + 1]);
    }

    // Merge two segments
    private Node merge(Node left, Node right) {

        Node result = new Node();

        result.len = left.len + right.len;

        result.leftChar = left.leftChar;
        result.rightChar = right.rightChar;

        // Length of prefix
        result.leftLen = left.leftLen;

        if (left.leftLen == left.len &&
            left.rightChar == right.leftChar) {

            result.leftLen = left.len + right.leftLen;
        }

        // Length of suffix
        result.rightLen = right.rightLen;

        if (right.rightLen == right.len &&
            left.rightChar == right.leftChar) {

            result.rightLen = right.len + left.rightLen;
        }

        // Maximum inside either child
        result.maxLen = Math.max(left.maxLen, right.maxLen);

        // Possible substring crossing the middle
        if (left.rightChar == right.leftChar) {
            result.maxLen = Math.max(
                result.maxLen,
                left.rightLen + right.leftLen
            );
        }

        return result;
    }
}