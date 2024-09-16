package W17_BOJ_2458_키순서;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	
	static int n, m;
	static List<Integer>[][] graph;	// 키 큰 경우 작은 경우 나누기 위해서
	static boolean[] visited;

	public static void main(String[] args) throws IOException {
		/* 문제) 2458_키 순서
		 * 
		 * 1번부터 n번까지 학생들에 대해 두 학생의 키를 비교한 결과의 일부가 주어진다.
		 * 본인의 키 순서를 알 수 있는 학생들이 몇 명일까?
		 */
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		graph = new List[2][n+1];
		
		for(int i=0; i<2; i++) {
			for(int j=1; j<=n; j++) {
				graph[i][j] = new ArrayList<>();
			}
		}
		
		
		for(int i=0; i<m; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			graph[0][a].add(b);
			graph[1][b].add(a);
		}
		
		int ans = 0;
		
		// 모든 정점에 대해 키 작은 경우, 큰 경우 dfs 각각 돌리기
		for(int i=1; i<=n; i++) {
			visited = new boolean[n+1];
			int tall = dfs(0, i);
			int small = dfs(1, i);
			
			if (tall+small == n+1) {
				ans += 1;
			}
			
		}
		System.out.println(ans);
		
	}
	
	static int dfs(int a, int b) {
		int cnt = 1;
		visited[b] = true;
		
		for(int next : graph[a][b]) {
			if (!visited[next]) {
				cnt += dfs(a, next);
			}
		}
		return cnt;
	}

}
