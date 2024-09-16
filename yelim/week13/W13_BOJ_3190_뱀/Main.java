package W13_BOJ_3190_뱀;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	static class Dir {
		int sec;
		char dir;
		
		public Dir(int sec, char dir) {
			this.sec = sec;
			this.dir = dir;
		}
	}
	
	static int n, k;	// 보드 크기, 사과 개수
	static int[][] board;
	static int l;	// 뱀의 방향 변환 횟수
	static Queue<Dir> change;
	static int[] dr = {0, 1, 0, -1};	// 우하좌상
	static int[] dc = {1, 0, -1, 0};

	public static void main(String[] args) throws IOException {
		/* 문제) 3190_뱀
		 * n*n 몇몇 칸에는 사과가 있음. 상하좌우 끝에는 벽
		 * 뱀은 처음에 맨위 맨좌측에 위치. 오른쪽을 향함
		 * 1. 몸 길이 늘려 머리를 다음칸에 위치
		 * 2. 벽이나 자기자신의 몸과 부딪히면 게임 끝
		 * 3. 이동한 칸에 사과가 있다면 사과는 없어지고 꼬리는 그대로
		 * 4. 사과가 없다면 몸 길이 줄여서 꼬리칸 비위주기
		 * 게임이 몇 초에 끝날까
		 * 
		 * L: 왼쪽으로 90도 / D: 오른쪽으로 90도
		 */
		
		/* 흠 그럼 뱀의 좌표 and 길이?
		 * 범위 벗어나면 끝
		 * 방향을 돌릴 때,, 같이 돌아야하는데 그럼 뱀 길이와 벽의 행..? 고려해야 함
		 * 
		 */
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());	// 보드 크기
		st = new StringTokenizer(br.readLine());
		k = Integer.parseInt(st.nextToken());	// 사과 개수
		
		board = new int[n][n];
		
		// 사과 위치 입력 받아서 board에 1로 저장 (인덱스 맞추기 위해 1 빼주기)
		for(int i=0; i<k; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken())-1;
			int b = Integer.parseInt(st.nextToken())-1;
			
			board[a][b] = 1;
		}

		st = new StringTokenizer(br.readLine());
		l = Integer.parseInt(st.nextToken());

		// 뱀 방향 변환 정보 입력 받아 저장
		change = new LinkedList<>();
		for(int i=0; i<l; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			char c = st.nextToken().charAt(0);
			
			change.add(new Dir(x, c));
		}
		
		// 0-우 1-하 2-좌 3-상
		Queue<int[]> q = new LinkedList<>();
		q.add(new int[] {0, 0});
		board[0][0] = 2;
		
		int time = 0;
		int dir = 0;
		
		int r = 0;
		int c = 0;
		
		while(true) {
			// 이렇게 꺼내버리면 안댐... 사과 없으면 그 때 꺼내줘야 함
//			int[] cur = q.poll();
			
			int nr = r+dr[dir];
			int nc = c+dc[dir];
			time++;
			
			// 범위 벗어나면 (벽 만나면) break
			if (nr<0 || nr>=n || nc<0 || nc>=n) {
				break;
			}
			// 뱀이 자신의 몸과 부딪히면 break
			if (board[nr][nc]==2) {
				break;
			}
			
			// 사과 없으면 큐에서 꺼내주자
			if (board[nr][nc] == 0) {
				int[] cur = q.poll();
				board[cur[0]][cur[1]] = 0;
			}
			
			// 방향 전환 끝나지 않았으면 확인해주기
			if(!change.isEmpty()) {
				// 시간이 큐의 첫 번째 요소의 시간과 같으면
				if(time == change.peek().sec) {
					// 큐에서 꺼내주기
					Dir d = change.poll();
					
					// 방향 확인해서 dir 바꿔주기
					if (d.dir == 'L') {
						dir = (dir+3)%4;
					}
					else {
						dir = (dir+1)%4;
					}
				}
			}
			
			// 뱀 표시
			board[nr][nc] = 2;
			q.add(new int[] {nr, nc});
			r = nr;
			c = nc;
		}
		
		System.out.println(time);
		
	}
	

}
