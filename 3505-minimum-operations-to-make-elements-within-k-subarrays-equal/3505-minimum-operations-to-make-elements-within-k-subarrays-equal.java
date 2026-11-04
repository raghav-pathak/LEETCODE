
import java.util.*;

class Solution {

    // Maintains median of the current sliding window
    class WindowMedian {

        TreeMap<Integer, Integer> low = new TreeMap<>();
        TreeMap<Integer, Integer> high = new TreeMap<>();

        int lowSize = 0;
        int highSize = 0;

        long lowSum = 0;
        long highSum = 0;

        // Insert an element into a TreeMap
        void add(TreeMap<Integer, Integer> map, int val) {
            map.put(val, map.getOrDefault(val, 0) + 1);
        }

        // Remove one occurrence of an element
        void remove(TreeMap<Integer, Integer> map, int val) {

            int count = map.get(val);

            if (count == 1) {
                map.remove(val);
            } else {
                map.put(val, count - 1);
            }
        }

        // Add a new number to the window
        void addNum(int val) {

            if (low.isEmpty() || val <= low.lastKey()) {

                add(low, val);
                lowSize++;
                lowSum += val;

            } else {

                add(high, val);
                highSize++;
                highSum += val;
            }

            balance();
        }

        // Remove a number from the window
        void removeNum(int val) {

            if (low.containsKey(val)) {

                remove(low, val);
                lowSize--;
                lowSum -= val;

            } else {

                remove(high, val);
                highSize--;
                highSum -= val;
            }

            balance();
        }

        // Maintain equal-sized halves
        void balance() {

            int target = (lowSize + highSize + 1) / 2;

            while (lowSize > target) {

                int val = low.lastKey();

                remove(low, val);
                lowSize--;
                lowSum -= val;

                add(high, val);
                highSize++;
                highSum += val;
            }

            while (lowSize < target) {

                int val = high.firstKey();

                remove(high, val);
                highSize--;
                highSum -= val;

                add(low, val);
                lowSize++;
                lowSum += val;
            }
        }

        // Calculate minimum operations for current window
        long getCost() {

            long median = low.lastKey();

            long leftCost =
                median * lowSize - lowSum;

            long rightCost =
                highSum - median * highSize;

            return leftCost + rightCost;
        }
    }

    public long minOperations(int[] nums, int x, int k) {

        int n = nums.length;

        int m = n - x + 1;

        // STEP 1: Calculate cost of every window

        long[] cost = new long[m];

        WindowMedian wm = new WindowMedian();

        // Create first window
        for (int i = 0; i < x; i++) {
            wm.addNum(nums[i]);
        }

        cost[0] = wm.getCost();

        // Slide the window
        for (int i = x; i < n; i++) {

            // Remove outgoing element
            wm.removeNum(nums[i - x]);

            // Add incoming element
            wm.addNum(nums[i]);

            // Store current window cost
            cost[i - x + 1] = wm.getCost();
        }

        // STEP 2: Dynamic Programming

        long INF = Long.MAX_VALUE / 4;

        long[] prev = new long[n + 1];
        long[] curr = new long[n + 1];

        // Base case: selecting zero windows costs zero
        Arrays.fill(prev, 0);

        // Select exactly t windows
        for (int t = 1; t <= k; t++) {

            Arrays.fill(curr, INF);

            for (int i = 1; i <= n; i++) {

                // Choice 1: Skip current element
                curr[i] = curr[i - 1];

                // Choice 2: Select a window ending at i-1
                if (i >= x && prev[i - x] != INF) {

                    curr[i] = Math.min(
                        curr[i],
                        prev[i - x] + cost[i - x]
                    );
                }
            }

            // Move to next DP row
            long[] temp = prev;
            prev = curr;
            curr = temp;
        }

        return prev[n];
    }
}
