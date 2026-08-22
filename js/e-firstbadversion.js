// First Bad Version
// Find the first bad version using the fewest calls to an isBadVersion API.
//
// Binary search over version numbers, narrowing toward the first version
// where isBadVersion returns true.
//
// Time: O(log n), Space: O(1)
function firstBadVersion(n, isBadVersion) {
  let lo = 1;
  let hi = n;

  while (lo < hi) {
    const mid = lo + Math.floor((hi - lo) / 2);
    if (isBadVersion(mid)) hi = mid;
    else lo = mid + 1;
  }

  return lo;
}

console.log(firstBadVersion(5, (v) => v >= 4)); // 4

module.exports = firstBadVersion;
