package BOJ_1753_최단경로;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;


public class Main {
	static Scanner sc = new Scanner(System.in);
	static int V, E, startIndex;
	static List<List<Edge>> graph;
	static int[] distance;
    static boolean[] visited;
	
	public static void main(String[] args) {
		V = sc.nextInt(); 
		E = sc.nextInt(); 
		startIndex = sc.nextInt();
		graph = new ArrayList<List<Edge>>();
		for (int i = 0; i <= V; i++) {
			graph.add(new ArrayList<>());
		}
		distance = new int[V + 1];
		visited = new boolean[V + 1];
		
		
		for (int i = 0; i < E; i++) {
			int a = sc.nextInt();
			int b = sc.nextInt();
			int cost = sc.nextInt();
			
			graph.get(a).add(new Edge(b, cost));
		}

        dijkstra(startIndex);
		
        for (int i = 1; i <= V; i++) {
			System.out.println(distance[i] == Integer.MAX_VALUE ? "INF" : distance[i]);
		}
		
	}
	
	// 다익스트라 알고리즘
    private static void dijkstra(int start) {
    	 // 우선순위 큐를 사용하여 최소 힙 구현 -> 갈 수 있는 큐 중에 비용이 가장 최소인 점을 우선으로
        PriorityQueue<Edge> pq = new PriorityQueue<>((a, b) -> a.cost - b.cost);
        pq.offer(new Edge(start, 0));

        // 시작 정점으로부터의 거리를 무한대로 초기화
        for (int i = 1; i <= V; i++) {
            distance[i] = Integer.MAX_VALUE;
        }

        distance[start] = 0; // 시작 정점의 최단 거리 = 0

        // 다익스트라 알고리즘 수행
        while (!pq.isEmpty()) {
            Edge nowEdge = pq.poll();
            int nowDestination = nowEdge.dest;

            if (visited[nowDestination]) continue; // 이미 방문한 노드면 다음 노드로 넘어감
            visited[nowDestination] = true; // 현재 노드를 방문 처리

            // 현재 노드와 연결된 인접한 노드들에 대해 최단 거리를 업데이트
            for (Edge neighbor : graph.get(nowDestination)) {
                if (distance[neighbor.dest] > distance[nowDestination] + neighbor.cost) {
                    distance[neighbor.dest] = distance[nowDestination] + neighbor.cost; // 최단 거리 업데이트
                    pq.offer(new Edge(neighbor.dest, distance[neighbor.dest]));
                }
            }
        }
    }

	// 엣지 클래스
	static class Edge {
		int dest;    
		int cost;
		
		public Edge(int dest, int cost) {
			this.dest = dest;
			this.cost = cost;
		}
	}
}
