// Koko Eating Bananas
// Find the minimum eating speed to finish all banana piles within h hours.
// Binary search on the eating speed itself; for each candidate speed,
// check whether all piles can be finished within h hours.
// Time: O(n log maxPile), Space: O(1)
function minEatingSpeed(piles, h) {
  let lo = 1;
  let hi = Math.max(...piles);

  while (lo < hi) {
    const mid = Math.floor((lo + hi) / 2);
    let hours = 0;
    for (const p of piles) hours += Math.ceil(p / mid);
    if (hours <= h) hi = mid;
    else lo = mid + 1;
  }

  return lo;
}

console.log(minEatingSpeed([3, 6, 7, 11], 8)); // 4
console.log(minEatingSpeed([30, 11, 23, 4, 20], 5)); // 30

module.exports = minEatingSpeed;
