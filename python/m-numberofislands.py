# hellointerview: https://www.hellointerview.com/learn/code/depth-first-search/number-of-islands
# Number of Islands
# Count the number of connected land regions in a 2D grid.
#
# Input: grid = [["1","1","0","0"],["1","1","0","0"],["0","0","1","0"],["0","0","0","1"]]
# Output: 3
#
# For every unvisited land cell, DFS flood-fills the entire connected
# region to zero it out, counting one island per flood-fill.
#
# 1 1 0 0
# 1 1 0 0   -> 3 islands
# 0 0 1 0
# 0 0 0 1
#
# Trace: scanning row by row, left to right:
#   (0,0)='1' -> count=1, DFS floods the whole connected top-left block:
#                (0,0),(0,1),(1,0),(1,1) all turn to '0'
#   scan continues, skipping the now-zeroed cells and the water cells,
#   until (2,2)='1' -> count=2, DFS floods just (2,2) (all 4 neighbors are '0')
#   until (3,3)='1' -> count=3, DFS floods just (3,3) (all 4 neighbors are '0')
#   count = 3
#
# Time: O(rows * cols), Space: O(rows * cols) recursion stack
def num_islands(grid):
    if not grid:
        return 0
    rows = len(grid)
    cols = len(grid[0])

    def dfs(r, c):
        if r < 0 or c < 0 or r >= rows or c >= cols or grid[r][c] == '0':
            return
        grid[r][c] = '0'
        dfs(r + 1, c)
        dfs(r - 1, c)
        dfs(r, c + 1)
        dfs(r, c - 1)

    count = 0
    for r in range(rows):
        for c in range(cols):
            if grid[r][c] == '1':
                count += 1
                dfs(r, c)

    return count


grid = [
    ['1', '1', '0', '0'],
    ['1', '1', '0', '0'],
    ['0', '0', '1', '0'],
    ['0', '0', '0', '1'],
]
print(num_islands(grid))  # 3
