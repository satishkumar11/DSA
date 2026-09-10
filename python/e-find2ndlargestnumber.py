def find_2nd_largest_number(nums):
    first = nums[0]
    second = nums[0]

    for i in range(len(nums)):
        if nums[i] > first:
            second = first
            first = nums[i]
        elif nums[i] > second and nums[i] != first:
            second = nums[i]

    return second


print(find_2nd_largest_number([10, 5, 25, 8, 30, 15]))  # 25
