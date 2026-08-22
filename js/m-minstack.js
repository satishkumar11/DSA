// Min Stack
// Design a stack that supports push, pop, top, and retrieving the minimum in O(1).
//
// Maintain a parallel stack that tracks the running minimum at each
// push, so getMin is always just a peek at its top.
//
class MinStack {
  constructor() {
    this.stack = [];
    this.minStack = [];
  }

  push(val) {
    this.stack.push(val);
    const min = this.minStack.length ? Math.min(val, this.minStack[this.minStack.length - 1]) : val;
    this.minStack.push(min);
  }

  pop() {
    this.stack.pop();
    this.minStack.pop();
  }

  top() {
    return this.stack[this.stack.length - 1];
  }

  getMin() {
    return this.minStack[this.minStack.length - 1];
  }
}

const s = new MinStack();
s.push(-2);
s.push(0);
s.push(-3);
console.log(s.getMin()); // -3
s.pop();
console.log(s.top()); // 0
console.log(s.getMin()); // -2

module.exports = MinStack;
