package Uni.DAA.Session19.krushkals;

import java.util.Arrays;

class kruskals {
    static int kruskalsMST(int V, int[][] edges) {
        Arrays.sort(edges, (a, b) -> a[2] - b[2]);

        int[] parent = new int[V];
        int[] rank = new int[V];
        for (int i = 0; i < V; i++) {
            parent[i] = i;
            rank[i] = 0;
        }
        int sum = 0;
        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            int w = edge[2];

            int pu = find(parent, u);
            int pv = find(parent, v);

            if (pu != pv) {
                sum += w;
                union(parent, rank, pu, pv);
            }
        }
        return sum;
    }

    static int find(int[] parent, int x) {
        if(parent[x] != x){
            parent[x] = find(parent, parent[x]);
        }
        return parent[x];
    }

    static void union(int[] parent, int[] rank, int x, int y) {
        if(rank[x] < rank[y]) {
            parent[x] = y;
        }
        else if (rank[x] > rank[y]) {
            parent[y] = x;
        }
        else {
            parent[y] = x;
            rank[x]++;
        }
    }

    public static void main(String[] args) {
        int V = 4;
        int[][] edges = {
                {0, 1, 10},
                {0, 2, 6},
                {0, 3, 5},
                {1, 3, 15},
                {2, 3, 4}
        };
        int result = kruskals.kruskalsMST(V, edges);
        System.out.println(result);
    }
}
