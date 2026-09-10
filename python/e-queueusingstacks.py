# Implement Queue using Stacks
# Implement a FIFO queue using only two stacks.
#
# Input: push(1); push(2); peek(); pop(); empty()
# Output: 1, 1, false
#
# Two stacks: in_stack collects pushes in LIFO order, out_stack serves
# pops/peeks in FIFO order.
#
# The trick: reversing a stack's order twice restores the original order.
# in_stack has the newest element on top; popping everything off in_stack
# and pushing each one onto out_stack flips it to oldest-on-top - which is
# exactly the FIFO order a queue needs. This transfer only happens when
# out_stack is empty, and each element is moved at most once in its
# lifetime, so the cost amortizes to O(1) per operation.
#
# Dry run: push(1); push(2); peek(); pop(); empty()
#   push(1):  in_stack=[1]      out_stack=[]
#   push(2):  in_stack=[1, 2]   out_stack=[]
#   peek():   out_stack empty -> drain in_stack (pop 2, pop 1, push each)
#             in_stack=[]       out_stack=[2, 1]   -> top is 1, returns 1
#   pop():    out_stack already has items, no re-drain -> pop top -> returns 1
#             in_stack=[]       out_stack=[2]
#   empty():  in_stack empty but out_stack still has [2] -> returns false
#
# push: O(1), pop/peek: amortized O(1)
class MyQueue:
    def __init__(self):
        self.in_stack = []  # holds pushed items, newest on top
        self.out_stack = []  # holds items ready to pop/peek, oldest on top

    def push(self, x):
        # always goes to in_stack - no reordering needed yet
        self.in_stack.append(x)

    def pop(self):
        # make sure out_stack is loaded and ordered, then remove its top (the oldest item)
        self.peek()
        return self.out_stack.pop()

    def peek(self):
        # only refill out_stack once it's fully drained - that's what keeps this amortized O(1)
        if not self.out_stack:
            while self.in_stack:
                self.out_stack.append(self.in_stack.pop())
        return self.out_stack[-1]

    def empty(self):
        # the queue is empty only when both stacks are - out_stack can hold
        # unconsumed items even after in_stack has been fully drained
        return not self.in_stack and not self.out_stack


q = MyQueue()
q.push(1)
q.push(2)
print(q.peek())  # 1
print(q.pop())  # 1
print(q.empty())  # false
