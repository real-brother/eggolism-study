package W7_BOJ_7576_토마토;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	static int m, n;	// m: 가로, n: 세로
	static int[][] arr;
	static int day;
	static boolean[][] visited;
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	static int nr, nc;
	static Queue<int[]> q = new LinkedList<>();

	public static void main(String[] args) throws IOException {
		/* 문제) 토마토
		 * 토마토를 창고에 보관하는 격자모양의 상자들의 크기와 
		 * 익은 토마토들과 익지 않은 토마토들의 정보가 주어졌을 때, 
		 * 며칠이 지나면 토마토들이 모두 익는지, 그 최소 일수를 구하는 프로그램을 작성하라. 
		 * 단, 상자의 일부 칸에는 토마토가 들어있지 않을 수도 있다.
		 * 
		 * M은 상자의 가로 칸의 수, N은 상자의 세로 칸의 수
		 * 정수 1은 익은 토마토, 정수 0은 익지 않은 토마토, 정수 -1은 토마토가 들어있지 않은 칸
		 */
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		m = Integer.parseInt(st.nextToken());	// 가로
		n = Integer.parseInt(st.nextToken());	// 세로
		
		arr = new int[n][m];
		
		
		// 토마토 상태 입력받기
		for(int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<m; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
				if (arr[i][j]==1) {
					q.add(new int[] {i, j});
				}
			}
		}
		
		System.out.println(bfs());

	}
	
	static int bfs() {
		while(!q.isEmpty()) {
			int[] cur = q.poll();
			
			for(int d=0; d<4; d++) {
				int nr = cur[0]+dr[d];
				int nc = cur[1]+dc[d];
				
				// 범위 벗어나면
				if (nr<0 || nr>=n || nc<0 || nc>=m) continue;
				// 안익은 토마토면
				if (arr[nr][nc] == 0) {
					arr[nr][nc] = arr[cur[0]][cur[1]]+1;	// 일 수 누적
					q.add(new int[] {nr, nc});
				}
				
			}
			
		}
		// 0이 한 개라도 있으면 다 익지 못하는 상황이므로 -1 출력
		// 아니면 누적된 일 수의 최대값 찾기
		int max = 0;
		for(int i=0; i<n; i++) {
			for(int j=0; j<m; j++) {
				if (arr[i][j]==0) 
					return -1;
				else if (arr[i][j] > max) 
					max = arr[i][j];
			}
		}
		return max-1;	// 맨 처음이 1이었으니까 1 빼주기
	}

}
