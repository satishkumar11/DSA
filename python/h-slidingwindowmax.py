# Sliding Window Maximum
# Return the maximum value in every sliding window of size k.
#
# Input: nums = [1, 3, -1, -3, 5, 3, 6, 7], k = 3
# Output: [3, 3, 5, 5, 6, 7]
#
# Maintain a monotonic decreasing deque of indices; the front always
# holds the max of the current window, evicting stale or smaller values.
#
# nums = [1, 3, -1, -3, 5, 3, 6, 7], k = 3
#
# [1  3 -1] -3  5  3  6  7   -> max 3
#  1 [3 -1 -3]  5  3  6  7   -> max 3
#  1  3 [-1 -3  5] 3  6  7   -> max 5
#  1  3 -1 [-3  5  3] 6  7   -> max 5
#  1  3 -1 -3 [5  3  6] 7    -> max 6
#  1  3 -1 -3  5 [3  6  7]   -> max 7
#
# Deque trace (storing indices, values shown in parens; front = current max):
#   i=0 (1):  deque=[0(1)]
#   i=1 (3):  1 beats the 1 at the back -> pop it, push 3 -> deque=[1(3)]
#   i=2 (-1): -1 doesn't beat 3 -> just push -> deque=[1(3), 2(-1)]        -> max 3
#   i=3 (-3): doesn't beat -1 -> push -> deque=[1(3), 2(-1), 3(-3)]        -> max 3
#   i=4 (5):  index 1 has aged out of the window -> evict from front;
#             5 beats everything behind it -> pop -3, -1, 3 -> deque=[4(5)] -> max 5
#   i=5 (3):  doesn't beat 5 -> push -> deque=[4(5), 5(3)]                 -> max 5
#   i=6 (6):  6 beats 3 and 5 -> pop both -> deque=[6(6)]                  -> max 6
#   i=7 (7):  7 beats 6 -> pop it -> deque=[7(7)]                         -> max 7
#   result: [3, 3, 5, 5, 6, 7]
#
# Time: O(n), Space: O(k)
from collections import deque


def max_sliding_window(nums, k):
    dq = deque()  # indices, values decreasing
    result = []

    for i in range(len(nums)):
        while dq and dq[0] <= i - k:
            dq.popleft()
        while dq and nums[dq[-1]] < nums[i]:
            dq.pop()
        dq.append(i)
        if i >= k - 1:
            result.append(nums[dq[0]])

    return result


# Simpler version: for every window position, just scan its k elements
# directly to find the max. No deque, no "is this index stale" bookkeeping.
#
# Time: O(n * k), Space: O(1) excluding output
def max_sliding_window_simple(nums, k):
    result = []

    for i in range(len(nums) - k + 1):
        max_val = nums[i]
        for j in range(i + 1, i + k):
            max_val = max(max_val, nums[j])
        result.append(max_val)

    return result


print(max_sliding_window([1, 3, -1, -3, 5, 3, 6, 7], 3))  # [3, 3, 5, 5, 6, 7]
print(max_sliding_window_simple([1, 3, -1, -3, 5, 3, 6, 7], 3))  # [3, 3, 5, 5, 6, 7]
