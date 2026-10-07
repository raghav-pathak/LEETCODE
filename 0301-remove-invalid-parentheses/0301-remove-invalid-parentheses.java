import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

class Solution {
    public List<String> removeInvalidParentheses(String s) {
        int remL = 0;
        int remR = 0;

        // Step 1: Count minimum '(' and ')' to remove
        for (char c : s.toCharArray()) {
            if (c == '(') {
                remL++;
            } else if (c == ')') {
                if (remL > 0) {
                    remL--;
                } else {
                    remR++;
                }
            }
        }

        Set<String> result = new HashSet<>();
        backtrack(s, 0, 0, remL, remR, new StringBuilder(), result);
        return new ArrayList<>(result);
    }

    private void backtrack(String s, int index, int open, int remL, int remR, StringBuilder current, Set<String> result) {
        // Base case: processed entire string
        if (index == s.length()) {
            if (open == 0 && remL == 0 && remR == 0) {
                result.add(current.toString());
            }
            return;
        }

        char c = s.charAt(index);
        int len = current.length();

        if (c == '(') {
            // Option 1: Remove '(' if we still need to remove '('
            if (remL > 0) {
                backtrack(s, index + 1, open, remL - 1, remR, current, result);
            }
            // Option 2: Keep '('
            current.append(c);
            backtrack(s, index + 1, open + 1, remL, remR, current, result);
            current.setLength(len); // backtrack

        } else if (c == ')') {
            // Option 1: Remove ')' if we still need to remove ')'
            if (remR > 0) {
                backtrack(s, index + 1, open, remL, remR - 1, current, result);
            }
            // Option 2: Keep ')' (only valid if open > 0)
            if (open > 0) {
                current.append(c);
                backtrack(s, index + 1, open - 1, remL, remR, current, result);
                current.setLength(len); // backtrack
            }

        } else {
            // Regular letters are always kept
            current.append(c);
            backtrack(s, index + 1, open, remL, remR, current, result);
            current.setLength(len); // backtrack
        }
    }
}