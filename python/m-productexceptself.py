# Product of Array Except Self
# Return an array where each element is the product of all other elements, without using division.
#
# Input: nums = [1, 2, 3, 4]
# Output: [24, 12, 8, 6]
#
# Build two arrays: pref_product[i] is the product of everything before i,
# suff_product[i] is the product of everything after i. The answer at each
# index is just those two multiplied together.
#
# Trace with nums = [1, 2, 3, 4]:
#   pref_product: [1,1,1,1] -> i=1: 1*1=1, i=2: 2*1=2, i=3: 3*2=6 -> [1,1,2,6]
#   suff_product: [1,1,1,1] -> j=2: 4*1=4, j=1: 3*4=12, j=0: 2*12=24 -> [24,12,4,1]
#   res[i] = pref_product[i] * suff_product[i]:
#     res[0]=1*24=24, res[1]=1*12=12, res[2]=2*4=8, res[3]=6*1=6
#   final: [24, 12, 8, 6]
#
# Time: O(n), Space: O(n)
def product_except_self(nums):
    n = len(nums)
    pref_product = [1] * n
    suff_product = [1] * n
    res = [0] * n

    # Construct the pref_product array
    for i in range(1, n):
        pref_product[i] = nums[i - 1] * pref_product[i - 1]

    # Construct the suff_product array
    for j in range(n - 2, -1, -1):
        suff_product[j] = nums[j + 1] * suff_product[j + 1]

    # Construct the result array using pref_product[] and suff_product[]
    for i in range(n):
        res[i] = pref_product[i] * suff_product[i]

    return res


print(product_except_self([1, 2, 3, 4]))  # [24, 12, 8, 6]
