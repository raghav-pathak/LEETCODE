class Solution {
    public int longestMonotonicSubarray(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        
        int maxLength = 1;
        int inc = 1;
        int dec = 1;
        
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > nums[i - 1]) {
                inc++;
                dec = 1; // Reset decreasing count
            } else if (nums[i] < nums[i - 1]) {
                dec++;
                inc = 1; // Reset increasing count
            } else {
                inc = 1;
                dec = 1; // Reset both if elements are equal
            }
            
            maxLength = Math.max(maxLength, Math.max(inc, dec));
        }
        
        return maxLength;
    }
}