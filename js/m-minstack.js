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
