package BOJ_15683_감시;

import java.util.Scanner;

public class Main {
    static Scanner sc = new Scanner(System.in);
    static int n, m;
    static char[][] office;
    static char[][] testOffice;
    static int cctvCount = 0;
    static int[] dr = {-1, 0, 1, 0}; // 0 1 2 3
    static int[] dc = {0, 1, 0, -1}; // 위 오른쪽 아래 왼쪽
    static int[] directPerm;
    static int minBlindSpot = Integer.MAX_VALUE;
    
    // 각 CCTV들의 방향을 중복 순열로 구하기
    // 모든 경우를 완전탐색하며 사각지대의 최소크기 구하기

    public static void main(String[] args) {
    	n = sc.nextInt();
    	m = sc.nextInt();
    	sc.nextLine();
    	office = new char[n][m];
    	testOffice = new char[n][m];
    	
    	for (int i = 0; i < n; i++) {
    	    String[] tempString = sc.nextLine().split(" ");
    	    for (int j = 0; j < m; j++) {
    	        office[i][j] = tempString[j].charAt(0);
    	        if (office[i][j] < '6' && office[i][j] > '0') cctvCount++;
    	    }
    	}
    	// 방향 중복 순열 구하기
    	directPerm = new int[cctvCount];
    	generatePerm(0);
    	System.out.println(minBlindSpot);
    }

	private static void generatePerm(int depth) {
		if (depth == cctvCount) {
			// cctv 방향이 다 정해지면 -> test배열 설정 -> 사각지대 찾기
			cloneOffice();
			findBlindSpot();
			return;
		}
		
		for (int i = 0; i < 4; i++) {
			directPerm[depth] = i;
			generatePerm(depth + 1);
		}
		
	}

	private static void findBlindSpot() {
		int cctv = 0;
		for (int i = 0; i < n; i++) {
    	    for (int j = 0; j < m; j++) {
    	    	if (office[i][j] >= '6' || office[i][j] <= '0') continue;
    	    	int direction = directPerm[cctv];
    	    	// cctv의 번호와 방향에 따라 시뮬레이션하기
    	    	// 각 cctv특성에 따라 구현하기
    	    	switch (office[i][j]) {
				case '1': {
					firstCCTV(i, j, direction);
					break;}
				case '2': {
					secondCCTV(i, j, direction);
					break;}
				case '3': {
					thirdCCTV(i, j, direction);
					break;}
				case '4': {
					fourthCCTV(i, j, direction);
					break;}
				case '5': {
					fifthCCTV(i, j, direction);
					break;}
	    	    }
    	    	cctv++;
	    	}
		}
		// 사각지대 개수 세기
		int count = countBlindSpot();

		minBlindSpot = Math.min(minBlindSpot, count);
	}
	
	private static int countBlindSpot() {
		int count = 0;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (testOffice[i][j] == '0') count++;
			}
		}
		return count;
	}

	private static void firstCCTV(int i, int j, int direction) {
		int nowR = i;
		int nowC = j;
		out:
		while (true) {
			nowR += dr[direction];
			nowC += dc[direction];
			// 범위 밖이면 out
			if (nowR < 0 || nowR >= n || nowC < 0 || nowC >= m) break out;
			// 0이거나 이미 사각지대아면 #으로 만들기
			// 1~5사이의 cctv를 만날 경우는 그냥 넘어가도됨 (구현X)
			if (testOffice[nowR][nowC] == '0' || testOffice[nowR][nowC] == '#') {
				testOffice[nowR][nowC] = '#';
			} 
			// 벽을 만났다면 out
			else if (testOffice[nowR][nowC] == '6') break out;
		}
	}
	
	private static void secondCCTV(int i, int j, int direction) {
		for (int dir = 0; dir < 2; dir++) {
			int nowR = i;
			int nowC = j;
			out:
			while (true) {
				nowR += dr[direction];
				nowC += dc[direction];
				
				if (nowR < 0 || nowR >= n || nowC < 0 || nowC >= m) break out;
				if (testOffice[nowR][nowC] == '0' || testOffice[nowR][nowC] == '#') {
					testOffice[nowR][nowC] = '#';
				} 
				else if (testOffice[nowR][nowC] == '6') break out;
				
			}
			direction+=2;
			if (direction >= 4) direction-=4;
		}
	}
	
	private static void thirdCCTV(int i, int j, int direction) {
		for (int dir = 0; dir < 2; dir++) {
			int nowR = i;
			int nowC = j;
			out:
			while (true) {
				nowR += dr[direction];
				nowC += dc[direction];
				if (nowR < 0 || nowR >= n || nowC < 0 || nowC >= m) break out;
				if (testOffice[nowR][nowC] == '0' || testOffice[nowR][nowC] == '#') {
					testOffice[nowR][nowC] = '#';
				} 
				else if (testOffice[nowR][nowC] == '6') break out;
			}
			direction++;
			if (direction >= 4) direction-=4;
		}
	}
	
	private static void fourthCCTV(int i, int j, int direction) {
		for (int dir = 0; dir < 3; dir++) {
			int nowR = i;
			int nowC = j;
			out:
			while (true) {
				nowR += dr[direction];
				nowC += dc[direction];
				if (nowR < 0 || nowR >= n || nowC < 0 || nowC >= m) break out;
				if (testOffice[nowR][nowC] == '0' || testOffice[nowR][nowC] == '#') {
					testOffice[nowR][nowC] = '#';
				} 
				else if (testOffice[nowR][nowC] == '6') break out;
			}
			direction++;
			if (direction >= 4) direction-=4;
		}
	}
	
	private static void fifthCCTV(int i, int j, int direction) {
		for (int dir = 0; dir < 4; dir++) {
			int nowR = i;
			int nowC = j;
			out:
			while (true) {
				nowR += dr[direction];
				nowC += dc[direction];
				if (nowR < 0 || nowR >= n || nowC < 0 || nowC >= m) break out;
				if (testOffice[nowR][nowC] == '0' || testOffice[nowR][nowC] == '#') {
					testOffice[nowR][nowC] = '#';
				} 
				else if (testOffice[nowR][nowC] == '6') break out;
			}
			direction++;
			if (direction >= 4) direction-=4;
		}
	}

	private static void cloneOffice() {
    	testOffice = new char[n][m];
		for (int i = 0; i < n; i++) {
			testOffice[i] = office[i].clone();
		}
	}
}
