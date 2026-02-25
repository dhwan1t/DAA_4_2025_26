package Uni.DAA.Session19.prims;

import java.util.*;

class prims {
    public int spanningTree(int V, int[][] edges) {
        List<List<int[]>> adj = new ArrayList<>();
        for (int i = 0; i < V; i++) {
            adj.add(new ArrayList<>());
        }

        for (int[] e : edges) {
            adj.get(e[0]).add(new int[]{e[1], e[2]});
            adj.get(e[1]).add(new int[]{e[0], e[2]});
        }

        boolean[] visited = new boolean[V];
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[1] - b[1]);
        pq.add(new int[]{0, 0});
        int sum = 0;

        while (!pq.isEmpty()) {
            int[] curr = pq.poll();
            int node = curr[0];
            int weight = curr[1];

            if (visited[node]) continue;

            visited[node] = true;
            sum += weight;

            for (int[] neighbor : adj.get(node)) {
                if (!visited[neighbor[0]]) {
                    pq.add(new int[]{neighbor[0], neighbor[1]});
                }
            }
        }
        return sum;
    }

    public static void main(String[] args) {
        prims obj = new prims();
        int V = 4;
        int[][] edges = {
                {0, 1, 10},
                {0, 2, 6},
                {0, 3, 5},
                {1, 3, 15},
                {2, 3, 4}
        };

        System.out.println(obj.spanningTree(V, edges));
    }
}