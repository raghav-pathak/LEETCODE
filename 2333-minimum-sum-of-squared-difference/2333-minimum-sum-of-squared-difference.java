import java.util.Arrays;

class Solution {
    public long minSumSquareDiff(int[] nums1, int[] nums2, int k1, int k2) {
        int n = nums1.length;
        long totalK = (long) k1 + k2;
        
        // Count frequencies of each absolute difference
        int maxDiff = 0;
        long[] count = new long[100001];
        
        for (int i = 0; i < n; i++) {
            int diff = Math.abs(nums1[i] - nums2[i]);
            count[diff]++;
            maxDiff = Math.max(maxDiff, diff);
        }
        
        // Greedily reduce the largest differences
        for (int d = maxDiff; d > 0; d--) {
            if (count[d] > 0) {
                long reduceCount = Math.min(totalK, count[d]);
                count[d] -= reduceCount;
                count[d - 1] += reduceCount;
                totalK -= reduceCount;
                
                if (totalK == 0) {
                    break;
                }
            }
        }
        
        // Calculate the final sum of squared differences
        long minSumSqDiff = 0;
        for (int d = 1; d <= maxDiff; d++) {
            if (count[d] > 0) {
                minSumSqDiff += count[d] * (long) d * d;
            }
        }
        
        return minSumSqDiff;
    }
}