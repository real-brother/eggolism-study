package BOJ_12100_2048easy;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
	static Scanner sc = new Scanner(System.in);
	static int n;
	static int[][] gameMap;
	static int[][] testGameMap;
	static List<Integer> permutations;
	static int maxValue = 0;

	public static void main(String[] args) {
		n = sc.nextInt();
		gameMap = new int[n][n];
		testGameMap = new int[n][n];
		permutations = new ArrayList<Integer>();

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				gameMap[i][j] = sc.nextInt();
			}
		}

		// 1~5번 이동했을때 -> 이동 획수에 따른 중복 순열 구하기 ->
		// 각각 가장 큰 블룩 구하기
		for (int length = 1; length <= 5; length++) {
			generatePermutaion(0, length);
		}
		
		System.out.println(maxValue);
	}

	// 중복조합 구하기
	private static void generatePermutaion(int depth, int length) {
		if (depth == length) {
			testGameMap = new int[n][n];
			// 구해지면 게임 시작
			gameStart();
			return;
		}

		for (int i = 1; i <= 4; i++) {
			permutations.add(i);
			generatePermutaion(depth + 1, length);
			permutations.remove(permutations.size() - 1);
		}

	}

	private static void gameStart() {
		for (int i = 0; i < n; i++) {
			testGameMap[i] = gameMap[i].clone();
		}

		for (int direction : permutations) {
			switch (direction) {
			case 1: {
				upGameMap();
				break;}
			case 2: {
				rightGameMap();
				break;}
			case 3: {
				downGameMap();
				break;}
			case 4: {
				leftGameMap();
				break;}}
		}
		// testGameMap에서 최대 블록 구하기
		int max = 0;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				max = Math.max(max, testGameMap[i][j]);
			}
		}
		
		maxValue = Math.max(max, maxValue);
	}
	
	// 각각 4방향으로 블럭들 옮겨주기
	private static void upGameMap() {
	    for (int i = 0; i < n; i++) {  // 열(column)을 위로 올림
	        int index = 0;  // 합쳐진 블록을 저장할 인덱스 (j하고 다르게 움직임)
	        int block = 0;  // 현재 처리 중인 블록의 값
	        for (int j = 0; j < n; j++) {  // 행(row)을 위로 이동하며 처리
	            if (testGameMap[j][i] != 0) {  // 블록이 있을 때
	                if (block == testGameMap[j][i]) {  // 현재 블록과 이전 블록이 같을 때
	                    testGameMap[index - 1][i] = block * 2;  // 이전 블록을 합친 값을 저장
	                    block = 0;  // 현재 블록 초기화
	                    testGameMap[j][i] = 0;  // 현재 블록을 비움
	                } else {  // 현재 블록과 이전 블록이 다를 때
	                    block = testGameMap[j][i];  // 현재 블록 값 저장
	                    testGameMap[j][i] = 0;  // 현재 블록 비움
	                    testGameMap[index][i] = block;  // 현재 블록 값을 index 위치에 저장
	                    index++;  // 다음 블록의 위치 증가
	                }
	            }
	        }
	    }
	}

	private static void rightGameMap() {
		for(int i = 0; i < n; i++) {
            int index = n - 1;
            int block = 0;
            for(int j = n - 1; j >= 0; j--) {
                if(testGameMap[i][j] != 0) {
                    if(block == testGameMap[i][j]) {
                    	testGameMap[i][index + 1] = block * 2;
                        block = 0;
                        testGameMap[i][j] = 0;
                    } else {
                        block = testGameMap[i][j];
                        testGameMap[i][j] = 0;
                        testGameMap[i][index] = block;
                        index--;
                    }
                }
            }
        }
	}

	private static void downGameMap() {
		for(int i = 0; i < n; i++) {
            int index = n - 1;
            int block = 0;
            for(int j = n - 1; j >= 0; j--) {
                if(testGameMap[j][i] != 0) {
                    if(block == testGameMap[j][i]) {
                    	testGameMap[index + 1][i] = block * 2;
                        block = 0;
                        testGameMap[j][i] = 0;
                    } else {
                        block = testGameMap[j][i];
                        testGameMap[j][i] = 0;
                        testGameMap[index][i] = block;
                        index--;
                    }
                }
            }
        }
	}
	
	private static void leftGameMap() {
		for(int i = 0; i < n; i++) {
            int index = 0;
            int block = 0;
            for(int j = 0; j < n; j++) {
                if(testGameMap[i][j] != 0) {
                    if(block == testGameMap[i][j]) {
                    	testGameMap[i][index - 1] = block * 2;
                        block = 0;
                        testGameMap[i][j] = 0;
                    } else {
                        block = testGameMap[i][j];
                        testGameMap[i][j] = 0;
                        testGameMap[i][index] = block;
                        index++;
                    }
                }
            }
        }
	}
}
