package W6_BOJ_1916_최소비용;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

	static int n;
	static int[][] graph;
	static boolean[] visited;
	static int[] distance;
	
	public static void main(String[] args) throws IOException {
		/* 문제) 최소비용 구하기
		 * N개의 도시가 있다. 그리고 한 도시에서 출발하여 다른 도시에 도착하는 M개의 버스가 있다. 
		 * 우리는 A번째 도시에서 B번째 도시까지 가는데 드는 버스 비용을 최소화 시키려고 한다.
		 * A번째 도시에서 B번째 도시까지 가는데 드는 최소비용을 출력하여라. 
		 * 도시의 번호는 1부터 N까지이다.
		 */
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		int m = Integer.parseInt(st.nextToken());
		
		graph = new int[n+1][n+1];
		visited = new boolean[n+1];
		
		
		
		for (int i=0; i<m; i++) {
			st = new StringTokenizer(br.readLine());
			int depart = Integer.parseInt(st.nextToken());
			int arrive = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			
			graph[depart][arrive] = cost;
			graph[arrive][depart] = cost;

		}
		
		// 최소값 구하기 위해 i와 j가 같지 않고, graph 값이 0이면 max값 설정해주기
		for(int i=1; i<=n; i++) {
			for(int j=1; j<=n; j++) {
				if (i!=j && graph[i][j]==0) {
					graph[i][j] = Integer.MAX_VALUE;
				}
			}
		}
		
		st = new StringTokenizer(br.readLine());
		int start = Integer.parseInt(st.nextToken());
		int end = Integer.parseInt(st.nextToken());
		
		search(start);
		
		System.out.println(distance[end]);
	}
	
	static void search(int num) {
		distance = new int[n+1];
		
		// 초기값 설정해주기
		for(int i=0; i<n; i++) {
			distance[i] = graph[num][i];
		}
		
		int min = Integer.MAX_VALUE;
		int idx = 0;
		for(int i=0; i<n; i++) {
			if (distance[i]<min && !visited[i]) {
				min = distance[i];
				idx = i;
			}
		}
		
		visited[num] = true;
		for(int i=1; i<=n-2; i++) {
			int cur = idx;
			visited[cur] = true;
			for(int j=1; j<=n; j++) {
				if (!visited[j]) {
					distance[j] = Math.min(distance[cur]+graph[cur][j], graph[cur][j]);
				}
			}
		}
		
	
	}

}
