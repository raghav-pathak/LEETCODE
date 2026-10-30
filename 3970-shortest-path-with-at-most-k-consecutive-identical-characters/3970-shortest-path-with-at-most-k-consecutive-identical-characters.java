import java.util.*;

class Solution {
    public int shortestPath(int n, int[][] edges, String labels, int k) {
        List<int[]>[] adj = new ArrayList[n];
        for (int i = 0; i < n; i++) adj[i] = new ArrayList<>();
        for (int[] e : edges) adj[e[0]].add(new int[]{e[1], e[2]});

        // Min-heap stores: weight (long), node (int), count (int)
        PriorityQueue<long[]> pq = new PriorityQueue<>(Comparator.comparingLong(a -> a[0]));
        long[][] minCost = new long[n][k + 1];
        for (long[] row : minCost) Arrays.fill(row, Long.MAX_VALUE);

        pq.offer(new long[]{0, 0, 1});
        minCost[0][1] = 0;

        char[] lbl = labels.toCharArray();

        while (!pq.isEmpty()) {
            long[] curr = pq.poll();
            long d = curr[0];
            int u = (int) curr[1];
            int count = (int) curr[2];

            if (d > minCost[u][count]) continue;
            if (u == n - 1) return (int) d;

            for (int[] edge : adj[u]) {
                int v = edge[0], w = edge[1];
                int nextCount = (lbl[v] == lbl[u]) ? count + 1 : 1;

                if (nextCount <= k && d + w < minCost[v][nextCount]) {
                    minCost[v][nextCount] = d + w;
                    pq.offer(new long[]{d + w, v, nextCount});
                }
            }
        }

        long ans = Long.MAX_VALUE;
        for (int c = 1; c <= k; c++) ans = Math.min(ans, minCost[n - 1][c]);
        return ans == Long.MAX_VALUE ? -1 : (int) ans;
    }
}