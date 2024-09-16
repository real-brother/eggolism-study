package W8_BOJ_4179_불;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	static int r, c;
	static char[][] map;
	static int[][] time1;	// 지훈이 이동시간
	static int[][] time2;	// 불 이동시간
	static int dr[] = {-1, 1, 0, 0};
	static int dc[] = {0, 0, -1, 1};
	static Queue<int[]> q1 = new LinkedList<>();	// 지훈이
	static Queue<int[]> q2 = new LinkedList<>();	// 불

	public static void main(String[] args) throws IOException {
		/* 문제) 불!
		 * 불은 매 분마다 네 방향으로 확산
		 * 지훈이는 미로의 가장자리에 접한 공간에서 탈출
		 * 지훈이와 불은 벽이 있는 공간은 통과하지 못함.
		 * 
		 * #: 벽
		 * .: 지나갈 수 있음
		 * J: 지훈이 초기위치
		 * F: 불이 난 공간
		 * 
		 * 가장 빠른 탈출시간 출력
		 */
		
		
		/* 설계)
		 * 그냥 지훈이 이동이랑 불 이동 따로따로 bfs만 돌려주려고 했는데,, 안되는거 같아서ㅜㅜ
		 * 각각 이동 시간을 저장할 2차원 배열을 만들어줌.
		 * 
		 * 각각 이동할 때마다 1씩 더해주고,
		 * 지훈이가 이동할 때 불의 이동보다 빠른지 검사
		 */
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		
		map = new char[r][c];
		time1 = new int[r][c];	// 지훈이 이동시간
		time2 = new int[r][c];	// 불 이동시간
		
		for(int i=0; i<r; i++) {
			String s = br.readLine();
			for(int j=0; j<c; j++) {
				map[i][j] = s.charAt(j);	// 지도 값 입력받기
				
				// 각 이동시간 -1로 초기화
				time1[i][j] = -1;
				time2[i][j] = -1;
				
				// 지훈이가 있는 곳 큐에 넣어주고, 시간 0으로 설정
				if (map[i][j] == 'J') {
					q1.offer(new int[] {i, j});	
					time1[i][j] = 0;
				}
				// 불이 있는 곳 큐에 넣어주고, 시간 0으로 설정
				else if (map[i][j] == 'F') {
					q2.offer(new int[] {i, j});
					time2[i][j] = 0;
				}
			}
		}
		
		// 불 이동
		fireBfs();
		
		moveBfs();
		
//		for(int i=0; i<r; i++) {
//			for(int j=0; j<c; j++) {
//				System.out.print(time1[i][j]);
//			}
//			System.out.println();
//		}
	
	}
	
	// 불 이동 bfs
	static void fireBfs() {

		while(!q2.isEmpty()) {
			int[] cur = q2.poll();	// 현재 불 위치
			
			for(int d=0; d<4; d++) {
				int nr = cur[0]+dr[d];
				int nc = cur[1]+dc[d];
				
				// 범위 벗어나면 continue
				if (nr<0 || nr>=r || nc<0 || nc>=c) continue;
				
				// 벽이거나, 이미 왔던 곳이면 continue
				if (map[nr][nc]=='#' || time2[nr][nc]>=0) continue;
				
				// 현재 시간에 1 더한 값을 넣어주기
				time2[nr][nc] = time2[cur[0]][cur[1]]+1;
				q2.offer(new int[] {nr, nc});
			}
		}
	}
	
	
	// 지훈이 이동 bfs
	static void moveBfs() {
		
		while(!q1.isEmpty()) {
			int[] cur = q1.poll();	// 현재 지훈이 위치
			
			for(int d=0; d<4; d++) {
				int nr = cur[0]+dr[d];
				int nc = cur[1]+dc[d];
				
				// 범위 가장자리까지 오면 탈출한 것이므로. 현재 시간에 1 더하여 출력
				if (nr<0 || nr>=r || nc<0 || nc>=c) {
					System.out.println(time1[cur[0]][cur[1]]+1);
					return;
					
				}
				
				// 벽이거나, 이미 왔던 곳이면 continue
				if (map[nr][nc]=='#' || time1[nr][nc]>=0) {
					continue;
				}
				
				
				/* time2[nr][nc]!=-1 안넣었을 때 반례
				    5 5
					#####
					#F#J#
					###.#
					###.#
					###.#
				 * 답이 4가 나와야 하는데, IMPOSSIBLE이 나옴
				 */
				
				// 불이 온 시간이 지훈이 이동시간보다 작거나 같으면(불이 먼저 왔으면) continue
				if (time2[nr][nc]!=-1 && (time2[nr][nc] <= time1[cur[0]][cur[1]]+1)) {
					continue;
				}
				
				// 이동 가능하면 현재 시간 +1 값을 넣어주기
				time1[nr][nc] = time1[cur[0]][cur[1]]+1;
				q1.offer(new int[] {nr, nc});
			}
		}
		// 탈출 못하고 여기까지 오면 이동 불가. impossible 출력
		System.out.println("IMPOSSIBLE");
	}

}
