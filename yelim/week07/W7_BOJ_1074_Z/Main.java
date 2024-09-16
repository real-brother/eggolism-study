package W7_BOJ_1074_Z;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static int n, r, c;
	static int[][] arr;
	static int cnt;	

	public static void main(String[] args) throws IOException {
		/* 문제) Z
		 * 크기가 2N × 2N인 2차원 배열을 Z모양으로 탐색하려고 한다. 
		 * 예를 들어, 2×2배열을 왼쪽 위칸, 오른쪽 위칸, 왼쪽 아래칸, 오른쪽 아래칸 순서대로 방문하면 Z모양이다.
		 * N > 1인 경우, 배열을 크기가 2N-1 × 2N-1로 4등분 한 후에 재귀적으로 순서대로 방문한다.
		 * 
		 * N이 주어졌을 때, r행 c열을 몇 번째로 방문하는지 출력
		 */
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		
		find(r, c, (int)(Math.pow(2, n)));
		
		System.out.println(cnt);

	}
	
	static void find(int r, int c, int size) {
		if (size==1) {
			return;
		}
		
		// 1사분면에 있을 때
		if (r<size/2 && c<size/2) {
			find(r, c, size/2);
		}
		// 2사분면에 있을 때 1사분면 개수 더해주기
		else if (r<size/2 && c>=size/2) {
			cnt += (size*size)/4;
			find(r, c-size/2, size/2);
		}
		// 3사분면일 때 1, 2사분면 개수 더해주기
		else if (r>=size/2 && c<size/2) {
			cnt += (size*size/4)*2;
			find(r-size/2, c, size/2);
		}
		// 4사분면일 때 1, 2, 3사분면 개수 더해주기
		else {
			cnt += (size*size/4)*3;
			find(r-size/2, c-size/2, size/2);
		}
		
	}

}
