package BOJ_15684_사다리조작;

import java.util.Scanner;

public class Main {
	static Scanner sc = new Scanner(System.in);
	static int n, m, h, addLadderCount = 0;
	static int[][] ladder;

	public static void main(String[] args) {
		n = sc.nextInt();
		m = sc.nextInt();
		h = sc.nextInt();
		ladder = new int[h+1][n+1];
		
		for (int i = 0; i < m; i++) {
			int a = sc.nextInt();
			int b = sc.nextInt();
			ladder[a][b] = 1;
		}
		
		for (int i = 0; i <= 3; i++) {
            generateComb(1, 0, i);
        }
		// 3번안에 찾지 못할 경우 -1리턴
        System.out.println(-1);
	}
	
	static void generateComb(int start, int depth, int size) {
        if (depth == size) {
            if (checkItoI()) {
            	// 사다리가 제대로 만들어진다면 사이즈 리턴하고 종료
                System.out.println(size);
                System.exit(0);
            }
            return;
        }
        
        // 사다리 백트래킹으로 설정 및 해제하기
        for (int r = start; r <= h; r++) {
            for (int c = 1; c < n; c++) {
                if (ladder[r][c] == 1)  continue;
                if (ladder[r][c - 1] == 1) continue;
                if (ladder[r][c + 1] == 1) continue;
                ladder[r][c] = 1;
                generateComb(r, depth + 1, size);
                ladder[r][c] = 0;

            }
        }
    }

	static boolean checkItoI() {
	    for (int i = 1; i <= n; i++) {
	        int currPosition = i;
	        int start = 1;
	        while (start <= h) {
	        	// 현재 위치가 사다리라면 오른쪽으로
	            if (ladder[start][currPosition] == 1) {    
	                currPosition++;
	            } // 왼쪽이 사다리라면 왼쪽으로
	            else if (ladder[start][currPosition - 1] == 1) {    
	                currPosition--;
	            } 
	            // 아래로~~
                start++;
	        }
	        // 하나라도 출도착점이 다르다면 false
	        if (i != currPosition)    
	            return false;
	    }
	    return true;
	}
}