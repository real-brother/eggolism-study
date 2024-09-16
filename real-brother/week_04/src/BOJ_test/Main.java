package BOJ_test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	
	static List<List<Integer>> graph = new ArrayList<>();	// 인접리스트
	static boolean[] visited;	// 방문체크
	static int n;
	static int cnt;

	public static void main(String[] args) throws IOException {
		/* 문제) 바이러스
		 * 컴퓨터의 수와 네트워크 상에서 서로 연결되어 있는 정보가 주어질 때, 
		 * 1번 컴퓨터를 통해 웜 바이러스에 걸리게 되는 컴퓨터의 수를 출력하는 프로그램을 작성하시오.
		 */
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());	// 컴퓨터의 수
		st = new StringTokenizer(br.readLine());
		int m = Integer.parseInt(st.nextToken());	// 이어져있는 쌍의 수
		
		visited = new boolean[n+1];
		
		// 리스트 선언
		for(int i=0; i<n+1; i++) {
			graph.add(new ArrayList<>());
		}
		
		// 인접 리스트에 값 넣어주기
		for(int i=0; i<m; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			
			graph.get(x).add(y);
			graph.get(y).add(x);
		}
		
		// 연결된 쌍이 하나도 없을 때 (이거 고려 안하니까 index에러 남)
//		if (m==0) {
//			cnt = 0;
//		}
//		else {
//			dfs(1);
//		}
		dfs(1);
		
		System.out.println(cnt);
		
	}
	
	// dfs
	static void dfs(int num) {
		visited[num] = true;
		int a = graph.get(num).size();
		for(int i=0; i<graph.get(num).size(); i++) {
			int idx = graph.get(num).get(i);
			
			if (!visited[idx]) {
				cnt++;
				dfs(idx);
			}
		}
	}

}