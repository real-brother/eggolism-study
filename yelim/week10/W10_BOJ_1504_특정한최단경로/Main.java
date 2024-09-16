package W10_BOJ_1504_특정한최단경로;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	
	static class Node2 implements Comparable<Node2>{
		int end;
		int w;
		
		public Node2(int end, int w) {
			this.end = end;
			this.w = w;
		}

		@Override
		public int compareTo(Node2 o) {
			return this.w-o.w;
		}
	}
	
	static final int inf = Integer.MAX_VALUE;
	static int v, e;
	static List<Node2>[] list;
	static int[] dist;
	static int v1, v2;

	public static void main(String[] args) throws IOException {
		/* 문제) 특정한 최단 경로
		 * 무방향 그래프. 임의로 주어진 두 정점은 반드시 통과해야 함.
		 * 한 번 이동했던 정점과 간선도 다시 이동할 수 있음.
		 * 반드시 최단 경로로 이동해야 함.
		 * 1번에서 N번으로 이동할 때 주어진 두 정점을 반드시 거치면서 최단 경로로 이동하는 프로그램 작성
		 */
		
		/* 설계)
		 * 다익스트라
		 * 두 정점을 무조건 지나야 함.
		 * 그럼 두 가지 경우를 고려해주면 된다. 1-v1-v2-v 이거나 1-v2-v1-v 이거나.
		 * 그럼 1번 정점을 시작으로 v1까지의 최단경로, v2까지의 최단경로를 각각 저장해주고
		 * 그 다음은 v1-v2까지 경로, v1-v까지의 경로,
		 * v2-v1까지 경로, v2-v까지의 경로를 각각 구해주면 된다.
		 * 
		 * 주의!!!
		 * 인덱스 조심!
		 * int로 하면 안됨..! 간선이 없으면 inf에서 계속 값을 더해주기 때문에
		 * first, second를 long으로 해주어야 함..
		 */
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		v = Integer.parseInt(st.nextToken());	// 정점 수
		e = Integer.parseInt(st.nextToken());	// 간선 수
		
		// 인접리스트 초기화
		list = new ArrayList[v];
		
		for(int i=0; i<v; i++) {
			list[i] = new ArrayList<>();
		}
		
		// 간선 정보 입력받기
		for(int i=0; i<e; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken())-1;
			int b = Integer.parseInt(st.nextToken())-1;
			int c = Integer.parseInt(st.nextToken());
			
			// 인접리스트 저장
			list[a].add(new Node2(b, c));
			list[b].add(new Node2(a, c));
		}
		
		// 거쳐야 하는 두 정점 입력받기 (인덱스 맞추기 위해 1 빼줌)
		st = new StringTokenizer(br.readLine());
		v1 = Integer.parseInt(st.nextToken())-1;
		v2 = Integer.parseInt(st.nextToken())-1;
		
		// 각 정점의 최단 거리 저장할 배열
		dist = new int[v];
		
		long first = 0;
		long second = 0;
		
		// 시작정점부터 v1, v2 까지의 최단 경로 각각 저장
		Arrays.fill(dist, inf);
		dijkstra(0);
		first += dist[v1];
		second += dist[v2];
		
		// v1에서 v2, v 까지의 최단 경로 각각 저장	
		// dist 배열 초기화 다시 해줘야함!!
		Arrays.fill(dist, inf);
		dijkstra(v1);
		first += dist[v2];
		second += dist[v-1];
		
		// v2에서 v, v1 까지의 최단 경로 각각 저장
		Arrays.fill(dist, inf);
		dijkstra(v2);
		first += dist[v-1];
		second += dist[v1];
		
		// inf보다 크다면(경로가 없다면) -1 출력
		if (first>=inf || second>=inf) {
			System.out.println(-1);
		} 
		// 아니면 쵝소값 비교하여 출력
		else {
			long min = Math.min(first, second);
			System.out.println(min);
		}

	}
	
	// 다익스트라
	static void dijkstra(int start) {
		PriorityQueue<Node2> pq = new PriorityQueue<>();
		
		// 처음 시작지점은 거리 0으로 초기화
		dist[start] = 0;
		// 큐에 넣어주기
		pq.add(new Node2(start, 0));
		
		while(!pq.isEmpty()) {
			Node2 cur = pq.poll();
			
			// 거리가 현재 정점까지의 거리보다 크다면 탐색 중지
			if (dist[cur.end]<cur.w) continue;
			
			// 저장된 거리와 현재까지의 거리+다음 거리 비교하여 갱신
			for(Node2 node : list[cur.end]) {
				if(dist[node.end]>dist[cur.end]+node.w) {
					dist[node.end] = dist[cur.end]+node.w; 
					pq.add(new Node2(node.end, dist[node.end]));
				}
			}
		}
		
	}

}
