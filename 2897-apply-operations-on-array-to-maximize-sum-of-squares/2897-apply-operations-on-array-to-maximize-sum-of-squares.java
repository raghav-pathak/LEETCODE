import java.util.List;

class Solution {
    public int maxSum(List<Integer> nums, int k) {
        int[] bitCount = new int[32];
        
        // Count the frequency of set bits at each bit position
        for (int num : nums) {
            for (int i = 0; i < 32; i++) {
                if ((num & (1 << i)) != 0) {
                    bitCount[i]++;
                }
            }
        }
        
        long mod = 1_000_000_007;
        long totalSum = 0;
        
        // Greedily form the k numbers by setting bits from highest frequency positions
        for (int i = 0; i < k; i++) {
            long currentNum = 0;
            for (int bit = 0; bit < 32; bit++) {
                if (bitCount[bit] > 0) {
                    currentNum |= (1L << bit);
                    bitCount[bit]--;
                }
            }
            totalSum = (totalSum + (currentNum * currentNum) % mod) % mod;
        }
        
        return (int) totalSum;
    }
}