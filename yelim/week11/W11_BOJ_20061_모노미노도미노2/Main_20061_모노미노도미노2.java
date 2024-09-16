package WEEK_11;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;


public class Main_20061_모노미노도미노2 {
	
	static int n;
	static int[][] info;
	static int[][] red;
	static int[][] blue;
	static int[][] green;
	static int score;

	public static void main(String[] args) throws IOException {
		/* 문제) 모노미노도미노2
		 *    
		 */

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		info = new int[n][3];
		
		// 블록 놓은 정보 입력받아서 저장
		for(int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			info[i][0] = Integer.parseInt(st.nextToken());
			info[i][1] = Integer.parseInt(st.nextToken());
			info[i][2] = Integer.parseInt(st.nextToken());
		}
		
		red = new int[4][4];
		blue = new int[4][6];
		green = new int[6][4];
		
		for(int k=0; k<n; k++) {
			move(info[k][0], info[k][1], info[k][2]);
			
			System.out.println("Green");
			for(int i=0; i<6; i++) {
				for(int j=0; j<4; j++) {
					System.out.print(green[i][j]+" ");
				}
				System.out.println();
			}
			
			System.out.println("Blue");
			for(int i=0; i<4; i++) {
				for(int j=0; j<6; j++) {
					System.out.print(blue[i][j]);
				}
				System.out.println();
			}
			check();
		}
		
		// 파란색 보드와 초록색 보드에 타일이 있는 개수 
		int sum = 0;
		for(int i=5; i>=2; i--) {
			for(int j=0; j<4; j++) {
				if (green[i][j] == 1) {
					sum++;
				}
				if (blue[j][i] == 1) {
					sum++;
				}
			}
		}
		System.out.println("Green");
		for(int i=0; i<6; i++) {
			for(int j=0; j<4; j++) {
				System.out.print(green[i][j]+" ");
			}
			System.out.println();
		}
		
		System.out.println("Blue");
		for(int i=0; i<4; i++) {
			for(int j=0; j<6; j++) {
				System.out.print(blue[i][j]);
			}
			System.out.println();
		}
		
		System.out.println(score);
		System.out.println(sum);
		
	}
	
	static void move(int t, int r, int c) {
		for(int i=0; i<4; i++) {
			Arrays.fill(red[i], 0);
		}
		
		int row = 0;
		int col = 0;
		
		if (t==1) {
			red[r][c] = 1;
			// 초록색 보드에 블록 놓기
			for(int i=5; i>=0; i--) {
				if(green[i][c] == 0) {
					green[i][c] = 1;
					break;
				}
			}
			
			// 파란색 보드에 블록 놓기
			for(int i=5; i>=0; i--) {
				if (blue[r][i] == 0) {
					blue[r][i] = 1;
					break;
				}
			}
		}
		
		else if (t==2) {
			red[r][c] = 1;
			red[r][c+1] = 1;
			// 초록색 보드에 블록 놓기
			int isOk = 5;
			for(int i=5; i>=2; i--) {
				if(green[i][c]==0 && green[i][c+1]==0) {
					isOk = Math.max(isOk, i);
				}
				else if (green[i][c]!=0 || green[i][c+1]!=0) {
					isOk = i-1;
				}
			}
			green[isOk][c] = 1;
			green[isOk][c+1] = 1;
			
			// 파란색 보드에 블록 놓기
			isOk = 5;
			for(int i=5; i>=2; i--) {
				if (blue[r][i] == 0) {
					isOk = Math.max(isOk, i);
				}
				else {
					isOk = i-1;
				}
			}
			blue[r][isOk] = 1;
			blue[r][isOk-1] = 1;
		}
		
		else {
			red[r][c] = 1;
			red[r+1][c] = 1;
			
			// 초록색 보드에 블록 놓기			
			int isOk = 5;
			for(int i=5; i>=2; i--) {
				if(green[i][c]==0) {
					isOk = Math.max(isOk, i);
				}
				else {
					isOk = i-1;
				}
			}
			
			// 여기서 왜... isOk 찍어봐도 3 잘나오는데..
			// 왜 green[1][c]가 0이었다가 
			// green[isOk-1][c]=1 만 하면 green[2][c]랑 같이 1이 될까..?
			// 짜증나네 ㅠ
			System.out.println(green[1][c]+" / "+green[2][c]);
			System.out.println(green[isOk-1][c]);
			
			green[isOk-1][c] = 1;
			
			System .out.println(green[1][c]+" / "+green[2][c]);
			
			green[isOk][c] = 1;
			
			// 파란색 보드에 블록 놓기
			int isOkB = 5;
			for(int i=5; i>=2; i--) {
				if (blue[r][i]==0 && blue[r+1][i]==0) {
					isOkB = Math.max(isOkB, i);
				}
				else if (blue[r][i]!=0 || blue[r+1][i]!=0) {
					isOkB = i-1;
				}
			}
			blue[r][isOkB] = 1;
			blue[r+1][isOkB] = 1;
		}
		
		
	}
	
