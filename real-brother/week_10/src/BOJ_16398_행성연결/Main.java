package BOJ_16398_행성연결;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Main {
    static Scanner sc = new Scanner(System.in);
    static int n;
    static int[][] adjacencyMatrix;

    public static void main(String[] args) {
        n = sc.nextInt();

        // 인접 행렬 초기화
        adjacencyMatrix = new int[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(adjacencyMatrix[i], Integer.MAX_VALUE);
        }

        // 입력 받기
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                adjacencyMatrix[i][j] = sc.nextInt();
            }
        }

        // 프림 알고리즘 구현하기
        int[] parent = new int[n];
        int[] key = new int[n];
        boolean[] mstSet = new boolean[n];

        PriorityQueue<Edge> pq = new PriorityQueue<>(n, Comparator.comparingInt(e -> e.cost));

        Arrays.fill(key, Integer.MAX_VALUE);
        Arrays.fill(parent, -1);

        key[0] = 0; // 시작 노드를 0으로 가정
        pq.offer(new Edge(0, key[0]));

        while (!pq.isEmpty()) {
            int u = pq.poll().dest;
            mstSet[u] = true;

            for (int v = 0; v < n; v++) {
                int cost = adjacencyMatrix[u][v];

                if (cost != Integer.MAX_VALUE && !mstSet[v] && cost < key[v]) {
                    parent[v] = u;
                    key[v] = cost;
                    pq.offer(new Edge(v, key[v]));
                }
            }
        }

        // 최소 신장 트리의 가중치 합 구하기
        long answer = 0;
        for (int i = 0; i < n; i++) {
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

