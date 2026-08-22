// hellointerview: https://www.hellointerview.com/learn/code/stack/daily-temperatures
import java.util.Arrays;
import java.util.Stack;

// Daily Temperatures
// For each day, find how many days until a warmer temperature.
//
// Input: temperatures = [73,74,75,71,69,72,76,73]
// Output: [1, 1, 4, 2, 1, 1, 0, 0]
//
// Monotonic decreasing stack of indices; whenever a warmer temperature
// arrives, pop and resolve every colder day still waiting on the stack.
//
// Time: O(n), Space: O(n)
class DailyTemperatures {
    public static int[] dailyTemperatures(int[] temperatures) {
        Stack<Integer> stack = new Stack<>();
        int[] result = new int[temperatures.length];

        for (int i = 0; i < temperatures.length; i++) {
            while (!stack.isEmpty() && temperatures[stack.peek()] < temperatures[i]) {
                int index = stack.pop();
                result[index] = i - index;
            }
            stack.add(i);
        }

        while (!stack.isEmpty()) {
            int index = stack.pop();
            result[index] = 0;
        }

        return result;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(dailyTemperatures(new int[] {73, 74, 75, 71, 69, 72, 76, 73})));
        // [1, 1, 4, 2, 1, 1, 0, 0]
    }
}
