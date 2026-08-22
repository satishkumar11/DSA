import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

// Daily Temperatures
// For each day, find how many days until a warmer temperature.
//
// Monotonic decreasing stack of indices; whenever a warmer temperature
// arrives, pop and resolve every colder day still waiting on the stack.
//
// Time: O(n), Space: O(n)
class DailyTemperatures {
    public static int[] dailyTemperatures(int[] temperatures) {
        int[] result = new int[temperatures.length];
        Deque<Integer> stack = new ArrayDeque<>();

        for (int i = 0; i < temperatures.length; i++) {
            while (!stack.isEmpty() && temperatures[i] > temperatures[stack.peek()]) {
                int idx = stack.pop();
                result[idx] = i - idx;
            }
            stack.push(i);
        }

        return result;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(dailyTemperatures(new int[] {73, 74, 75, 71, 69, 72, 76, 73})));
        // [1, 1, 4, 2, 1, 1, 0, 0]
    }
}
