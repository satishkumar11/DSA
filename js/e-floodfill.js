// hellointerview: https://www.hellointerview.com/learn/code/depth-first-search/flood-fill
// Flood Fill
// Recolor a connected region of a 2D image starting from a given pixel.
//
// Input: image = [[1,1,1],[1,1,0],[1,0,1]], sr = 1, sc = 1, color = 2
// Output: [[2,2,2],[2,2,0],[1,0,1]]
//
// DFS from the starting pixel, recoloring every connected pixel that
// matches the original color before the fill began.
//
// Before:      After (fill from (1,1) with 2):
// 1 1 1        2 2 2
// 1 1 0        2 2 0
// 1 0 1        1 0 1
//
// Time: O(rows * cols), Space: O(rows * cols)
function floodFill(image, startRow, startCol, newColor) {
  const numRows = image.length;
  const numCols = image[0].length;
  const originalColor = image[startRow][startCol];
  if (originalColor === newColor) return image;
  dfs(image, startRow, startCol, originalColor, newColor, numRows, numCols);
  return image;
}

function dfs(image, row, col, originalColor, newColor, numRows, numCols) {
  if (image[row][col] === originalColor) {
    image[row][col] = newColor;
    if (row >= 1) dfs(image, row - 1, col, originalColor, newColor, numRows, numCols);
    if (row + 1 < numRows) dfs(image, row + 1, col, originalColor, newColor, numRows, numCols);
    if (col >= 1) dfs(image, row, col - 1, originalColor, newColor, numRows, numCols);
    if (col + 1 < numCols) dfs(image, row, col + 1, originalColor, newColor, numRows, numCols);
  }
}

console.log(floodFill([[1, 1, 1], [1, 1, 0], [1, 0, 1]], 1, 1, 2));
// [[2,2,2],[2,2,0],[2,0,1]]

module.exports = floodFill;
