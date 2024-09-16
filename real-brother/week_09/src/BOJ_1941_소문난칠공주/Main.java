package BOJ_1941_소문난칠공주;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

public class Main {
	static Scanner sc = new Scanner(System.in);
	static char[][] girlClass = new char[5][5];
	static int[][] girlIndexClass = new int[5][5];
	static boolean[] visited;
	static List<Integer> combination;
	static int[] numList;
	static int answer = 0;
	static int[] dr = { -1, 0, 1, 0 };
	static int[] dc = { 0, -1, 0, 1 };

	public static void main(String[] args) {
		visited = new boolean[25];
		combination = new ArrayList<Integer>();
		numList = new int[25];
		for (int i = 0; i < 5; i++) {
			char[] temp = sc.nextLine().toCharArray();
			for (int j = 0; j < 5; j++) {
				girlClass[i][j] = temp[j];
				girlIndexClass[i][j] = i * 5 + j;
			}
		}
		for (int i = 0; i < 25; i++) {
			numList[i] = i;
		}

		generateCombinations(0, 0);

		System.out.println(answer);
	}

	// 백트래킹을 활용한 조합 구하기
	private static void generateCombinations(int start, int depth) {
		if (depth == 7) {
			// 이어져있는지 체크
			boolean isNearby = checkNearBy();
			if (isNearby) {
				char dom = whoDominate();
				if (dom == 'S') {
					answer++;
					System.out.println(combination);
//					printTest();
				}
			}
			return;
		}

		for (int i = start; i < numList.length; i++) {
			if (!visited[i]) {
				visited[i] = true;
				combination.add(numList[i]);
				generateCombinations(i + 1, depth + 1);
				combination.remove(combination.size()-1);
				visited[i] = false;
			}
		}
	}

	private static void printTest() {
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				int index = i * 5 + j;
				if (combination.contains(index)) {
					System.out.print(girlClass[i][j] + " ");
				} else {
					System.out.print(". ");
				}
			}
			System.out.println();
		}
		System.out.println();
	}

	private static char whoDominate() {
		int s = 0;
		int y = 0;
		
		for (int index : combination) {
			int r = index / 5;
			int c = index % 5;
			
			if (girlClass[r][c] == 'S') s++;
			else if (girlClass[r][c] == 'Y') y++;
		}
		
		if (s > y) return 'S';
		else return 'Y';
	}

	// 이어져있다면 -> 이다솜파(S)가 우세한지 확인
	private static boolean checkNearBy() {
		boolean[] combVisited = new boolean[7];

		int start = combination.get(0);
		combVisited[0] = true;
		
		Queue<Integer> queue = new ArrayDeque<Integer>();
		queue.add(start);
		
		
		while (!queue.isEmpty()) {
			int num = queue.poll();
			int nr = num / 5;
			int nc = num % 5;
			
			for (int i = 0; i < 4; i++) {
				int br = nr + dr[i];
				int bc = nc + dc[i];
				
				if (br < 0 || br >= 5 || bc < 0 || bc >= 5) continue;
				
				int testNum = br * 5 + bc;

				int visiIndex = combination.indexOf(testNum);
				if (combination.contains(testNum) && !combVisited[visiIndex]) {
					queue.add(testNum);
					combVisited[visiIndex] = true;
				}
			}
		}
		
		// 하나라도 방문하지 않았다면 - 이어져 있지 않은 것
		for (int i = 0; i < combVisited.length; i++) {
			if (!combVisited[i])
				return false;
		}

		return true;
	}
}