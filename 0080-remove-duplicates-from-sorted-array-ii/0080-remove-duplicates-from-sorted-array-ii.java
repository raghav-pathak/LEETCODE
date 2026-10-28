class Solution {
    public int removeDuplicates(int[] nums) {
        if (nums.length <= 2) return nums.length;
        
        int i = 2; // yeh 'write' ki tarah kaam karega
        
        for (int j = 2; j < nums.length; j++) { // yeh 'read' ki tarah kaam karega
            if (nums[j] != nums[i - 2]) {
                nums[i] = nums[j];
                i++;
            }
        }
        
        return i;
    }
}