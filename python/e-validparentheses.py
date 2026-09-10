# hellointerview: https://www.hellointerview.com/learn/code/stack/valid-parentheses
# Valid Parentheses
# Determine if a string of brackets is validly matched and nested.
#
# Input: s = "()[]{}"
# Output: true
#
# Push opening brackets onto a stack; on a closing bracket, pop and check
# it matches the expected opener, failing fast on any mismatch.
#
# Trace with s = "()[]{}":
#   '(' -> push -> stack=[(]
#   ')' -> pop '(' , matches pairs[')'] -> stack=[]
#   '[' -> push -> stack=[[]
#   ']' -> pop '[' , matches pairs[']'] -> stack=[]
#   '{' -> push -> stack=[{]
#   '}' -> pop '{' , matches pairs['}'] -> stack=[]
#   stack empty at the end -> true
#
# Time: O(n), Space: O(n)
def is_valid(s):
    stack = []
    pairs = {')': '(', ']': '[', '}': '{'}

    for c in s:
        if c == '(' or c == '[' or c == '{':
            stack.append(c)
        else:
            if len(stack) == 0:
                return False
            top = stack.pop()
            if top != pairs[c]:
                return False

    return len(stack) == 0


print(is_valid('()[]{}'))  # True
print(is_valid('(]'))  # False
