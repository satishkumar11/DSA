// Number of Islands
// Count the number of connected land regions in a 2D grid.
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
