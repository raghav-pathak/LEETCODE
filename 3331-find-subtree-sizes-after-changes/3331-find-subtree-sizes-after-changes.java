import java.util.*;

class Solution {
    public int[] findSubtreeSizes(int[] parent, String s) {
        int n = parent.length;
        List<List<Integer>> origChildren = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            origChildren.add(new ArrayList<>());
        }
        for (int i = 1; i < n; i++) {
            origChildren.get(parent[i]).add(i);
        }

        int[] newParent = Arrays.copyOf(parent, n);
        int[] lastSeen = new int[26];
        Arrays.fill(lastSeen, -1);

        assignNewParents(0, origChildren, s, lastSeen, newParent);

        List<List<Integer>> finalChildren = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            finalChildren.add(new ArrayList<>());
        }
        for (int i = 1; i < n; i++) {
            finalChildren.get(newParent[i]).add(i);
        }

        int[] answer = new int[n];
        computeSubtreeSizes(0, finalChildren, answer);
        return answer;
    }

    private void assignNewParents(
            int u,
            List<List<Integer>> origChildren,
            String s,
            int[] lastSeen,
            int[] newParent
    ) {
        int c = s.charAt(u) - 'a';
        int prev = lastSeen[c];

        if (u != 0 && prev != -1) {
            newParent[u] = prev;
        }

        lastSeen[c] = u;
        for (int v : origChildren.get(u)) {
            assignNewParents(v, origChildren, s, lastSeen, newParent);
        }
        lastSeen[c] = prev;
    }

    private int computeSubtreeSizes(int u, List<List<Integer>> finalChildren, int[] answer) {
        int size = 1;
        for (int v : finalChildren.get(u)) {
            size += computeSubtreeSizes(v, finalChildren, answer);
        }
        answer[u] = size;
        return size;
    }
}