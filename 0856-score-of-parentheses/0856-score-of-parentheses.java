import java.util.ArrayDeque;
import java.util.Deque;

class Solution {
    public int scoreOfParentheses(String s) {
        Deque<Integer> stack = new ArrayDeque<>();
        stack.push(0); // Base score for the outermost level

        for (char c : s.toCharArray()) {
            if (c == '(') {
                stack.push(0);
            } else {
                int innerScore = stack.pop();
                int currentScore = Math.max(2 * innerScore, 1);
                stack.push(stack.pop() + currentScore);
            }
        }

        return stack.pop();
    }
}