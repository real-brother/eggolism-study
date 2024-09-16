package BOJ_1504_특정한최단경로;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;


public class Main {
	static Scanner sc = new Scanner(System.in);
	static int N, E, viaIndex1, viaIndex2;
	static List<List<Edge>> graph;
	static int[] distance;
    static boolean[] visited;
	
	public static void main(String[] args) {
		N = sc.nextInt(); 
		E = sc.nextInt(); 
		graph = new ArrayList<List<Edge>>();
		for (int i = 0; i <= N; i++) {
			graph.add(new ArrayList<>());
		}
		distance = new int[N + 1];
		visited = new boolean[N + 1];
		
		
		for (int i = 0; i < E; i++) {
			int a = sc.nextInt();
			int b = sc.nextInt();
			int cost = sc.nextInt();
			
			graph.get(a).add(new Edge(b, cost));
			graph.get(b).add(new Edge(a, cost));
		}
		viaIndex1 = sc.nextInt();
		viaIndex2 = sc.nextInt();
		int endIndex = N;
		
		int minDis = Integer.MAX_VALUE;
		
		int d1 = dijkstra(1, viaIndex1) + dijkstra(viaIndex1, viaIndex2) + dijkstra(viaIndex2, endIndex);
        int d2 = dijkstra(1, viaIndex2) + dijkstra(viaIndex2, viaIndex1) + dijkstra(viaIndex1, endIndex);
        
        minDis = Math.min(minDis, Math.min(d1, d2));
			
		System.out.println(minDis == Integer.MAX_VALUE ? -1 : minDis);
	}
	
	// 다익스트라 알고리즘 (시작점, 도착점)
    private static int dijkstra(int start, int end) {
    	 // 우선순위 큐를 사용하여 최소 힙 구현 -> 갈 수 있는 큐 중에 비용이 가장 최소인 점을 우선으로
        PriorityQueue<Edge> pq = new PriorityQueue<>((a, b) -> a.cost - b.cost);
        pq.offer(new Edge(start, 0));

		visited = new boolean[N + 1];

        // 시작 정점으로부터의 거리를 무한대로 초기화
        for (int i = 1; i <= N; i++) {
            distance[i] = Integer.MAX_VALUE;
        }

        distance[start] = 0; // 시작 정점의 최단 거리 = 0

        // 다익스트라 알고리즘 수행
        while (!pq.isEmpty()) {
            Edge nowEdge = pq.poll();
            int nowDest = nowEdge.dest;

            if (visited[nowDest]) continue; // 이미 방문한 노드면 다음 노드로 넘어감
            visited[nowDest] = true; // 현재 노드를 방문 처리

            // 현재 노드와 연결된 인접한 노드들에 대해 최단 거리를 업데이트
            for (Edge neighbor : graph.get(nowDest)) {
                if (distance[neighbor.dest] > distance[nowDest] + neighbor.cost) {
                    distance[neighbor.dest] = distance[nowDest] + neighbor.cost; // 최단 거리 업데이트
                    pq.offer(new Edge(neighbor.dest, distance[neighbor.dest]));
                }
            }
        }
        // 종료지점 거리 리턴
        return distance[end];
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
