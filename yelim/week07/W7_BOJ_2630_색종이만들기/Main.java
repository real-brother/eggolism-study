package W7_BOJ_2630_색종이만들기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static int n;
	static int[][] paper;
	static int ans1, ans2;

	public static void main(String[] args) throws IOException {
		/* 문제) 색종이 만들기
		 * 모두 같은 색으로 칠해져 있지 않으면 같은 방법으로 똑같은 크기의 네 개의 색종이로 나눈다. 
		 * 이와 같은 과정을 잘라진 종이가 모두 하얀색 또는 모두 파란색으로 칠해져 있거나, 
		 * 하나의 정사각형 칸이 되어 더 이상 자를 수 없을 때까지 반복한다.
		 * 
		 * 입력으로 주어진 종이의 한 변의 길이 N과 각 정사각형칸의 색(하얀색 또는 파란색)이 주어질 때 
		 * 잘라진 하얀색 색종이와 파란색 색종이의 개수를 구하는 프로그램을 작성하시오.
		 */
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());

		paper = new int[n][n];
		
		for(int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<n; j++) {
				paper[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		divide(0, 0, n);
		
		System.out.println(ans1);
		System.out.println(ans2);

	}
	
	static void divide(int r, int c, int size) {
		if (size==1) {
			if (paper[r][c] == 0) ans1++;
			else ans2++;
			return;
		}
		
		int cnt1 = 0;
		int cnt2 = 0;
		// 각 숫자 개수 세기
		for(int i=r; i<r+size; i++) {
			for(int j=c; j<c+size; j++) {
				if (paper[i][j] == 0) cnt1++;
				else cnt2++;
			}
		}
		
		int total = size*size;
		if (total==cnt1 || total==cnt2) {
			if (total==cnt1) ans1++;
			else if (total==cnt2) ans2++;
		}
		else {
			int newSize = size/2;
			for(int i=0; i<2; i++) {
				for(int j=0; j<2; j++) {
					divide(r+i*newSize, c+j*newSize, newSize);
				}
			}
		}
		
	}

}
