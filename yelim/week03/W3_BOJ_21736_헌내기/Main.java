package W3_BOJ_21736_헌내기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static int n;
	static int m;
	static char[][] graph;
	static boolean[][] visited;
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	static int cnt;

	public static void main(String[] args) throws IOException {
		/* 문제) 헌내기는 친구가 필요해
		 * 도연이가 다니는 대학의 캠퍼스는 n*m 크기이며
		 * 캠퍼스에서 이동하는 방법은 벽이 아닌 상하좌우로 이동하는 것이다.
		 * O는 빈 공간, X는 벽, I는 도연이, P는 사람이다. I가 한 번만 주어짐이 보장된다.
		 */
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		graph = new char[n][m];
		visited = new boolean[n][m];
		
		// graph에 값 입력받아 저장
		for(int i=0; i<n; i++) {
			String s = br.readLine();
			for(int j=0; j<m; j++) {
				graph[i][j] = s.charAt(j);
			}
		}
		
		// P일 때 횟수 세는 변수 초기화
		cnt = 0;
		
		// graph 탐색하면서 I인 경우 dfs 호출
		for(int i=0; i<n; i++) {
			for(int j=0; j<m; j++) {
				if (graph[i][j] == 'I') {
					dfs(i, j);
				}
			}
		}
		
		// cnt가 0이 아니라면 횟수를 출력
		if (cnt != 0) {
			System.out.println(cnt);
		}
		// 0이면 TT 출력
		else {
			System.out.println("TT");
		}

	}
	
	// dfs 메소드
	public static void dfs(int x, int y) {
		visited[x][y] = true;	// 방문 체크
		
		// 델타배열 이용해서 탐색
		for(int d=0; d<4; d++) {
			int nr = x+dr[d];
			int nc = y+dc[d];
			
			// 배열 범위 벗어나지 않고, 방문한 적 없고, 이동 지점이 O or P일 경우 재귀호출하여 반복
			if (nr>=0 && nr<n && nc>=0 && nc<m && !visited[nr][nc] && 
				(graph[nr][nc]=='O' || graph[nr][nc]=='P')) {
				// P인 경우 cnt 1 증가
				if (graph[nr][nc] == 'P') {
					cnt++;
				}
				dfs(nr, nc);
			}
			
		}
	}

}
