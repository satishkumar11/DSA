import java.util.ArrayDeque;
import java.util.Deque;

// Min Stack
// Design a stack that supports push, pop, top, and retrieving the minimum in O(1).
//
// Maintain a parallel stack that tracks the running minimum at each
// push, so getMin is always just a peek at its top.
//
class MinStack {
    private final Deque<Integer> stack = new ArrayDeque<>();
    private final Deque<Integer> minStack = new ArrayDeque<>();

    public void push(int val) {
        stack.push(val);
        int min = minStack.isEmpty() ? val : Math.min(val, minStack.peek());
        minStack.push(min);
    }

    public void pop() {
        stack.pop();
        minStack.pop();
    }

    public int top() {
        return stack.peek();
    }

    public int getMin() {
        return minStack.peek();
    }

    public static void main(String[] args) {
        MinStack s = new MinStack();
        s.push(-2);
        s.push(0);
        s.push(-3);
        System.out.println(s.getMin()); // -3
        s.pop();
        System.out.println(s.top()); // 0
        System.out.println(s.getMin()); // -2
    }
}
