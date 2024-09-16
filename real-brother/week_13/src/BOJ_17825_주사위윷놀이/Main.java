package BOJ_17825_주사위윷놀이;

import java.util.Scanner;

public class Main {
	static Scanner sc = new Scanner(System.in);
	static int[] initDice = new int[10];
	static int[] perm = new int[10];
	static int[][] gameMap;
	static int answer;

	public static void main(String[] args) {
		for (int i = 0; i < 10; i++) {
			initDice[i] = sc.nextInt();
		}

		answer = Integer.MIN_VALUE;
		perm = new int[10];
		// 게임 맵 하드코딩
		gameMap = new int[6][];
		gameMap[0] = new int[] { 2, 4, 6, 8, 10, 12, 14, 16, 18, 20, 22, 24, 26, 28, 30, 32, 34, 36, 38 };
		gameMap[1] = new int[] { 13, 16, 19 };
		gameMap[2] = new int[] { 22, 24 };
		gameMap[3] = new int[] { 28, 27, 26 };
		gameMap[4] = new int[] { 25, 30, 35 };
		gameMap[5] = new int[] { 40 };

		// 중복순열을 통해서 10번의 주사위가 어떤 순서로 몇번말에게 할당될지 정해주기
		for (int i = 1; i <= 4; i++) {
			dfs(i, 0);
		}
		System.out.println(answer);
	}

	private static void dfs(int arange, int cnt) {
		if (cnt == 10) {
			moveHorse(); // 말 이동
			return;
		}

		for (int i = 0; i < arange; i++) {
			perm[cnt] = i;
			dfs(arange, cnt + 1);
		}
	}

	private static void moveHorse() {
		int horseIndex[] = { -1, -1, -1, -1 }; // 각 말의 초기 좌표는 -1
		int horseLines[] = { 0, 0, 0, 0 };
		int totalScore = 0;

		for (int d = 0; d < 10; d++) {
			int horseNo = perm[d];
			int horseLine = horseLines[horseNo]; // 현재 말이 몇 번 라인에 있는지 (0,1,2,3)
			int horseIdx = horseIndex[horseNo]; // 현재 말의 인덱스
			int dice = initDice[d]; // 나온 주사위 숫자

			if (horseLine == 6)	continue; // 끝점이라면 패스

			if (horseLine == 0 && horseIdx != -1) { // 0번 라인일 떄
				if (gameMap[0][horseIdx] == 10) { // 10에 있다면 파란 화살표 따라가야 한다
					horseLine = 1;
					horseIdx = -1;
				} else if (gameMap[0][horseIdx] == 20) { // 20에 있다면 파란 화살표 따라가야 한다
					horseLine = 2;
					horseIdx = -1;
				} else if (gameMap[0][horseIdx] == 30) { // 30에 있다면 파란 화살표 따라가야 한다
					horseLine = 3;
					horseIdx = -1;
				}
			}
			
			// 파란점에 왔을때 말이 타는 라인과 index를 재설정해주기
			for (int i = 1; i <= dice; i++) {
				if (horseLine == 6)
					break;
				if (++horseIdx == gameMap[horseLine].length) { // 현재 라인의 끝가지 왔다면
					if (horseLine == 0) {
						horseLine = 5;
						horseIdx = 0;
						continue;
					} else if (horseLine == 1 || horseLine == 2 || horseLine == 3) {
						horseLine = 4;
						horseIdx = 0;
						continue;
					} else if (horseLine == 4) {
						horseLine = 5;
						horseIdx = 0;
						continue;
					} else if (horseLine == 5) {
						horseLine = 6;
						horseIdx = 0;
						continue;
					}
				}
			}

			if (horseLine == 6) {
				horseLines[horseNo] = horseLine;
				continue; // 이미 통과 했다면 그냥 넘어감
			}

			// 말이 겹치는지 확인하기
			boolean isDuplicate = false;
			for (int i = 0; i < 4; i++) {
				if (horseIndex[i] == horseIdx && horseLines[i] == horseLine)
					isDuplicate = true;
			}

			if (isDuplicate) {
				horseIndex[horseNo] = horseIdx;
				horseLines[horseNo] = horseLine;
				totalScore += gameMap[horseLine][horseIdx];
			} else	return;

		}

		answer = Math.max(answer, totalScore); // 마지막에 스코어랑 result확인

	}

}