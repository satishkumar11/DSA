# hellointerview: https://www.hellointerview.com/learn/code/depth-first-search/flood-fill
# Flood Fill
# Recolor a connected region of a 2D image starting from a given pixel.
#
# Input: image = [[1,1,1],[1,1,0],[1,0,1]], sr = 1, sc = 1, color = 2
# Output: [[2,2,2],[2,2,0],[2,0,1]]
#
# DFS from the starting pixel, recoloring every connected pixel that
# matches the original color before the fill began.
#
# Before:      After (fill from (1,1) with 2):
# 1 1 1        2 2 2
# 1 1 0        2 2 0
# 1 0 1        2 0 1
#
# Non-obvious part: (2,0) also turns to 2, even though it's not adjacent to
# the start pixel (1,1) - it's reachable through (1,0), which is adjacent
# to the start and shares the same original color (1). Flood fill follows
# the whole connected region, not just the pixel's immediate neighbors.
# (2,2) stays 1 - its only neighbors, (1,2) and (2,1), are both 0, so it's
# never connected to the region at all.
#
# Time: O(rows * cols), Space: O(rows * cols)
def flood_fill(image, start_row, start_col, new_color):
    num_rows = len(image)
    num_cols = len(image[0])
    original_color = image[start_row][start_col]
    if original_color == new_color:
        return image
    dfs(image, start_row, start_col, original_color, new_color, num_rows, num_cols)
    return image


def dfs(image, row, col, original_color, new_color, num_rows, num_cols):
    if image[row][col] == original_color:
        image[row][col] = new_color
        if row >= 1:
            dfs(image, row - 1, col, original_color, new_color, num_rows, num_cols)
        if row + 1 < num_rows:
            dfs(image, row + 1, col, original_color, new_color, num_rows, num_cols)
        if col >= 1:
            dfs(image, row, col - 1, original_color, new_color, num_rows, num_cols)
        if col + 1 < num_cols:
            dfs(image, row, col + 1, original_color, new_color, num_rows, num_cols)


print(flood_fill([[1, 1, 1], [1, 1, 0], [1, 0, 1]], 1, 1, 2))
# [[2,2,2],[2,2,0],[2,0,1]]
