class Solution {
    public boolean checkValidString(String s) {
        int minOpen = 0; // Minimum possible open brackets '('
        int maxOpen = 0; // Maximum possible open brackets '('

        for (char c : s.toCharArray()) {
            if (c == '(') {
                minOpen++;
                maxOpen++;
            } else if (c == ')') {
                minOpen--;
                maxOpen--;
            } else { // c == '*'
                minOpen--; // Treat '*' as ')'
                maxOpen++; // Treat '*' as '('
            }

            // If maxOpen is negative, we have more ')' than '(' + '*' can accommodate
            if (maxOpen < 0) {
                return false;
            }

            // minOpen cannot be negative since we cannot have a negative count of open brackets
            if (minOpen < 0) {
                minOpen = 0;
            }
        }

        // The string is valid if we can balance all open brackets (minOpen == 0)
        return minOpen == 0;
    }
}