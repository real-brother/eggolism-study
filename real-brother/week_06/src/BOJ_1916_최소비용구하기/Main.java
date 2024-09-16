package BOJ_1916_최소비용구하기;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Main {
    static Scanner sc = new Scanner(System.in);
    static int n;
    static int m;
    static List<List<Edge>> graph;
    static boolean[] visited;
    static int[] distance; // 시작 정점으로부터의 거리 배열
    static int startIndex;
    static int endIndex;

    public static void main(String[] args) {
        n = sc.nextInt(); 
        m = sc.nextInt();
        visited = new boolean[n + 1];
        distance = new int[n + 1];
        graph = new ArrayList<>();

        // 그래프의 각 정점에 대한 인접 리스트 초기화
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }

        // 간선 정보 입력
        for (int i = 0; i < m; i++) {
            int start = sc.nextInt(); 
            int destination = sc.nextInt(); 
            int cost = sc.nextInt();
            graph.get(start).add(new Edge(destination, cost));
        }

        startIndex = sc.nextInt();
        endIndex = sc.nextInt();

        dijkstra(startIndex);

        System.out.println(distance[endIndex]);
    }

    // 간선을 나타내는 클래스 - 방향이 있음
    static class Edge {
        int destination; // 도착 정점
        int cost; // 가중치(비용)

        public Edge(int destination, int cost) {
            this.destination = destination;
            this.cost = cost;
        }
    }

    // 다익스트라 알고리즘
    private static void dijkstra(int start) {
    	 // 우선순위 큐를 사용하여 최소 힙 구현 -> 갈 수 있는 큐 중에 비용이 가장 최소인 점을 우선으로
        PriorityQueue<Edge> pq = new PriorityQueue<>((a, b) -> a.cost - b.cost);
        pq.offer(new Edge(start, 0)); // 시작 정점을 우선순위 큐에 추가

        // 시작 정점으로부터의 최단 거리를 무한대로 초기화
        // 알고리즘이 다 돌았는데도 무제한이라면 -> 갈 수 있는 간선이 없는 상태
        for (int i = 1; i <= n; i++) {
            distance[i] = Integer.MAX_VALUE;
        }

        distance[start] = 0; // 시작 정점의 최단 거리를 0으로 설정

        // 다익스트라 알고리즘 수행
        while (!pq.isEmpty()) {
            Edge nowEdge = pq.poll(); // 우선순위 큐에서 가장 작은 가중치를 가진 간선을 추출
            int nowDestination = nowEdge.destination; // 현재 노드
            int cost = nowEdge.cost; // 현재 노드까지의 가중치(비용)

            if (visited[nowDestination]) continue; // 이미 방문한 노드면 다음 노드로 넘어감
            visited[nowDestination] = true; // 현재 노드를 방문 처리

            // 현재 노드와 연결된 인접한 노드들에 대해 최단 거리를 업데이트
            for (Edge neighbor : graph.get(nowDestination)) {
                if (distance[neighbor.destination] > distance[nowDestination] + neighbor.cost) {
                    distance[neighbor.destination] = distance[nowDestination] + neighbor.cost; // 최단 거리 업데이트
                    Edge edge = new Edge(neighbor.destination, distance[neighbor.destination]);
                    pq.offer(edge); // 우선순위 큐에 새로운 간선 추가
                }
            }
        }
    }
}
