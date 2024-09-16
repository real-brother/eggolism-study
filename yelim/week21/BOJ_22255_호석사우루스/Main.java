import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	
	static int n, m;
	static int sr, sc, er, ec;
	static int[][] map;
	static int[] dr = {-1, 1, 0, 0};	// 상하 좌우
	static int[] dc = {0, 0, -1, 1};
	static int[][][] dist;

	public static void main(String[] args) throws IOException {
		/* 문제) 22255_호석사우루스
		 * 
		 * 다익스트라
		 * 오호 지난주꺼 바로 적용
		 */
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		
		sr = Integer.parseInt(st.nextToken())-1;
		sc = Integer.parseInt(st.nextToken())-1;
		er = Integer.parseInt(st.nextToken())-1;
		ec = Integer.parseInt(st.nextToken())-1;
		
		map = new int[n][m];
		for(int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<m; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		dist = new int[n][m][3];
		for(int i=0; i<n; i++) {
			for(int j=0; j<m; j++) {
				for(int k=0; k<3; k++) {
					dist[i][j][k] = Integer.MAX_VALUE;
				}
			}
		}
		
		int ans = move(sr, sc, er, ec);
		
		if (ans != -1) {
			System.out.println(dist[er][ec][ans%3]);
		} else {
			System.out.println(-1);
		}

	}
	
	static int move(int sr, int sc, int er, int ec) {
		PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[2]-o2[2]);	// cost 기준 정렬
		pq.add(new int[] {sr, sc, 0, 1});	// {시작 지점, cost, cnt}
		dist[sr][sc][1] = 0;	// 첫 번째 이동
		
		while (!pq.isEmpty()) {
			int[] cur = pq.poll();
			int r = cur[0];
			int c = cur[1];
			int cost = cur[2];
			int cnt = cur[3];
			
			if (dist[r][c][cnt%3] < cost) continue;
			if (r == er && c == ec) {
				return cnt;
			}
			
			// 3k - 상하좌우
			if (cnt%3 == 0) {
				for(int d=0; d<4; d++) {
					int nr = r+dr[d];
					int nc = c+dc[d];
					
					if (nr>=0 && nr<n && nc>=0 && nc<m) {
						if (map[nr][nc] != -1) {
							if (dist[nr][nc][(cnt+1) % 3] > map[nr][nc]+cost) {
								dist[nr][nc][(cnt+1) % 3] = map[nr][nc]+cost;
								pq.add(new int[] {nr, nc, dist[nr][nc][(cnt+1) % 3], cnt+1});
							}
						}
					}
				}
			}
			
			// 3k+1 - 상하
			else if (cnt%3 == 1) {
				for(int d=0; d<2; d++) {
					int nr = r+dr[d];
					int nc = c+dc[d];
					
					if (nr>=0 && nr<n && nc>=0 && nc<m) {
						if (map[nr][nc] != -1) {
							if (dist[nr][nc][(cnt+1) % 3] > map[nr][nc]+cost) {
								dist[nr][nc][(cnt+1) % 3] = map[nr][nc]+cost;
								pq.add(new int[] {nr, nc, dist[nr][nc][(cnt+1) % 3], cnt+1});
							}
						}
					}
				}
			}
			// 3k+2 - 좌우
			else {
				for(int d=2; d<4; d++) {
					int nr = r+dr[d];
					int nc = c+dc[d];
					
					if (nr>=0 && nr<n && nc>=0 && nc<m) {
						if (map[nr][nc] != -1) {
							if (dist[nr][nc][(cnt+1) % 3] > map[nr][nc]+cost) {
								dist[nr][nc][(cnt+1) % 3] = map[nr][nc]+cost;
								pq.add(new int[] {nr, nc, dist[nr][nc][(cnt+1) % 3], cnt+1});
							}
						}
					}
				}
			}
		}
		
		return -1;
		
	}

}
