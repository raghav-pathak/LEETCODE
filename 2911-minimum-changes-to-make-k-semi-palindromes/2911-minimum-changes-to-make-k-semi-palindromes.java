import java.util.Arrays;

class Solution {
    public int minimumChanges(String s, int k) {
        int n = s.length();
        int INF = 1_000_000;
        
        // cost[i][j] = min changes to make s[i..j] a semi-palindrome
        int[][] cost = new int[n][n];
        for (int[] row : cost) {
            Arrays.fill(row, INF);
        }

        // Step 1: Precompute semi-palindrome cost for every substring s[i..j]
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) { // Length must be at least 2
                int len = j - i + 1;
                int minChanges = INF;

                // Try all valid divisors d (1 <= d < len)
                for (int d = 1; d <= len / 2; d++) {
                    if (len % d != 0) continue;

                    int changesForD = 0;
                    // Check each of the d interleaved groups
                    for (int offset = 0; offset < d; offset++) {
                        int left = i + offset;
                        int right = j - (d - 1 - offset);
                        while (left < right) {
                            if (s.charAt(left) != s.charAt(right)) {
                                changesForD++;
                            }
                            left += d;
                            right -= d;
                        }
                    }
                    minChanges = Math.min(minChanges, changesForD);
                }
                cost[i][j] = minChanges;
            }
        }

        // Step 2: DP to partition prefix of length i into p semi-palindromes
        int[][] dp = new int[k + 1][n + 1];
        for (int[] row : dp) {
            Arrays.fill(row, INF);
        }
        dp[0][0] = 0;

        for (int p = 1; p <= k; p++) {
            // Each partition needs at least 2 characters
            for (int i = 2 * p; i <= n; i++) {
                for (int m = 2 * (p - 1); m <= i - 2; m++) {
                    if (dp[p - 1][m] != INF) {
                        dp[p][i] = Math.min(dp[p][i], dp[p - 1][m] + cost[m][i - 1]);
                    }
                }
            }
        }

        return dp[k][n];
    }
}