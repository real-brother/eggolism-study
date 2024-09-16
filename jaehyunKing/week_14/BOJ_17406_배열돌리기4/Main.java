package BOJ_17406_배열돌리기4;

import java.util.Scanner;

/*
 *  각 행에 있는 모든 수의 합 중 최솟값을 찾는 문제
 *  연산 순서 바뀌어도됨 -> 순열
 *  원본 배열 유지를 위해 배열을 복사한다
 *  순열을 통해서 나온 순서에 따라 배열을 돌린다(깡구현)
 *  그 후 행의 합이 min값보다 작으면 갱신해준다
 *  순열 개수만큼 반복
 */

public class Main {
	static int N, M, K, min;
	static int[][] howToRotate;
	static int[][] graph;
	static int[][] copy;
	static boolean[] visited;
	static int[] sel;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		M = sc.nextInt();
		K = sc.nextInt();
		
		graph = new int[N+1][M+1];
		copy = new int[N+1][M+1];
		for(int r = 1; r <= N; r++) for(int c = 1; c <= M; c++) graph[r][c] = sc.nextInt();
		
		// 회전 연산의 정보를 담는 배열
		howToRotate = new int[K][3];
		for(int i = 0; i < K; i++) {
			howToRotate[i][0] = sc.nextInt();
			howToRotate[i][1] = sc.nextInt();
			howToRotate[i][2] = sc.nextInt();
		}
		
		visited = new boolean[K];
		sel = new int[K];
		min = Integer.MAX_VALUE;
		perm(0);
		
		System.out.println(min);

	}
	
	// 순열
	static void perm(int idx) {
		if(idx == K) {
			copy();
			for(int i = 0; i < K; i++) rotate(howToRotate[sel[i]][0], howToRotate[sel[i]][1], howToRotate[sel[i]][2]);
			cal();
			return;
		}
		
		for(int i = 0; i < K; i++) {
			if(!visited[i]) {
				visited[i] = true;
				sel[idx] = i; 
				perm(idx+1);
				visited[i] = false;
			}
		}
	}
	
	// 배열 돌리기 메서드(깡 구현)
	static void rotate(int nowY, int nowX, int range) {
		while(range>0) {
			int temp = copy[nowY-range][nowX-range];
			for(int i = 0; i < range*2; i++) copy[nowY-range+i][nowX-range] = copy[nowY-range+i+1][nowX-range];
			for(int i = 0; i < range*2; i++) copy[nowY+range][nowX-range+i] = copy[nowY+range][nowX-range+i+1];
			for(int i = 0; i < range*2; i++) copy[nowY+range-i][nowX+range] = copy[nowY+range-i-1][nowX+range];
			for(int i = 0; i < range*2; i++) {
				if(i == range*2-1) copy[nowY-range][nowX+range-i] = temp;
				else copy[nowY-range][nowX+range-i] = copy[nowY-range][nowX+range-i-1];
			}
			range--;
		}
	}
	
	// 행의 최소값을 찾는 메서드
	static void cal() {
		for(int r = 1; r <= N; r++) {
			int count = 0;
			for(int c = 1; c <= M; c++) {
				count += copy[r][c];
			}
			if(min > count) min = count;
		}
	}
	
	// 배열을 복사해주는 메서드
	static void copy() {
		for(int r = 1; r <= N; r++) for(int c = 1; c <= M; c++) copy[r][c] = graph[r][c];
	}

}