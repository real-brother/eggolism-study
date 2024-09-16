package W10_BOJ_16398_행성연결;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	
	static class Edge implements Comparable<Edge>{
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
		/* 문제) 행성 연결
		 * 행성 간에 가중치 간선 추가하고, 모든 행성 연결했을 때 최소비용 출력
		 */
		
		/* 설계)
		 * 우선순위 큐 활용한 프림 알고리즘
		 */
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		// 행성의 수
		int n = Integer.parseInt(st.nextToken());
		
		List<Edge>[] list = new ArrayList[n];
		
		// 리스트 초기화
		for(int i=0; i<n; i++) {
			list[i] = new ArrayList<>();
		}
		
		// 인접리스트에 값 넣어주기
		for(int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<n; j++) {
				int cost = Integer.parseInt(st.nextToken());
				list[i].add(new Edge(j, cost));
			}
		}
		
		boolean[] visited = new boolean[n];
		
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		
		// 0번 정점에서 시작
		visited[0] = true;
		
		// 0번 정점과 인접한 정점들 간선 정보 큐에 넣기
		for(Edge edge : list[0]) {
			pq.add(edge);
		}
		
//		int pick = 1;
		long ans = 0;
		
		// 큐가 빌 때까지
		while(!pq.isEmpty()) {
			Edge cur = pq.poll();
			
			// 이미 방문했다면 넘어가기
			if(visited[cur.end]) continue;
			
			// 방문체크
			visited[cur.end] = true;
			// 비용 더해주기
			ans += cur.w;
			
//			// 선택 카운트 증가
//			pick++;
			
			// cur.end 정점과 인접한 정점 큐에 넣어주기
			for(Edge edge : list[cur.end]) {
				pq.add(edge);
			}
			
//			pq.addAll(list[cur.end]);
			
		}
		
		System.out.println(ans);
		
	}

}
