// Flood Fill
// Recolor a connected region of a 2D image starting from a given pixel.
// DFS from the starting pixel, recoloring every connected pixel that
// matches the original color before the fill began.
// Time: O(rows * cols), Space: O(rows * cols)
function floodFill(image, sr, sc, color) {
  const startColor = image[sr][sc];
  if (startColor === color) return image;

  function dfs(r, c) {
    if (r < 0 || c < 0 || r >= image.length || c >= image[0].length || image[r][c] !== startColor) return;
    image[r][c] = color;
    dfs(r + 1, c);
    dfs(r - 1, c);
    dfs(r, c + 1);
    dfs(r, c - 1);
  }

  dfs(sr, sc);
  return image;
}

console.log(floodFill([[1, 1, 1], [1, 1, 0], [1, 0, 1]], 1, 1, 2));
// [[2,2,2],[2,2,0],[2,0,1]]

module.exports = floodFill;
