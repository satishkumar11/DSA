# Remove K Digits
# Remove k digits from a number string to make it as small as possible.
#
# Input: num = "1432219", k = 3
# Output: "1219"
#
# Monotonic increasing stack: pop larger digits off the top whenever a
# smaller digit arrives and removals remain, then trim leading zeros.
#
# Trace with num = "1432219", k = 3:
#   '1': stack empty -> push -> [1]
#   '4': top(1) < 4 -> just push -> [1,4]
#   '3': top(4) > 3 -> pop 4 (k=2), top(1) < 3 -> push -> [1,3]
#   '2': top(3) > 2 -> pop 3 (k=1), top(1) < 2 -> push -> [1,2]
#   '2': top(2) not > 2 -> just push -> [1,2,2]
#   '1': top(2) > 1 -> pop 2 (k=0), no removals left -> push -> [1,2,1]
#   '9': k already 0 -> just push -> [1,2,1,9]
#   k=0, nothing left to trim, no leading zero to strip -> "1219"
#
# Time: O(n), Space: O(n)
def remove_k_digits(num, k):
    stack = []

    for c in num:
        while k > 0 and stack and stack[-1] > c:
            stack.pop()
            k -= 1
        stack.append(c)

    while k > 0:
        stack.pop()
        k -= 1

    while len(stack) > 1 and stack[0] == '0':
        stack.pop(0)

    result = ''.join(stack)
    return result if result else '0'


print(remove_k_digits('1432219', 3))  # "1219"
