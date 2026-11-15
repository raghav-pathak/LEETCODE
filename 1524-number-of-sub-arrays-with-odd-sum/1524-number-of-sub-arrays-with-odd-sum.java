class Solution {
    public int numOfSubarrays(int[] arr) {
        long oddCount = 0;
        long evenCount = 1; // prefix sum of 0 is even
        long currentSum = 0;
        long totalSubarrays = 0;
        int MOD = 1_000_000_007;

        for (int num : arr) {
            currentSum += num;

            if (currentSum % 2 != 0) { // Current prefix sum is odd
                totalSubarrays = (totalSubarrays + evenCount) % MOD;
                oddCount++;
            } else { // Current prefix sum is even
                totalSubarrays = (totalSubarrays + oddCount) % MOD;
                evenCount++;
            }
        }

        return (int) totalSubarrays;
    }
}