import java.util.Arrays;

class Solution {
    public int climbStairs(int n, int[] costs) {
        // dp[j] stores the minimum cost to reach step j
        int[] dp = new int[n + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        
        dp[0] = 0; // Starting point at step 0 with 0 cost
        
        for (int j = 1; j <= n; j++) {
            // We can reach step j from j-1, j-2, or j-3 (if they exist)
            for (int i = Math.max(0, j - 3); i < j; i++) {
                if (dp[i] != Integer.MAX_VALUE) {
                    // cost[j-1] corresponds to costs[i] since array is 0-indexed in Java
                    int jumpCost = costs[j - 1] + (j - i) * (j - i);
                    dp[j] = Math.min(dp[j], dp[i] + jumpCost);
                }
            }
        }
        
        return dp[n];
    }
}