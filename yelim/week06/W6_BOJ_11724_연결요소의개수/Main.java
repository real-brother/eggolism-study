package W6_BOJ_11724_연결요소의개수;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	
	static List<List<Integer>> graph = new ArrayList<>();
	static boolean[] visited;
	static int cnt;

	public static void main(String[] args) throws IOException {
		/* 문제) 연결 요소의 개수
		 * 방향 없는 그래프가 주어졌을 때, 연결 요소의 개수를 구하는 프로그램을 작성하시오.
		 */

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());	// 정점의 개수
		int m = Integer.parseInt(st.nextToken());	// 간선의 개수
		
		for(int i=0; i<n+1; i++) {
			graph.add(new ArrayList<>());
		}
		
		visited = new boolean[n+1];
		
		for(int i=0; i<m; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			
			graph.get(x).add(y);
			graph.get(y).add(x);
		}
		
		// 방문한 적 없는 정점에서 dfs 호출하고 cnt++
		for (int i=1; i<n+1; i++) {
			if (!visited[i]) {
				dfs(i);
				cnt++;
			}
		}
		
		System.out.println(cnt);
		
	}
	
	// dfs 메소드
	static void dfs(int num) {
		visited[num] = true;
		
		for(int i=0; i<graph.get(num).size(); i++) {
			int idx = graph.get(num).get(i);
			
			if (!visited[idx]) {
				dfs(idx);
			}
		}
	}

}
