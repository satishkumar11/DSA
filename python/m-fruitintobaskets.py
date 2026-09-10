# Fruit Into Baskets
# Trees are planted in a row; fruits[i] is the type of fruit on tree i. You
# have exactly two baskets, each can hold unlimited fruit but only one type.
# Starting at any tree, pick fruit moving right and stop as soon as a tree
# holds a third type you have no basket for. Find the most fruit you can
# collect - equivalently, the length of the longest contiguous subarray
# containing at most two distinct values.
#
# Input: fruits = [1, 2, 1]
# Output: 3  (only two types total, so the whole array fits in two baskets)
#
# Input: fruits = [0, 1, 2, 2]
# Output: 3  (three types overall, but [1, 2, 2] - types 1 and 2 - is the
#             longest run that fits two baskets; starting from 0 caps out at length 2)
#
# Sliding window with a count map of fruit types inside it; shrink from
# the left whenever more than two distinct types are present.
#
# Time: O(n), Space: O(1)
def total_fruit(fruits):
    count = {}
    left = 0
    max_len = 0

    for right in range(len(fruits)):
        fruit_type = fruits[right]
        if fruit_type in count:
            count[fruit_type] = count[fruit_type] + 1
        else:
            count[fruit_type] = 1

        while len(count) > 2:
            left_type = fruits[left]
            count[left_type] = count[left_type] - 1
            if count[left_type] == 0:
                del count[left_type]
            left += 1

        if right - left + 1 > max_len:
            max_len = right - left + 1

    return max_len


print(total_fruit([1, 2, 1]))  # 3
print(total_fruit([0, 1, 2, 2]))  # 3
