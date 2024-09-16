package W9_BOJ_17144_미세먼지안녕;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	static int R, C, t;
	static int[][] map;
	static boolean[][] visited;
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	static int[][] copied;
	static int airR;

	public static void main(String[] args) throws IOException {
		// 문제) 미세먼지 안녕!
		
		/* 설계)
		 * 주어진 시간 동안 반복하며 map 카피 새로 해주고,
		 * bfs, move(미세먼지 이동) 호출
		 * 
		 * bfs:
		 * 큐에 미세먼지가 있는 곳 좌표와 양 넣고 반복 돌기
		 * 인접한 곳에 확산 가능한 곳 카운트 세서 
		 * 각 방향에 확산 값 더해주고, 해당 칸도 남은 양 구해주기
		 * 
		 * move:
		 * 공기청정기 윗부분은 반시계, 아랫부분은 시계방향으로 돈다.
		 * 뒤에서부터 거꾸로 값을 하나씩 옮겨준다.
		 * 공기청정기 다음 열은 정화되므로 0으로 설정
		 */
	
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		R = Integer.parseInt(st.nextToken());	// 행
		C = Integer.parseInt(st.nextToken());	// 열
		t = Integer.parseInt(st.nextToken());	// 시간
		
		map = new int[R][C];
		visited = new boolean[R][C];
		
		for(int i=0; i<R; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<C; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == -1) {
					airR = i;	// 공기청정기가 있는 행 저장 (아래행이 저장되겠져)
				}
			}
		}
		
		// 주어진 시간 동안 반복하면서 map 카피
		// 미세먼지 확산 bfs 호출, 미세먼지 이동 메소드 호출
		for(int i=0; i<t; i++) {
			copied = new int[R][C];
			for(int j=0; j<R; j++) {
				copied[j] = map[j].clone();
			}
			bfs();
			move(airR);
		}
		
		// 미세먼지 양 변수 초기화
		int sum = 0;
		
		// 미세먼지 양 합 구하기
		for(int i=0; i<R; i++) {
			for(int j=0; j<C; j++) {
				if(map[i][j] != -1) {
					sum+=map[i][j];
				}
			}
		}
		
		System.out.println(sum);

	}
	
	
	// 미세먼지 확산 bfs 메소드
	static void bfs() {
		Queue<Dust> q = new LinkedList<>();
		
		// 큐에 미세먼지가 있는 곳 좌표와 양 넣기
		for(int i=0; i<R; i++) {
			for(int j=0; j<C; j++) {
				if (copied[i][j] > 0) {
					q.offer(new Dust(i, j, copied[i][j]));
				}
			}
		}
		
		
		while(!q.isEmpty()) {
			Dust cur = q.poll();
			
			// 상하좌우에 확산 가능한 개수 저장 변수
			int cnt = 0;
			
			for(int d=0; d<4; d++) {
				int nr = cur.x+dr[d];
				int nc = cur.y+dc[d];
				
				// 범위 내에 있고
				if (nr>=0 && nr<R && nc>=0 && nc<C) {
					// 공기청정기가 아니라면 확산 가능
					if (map[nr][nc]!=-1) {
						cnt++;
						map[nr][nc] += cur.dust/5;	// 현재 칸의 미세먼지 양/5 값 더해주기
					}
				}
			}
			// 현재 칸의 미세먼지 양 구해주기
			map[cur.x][cur.y] -= (cur.dust/5) * cnt; 
		}
		
		
	}
	
	
	// 공기청정기 작동!
	static void move(int air) {
		int upAir = air-1;	// 공기청정기 윗부분 행
		
		// 돌아돌아... 거꾸로 해주ㅓ야댐!
		for(int i=upAir-1; i>0; i--) {
			map[i][0] = map[i-1][0];
		}
		for(int i=0; i<C-1; i++) {
			map[0][i] = map[0][i+1];
		}
		for(int i=0; i<upAir; i++) {
			map[i][C-1] = map[i+1][C-1];
		}
		for(int i=C-1; i>0; i--) {
			map[upAir][i] = map[upAir][i-1];
		}
		
		// 공기청정기 들어갔다 나와서 정화됨
		map[upAir][1] = 0;
		
		
		// 아랫부분
		for(int i=air+1; i<R-1; i++) {
			map[i][0] = map[i+1][0];
		}
		for(int i=0; i<C-1; i++) {
			map[R-1][i] = map[R-1][i+1];
		}
		for(int i=R-1; i>air; i--) {
			map[i][C-1] = map[i-1][C-1]; 
		}
		for(int i=C-1; i>0; i--) {
			map[air][i] = map[air][i-1];
		}
		
		// 여기도 공기청정기 덕분에 정화됨
		map[air][1] = 0;
	}

	
	static class Dust {
		int x;
		int y;
		int dust;
		
		public Dust(int x, int y, int dust) {
			this.x = x;
			this.y = y;
			this.dust = dust;
		}
	}
}
