// hellointerview: https://www.hellointerview.com/learn/code/depth-first-search/number-of-islands
// Number of Islands
// Count the number of connected land regions in a 2D grid.
//
// Input: grid = [["1","1","0","0"],["1","1","0","0"],["0","0","1","0"],["0","0","0","1"]]
// Output: 3
//
// For every unvisited land cell, DFS flood-fills the entire connected
// region to zero it out, counting one island per flood-fill.
//
// 1 1 0 0
// 1 1 0 0   -> 3 islands
// 0 0 1 0
// 0 0 0 1
//
// Trace: scanning row by row, left to right:
//   (0,0)='1' -> count=1, DFS floods the whole connected top-left block:
//                (0,0),(0,1),(1,0),(1,1) all turn to '0'
//   scan continues, skipping the now-zeroed cells and the water cells,
//   until (2,2)='1' -> count=2, DFS floods just (2,2) (all 4 neighbors are '0')
//   until (3,3)='1' -> count=3, DFS floods just (3,3) (all 4 neighbors are '0')
//   count = 3
//
// Time: O(rows * cols), Space: O(rows * cols) recursion stack
function numIslands(grid) {
  if (!grid.length) return 0;
  const rows = grid.length;
  const cols = grid[0].length;

  function dfs(r, c) {
    if (r < 0 || c < 0 || r >= rows || c >= cols || grid[r][c] === '0') return;
    grid[r][c] = '0';
    dfs(r + 1, c);
    dfs(r - 1, c);
    dfs(r, c + 1);
    dfs(r, c - 1);
  }

  let count = 0;
  for (let r = 0; r < rows; r++) {
    for (let c = 0; c < cols; c++) {
      if (grid[r][c] === '1') {
        count++;
        dfs(r, c);
      }
    }
  }

  return count;
}

const grid = [
  ['1', '1', '0', '0'],
  ['1', '1', '0', '0'],
  ['0', '0', '1', '0'],
  ['0', '0', '0', '1'],
];
console.log(numIslands(grid)); // 3

module.exports = numIslands;
