package BOJ_1780_종이의개수;

import java.util.Scanner;

public class Main {
	static int[][] graph;
	static int[] result;
	static int[] count;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		
		graph = new int[N+1][N+1];
		
		for(int r = 1; r <= N; r++) for(int c = 1; c <= N; c++) graph[r][c] = sc.nextInt();
		
		// -1 0 1 -> 0 1 2 인덱스 사용
		result = new int[3];
		paper(1, N, 1, N);
		
		for(int x : result) System.out.println(x);
	}
	
	// y축은 y_start ~ y_end, x축은 x_start ~ x_end까지 돌면서 확인
	static void paper(int y_start, int y_end, int x_start, int x_end) {
		
		// y_start == y_end 이면 원소가 1개일 때 이므로 
		// 해당 값의 result에 1을 더 해준다
		if(y_start == y_end) {
			result[graph[y_start][x_start]+1]++;
			return;
		}
		
		
		int before = graph[y_start][x_start];
		loop : for(int r = y_start; r <= y_end; r++) {
			for(int c = x_start; c <= x_end; c++) {
				
				// 이전 값을 저장하고 순회하면서
				// 현재 값과 이전 값이 다르면 반복문을 break
				if(graph[r][c] != before) break loop;
				before = graph[r][c];
				
				// 끝까지 간다면 해당 값의 result에 1을 더해준다
				if(r == y_end && c == x_end) {
					result[graph[r][c]+1]++;
					return;
				}
			}
		}
		
		// 구간을 정할 div 변수를 선언 및 초기화
		int div = (y_end - y_start + 1) / 3;
		int div_two = 2 * div;
	
		// 9칸을 나눠 구할 재귀함수
		// 순서대로 1 2 3
		//        4 5 6
		//        7 8 9
		paper(y_start, y_start+div-1, x_start, x_start+div-1);
		paper(y_start, y_start+div-1, x_start+div, x_start+div_two-1);
		paper(y_start, y_start+div-1, x_start+div_two, x_end);
		paper(y_start+div, y_start+div_two-1, x_start, x_start+div-1);
		paper(y_start+div, y_start+div_two-1, x_start+div, x_start+div_two-1);
		paper(y_start+div, y_start+div_two-1, x_start+div_two, x_end);
		paper(y_start+div_two, y_end, x_start, x_start+div-1);
		paper(y_start+div_two, y_end, x_start+div, x_start+div_two-1);
		paper(y_start+div_two, y_end, x_start+div_two, x_end);
	}
	
}