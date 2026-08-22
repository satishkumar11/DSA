// Group Anagrams
// Group an array of strings into sets where every string in a set is an anagram of the others.
// Time: O(n * k log k), Space: O(n * k)
function groupAnagrams(strs) {
  const map = new Map();
  for (const s of strs) {
    const key = s.split('').sort().join('');
    if (!map.has(key)) map.set(key, []);
    map.get(key).push(s);
  }
  return Array.from(map.values());
}

console.log(groupAnagrams(['eat', 'tea', 'tan', 'ate', 'nat', 'bat']));

module.exports = groupAnagrams;
