package BOJ_17070_파이프옮기기1;

import java.util.Scanner;

public class Main {
	/* 처음부터 dp로 어떻게 할 수 있을 꺼 같았는데
	 * 뭔가 bfs로 하면 더 편하지 않을까 하다가 생각할 수록 좀 막막해서 dp로 풀었다
	 * dfs bfs 연습해야겠다..
	 * dp를 위한 3차원 배열 D -> 2차원은 좌표 3차원은 방향
	 * 0, 1, 2 각각 오른쪽, 대각, 아래
	 */
	static int[][][] D;
	static int[][] graph;
	static int N;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		
		D = new int[N+1][N+1][3];
		graph = new int[N+1][N+1];
		
		for(int r = 1; r <= N; r++) for(int c = 1; c <= N; c++) graph[r][c] = sc.nextInt();
		
		// 0 -> 오른쪽, 1 -> 대각, 2 -> 아래
		// 초기값 세팅(파이프 하나가 오른쪽 방향으로 미리 놓아져있음)
		// 다른 초기값들은 0이라 패스
		D[1][2][0] = 1;
		
		// 2차원 배열 순회
		for(int r = 1; r <= N; r++) {
			for(int c = 1; c <= N; c++) {
				// 초기값을 D[1][2][0] = 1을 유지하기 위해서 continue 
				if(r == 1 && c == 2) continue;
				
				// 벽이 있는지 확인
				if(graph[r][c] == 1 || graph[r][c-1] == 1 || graph[r-1][c] == 1) {
					
					// 현재 위치가 1이거나 위 또는 아래가 1이라면 continue 
					// -> 어떤 파이프도 연결할 수 없음
					if(graph[r][c] == 1 || graph[r][c-1] == 1 && graph[r-1][c] == 1) continue;
					
					// 현재 위치의 왼쪽에 파이프가 있다면
					// 위에서 오는 파이프의 값만 현재 위치에 추가
					else if(graph[r][c-1] == 1) {
						D[r][c][2] = D[r-1][c][1] + D[r-1][c][2];
					}
					// 현재 위치의 위쪽에 파이프가 있다면
					// 왼쪽에서 오는 파이프의 값만 현재 위치에 추가
					else if(graph[r-1][c] == 1) {
						D[r][c][0] = D[r][c-1][0] + D[r][c-1][1];
					}
				}
				// 아무런 벽이 없다면 모든 파이프의 값을 추가
				else {	
					D[r][c][0] = D[r][c-1][0] + D[r][c-1][1];
					D[r][c][1] = D[r-1][c-1][0] + D[r-1][c-1][1] + D[r-1][c-1][2];
					D[r][c][2] = D[r-1][c][1] + D[r-1][c][2];
				}
			}
		}
		//2차원 배열을 순회하면 D의 값이 채워졌을테니 D[N][N] 값을 구해준다
		int result = D[N][N][0] + D[N][N][1] + D[N][N][2]; 
		System.out.println(result);
	}
}