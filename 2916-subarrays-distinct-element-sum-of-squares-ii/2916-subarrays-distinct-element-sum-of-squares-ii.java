class Solution {
    static final int MOD = 1000000007;

    long[] sumTree;
    long[] sumSqTree;
    long[] lazy;

    public int sumCounts(int[] nums) {
        int n = nums.length;
        sumTree = new long[4 * n];
        sumSqTree = new long[4 * n];
        lazy = new long[4 * n];

        java.util.HashMap<Integer, Integer> lastOccurrence = new java.util.HashMap<>();
        long totalSumSq = 0;
        long currentSumSq = 0;

        for (int i = 0; i < n; i++) {
            int val = nums[i];
            int prevIndex = lastOccurrence.getOrDefault(val, -1);
            int L = prevIndex + 1;
            int R = i;

            // Update segment tree for range [L, i] by adding 1
            if (L <= R) {
                update(1, 0, n - 1, L, R, 1);
            }

            // The total sum of squares of all subarrays ending at index i
            // is stored at the root of the segment tree representing range [0, i]
            currentSumSq = sumSqTree[1];
            totalSumSq = (totalSumSq + currentSumSq) % MOD;

            lastOccurrence.put(val, i);
        }

        return (int) totalSumSq;
    }

    private void pushDown(int node, int l, int r) {
        if (lazy[node] != 0) {
            int mid = (l + r) / 2;
            apply(2 * node, l, mid, lazy[node]);
            apply(2 * node + 1, mid + 1, r, lazy[node]);
            lazy[node] = 0;
        }
    }

    private void apply(int node, int l, int r, long v) {
        long len = (r - l + 1);
        // sumSq_new = sumSq + 2*v*sum + v^2*len
        long newSumSq = (sumSqTree[node] + 2 * v * sumTree[node] % MOD + v * v % MOD * len) % MOD;
        // sum_new = sum + v*len
        long newSum = (sumTree[node] + v * len) % MOD;

        sumSqTree[node] = (newSumSq + MOD) % MOD;
        sumTree[node] = (newSum + MOD) % MOD; // Fixed missing parenthesis and mod
        lazy[node] = (lazy[node] + v) % MOD;
    }

    private void update(int node, int l, int r, int ql, int qr, long v) {
        if (ql <= l && r <= qr) {
            apply(node, l, r, v);
            return;
        }
        pushDown(node, l, r);
        int mid = (l + r) / 2;
        if (ql <= mid) {
            update(2 * node, l, mid, ql, qr, v);
        }
        if (qr > mid) {
            update(2 * node + 1, mid + 1, r, ql, qr, v);
        }
        sumTree[node] = (sumTree[2 * node] + sumTree[2 * node + 1]) % MOD;
        sumSqTree[node] = (sumSqTree[2 * node] + sumSqTree[2 * node + 1]) % MOD;
    }
}