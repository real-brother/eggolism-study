package BOJ_11403_경로찾기;

import java.util.Scanner;

public class Main {
	static int[][] graph;
	static int[] visited;
	static int[][] result;
	static int N;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		
		graph = new int[N+1][N+1];
		visited = new int[N+1];
		result = new int[N+1][N+1];
		
		for(int r = 1; r <= N; r++) {
			for(int c = 1; c <= N; c++) {
				graph[r][c] = sc.nextInt();
			}
		}
		
		// 해당 노드에서 출발했을 때의 visited값을 result에 옮겨주고
		// visited를 초기화 해줬다.
		for(int r = 1; r <= N; r++) {
			dfs(r);
			for(int i = 1; i <= N; i++) {
				result[r][i] = visited[i];
				visited[i] = 0;
			}
		}
		
		
		//출력
		for(int r = 1; r <= N; r++) {
			for(int c = 1; c <= N; c++) {
				System.out.print(result[r][c] + " ");
			}
			System.out.println();
		}

	}
	
	static void dfs(int start) {
		for(int c = 1; c <= N; c++) {
			if(graph[start][c] == 1) {
				if(visited[c] == 0) {
					// dfs를 처음 시작할 때 visited를 하지 않기 위해서 
					// 여기에서 visited를 1로 바꿔준다.
					visited[c] = 1;
					dfs(c);
				}
			}
		}
	}
}