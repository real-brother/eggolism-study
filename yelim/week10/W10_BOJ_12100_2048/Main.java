package W10_BOJ_12100_2048;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static int n;
	static int[][] board;
	static int max = 0;
	
	public static void main(String[] args) throws IOException {
		/* 문제) 2048(Easy)
		 * n*n의 보드 크기에서 상태가 주어졌을 때, 
		 * 최대 5번 이동해서 만들 수 있는 가장 큰 블록의 값을 구하기
		 */
		 
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		board = new int[n][n];
		
		// 게임판 초기상태 입력받기
		for(int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<n; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		dfs(0);
		
		System.out.println(max);
		
		
	}
	
	static void dfs(int cnt) {
		// 5번 다 하면 끝내기
		if (cnt==5) {
			return;
		}
		
		// 현재 보드 저장
		int[][] copyBoard = new int[n][n];
		for(int i=0; i<n; i++) {
			copyBoard[i] = board[i].clone();
		}
		
		// 4 방향으로 이동해보며 최대값 갱신
		for(int d=0; d<4; d++) {
			move(d);
			for(int i=0; i<n; i++) {
				for(int j=0; j<n; j++) {
					max = Math.max(max, board[i][j]);
				}
			}
			dfs(cnt+1);
			
			// 저장해둔 보드로 복구 - 아 다른 방향으로도 가봐야되니까!
			for(int i=0; i<n; i++) {
				board[i] = copyBoard[i].clone();
			}
		}
		
	}
	
	// 숫자 이동
	static void move(int dir) {
		Queue<Integer> q = new LinkedList<>();
		
		// 방향 별로 이동시켜주기
		switch (dir) {
		// 왼쪽
		case 0:
			for(int i=0; i<n; i++) {
				for(int j=0; j<n; j++) {
					if(board[i][j] != 0) {
						q.add(board[i][j]);
					}
					// 0으로 초기화
					board[i][j] = 0;
				}
				int idx = 0;
				while(!q.isEmpty()) {
					int num = q.poll();
					
					if(board[i][idx]==0) {
						board[i][idx] = num;
					}
					// 두 수가 같으면 더해서 저장, 인덱스 옆으로 하나 이동
					else if (board[i][idx]==num) {
						board[i][idx] += num;
						idx++;
					}
					// 0도 아니고, 같은 값도 아니면 다음 인덱스에 값 저장 
					else {
						idx++;
						board[i][idx] = num;
					}
				}
			}
			break;
			
		// 오른쪽
		case 1:
			for(int i=0; i<n; i++) {
				for(int j=n-1; j>=0; j--) {
					if(board[i][j] != 0) {
						q.add(board[i][j]);
					}
					board[i][j] = 0;
				}
				int idx = n-1;
				while(!q.isEmpty()) {
					int num = q.poll();
					
					if (board[i][idx] == 0) {
						board[i][idx] = num;
					}
					else if (board[i][idx] == num) {
						board[i][idx] += num;
						idx--;
					}
					else {
						idx--;
						board[i][idx] = num;
					}
				}
			}
			break;
			
		// 위쪽
		case 2:
			for(int j=0; j<n; j++) {
				for(int i=0; i<n; i++) {
					if(board[i][j] != 0) {
						q.add(board[i][j]);
					}
					board[i][j] = 0;
				}
				
				int idx = 0;
				while(!q.isEmpty()) {
					int num = q.poll();
					
					if (board[idx][j] == 0) {
						board[idx][j] = num;
					}
					else if (board[idx][j] == num) {
						board[idx][j] += num;
						idx++;
					}
					else {
						idx++;
						board[idx][j] = num;
					}
				}
			}
			break;
			
		// 아래쪽
		case 3:
			for(int j=0; j<n; j++) {
				for(int i=n-1; i>=0; i--) {
					if (board[i][j] != 0) {
						q.add(board[i][j]);
					}
					board[i][j] = 0;
				}
				int idx = n-1;
				while(!q.isEmpty()) {
					int num = q.poll();
					
					if(board[idx][j] == 0) {
						board[idx][j] = num;
					}
					else if (board[idx][j] == num) {
						board[idx][j] += num;
						idx--;
					}
					else {
						idx--;
						board[idx][j] = num;
					}
				}
			}
			break;
			
		}	// switch 끝
	}

}
