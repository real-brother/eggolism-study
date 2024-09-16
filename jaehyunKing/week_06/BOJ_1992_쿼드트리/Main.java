package BOJ_1992_쿼드트리;

import java.util.Scanner;

public class Main {
	static int graph[][];
	static int count;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		
		graph = new int[N+1][N+1];
		
		// 띄어쓰기가 없는 숫자를 하나씩 받기 위해서 charAt() 사용
		// Integer.parseInt 대신 -'0'으로 int형으로 바꿔줌
		for(int r = 1; r <= N; r++) {
			String S = sc.next();
			for(int c = 1; c <= N; c++) graph[r][c] = S.charAt(c-1)-'0';
		}
		
		divide(1, N, 1, N);
		
	}
	
	// y축은 y_start ~ y_end, x축은 x_start ~ x_end까지 돌면서 확인
	static void divide(int y_start, int y_end, int x_start, int x_end) {
		
		// y_start == y_end 또는 x_start == x_end라고 해놨는데
		// 사실 하나만 적어놔도 상관 x
		if(y_start == y_end || x_start == x_end) {
			System.out.print(graph[y_start][x_start]);
			return;
		}
		
		count = 0;
		
		// 숫자가 1이면 count를 올린다.
		for(int r = y_start; r <= y_end; r++) {
			for(int c = x_start; c <= x_end; c++) {
				if(graph[r][c] == 1) count++;
			}
		}
		
		// count의 개수가 0이거나 count의 개수가 순회한 총 개수와 같을 때
		// 값을 출력해준다.
		if(count == 0 || count == (y_end - y_start + 1) * (x_end - x_start + 1)) {
			if(count == 0) System.out.print(0);
			else System.out.print(1);
			return;
		}
		
		int x_mid = (x_start + x_end) / 2;
		int y_mid = (y_start + y_end) / 2;
		
		// 재귀함수를 통해 1, 2, 3, 4 분면을 각각 나눈다
		// 괄호를 재귀의 시작과 끝에 배치해서 하나로 묶이게(?) 한다
		System.out.print("(");
		divide(y_start, y_mid, x_start, x_mid);
		divide(y_start, y_mid, x_mid+1, x_end);
		divide(y_mid+1, y_end, x_start, x_mid);
		divide(y_mid+1, y_end, x_mid+1, x_end);
		System.out.print(")");
	}

}