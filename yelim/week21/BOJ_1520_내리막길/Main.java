import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static int n, m;
	static int[][] map;
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, -1, 0, 1};
	static boolean[][] visited;
	static int[][] dp;
	static int cnt;
	
	public static void main(String[] args) throws IOException {
		/* 문제) 1520_내리막 길
		 * 
		 * dfs만 하면 시간초과
		 */
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());	// 행
		m = Integer.parseInt(st.nextToken());	// 열
		
		map = new int[n][m];
		visited = new boolean[n][m];
		dp = new int[n][m];	// 경로 개수 저장할 dp 배열
		
		// dp는 -1로 초기화: 탐색한 적 없음
		for(int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<m; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				dp[i][j] = -1;
			}
		}
		
		int ans = dfs(0, 0);
		
		System.out.println(ans);
		

	}
	
	static int dfs(int r, int c) {
//		visited[r][c] = true;
		
		// 끝까지 왔으면 경로 있으니 1 반환
		if (r==n-1 && c==m-1) {
			return 1;
		}
		
		// 탐색한 적이 있으면 해당 좌표에서의 경로 개수 반환
		if (dp[r][c] != -1) {
			return dp[r][c];
		}
		
		// 탐색: 지금은 경로 0개
		dp[r][c] = 0;
		
		for(int d=0; d<4; d++) {
			int nr = r+dr[d];
			int nc = c+dc[d];
			
			if (nr>=0 && nr<n && nc>=0 && nc<m) {
				// 높이가 낮으면 현재 dp 값(경로 개수)에 nr, nc의 경로 개수 더해준다.
				if (map[nr][nc]<map[r][c]) {
					dp[r][c] += dfs(nr, nc);
				}
			}
		}
		// 경로 개수 반환
		return dp[r][c];
	}

}
