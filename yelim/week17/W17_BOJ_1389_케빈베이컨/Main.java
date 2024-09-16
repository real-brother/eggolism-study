package W17_BOJ_1389_케빈베이컨;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static int n, m;
	static List<List<Integer>> graph = new ArrayList<>();
	static int min = Integer.MAX_VALUE;
	static int ans = 0;
	
	public static void main(String[] args) throws IOException {
		/* 문제) 1389_케빈 베이컨의 6단계 법칙
		 * 
		 * 케빈 베이컨 수 = 친구관계가 주어졌을 때, 모든 사람과의 최단 관계 수를 더한 값
		 * 케빈 베이컨 수가 가장 작은 사람 구하기
		 */
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		// 그래프 초기화
		for(int i=0; i<=n; i++) {
			graph.add(new ArrayList<>());
		}
		
		for(int i=0; i<m; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			graph.get(a).add(b);
			graph.get(b).add(a);
		}
		
		for(int i=1; i<=n; i++) {
			bfs(i);
		}
		
		System.out.println(ans);
	}
	
	static void bfs(int idx) {
		Queue<int[]> q = new LinkedList<>();
		boolean[] visited = new boolean[n+1];
		
		q.add(new int[] {idx, 0});
		visited[idx] = true;
		
		int result = 0;
		
		while(!q.isEmpty()) {
			int[] cur = q.poll();
			
			for(int next : graph.get(cur[0])) {
				if (!visited[next]) {
					result += cur[1]+1;
					q.add(new int[] {next, cur[1]+1});
					visited[next] = true;
				}
			}
		}
		
		// 최소값 갱신하며 정답 번호도 갱신
		if (result < min) {
			min = result;
			ans = idx;
		}
		
	}

}
