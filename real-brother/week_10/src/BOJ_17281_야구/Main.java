package BOJ_17281_야구;

import java.util.List;
import java.util.Scanner;

public class Main {
	static Scanner sc = new Scanner(System.in);
	static int innings;
	static int[][] hitters;
	static List<Integer> thisInning;
	static int[] fieldStatus = { 0, 0, 0 }; // 1루, 2루, 3루 상태
	static int out = 0, maxTeamScore = 0;

	static int[] hitterOrder = { 2, 3, 4, 5, 6, 7, 8, 9 };
	static int[] permutation = new int[9];
	static boolean[] visited = new boolean[8];

	public static void main(String[] args) {
		innings = sc.nextInt();
		hitters = new int[innings][9];
		for (int i = 0; i < innings; i++) {
			for (int j = 0; j < 9; j++) {
				hitters[i][j] = sc.nextInt();
			}
		}

		permutation[3] = 1;
		settingHitterOrder(0, 0);

		System.out.println(maxTeamScore);
	}

	// 순열로 타자 순서 구하기
	// 4번째 순서에 1번타자 두고, 나머지 순서를 순열로 구하기
	// 8! = 약 4만
	private static void settingHitterOrder(int start, int depth) {
		if (depth == 9) {
			simulateBaseballGame();
			return;
		}
		if (depth == 3) {
			settingHitterOrder(0, depth + 1);
			return;
		}
		for (int i = 0; i < 8; i++) {
			int order = hitterOrder[i];
			if (!visited[i]) {
				visited[i] = true;
				permutation[depth] = order;
				settingHitterOrder(i + 1, depth + 1);
				visited[i] = false;
			}
		}
	}

	private static void simulateBaseballGame() {
	    int gameScore = 0;
	    int[] applyOrder = permutation.clone();
	    // Index 맞춰주기
	    for (int i = 0; i < 9; i++) {
	        applyOrder[i] -= 1;
	    }
        int startHitter = 0; // startHitter를 맨 처음에 초기화
        for (int i = 0; i < innings; i++) {
        	int[] hitterStatus = new int[9];
        	for (int j = 0; j < hitterStatus.length; j++) {
        		hitterStatus[j] = hitters[i][applyOrder[j]];
        	}
	    
	        out = 0;
	        fieldStatus = new int[]{0, 0, 0};
	        
	        inningOut:
	        while (true) {
	            for (int o = startHitter; o < 9; o++) {
	                switch (hitterStatus[o]) {
	                    case 0:
	                        out++;
	                        startHitter = o + 1;
	                        break;
	                    case 1:
	                    	if (fieldStatus[2] == 1) gameScore++;
	                    	fieldStatus[2] = fieldStatus[1];
	                    	fieldStatus[1] = fieldStatus[0];
	                    	fieldStatus[0] = 1;
	                        break;
	                    case 2:
	                    	if (fieldStatus[2] == 1) gameScore++;
	                    	if (fieldStatus[1] == 1) gameScore++;
	                    	fieldStatus[2] = fieldStatus[0];
	                    	fieldStatus[1] = 1;
	                    	fieldStatus[0] = 0;
	                        break;
	                    case 3:
	                    	if (fieldStatus[2] == 1) gameScore++;
	                    	if (fieldStatus[1] == 1) gameScore++;
	                    	if (fieldStatus[0] == 1) gameScore++;
	                    	fieldStatus[2] = 1;
	                    	fieldStatus[1] = 0;
	                    	fieldStatus[0] = 0;
	                        break;
	                    case 4:
	                    	if (fieldStatus[2] == 1) gameScore++;
	                    	if (fieldStatus[1] == 1) gameScore++;
	                    	if (fieldStatus[0] == 1) gameScore++;
	                    	gameScore++;
	                    	fieldStatus[2] = 0;
	                    	fieldStatus[1] = 0;
	                    	fieldStatus[0] = 0;
	                        break;
	                }
	                if (out == 3) {
	                    if (startHitter == 9) startHitter -= 9;
	                    break inningOut;
	                }
	            }
	            
	            startHitter = 0; // 이닝 종료 후 다음 이닝을 위해 초기화
	        }
	    }
        maxTeamScore = Math.max(maxTeamScore, gameScore);
	}

}
