class Solution {
    public int[] maxDepthAfterSplit(String seq) {
        int n = seq.length();
        int[] answer = new int[n];
        int depth = 0;

        for (int i = 0; i < n; i++) {
            char c = seq.charAt(i);
            if (c == '(') {
                // Assign group based on current depth parity (0 or 1)
                answer[i] = depth % 2;
                depth++;
            } else {
                // Decrement depth first for a closing parenthesis
                depth--;
                answer[i] = depth % 2;
            }
        }

        return answer;
    }
}
