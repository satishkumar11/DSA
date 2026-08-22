import java.util.ArrayDeque;
import java.util.Deque;

// Implement Queue using Stacks
// Implement a FIFO queue using only two stacks.
//
// Input: push(1); push(2); peek(); pop(); empty()
// Output: 1, 1, false
//
// Two stacks: inStack collects pushes in LIFO order, outStack serves
// pops/peeks in FIFO order.
//
// The trick: reversing a stack's order twice restores the original order.
// inStack has the newest element on top; popping everything off inStack
// and pushing each one onto outStack flips it to oldest-on-top - which is
// exactly the FIFO order a queue needs. This transfer only happens when
// outStack is empty, and each element is moved at most once in its
// lifetime, so the cost amortizes to O(1) per operation.
//
// Dry run: push(1); push(2); peek(); pop(); empty()
//   push(1):  inStack=[1]      outStack=[]
//   push(2):  inStack=[1, 2]   outStack=[]
//   peek():   outStack empty -> drain inStack (pop 2, pop 1, push each)
//             inStack=[]       outStack=[2, 1]   -> top is 1, returns 1
//   pop():    outStack already has items, no re-drain -> pop top -> returns 1
//             inStack=[]       outStack=[2]
//   empty():  inStack empty but outStack still has [2] -> returns false
//
// push: O(1), pop/peek: amortized O(1)
class QueueUsingStacks {
    private final Deque<Integer> inStack = new ArrayDeque<>(); // holds pushed items, newest on top
    private final Deque<Integer> outStack = new ArrayDeque<>(); // holds items ready to pop/peek, oldest on top

    public void push(int x) {
        // always goes to inStack - no reordering needed yet
        inStack.push(x);
    }

    public int pop() {
        // make sure outStack is loaded and ordered, then remove its top (the oldest item)
        peek();
        return outStack.pop();
    }

    public int peek() {
        // only refill outStack once it's fully drained - that's what keeps this amortized O(1)
        if (outStack.isEmpty()) {
            while (!inStack.isEmpty()) outStack.push(inStack.pop());
        }
        return outStack.peek();
    }

    public boolean empty() {
        // the queue is empty only when both stacks are - outStack can hold
        // unconsumed items even after inStack has been fully drained
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
