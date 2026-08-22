import java.util.ArrayDeque;
import java.util.Deque;

// Valid Parentheses
// Determine if a string of brackets is validly matched and nested.
// Push opening brackets onto a stack; on a closing bracket, pop and check
// it matches the expected opener, failing fast on any mismatch.
// Time: O(n), Space: O(n)
class ValidParentheses {
    public static boolean isValid(String s) {
        Deque<Character> stack = new ArrayDeque<>();

        for (char c : s.toCharArray()) {
            if (c == '(' || c == '[' || c == '{') {
                stack.push(c);
            } else {
                if (stack.isEmpty()) return false;
                char open = stack.pop();
                if ((c == ')' && open != '(') || (c == ']' && open != '[') || (c == '}' && open != '{')) {
                    return false;
                }
            }
        }

        return stack.isEmpty();
    }

    public static void main(String[] args) {
        System.out.println(isValid("()[]{}")); // true
        System.out.println(isValid("(]")); // false
    }
}
