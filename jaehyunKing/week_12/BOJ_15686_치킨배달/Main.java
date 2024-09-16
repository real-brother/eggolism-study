package BOJ_15686_치킨배달;

/*
 *  치킨집을 조합으로 선택(최대 M개를 고를 수 있으므로 그냥 M개를 고른다)
 *  선택된 치킨집은 2로 만들어주고 선택되지않은 치킨집은 0으로 만들어준다.
 *  2차원 배열을 완탐하면서 집을 찾으면 bfs로 치킨집을 찾는다
 */

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
	static int N, M, min, chickenCount;
	static int[][] graph;
	static boolean[][] visited;
	static boolean[] selectChicken;
	static int[][] chickenPos;
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		M = sc.nextInt();
		
		graph = new int[N][N];
		chickenPos = new int[13][2];
		
		int idx = 0;
		for(int r = 0; r < N; r++) {
			for(int c = 0; c < N; c++) {
				graph[r][c] = sc.nextInt();
				if(graph[r][c] == 2) {
					chickenPos[idx][0] = r;
					chickenPos[idx][1] = c;
					chickenCount++;
					idx++;
				}
			}
		}
		
		selectChicken = new boolean[13];
		min = Integer.MAX_VALUE;
		combination(0, 0);
		
		System.out.println(min);
	}
	
	static void combination(int idx, int count) {
		if(count == M) {
			for(int i = 0; i < chickenCount; i++) {
				if(!selectChicken[i]) graph[chickenPos[i][0]][chickenPos[i][1]] = 0;
				else graph[chickenPos[i][0]][chickenPos[i][1]] = 2;
			}
					
			int chickenDistance = 0;
			for(int r = 0; r < N; r++) {
				for(int c = 0; c < N; c++) {
					if(graph[r][c] == 1) {
						int tmp = bfs(r, c);
						chickenDistance += tmp;
					}
				}
			}
			if(min > chickenDistance) min = chickenDistance;
			return;
		}
		
		if(idx == chickenCount) return;
		
		selectChicken[idx] = true;
		combination(idx+1, count+1);
		
		selectChicken[idx] = false;
		combination(idx+1, count);
	}
	
	static int bfs(int nowY, int nowX) {
		Queue<int[]> queue = new LinkedList<>();
		visited = new boolean[N][N];
		queue.offer(new int[] {nowY, nowX, 0});
		while(!queue.isEmpty()) {
			int[] num = queue.poll();
			
			for(int i = 0; i < 4; i++) {
				int moveY = num[0] + dr[i];
				int moveX = num[1] + dc[i];
				if(0 <= moveY && moveY < N && 0 <= moveX && moveX < N) {
					if(!visited[moveY][moveX]) {
						visited[moveY][moveX] = true;
						if(graph[moveY][moveX] == 2) return num[2] + 1;
						queue.offer(new int[] {moveY, moveX, num[2] + 1});
					}
				}
			}
		}
		return -1;
	}

}
