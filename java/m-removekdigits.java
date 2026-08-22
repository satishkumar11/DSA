import java.util.ArrayDeque;
import java.util.Deque;

// Remove K Digits
// Remove k digits from a number string to make it as small as possible.
//
// Monotonic increasing stack: pop larger digits off the top whenever a
// smaller digit arrives and removals remain, then trim leading zeros.
//
// Time: O(n), Space: O(n)
class RemoveKDigits {
    public static String removeKdigits(String num, int k) {
        Deque<Character> stack = new ArrayDeque<>();

        for (char c : num.toCharArray()) {
            while (k > 0 && !stack.isEmpty() && stack.peekLast() > c) {
                stack.pollLast();
                k--;
            }
            stack.offerLast(c);
        }

        while (k > 0 && !stack.isEmpty()) {
            stack.pollLast();
            k--;
        }

        StringBuilder sb = new StringBuilder();
        for (char c : stack) sb.append(c);

        int i = 0;
        while (i < sb.length() - 1 && sb.charAt(i) == '0') i++;
        String result = sb.substring(i);

        return result.isEmpty() ? "0" : result;
    }

    public static void main(String[] args) {
        System.out.println(removeKdigits("1432219", 3)); // "1219"
    }
}
