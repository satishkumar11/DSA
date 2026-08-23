import java.util.ArrayDeque;
import java.util.Deque;

// Remove K Digits
// Remove k digits from a number string to make it as small as possible.
//
// Input: num = "1432219", k = 3
// Output: "1219"
//
// Monotonic increasing stack: pop larger digits off the top whenever a
// smaller digit arrives and removals remain, then trim leading zeros.
//
// Trace with num = "1432219", k = 3:
//   '1': stack empty -> push -> [1]
//   '4': top(1) < 4 -> just push -> [1,4]
//   '3': top(4) > 3 -> pop 4 (k=2), top(1) < 3 -> push -> [1,3]
//   '2': top(3) > 2 -> pop 3 (k=1), top(1) < 2 -> push -> [1,2]
//   '2': top(2) not > 2 -> just push -> [1,2,2]
//   '1': top(2) > 1 -> pop 2 (k=0), no removals left -> push -> [1,2,1]
//   '9': k already 0 -> just push -> [1,2,1,9]
//   k=0, nothing left to trim, no leading zero to strip -> "1219"
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
