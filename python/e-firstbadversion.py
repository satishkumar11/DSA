# First Bad Version
# Find the first bad version using the fewest calls to an isBadVersion API.
#
# Input: n = 5, bad = 4
# Output: 4
#
# Binary search over version numbers, narrowing toward the first version
# where isBadVersion returns true.
#
# Trace with n = 5, bad = 4 (isBadVersion(v) is true for v >= 4):
#   lo=1, hi=5: mid=3, isBadVersion(3)=false -> lo=4
#   lo=4, hi=5: mid=4, isBadVersion(4)=true  -> hi=4
#   lo == hi (4) -> loop ends -> return 4
#
# Time: O(log n), Space: O(1)
def first_bad_version(n, is_bad_version):
    lo = 1
    hi = n

    while lo < hi:
        mid = lo + (hi - lo) // 2
        if is_bad_version(mid):
            hi = mid
        else:
            lo = mid + 1

    return lo


bad = 4
def is_bad_version(version):
    return version >= bad  # every version from `bad` onward is bad


print(first_bad_version(5, is_bad_version))  # 4
