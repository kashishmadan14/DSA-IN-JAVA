class Solution {
    public int passwordStrength(String password) {

        boolean[] seen = new boolean[256];
        int strength = 0;

        for (char c : password.toCharArray()) {

            // Count each character only once
            if (seen[c]) continue;
            seen[c] = true;

            // Lowercase letters
            if (c >= 'a' && c <= 'z') {
                strength += 1;
            }

            // Uppercase letters
            else if (c >= 'A' && c <= 'Z') {
                strength += 2;
            }

            // Digits
            else if (c >= '0' && c <= '9') {
                strength += 3;
            }

            // Special characters: ! @ # $
            else {
                strength += 5;
            }
        }

        return strength;
    }
}