import java.util.ArrayDeque;
import java.util.Deque;

// Implement Queue using Stacks
// Implement a FIFO queue using only two stacks.
//
// Two stacks: push always goes to the input stack; pop/peek drain the
// input stack into the output stack only when the output stack is empty.
//
// push: O(1), pop/peek: amortized O(1)
class QueueUsingStacks {
    private final Deque<Integer> inStack = new ArrayDeque<>();
    private final Deque<Integer> outStack = new ArrayDeque<>();

    public void push(int x) {
        inStack.push(x);
    }

    public int pop() {
        peek();
        return outStack.pop();
    }

    public int peek() {
        if (outStack.isEmpty()) {
            while (!inStack.isEmpty()) outStack.push(inStack.pop());
        }
        return outStack.peek();
    }

    public boolean empty() {
        return inStack.isEmpty() && outStack.isEmpty();
    }

    public static void main(String[] args) {
        QueueUsingStacks q = new QueueUsingStacks();
        q.push(1);
        q.push(2);
        System.out.println(q.peek()); // 1
        System.out.println(q.pop()); // 1
        System.out.println(q.empty()); // false
    }
}
