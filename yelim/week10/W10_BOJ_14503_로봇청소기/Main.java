package W10_BOJ_14503_로봇청소기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static int n, m;
	static int r, c, d;
	static int[][] map;
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, 1, 0, -1};
	static int cnt = 1;	// 처음은 무조건 빈 칸

	public static void main(String[] args) throws IOException {
		/* 문제) 로봇 청소기
		 * 1. 현재 칸이 아직 청소되지 않은 경우, 현재 칸 청소
		 * 2. 주변 4칸 중 빈 칸이 없는 경우,
		 * 	- 방향 유지한 채로 한 칸 후진할 수 있다면 한 칸 후진하고 1번으로
		 *  - 벽이라 후진할 수 없다면 작동을 멈춤
		 * 3. 주변 4칸 중 빈 칸이 있는 경우,
		 * 	- 반시계 방향으로 90도 회전
		 *  - 앞쪽 칸이 청소되지 않은 빈 칸인 경우 한 칸 전진
		 *  - 1번으로 돌아가기
		 *  
		 * d: 0-북, 1-동, 2-남, 3-서
		 * 반시계방향 회전: 
		 * 0->3, 3->2, 2->1, 1->0
		 */
		
		/* 주의)
		 * 주변에 빈 칸이 있으면 무조건 반시계 방향으로 90도 회전하고 가기 때문에
		 * 90도 회전한 방향부터 탐색해야 함..
		 */
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		// 방 크기
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		
		// 로봇 청소기가 있는 위치의 좌표와 바라보고 있는 방향
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		d = Integer.parseInt(st.nextToken());
		
		// 방의 상태 입력받기
		map = new int[n][m];
		for(int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<m; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		clean(r, c, d);
		System.out.println(cnt);

	}
	
	// 청소 메소드
	static void clean(int r, int c, int d) {
		
		// 현재 칸 청소 (무조건 빈 칸)
		map[r][c] = -1;
		
		for(int i=0; i<4; i++) {
			// 반시계 방향으로 회전
			d = (d+3)%4;
			
			int nr = r+dr[d];
			int nc = c+dc[d];
			
			// 범위 내에 있고, 빈 칸이면 카운트 증가, 전진
			if(nr>=0 && nr<n && nc>=0 && nc<m) {
				if(map[nr][nc] == 0) {
					cnt++;
					clean(nr, nc, d);	// 그 위치로 이동해서 다시 탐색
					// 여기서 리턴 안하면 돌아가면서 엄한데 청소함,,
					return;
				}
			}
		}
		
		// 빈 칸 없으면 후진
		int back = (d+2)%4;
		
		int br = r+dr[back];
		int bc = c+dc[back];
		
		// 범위 내에 있고, 벽이 아니면 후진해서 다시 탐색 (방향은 그대로)
		if (br>=0 && br<n && bc>=0 && bc<m) {
			if (map[br][bc] != 1) {
				clean(br, bc, d);
			}
		}
		
		
	}

}
