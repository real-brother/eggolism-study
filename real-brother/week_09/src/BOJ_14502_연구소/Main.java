package BOJ_14502_연구소;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

public class Main {
	static Scanner sc = new Scanner(System.in);
	static int n;
	static int m;
	static int maxSize = 0; // 이거 처음에 MIN_VALUE로 했다가 틀림..
	static int[][] originLaboratory;
	static int[][] testLaboratory;
	static boolean[] visited;
	static boolean[][] labVisited;
	static boolean[][] safeVisited;
	static int[] combination;
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, 1, 0, -1};
	static List<Integer> empty; // 빈칸 좌표를 1차원배열로 변환하여 저장
	
	public static void main(String[] args) {
		n = sc.nextInt();
		m = sc.nextInt();
		
		combination = new int[3];
		originLaboratory = new int[n][m];
		testLaboratory = new int[n][m];
		empty = new ArrayList<Integer>();
		labVisited = new boolean[n][m];
		safeVisited = new boolean[n][m];
		
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				originLaboratory[i][j] = sc.nextInt();
				if (originLaboratory[i][j] == 0) empty.add(i * m + j);
			}
		}
		visited = new boolean[empty.size()];
		
		for (int i = 0; i < originLaboratory.length; i++) {
		    testLaboratory[i] = new int[originLaboratory[i].length];
		    System.arraycopy(originLaboratory[i], 0, testLaboratory[i], 0, originLaboratory[i].length);
		}
		// 빈칸중 3개를 조합으로 골라서
		generateCombinations(0, 0);
		
		System.out.println(maxSize);
		
	}
	
	// 백트래킹을 활용한 조합 구하기
	private static void generateCombinations(int start, int depth) {
	    if (depth == 3) {
	        spreadVirus();
	        for (int i = 0; i < originLaboratory.length; i++) {
			    testLaboratory[i] = new int[originLaboratory[i].length];
			    System.arraycopy(originLaboratory[i], 0, testLaboratory[i], 0, originLaboratory[i].length);
			}
	        return;
	    }

	    for (int i = start; i < empty.size(); i++) {
	        if (!visited[i]) {
	            visited[i] = true;
	            combination[depth] = empty.get(i);
	            generateCombinations(i + 1, depth + 1);
	            visited[i] = false;
	        }
	    }
	}

	// 바이러스 퍼뜨리기
	private static void spreadVirus() {
		// 벽 세워주기
		for (int comb : combination) {
			int r = comb / m;
			int c = comb % m;
			testLaboratory[r][c] = 1;
		}
		
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (testLaboratory[i][j] == 2 && !labVisited[i][j]) {
					bfs(i * m + j);
				}
			}
		}
		
		// 안전영역 크기들 구해서 -> 최대값 출력
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (testLaboratory[i][j] == 0 && !safeVisited[i][j]) {
					int safeSize = getSafeSize(i * n + j);
					if (safeSize > maxSize) {
						maxSize = safeSize;
					}
				}
			}
		}
	}

	private static void bfs(int index) {
		int startR = index / m;
		int startC = index % m;
		int[] start = {startR, startC};
		Queue<int[]> queue = new ArrayDeque<int[]>();
		queue.add(start);
		
		while (!queue.isEmpty()) {
			int[] now = queue.poll();
			int nr = now[0];
			int nc = now[1];
			
			for (int i = 0; i < 4; i++) {
				int nextR = nr + dr[i];
				int nextC = nc + dc[i];
				
				if (nextR < 0 || nextR >= n || nextC < 0 || nextC >= m) continue;
				if (testLaboratory[nextR][nextC] == 0) {
					testLaboratory[nextR][nextC] = 2;
					queue.add(new int[] {nextR, nextC});
				}
				
			}
		}
	}
	
	// 안전영역의 가장 큰 덩어리는 구하는줄 알았음....
	// 문제를 똑바로 읽읍시다.
	// 그냥 0의 개수를 구하면 된 
	private static int getSafeSize(int index) {
		int size = 0;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (testLaboratory[i][j] == 0) size++;
			}
		}
		return size;
	}
}