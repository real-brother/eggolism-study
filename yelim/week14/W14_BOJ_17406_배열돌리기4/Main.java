package W14_BOJ_17406_배열돌리기4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int n, m, k;	// 배열 크기 n*m, 회전 연산 개수 k
	static int[][] arr;	// 배열
	static int[][] rotate;	// 회전 연산 정보
	static int[][] copy;
	static int[] order;	// 회전 연산 실행시킬 순서 저장할 리스트?
	static boolean[] visited;
	static int min = Integer.MAX_VALUE;
	
	public static void main(String[] args) throws IOException {
		/* 문제) 17406_배열돌리기4
		 * 
		 */
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		
		// 배열 정보 입력받기
		arr = new int[n][m];
		for(int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<m; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		copy = new int[n][m];
		
		// 회전 연산 정보 입력받기
		// 인덱스 맞추기 위해 -1
		rotate = new int[k][3];
		for(int i=0; i<k; i++) {
			st = new StringTokenizer(br.readLine());
			rotate[i][0] = Integer.parseInt(st.nextToken())-1;
			rotate[i][1] = Integer.parseInt(st.nextToken())-1;
			rotate[i][2] = Integer.parseInt(st.nextToken());
		}
		
		order = new int[k];
		visited = new boolean[k];
		
		perm(0);
		
		System.out.println(min);

	}
	
	// 회전 연산 순서 정하는 순열
	static void perm(int idx) {
		if (idx == k) {
			// 원래 배열 복사
			for(int i=0; i<n; i++) {
				copy[i] = arr[i].clone();
			}
			
			// 회전
			rotate(order);
			return;
		}
		
		for(int i=0; i<k; i++) {
			if (!visited[i]) {
				visited[i] = true;
				order[idx] = i;
				perm(idx+1);
				visited[i] = false;
			}
		}
	
	}
	
	static void rotate(int[] order) {
		// 회전연산 순서 저장한대로 반복
		for(int i=0; i<k; i++) {
			// 회전 정보 저장
			int r = rotate[order[i]][0];
			int c = rotate[order[i]][1];
			int s = rotate[order[i]][2];
			
			for(int k=1; k<=s; k++) {
				// 위쪽 회전
				int tmp1 = copy[r-k][c+k];
				for(int j=c+k; j>c-k; j--) {
					copy[r-k][j] = copy[r-k][j-1];
				}
				
				// 오른쪽 회전
				int tmp2 = copy[r+k][c+k];
				for(int j=r+k; j>r-k; j--) {
					copy[j][c+k] = copy[j-1][c+k];
				}
				copy[r-k+1][c+k] = tmp1;
				
				// 아래쪽 회전
				int tmp3 = copy[r+k][c-k];
				for(int j=c-k; j<c+k; j++) {
					copy[r+k][j] = copy[r+k][j+1];
				}
				copy[r+k][c+k-1] = tmp2;
				
				// 왼쪽 회전
				for(int j=r-k; j<r+k; j++) {
					copy[j][c-k] = copy[j+1][c-k];
				}
				copy[r+k-1][c-k] = tmp3;
			}
		}
		
		for(int i=0; i<n; i++) {
			int sum = 0;
			for(int j=0; j<m; j++) {
				sum += copy[i][j];
			}
			min = Math.min(min, sum);
		}
	}
	
	

}
