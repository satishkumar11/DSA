# Next Greater Element I
# nums1 is a subset of nums2. For each value in nums1, find the first greater
# number that appears to its right in nums2 - if none exists, the answer is -1.
#
# Input: nums1 = [4, 1, 2], nums2 = [1, 3, 4, 2]
# Output: [-1, 3, -1]
#
# Monotonic decreasing stack over nums2 to precompute each value's next
# greater element in a dict, then look up answers for nums1 from that dict.
#
# Time: O(n + m), Space: O(n)
#
# Dry run building the map from nums2 = [1, 3, 4, 2]:
#   n=1: stack=[]     -> push 1                              stack=[1]
#   n=3: top 1 < 3     -> pop 1, map[1]=3; push 3             stack=[3]     map={1:3}
#   n=4: top 3 < 4     -> pop 3, map[3]=4; push 4             stack=[4]     map={1:3,3:4}
#   n=2: top 4 < 2? no -> just push 2                         stack=[4,2]   map={1:3,3:4}
#   4 and 2 are never popped -> no map entry -> answer -1
#
# Lookup for nums1 = [4, 1, 2]: 4 -> -1, 1 -> 3, 2 -> -1 => [-1, 3, -1]
def next_greater_element(nums1, nums2):
    stack = []
    greater = {}

    for n in nums2:
        while stack and stack[-1] < n:
            value = stack.pop()
            greater[value] = n
        stack.append(n)

    result = []
    for n in nums1:
        if n in greater:
            result.append(greater[n])
        else:
            result.append(-1)
    return result


print(next_greater_element([4, 1, 2], [1, 3, 4, 2]))  # [-1,3,-1]
