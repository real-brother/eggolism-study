package W10_BOJ_1753_최단경로;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	
	static class Node implements Comparable<Node>{
		int end;
		int w;
		
		public Node(int end, int w) {
			this.end = end;
			this.w = w;
		}

		@Override
		public int compareTo(Node o) {
			return this.w - o.w;
		}
	}

	
	static final int inf = Integer.MAX_VALUE; 
	static int v, e;
	static int start;
	static List<Node>[] list;
	static boolean[] visited;
	static int[] dist;
	
	
	public static void main(String[] args) throws IOException {
		/* 문제) 최단경로
		 * 방향 그래프가 주어지면 주어진 싲가점에서 다른 모든 정점으로의 최단 경로를 구하는 프로그램
		 * 단, 모든 간선의 가중치는 10 이하의 자연수
		 */
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		v = Integer.parseInt(st.nextToken());
		e = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		start = Integer.parseInt(st.nextToken())-1;
		
		// 리스트 초기화
		list = new ArrayList[v];
		for(int i=0; i<v; i++) {
			list[i] = new ArrayList<>();
		}
		
		// 각 정점의 최단거리 저장할 배열 큰 값으로 모두 초기화
		dist = new int[v];
		Arrays.fill(dist, inf);
		// 시작정점-도착정점-가중치 순으로 입력받아서 리스트에 넣어주기
		for(int i=0; i<e; i++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken())-1;
			int endV = Integer.parseInt(st.nextToken())-1;
			int weight = Integer.parseInt(st.nextToken());
			
			list[s].add(new Node(endV, weight));
		}
		
		dijkstra(start);
		for(int x : dist) {
			if (x == Integer.MAX_VALUE) System.out.println("INF");
			else { 
				System.out.println(x);
			}
		}
		
	}
	
	static void dijkstra(int s) {
		
		PriorityQueue<Node> pq = new PriorityQueue<>();
		visited = new boolean[v];
		
		// 시작정점의 거리는 0으로 초기화
		dist[s] = 0;
		
		// 큐에 값 넣어주기
		pq.add(new Node(s, 0));
		
		while(!pq.isEmpty()) {
			Node cur = pq.poll();
			
			if (visited[cur.end]) continue;
			visited[cur.end] = true;
			
			for(Node node : list[cur.end]) {
				if(!visited[node.end] && dist[node.end]>dist[cur.end]+node.w) {
					dist[node.end] = dist[cur.end]+node.w;
					pq.add(new Node(node.end, dist[node.end]));
				}
			}
		}
		
	}

}
