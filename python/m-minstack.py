# Min Stack
# Design a stack that supports push, pop, top, and retrieving the minimum in O(1).
#
# Input: push(-2); push(0); push(-3); getMin(); pop(); top(); getMin()
# Output: -3, 0, -2
#
# Maintain a parallel stack that tracks the running minimum at each
# push, so get_min is always just a peek at its top.
#
# Trace with push(-2); push(0); push(-3); get_min(); pop(); top(); get_min():
#   push(-2): stack=[-2],     min_stack=[-2]         (nothing pushed yet, so -2 is the min)
#   push(0):  stack=[-2,0],   min_stack=[-2,-2]       (min(0,-2)=-2)
#   push(-3): stack=[-2,0,-3],min_stack=[-2,-2,-3]    (min(-3,-2)=-3)
#   get_min(): min_stack top -> -3
#   pop():    stack=[-2,0],   min_stack=[-2,-2]        (both stacks drop their top together)
#   top():    stack top -> 0
#   get_min(): min_stack top -> -2
class MinStack:
    def __init__(self):
        self.stack = []
        self.min_stack = []

    def push(self, val):
        self.stack.append(val)
        if len(self.min_stack) > 0:
            min_val = min(val, self.min_stack[-1])
        else:
            min_val = val
        self.min_stack.append(min_val)

    def pop(self):
        self.stack.pop()
        self.min_stack.pop()

    def top(self):
        return self.stack[-1]

    def get_min(self):
        return self.min_stack[-1]


s = MinStack()
s.push(-2)
s.push(0)
s.push(-3)
print(s.get_min())  # -3
s.pop()
print(s.top())  # 0
print(s.get_min())  # -2
