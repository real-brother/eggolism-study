package W12_BOJ_2636_치즈;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static int n, m;
	static int[][] map;
	static boolean[][] visited;
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	static int cheeseCnt;
	
	
	public static void main(String[] args) throws IOException {
		/* 문제) 2636_치즈
		 * 치즈의 가장자리만 한시간 후 녹아 없어짐
		 * 치즈가 모두 녹기까지 걸리는 시간과 다 녹기 한시간 전 남아있는 칸의 개수 구하기 
		 */
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		map = new int[n][m];
		cheeseCnt = 0;
		
		for(int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<m; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j]==1) {
					cheeseCnt++;
				}
			}
		}
		
		int cnt = 0;
		int time = 0;
		// 치즈 개수가 0일 때까지 반복
		while(cheeseCnt != 0) {
			// 현재 치즈 개수 저장
			cnt = cheeseCnt;
			// 시간 증가
			time++;
			// 방문 배열 초기화
			visited = new boolean[n][m];
			// bfs 호출
			bfs();
		}

		System.out.println(time);
		System.out.println(cnt);
		
	}
	
	static void bfs() {
		Queue<int[]> q = new LinkedList<>();
		q.add(new int[] {0, 0});
		visited[0][0] = true;
		
		while(!q.isEmpty()) {
			int[] cur = q.poll();
			
			// 사방 탐색해주기
			for(int d=0; d<4; d++) {
				int nr = cur[0]+dr[d];
				int nc = cur[1]+dc[d];
				
				// 범위 내에 있고, 방문하지 않았다면
				if (nr>=0 && nr<n && nc>=0 && nc<m && !visited[nr][nc]) {
					// 방문 처리
					visited[nr][nc] = true;
					// 빈 곳이면 큐에 넣어주기
					if (map[nr][nc] == 0) {
						q.add(new int[] {nr, nc});
					}
					// 치즈가 있는 곳이면 0으로 바꿔주고, 치즈 개수 감소
					else {
						map[nr][nc] = 0;
						cheeseCnt--;
						
					}
				}
			}
			
		}
		
	}

}
