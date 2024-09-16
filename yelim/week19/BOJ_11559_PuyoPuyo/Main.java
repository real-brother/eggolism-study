import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

public class Main {
	
	static char[][] board;
	static boolean[][] visited;
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, -1, 0, 1};
	static char color;
	static int cnt;
	static List<int[]> idx;
	static boolean check;

	public static void main(String[] args) {
		/* 문제) 11559_Puyo Puyo
		 * 
		 * !!! 터질 수 있는 뿌요가 여러 그룹이 있다면 동시에 터져야 하고 
		 * 여러 그룹이 터지더라도 한번의 연쇄가 추가된다 !!!
		 */
		
		Scanner sc = new Scanner(System.in);
		
		board = new char[12][6];
		
		
		for(int i=0; i<12; i++) {
			String s = sc.next();
			for(int j=0; j<6; j++) {
				board[i][j] = s.charAt(j);
			}
		}
		
		while(true) {
			check = false;
			
			bfs();
			if (!check) break;
			
			cnt++;
		}

		
		System.out.println(cnt);
		
	}

	static void bfs() {
		Queue<int[]> q = new LinkedList<>();
		visited = new boolean[12][6];

		for(int i=0; i<12; i++) {
			for(int j=0; j<6; j++) {
				if (board[i][j] != '.' && !visited[i][j]) {
					idx = new ArrayList<>();
					color = board[i][j];
					
					q.add(new int[] {i, j});
					visited[i][j] = true;
					idx.add(new int[] {i, j});
					
					while(!q.isEmpty()) {
						int[] cur = q.poll();
						
						for(int d=0; d<4; d++) {
							int nr = cur[0]+dr[d];
							int nc = cur[1]+dc[d];
							
							if (nr>=0 && nr<12 && nc>=0 && nc<6) {
								if (!visited[nr][nc] && board[nr][nc]==color) {
									q.add(new int[] {nr, nc});
									visited[nr][nc] = true;
									idx.add(new int[] {nr, nc});

								}
							}
						}
					}
					
					// 터트리기
					if (idx.size() >= 4) {
						for(int k=0; k<idx.size(); k++) {
							int x = idx.get(k)[0];
							int y = idx.get(k)[1];
							
							board[x][y] = '.';
							check = true; // 터트렸다!
						}
						
					}
					
				}
			}
		}
		
		
		// 아래로 내려주기
		for(int i=0; i<6; i++) {
			for(int j=11; j>=1; j--) {
				if (board[j][i]=='.') {
					for(int k=j-1; k>=0; k--) {
						if(board[k][i] != '.') {
							board[j][i] = board[k][i];
							board[k][i] = '.';
							break;
						}
					}
				}
			}
		}
		
	}
	
	
}
