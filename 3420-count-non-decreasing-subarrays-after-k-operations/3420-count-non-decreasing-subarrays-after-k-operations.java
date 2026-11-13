class Solution {
    public long countNonDecreasingSubarrays(int[] nums, int k) {
        long ans = 0;
        long cost = 0;
        // Store indices of nums with nums[i] in non-increasing order.
        Deque<Integer> dq = new ArrayDeque<>();
        int n = nums.length;
        
        for (int i = n - 1, j = n - 1; i >= 0; --i) {
            int num = nums[i];
            while (!dq.isEmpty() && nums[dq.peekLast()] < num) {
                int l = dq.pollLast();
                int r = dq.peekLast() != null ? dq.peekLast() : j + 1;
                cost += (long) (r - l) * (num - nums[l]);
            }
            dq.offerLast(i);
            
            while (cost > k) {
                cost -= nums[dq.peekFirst()] - nums[j];
                if (dq.peekFirst() == j) {
                    dq.pollFirst();
                }
                --j;
            }
            
            ans += j - i + 1;
        }
        
        return ans;
    }
}