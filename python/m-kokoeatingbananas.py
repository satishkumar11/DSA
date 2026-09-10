# hellointerview: https://www.hellointerview.com/learn/code/binary-search/apple-harvest
# Koko Eating Bananas
# Koko eats at a constant speed of k bananas per hour. Each hour she picks
# one pile and eats up to k bananas from it - if the pile has fewer than k
# left, she finishes it and the rest of that hour is wasted (she can't start
# another pile). Given h hours before the guards return, find the minimum
# integer speed k that lets her finish every pile in time.
#
# Input: piles = [3, 6, 7, 11], h = 8
# Output: 4  (at speed 4: hours = ceil(3/4)+ceil(6/4)+ceil(7/4)+ceil(11/4)
#             = 1+2+2+3 = 8, exactly fits; speed 3 needs 10 hours, too slow)
#
# Input: piles = [30, 11, 23, 4, 20], h = 5
# Output: 30  (h equals the pile count, so Koko can only manage one pile per
#              hour - she must be fast enough to clear the biggest pile, 30, in a single hour)
#
# Binary search on the eating speed itself; for each candidate speed,
# check whether all piles can be finished within h hours.
#
# Time: O(n log maxPile), Space: O(1)
import math


def min_eating_speed(piles, h):
    lo = 1
    hi = piles[0]
    for p in piles:
        if p > hi:
            hi = p

    while lo < hi:
        mid = (lo + hi) // 2
        hours = 0
        for p in piles:
            hours += math.ceil(p / mid)
        if hours <= h:
            hi = mid
        else:
            lo = mid + 1

    return lo


print(min_eating_speed([3, 6, 7, 11], 8))  # 4
print(min_eating_speed([30, 11, 23, 4, 20], 5))  # 30
