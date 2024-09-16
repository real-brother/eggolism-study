package W10_BOJ_1197_최소스패닝트리;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	
	static class Edge implements Comparable<Edge> {
		int end;
		int w;
		
		public Edge(int end, int w) {
			this.end = end;
			this.w = w;
		}

		@Override
		public int compareTo(Edge o) {
			return this.w-o.w;
		}
	}

	public static void main(String[] args) throws IOException {
		/* 문제) 최소 스패닝 트리
		 * 그래프가 주어졌을 때, 그 그래프의 최소 스패닝 트리를 구하는 프로그램
		 */
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int v = Integer.parseInt(st.nextToken());
		int e = Integer.parseInt(st.nextToken());
		
		List<Edge>[] list = new ArrayList[v+1];
		
		for(int i=1; i<=v; i++) {
			list[i] = new ArrayList<>();
		}
		
		for(int i=0; i<e; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			
			list[a].add(new Edge(b, c));
			list[b].add(new Edge(a, c));
		}
		
		boolean[] visited = new boolean[v+1];
		
		PriorityQueue<Edge> pq = new PriorityQueue<>();

		// 0번 정점부터 시작
		visited[1] = true;
		
		// 0번 정점과 인접한 정점들 큐에 넣어주기
		for(Edge edge : list[1]) {
			pq.add(edge);
		}
		
		long ans = 0;
		
		while(!pq.isEmpty()) {
			Edge cur = pq.poll();
			
			// 이미 방문했으면 넘어가기
			if (visited[cur.end]) continue;
			
			// 방문체크
			visited[cur.end] = true;
			// 가중치 더해주기
			ans += cur.w;
			
			for(Edge edge : list[cur.end]) {
				pq.add(edge);
			}
			
		}
		
		System.out.println(ans);
		
	}

}
