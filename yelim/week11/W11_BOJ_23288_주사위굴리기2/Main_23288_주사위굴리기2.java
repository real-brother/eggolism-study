package WEEK_11;
 
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_23288_주사위굴리기2 {
	
	static int n, m, k;
	static int[][] map;
	static boolean[][] visited;
	static int[] dr = {0, 1, 0, -1};	// 우 하 좌 상
	static int[] dc = {1, 0, -1, 0};
	// 주사위 저장 배열 초기 설 (1-위, 2-뒤, 3-오, 4-왼, 5-앞, 6-아래)
	static int[] dice = {0, 1, 2, 3, 4, 5, 6};
	static int sum;

	public static void main(String[] args) throws IOException {
		/* 문제) 주사위 굴리기2_23288
		 * 
		 */
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		
		map = new int[n][m];
		
		for(int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<m; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		int x = 0;
		int y = 0;
		int d = 0;
		
		for(int i=0; i<k; i++) {
			int nx = x+dr[d];
			int ny = y+dc[d];
			
			// 갈 수 없으면
			if (nx<0 || nx>=n || ny<0 || ny>=m) {
				d = (d+2)%4;	// 이동 방향 반대로
				nx = x+dr[d];
				ny = y+dc[d];
			}
			bfs(nx, ny);
			dice(d);
			// 주사위 아랫면이 지도의 숫자보다 크면 시계방향으로 90
			if (dice[6]>map[nx][ny]) {
				d = (d+1)%4;	// 시계방향으로 90도
			}
			// 작으면 반시계방향으로 90
			else if (dice[6] < map[nx][ny]){
				d = (d+3)%4;	// 반시계 90도
			}
			x = nx;
			y = ny;
		}
		
		System.out.println(sum);
		

	}

	
	// 점수 계산
	static void bfs(int r, int c) {
		visited = new boolean[n][m];
		
		int num = map[r][c];
		int cnt = 1;
		
		Queue<int[]> q = new LinkedList<>();
		q.add(new int[] {r, c});
		visited[r][c] = true;
		
		while(!q.isEmpty()) {
			int[] cur = q.poll();
			
			for(int d=0; d<4; d++) {
				int nr = cur[0]+dr[d];
				int nc = cur[1]+dc[d];
				
				if (nr>=0 && nr<n && nc>=0 && nc<m) {
					if (!visited[nr][nc] && map[nr][nc]==num) {
						visited[nr][nc] = true;
						cnt++;
						q.add(new int[] {nr, nc});
					}
						
				}
			}
		}
		sum += (num*cnt);
		
	}
	
	// 주사위 굴러가는 메소드
	static void dice(int dir) {
		int d1 = dice[1];	// 위
		int d2 = dice[2];	// 뒤
		int d3 = dice[3];	// 오른쪽
		int d4 = dice[4];	// 왼쪽
		int d5 = dice[5];	// 앞
		int d6 = dice[6];	// 아래
		
		switch(dir) {
		case 0: // 오른쪽으로 돌아갈 때
			dice[1] = d4;
			dice[3] = d1;
			dice[4] = d6;
			dice[6] = d3;
			break;
			
		case 1: // 아래로 돌아갈 때
			dice[1] = d2;
			dice[2] = d6;
			dice[5] = d1;
			dice[6] = d5;
			break;
			
		case 2:	// 왼쪽으로 돌아갈 때
			dice[1] = d3;
			dice[3] = d6;
			dice[4] = d1;
			dice[6] = d4;
			break;
			
		case 3:	// 위로 돌아갈 때
			dice[1] = d5;
			dice[2] = d1;
			dice[5] = d6;
			dice[6] = d2;
			break;
		}
		
		
	}

}
