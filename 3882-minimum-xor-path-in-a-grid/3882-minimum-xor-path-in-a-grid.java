import java.util.HashSet;
import java.util.Set;

class Solution {
    public int minCost(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        
        // dp[i][j] stores all possible XOR values to reach cell (i, j)
        // Using a HashSet for each cell to keep unique reachable XOR values
        Set<Integer>[][] dp = new HashSet[m][n];
        
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                dp[i][j] = new HashSet<>();
            }
        }
        
        // Base case: start cell
        dp[0][0].add(grid[0][0]);
        
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                // If we can come from the top cell (i-1, j)
                if (i > 0) {
                    for (int xorVal : dp[i - 1][j]) {
                        dp[i][j].add(xorVal ^ grid[i][j]);
                    }
                }
                // If we can come from the left cell (i, j-1)
                if (j > 0) {
                    for (int xorVal : dp[i][j - 1]) {
                        dp[i][j].add(xorVal ^ grid[i][j]);
                    }
                }
            }
        }
        
        // Find the minimum XOR value at the bottom-right cell (m - 1, n - 1)
        int minXor = Integer.MAX_VALUE;
        for (int xorVal : dp[m - 1][n - 1]) {
            minXor = Math.min(minXor, xorVal);
        }
        
        return minXor;
    }
}