	// 가득 찬 행이나 열이 있는지 확인! 파랑, 초록의 0, 1도 ?
	static void check() {
		int r = 5;
		// 초록색 블록 확인
//		for(int i=5; i>=2; i--) {
		while (r > 2) {
			boolean isFull = true;
			for(int j=0; j<4; j++) {
				if (green[r][j] == 0) {
					isFull = false;
				}
			}
			// 가득 찬 행이 있으면 비워주기
			if(isFull) {
				Arrays.fill(green[r], 0);
				score++;
				// 비워준 행부터 위로 올라가면서 이동시키기
				for(int k=r; k>=2; k--) {
					green[k] = green[k-1];
				}
				// 2행 초기화
				Arrays.fill(green[2], 0);
			}
			else r--;
		}
		
		// 파란색 블록 확인
//		for(int i=5; i>=2; i--) {
		while (r > 2) {
			boolean isFull = true;
			for(int j=0; j<4; j++) {
				if (blue[j][r] == 0) {
					isFull = false;
				}
			}
			// 가득 찬 열이 있으면 비워주기
			if(isFull) {
				for(int j=0; j<4; j++) {
					blue[j][r] = 0;
				}
				score++;
				// 비워준 열부터 왼쪽으로 가면서 이동시키기
				for(int k=r; k>=2; k--) {
					for(int j=0; j<4; j++) {
						blue[j][k] = blue[j][k-1];
					}
				}
				// 2열 초기화
				for(int k=0; k<4; k++) {
					blue[k][2] = 0;
				}
			}
			else r--;
		}
		
		
		// 특별한 초록색, 파란색 보드에 블록이 있으면
		for(int k=0; k<2; k++) {
			// 초록색 보드 체크
			boolean check = false;
			for(int j=0; j<4; j++) {
				// 블록이 있으면 true
				if (green[1][j] != 0) {
					check = true;
					break;
				}
			}
			// 특별한 초록색 보드에 블록이 있으면
			if (check) {
				// 5번 행 삭제
				Arrays.fill(green[5], 0);
				// 블록 이동
				for(int i=5; i>=1; i--) {
					green[i] = green[i-1];
				}
				// 0행 초기화
				Arrays.fill(green[0], 0);
			}
			
			// 파란색 보드 체크
			check = false;
			for(int i=0; i<4; i++) {
				// 블록이 있으면 true
				if (blue[i][1] != 0) {
					check = true;
					break;
				}
			}
			
			// 특별한 파란색 보드에 블록이 있으면
			if (check) {
				// 5번 열 삭제
				for(int i=0; i<4; i++) {
					blue[i][5] = 0;
				}
				// 블록 이동
				for(int i=5; i>=1; i--) {
					for(int j=0; j<4; j++) {
						blue[j][i] = blue[j][i-1];
					}
				}
				// 0 열을 초기화
				for(int i=0; i<4; i++) {
					blue[i][0] = 0;
				}
			}
			
		}
	}

}
