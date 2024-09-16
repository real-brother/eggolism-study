import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		int N = sc.nextInt();
		int M = sc.nextInt();
		
		int curR = sc.nextInt();
		int curC = sc.nextInt();
		int curD = sc.nextInt();
		
		
		// 입력 받기
		int[][] matrix = new int[N][M];
		int[][] targetArea = new int[N][M];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				int num = sc.nextInt();
				matrix[i][j] = num;
				
				// 청소해야 할 곳 표시
				if (num == 0) targetArea[i][j] = 1;
			}
		}
		
		// 로봇 청소기가 있는 칸은 항상 빈 칸이다.
		targetArea[curR][curC] = 0; // 청소할 곳 아님
		
		int[] dr = new int[] {-1, 0, 1, 0}; // 북동남서
		int[] dc = new int[] {0, 1, 0, -1};
		int[] ddr = new int[] {1, 0, -1, 0}; // 바라보는 방향 반대로 이동
		int[] ddc = new int[] {0, -1, 0, 1};
		
		// 청소하기
		int cleanComplete = 0;
		boolean is_on = true;
		
		while (is_on) {
			
			// 1. 현재 칸이 아직 청소되지 않은 경우, 현재 칸을 청소한다.
			if (targetArea[curR][curC] == 1) {
				targetArea[curR][curC] = 0;
				cleanComplete++;
			}
			
			// 주변 탐색
			boolean search = false;
			for (int i = 0; i < 4; i++) {
				int nr = curR + dr[i];
				int nc = curC + dc[i];
				
				// 경계선을 넘거나 벽인 경우는 제외
				if (nr<0||nr>=N||nc<0||nc>=M||matrix[nr][nc] == 1) continue;
				
				if (targetArea[nr][nc] == 1) {
					search = true;
					break; // 한개만 찾으면 됨
				}
			}
			
			// 2. 현재 칸의 주변 4칸 중 청소되지 않은 빈 칸이 없는 경우
			if (!search) {
				int nr = curR + ddr[curD];
				int nc = curC + ddc[curD];
				
				// 작동 불가능 -> 벽이나 경계선
				if (nr<0||nr>=N||nc<0||nc>=M||matrix[nr][nc]== 1) {
					is_on = false;
				} else {
					// 작동 가능
					curR = nr;
					curC = nc;
				}
				
			} 
			
			// 3. 현재 칸의 주변 4칸 중 청소되지 않은 빈 칸이 있는 경우
			else {
				curD = (curD -1 + 4) % 4;
			
				int nr = curR + dr[curD];
				int nc = curC + dc[curD];
				
				if (targetArea[nr][nc] == 1) {
					curR = nr;
					curC = nc;
				}
				
				
			}
				
		} // while 끝
		
		System.out.println(cleanComplete+1);
		

	}
}