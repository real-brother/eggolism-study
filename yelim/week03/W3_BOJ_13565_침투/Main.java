package W3_BOJ_13565_침투;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static int[][] graph;
	static boolean[][] visited;
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	static boolean check = false;	// 끝까지 탐색 완료되었는지 확인

	public static void main(String[] args) throws IOException {
		/* 문제) 침투
		 * 바깥쪽부터 시작해서 안쪽까지 이어지는지 판단
		 * 첫째 줄에는 격자의 크기를 나타내는  M (2 ≤ M ≤ 1,000) 과 N (2 ≤ N ≤ 1,000) 이 주어진다. 
		 * M줄에 걸쳐서, N개의 0 또는 1 이 공백 없이 주어진다. 
		 * 0은 전류가 잘 통하는 흰색, 1은 전류가 통하지 않는 검은색 격자임을 뜻한다.
		 * 
		 * 바깥에서 흘려준 전류가 안쪽까지 잘 전달되면 YES를 출력한다.
		 * 그렇지 않으면 NO를 출력한다.
		 */
		
		
		/* 설계)
		 * dfs + 델타탐색!
		 * 델타배열 만들어서 상하좌우 갈 수 있는지 확인, visited 값 확인, 배열 범위 확인해서
		 * 갈 수 있으면 재귀호출해서 반복.
		 * 마지막 행까지 갔고, 그래프 값이 0이면 끝까지 온거니까 true 값 반환
		 */

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		
		graph = new int[n][m];
		visited = new boolean[n][m];
		
		// graph에 값 입력받아서 저장
		for(int i=0; i<n; i++) {
			String s = br.readLine();
			for(int j=0; j<m; j++) {
				graph[i][j] = s.charAt(j)-'0';	// 숫자로 바꿔서 저장해주기
			}
		}
		
		
		// 열의 개수만큼 반복
		for(int i=0; i<m; i++) {
			// 0행의 i값이 0이라면 dfs 호출
			if (graph[0][i] == 0) {
				dfs(0, i);
			}
			
		}
		// check값이 true라면 YES 출력
		if (check) {
			System.out.println("YES");
		}
		else {
			System.out.println("NO");
		}
		
	}
	
	// DFS 메소드
	public static void dfs(int x, int y) {
		// 방문한 노드 true로 표시
		visited[x][y] = true;
		// 행이 마지막 행이고, 그래프 값이 0이면 (끝까지 왔으면) check 값 true, 끝내기
		if (x==graph.length-1 && graph[x][y] == 0) {
			check = true;
			return;
		}
		
		// 델타탐색(상하좌우 확인)
		for(int d=0; d<4; d++) {
			int nr = x+dr[d];
			int nc = y+dc[d];
			
			// 배열의 범위를 벗어나지 않고, 방문한 적이 없으며, 그래프 값이 0일 때(길이 있을 때)
			// 이동해서 재귀호출
			if (nr>=0 && nr<graph.length && nc>=0 && nc<graph[0].length && 
				visited[nr][nc]==false && graph[nr][nc]==0) {
				dfs(nr, nc);
			}
		}
		
		
	}

}
