class Solution {
    public int sortPermutation(int[] nums) {
        int k = -1; // Initialize with -1 to properly capture the first out-of-place element's bits
        
        for (int i = 0; i < nums.length; i++) {
            // If the element is not in its correct position
            if (nums[i] != i) {
                if (k == -1) {
                    k = nums[i];
                } else {
                    k &= nums[i];
                }
            }
        }
        
        // If all elements were already in their correct places, return 0
        return (k == -1) ? 0 : k;
    }
}