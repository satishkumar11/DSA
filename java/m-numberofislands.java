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
class NumberOfIslands {
    public static int numIslands(char[][] grid) {
        if (grid.length == 0) return 0;
        int rows = grid.length, cols = grid[0].length;
        int count = 0;

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (grid[r][c] == '1') {
                    count++;
                    dfs(grid, r, c);
                }
            }
        }

        return count;
    }

    private static void dfs(char[][] grid, int r, int c) {
        if (r < 0 || c < 0 || r >= grid.length || c >= grid[0].length || grid[r][c] == '0') return;
        grid[r][c] = '0';
        dfs(grid, r + 1, c);
        dfs(grid, r - 1, c);
        dfs(grid, r, c + 1);
        dfs(grid, r, c - 1);
    }

    public static void main(String[] args) {
        char[][] grid = {
            {'1', '1', '0', '0'},
            {'1', '1', '0', '0'},
            {'0', '0', '1', '0'},
            {'0', '0', '0', '1'}
        };
        System.out.println(numIslands(grid)); // 3
    }
}
