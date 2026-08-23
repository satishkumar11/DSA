import java.util.ArrayDeque;
import java.util.Deque;

// Min Stack
// Design a stack that supports push, pop, top, and retrieving the minimum in O(1).
//
// Input: push(-2); push(0); push(-3); getMin(); pop(); top(); getMin()
// Output: -3, 0, -2
//
// Maintain a parallel stack that tracks the running minimum at each
// push, so getMin is always just a peek at its top.
//
// Trace with push(-2); push(0); push(-3); getMin(); pop(); top(); getMin():
//   push(-2): stack=[-2],     minStack=[-2]         (nothing pushed yet, so -2 is the min)
//   push(0):  stack=[-2,0],   minStack=[-2,-2]       (min(0,-2)=-2)
//   push(-3): stack=[-2,0,-3],minStack=[-2,-2,-3]    (min(-3,-2)=-3)
//   getMin(): minStack top -> -3
//   pop():    stack=[-2,0],   minStack=[-2,-2]        (both stacks drop their top together)
//   top():    stack top -> 0
//   getMin(): minStack top -> -2
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
