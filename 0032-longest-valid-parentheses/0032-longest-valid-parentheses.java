import java.util.ArrayDeque;
import java.util.Deque;

class Solution {
    public int longestValidParentheses(String s) {
        int maxLen = 0;
        Deque<Integer> stack = new ArrayDeque<>();
        
        // Push -1 onto the stack as a base boundary for valid substring length calculation
        stack.push(-1);
        
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            
            if (c == '(') {
                // Push index of open parenthesis
                stack.push(i);
            } else {
                // Pop the top index for matching
                stack.pop();
                
                if (stack.isEmpty()) {
                    // Stack is empty, current ')' acts as a new boundary index
                    stack.push(i);
                } else {
                    // Valid substring length = current_index - index_at_stack_top
                    maxLen = Math.max(maxLen, i - stack.peek());
                }
            }
        }
        
        return maxLen;
    }
}