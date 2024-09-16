package W3_BOJ_1260_DFS와BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	static int[][] graph;
	static int[] visited;
	static Queue<Integer> q = new LinkedList<>();

	public static void main(String[] args) throws IOException {
		/* 문제)
		 * 그래프를 DFS로 탐색한 결과와 BFS로 탐색한 결과를 출력하는 프로그램을 작성하시오. 
		 * 단, 방문할 수 있는 정점이 여러 개인 경우에는 정점 번호가 작은 것을 먼저 방문하고, 
		 * 더 이상 방문할 수 있는 점이 없는 경우 종료한다. 정점 번호는 1번부터 N번까지이다.
		 * 
		 * 첫째 줄에 정점의 개수 N(1 ≤ N ≤ 1,000), 간선의 개수 M(1 ≤ M ≤ 10,000), 
		 * 탐색을 시작할 정점의 번호 V가 주어진다. 
		 * 다음 M개의 줄에는 간선이 연결하는 두 정점의 번호가 주어진다. 
		 * 어떤 두 정점 사이에 여러 개의 간선이 있을 수 있다. 입력으로 주어지는 간선은 양방향이다.
		 */

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int v = Integer.parseInt(st.nextToken());
		
		
		graph = new int[n+1][n+1];
		visited = new int[n+1];
		
		// 간선 수만큼 입력받으며 graph에 이동가능한 경로 1로 저장
		for(int i=0; i<m; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			
			graph[x][y] = 1;
			graph[y][x] = 1;
		}
		
		dfs(v);
		System.out.println();
		
		// visited 배열 초기화
		visited = new int[n+1];
		// 첫번째 시작할 노드 큐에 저장
		q.add(v);
		// 해당 노드 방문 표시
		visited[v] = 1;
		bfs();
		
	}
	
	// DFS 깊이우선탐색 메소드
	public static void dfs(int n) {
		visited[n] = 1;	// 해당 노드에 방문했음을 표시
		System.out.print(n+" ");	// 출력
		
		// 그래프의 길이만큼 반복하며 방문한 적이 있는지, 노드에서 이동 가능한 경로가 있는지 확인
		// 있으면 재귀호출하여 반복
		for(int i=1; i<graph.length; i++) {
			if (visited[i]==0 && graph[n][i]==1) {
				dfs(i);
			}
		}
	}
	
	// BFS 너비우선탐색 메소드
	public static void bfs() {
		// 큐에 요소가 없을 때까지 반복
		while (!q.isEmpty()) {
			// 큐의 첫 요소를 제거
			int cur = q.poll();
			// 현재 지점 출력
			System.out.print(cur+" ");
			
			// 그래프 길이만큼 반복하며 방문한 적이 있는지, 이동가능한 경로가 있는지 확인
			// 경로가 있으면 방문했다고 표시, 큐에 넣기
			for(int i=1; i<graph.length; i++) {
				if (visited[i]==0 && graph[cur][i]==1) {
					visited[i] = 1;
					q.add(i);
				}
			}
			
		}
	}

}
