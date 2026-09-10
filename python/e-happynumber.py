# Happy Number
# Repeatedly replace a number with the sum of the squares of its digits;
# determine whether this process eventually reaches 1.
#
# Input: n = 19
# Output: true
#
# Unhappy numbers fall into a repeating cycle instead of reaching 1, so
# track every value seen - if one repeats before hitting 1, it can never
# reach 1 (the same value always produces the same next value).
#
# Trace with n = 19:
#   19 -> 1^2+9^2 = 1+81 = 82
#   82 -> 8^2+2^2 = 64+4 = 68
#   68 -> 6^2+8^2 = 36+64 = 100
#   100 -> 1^2+0^2+0^2 = 1
#   n == 1 -> true
#
# Time: O(log n) per step, Space: O(log n) for the seen set
def is_happy(n):
    seen = set()

    while n != 1 and n not in seen:
        seen.add(n)
        n = sum_of_squared_digits(n)

    return n == 1


def sum_of_squared_digits(n):
    total = 0
    while n > 0:
        digit = n % 10
        total += digit * digit
        n = n // 10
    return total


print(is_happy(19))  # True
