package BOJ_1197_최소스패닝트리;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Main {
    static Scanner sc = new Scanner(System.in);
    static int V, E;
    static List<List<Edge>> graph;

    public static void main(String[] args) {
        V = sc.nextInt();
        E = sc.nextInt();

        graph = new ArrayList<>();
        for (int i = 0; i <= V; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < E; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            int c = sc.nextInt();
            graph.get(a).add(new Edge(b, c));
            graph.get(b).add(new Edge(a, c));
        }
        
        // 프림 알고리즘 구현하기

        int[] parent = new int[V + 1];
        int[] key = new int[V + 1];
        boolean[] mstSet = new boolean[V + 1];

        PriorityQueue<Edge> pq = new PriorityQueue<>(V, Comparator.comparingInt(e -> e.cost));

        Arrays.fill(key, Integer.MAX_VALUE);
        Arrays.fill(parent, -1);

        key[1] = 0; // 시작 노드를 1로 가정
        pq.offer(new Edge(1, key[1]));

        while (!pq.isEmpty()) {
            int u = pq.poll().dest;
            mstSet[u] = true;

            for (Edge e : graph.get(u)) {
                int v = e.dest;
                int cost = e.cost;

                if (!mstSet[v] && cost < key[v]) {
                    parent[v] = u;
                    key[v] = cost;
                    pq.offer(new Edge(v, key[v]));
                }
            }
        }

        int answer = 0;
        for (int i = 1; i <= V; i++) {
            answer += key[i];
        }
        System.out.println(answer);
    }

    static class Edge {
        int dest;
        int cost;

        public Edge(int dest, int cost) {
            this.dest = dest;
            this.cost = cost;
        }
    }
}
