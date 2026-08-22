// Implement Queue using Stacks
// Implement a FIFO queue using only two stacks.
// Two stacks: push always goes to the input stack; pop/peek drain the
// input stack into the output stack only when the output stack is empty.
// push: O(1), pop/peek: amortized O(1)
class MyQueue {
  constructor() {
    this.inStack = [];
    this.outStack = [];
  }

  push(x) {
    this.inStack.push(x);
  }

  pop() {
    this.peek();
    return this.outStack.pop();
  }

  peek() {
    if (!this.outStack.length) {
      while (this.inStack.length) this.outStack.push(this.inStack.pop());
    }
    return this.outStack[this.outStack.length - 1];
  }

  empty() {
    return !this.inStack.length && !this.outStack.length;
  }
}

const q = new MyQueue();
q.push(1);
q.push(2);
console.log(q.peek()); // 1
console.log(q.pop()); // 1
console.log(q.empty()); // false

module.exports = MyQueue;
