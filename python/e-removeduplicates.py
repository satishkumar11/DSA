# Remove Duplicates from Sorted Array
# Remove duplicates in place from a sorted array and return the new length.
#
# Input: nums = [1, 1, 2, 2, 3]
# Output: 3 (array becomes [1, 2, 3, ...])
#
# Since the array is sorted, any duplicate sits right next to its match -
# compare each element to the one before it; only write it forward when
# it's different, using a separate index to track where to write next.
#
# Time: O(n), Space: O(1)
#
# Dry run with nums = [1, 1, 2, 2, 3]:
#   writeIndex = 1
#   i=1: nums[1]=1 == nums[0]=1        -> skip                        [1,1,2,2,3]
#   i=2: nums[2]=2 != nums[1]=1        -> nums[1]=2, writeIndex=2      [1,2,2,2,3]
#   i=3: nums[3]=2 == nums[2]=2        -> skip                        [1,2,2,2,3]
#   i=4: nums[4]=3 != nums[3]=2        -> nums[2]=3, writeIndex=3      [1,2,3,2,3]
#   loop ends -> return writeIndex = 3
def remove_duplicates(nums):
    if len(nums) == 0:
        return 0
    write_index = 1

    for i in range(1, len(nums)):
        if nums[i] != nums[i - 1]:
            nums[write_index] = nums[i]
            write_index += 1

    return write_index


arr = [1, 1, 2, 2, 3]
print(remove_duplicates(arr), arr)  # 3 [1, 2, 3, 2, 3]
