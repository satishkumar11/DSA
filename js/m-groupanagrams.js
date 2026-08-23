// Group Anagrams
// Group an array of strings into sets where every string in a set is an anagram of the others.
//
// Input: strs = ["eat","tea","tan","ate","nat","bat"]
// Output: [["eat","tea","ate"],["tan","nat"],["bat"]]
//
// Sort each string's characters to build a canonical key, then group
// original strings by that key in a hash map.
//
// Trace with strs = ["eat","tea","tan","ate","nat","bat"] (sorted-letters key):
//   eat -> "aet"   tea -> "aet"   tan -> "ant"
//   ate -> "aet"   nat -> "ant"   bat -> "abt"
//   groups: "aet"->[eat,tea,ate], "ant"->[tan,nat], "abt"->[bat]
//
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
