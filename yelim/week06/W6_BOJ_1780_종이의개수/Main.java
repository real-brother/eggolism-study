package W6_BOJ_1780_종이의개수;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static int n;
	static int[][] paper;
	static int ans1, ans2, ans3;	// -1, 0, 1

	public static void main(String[] args) throws IOException {
		/* 문제) 종이의 개수
		 * 1) 만약 종이가 모두 같은 수로 되어 있다면 이 종이를 그대로 사용한다.
		 * 2) (1)이 아닌 경우에는 종이를 같은 크기의 종이 9개로 자르고, 
		 * 각각의 잘린 종이에 대해서 (1)의 과정을 반복한다.
		 * 이와 같이 종이를 잘랐을 때, -1로만 채워진 종이의 개수, 0으로만 채워진 종이의 개수, 
		 * 1로만 채워진 종이의 개수를 구해내는 프로그램을 작성하시오.
		 */
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		
		paper = new int[n][n];
		
		// 입력받아서 배열에 저장
		for(int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<n; j++) {
				paper[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		// 메소드 호출
		divide(0, 0, n);
		
		System.out.println(ans1);
		System.out.println(ans2);
		System.out.println(ans3);
		

	}
	
	// 문제 내용 수행하는 메소드 (행, 열, 종이 크기)
	static void divide(int r, int c, int size) {
		// 종이의 크기가 1이면 각 숫자의 카운트에 1씩 더해주고 리턴
		if (size==1) {
			if(paper[r][c]==-1) ans1++;
			else if (paper[r][c]==0) ans2++;
			else ans3++;
			
			return;
		}
		
		// 종이에서 각 숫자의 개수 세주기
		int cnt1 = 0;
		int cnt2 = 0;
		int cnt3 = 0;
		for(int i=r; i<r+size; i++) {
			for(int j=c; j<c+size; j++) {
				if (paper[i][j]==-1) cnt1++;
				else if (paper[i][j]==0) cnt2++;
				else cnt3++;
			}
		}
		
		// 종이의 총 숫자 개수
		int total = size*size;
		// size 크기의 종이의 숫자가 모두 같으면 각 숫자의 카운트++
		if (total==cnt1 || total==cnt2 || total==cnt3) {
			if (total==cnt1) ans1++;
			else if (total==cnt2) ans2++;
			else ans3++;
		}
		
		// 다르면 종이 잘라주고, 자른만큼 또 재귀 호출
		else {
			int cut = size/3;
			for(int i=0; i<3; i++) {
				for(int j=0; j<3; j++) {
					divide(r+i*cut, c+j*cut, cut);
				}
			}
		}
		
	}

}
