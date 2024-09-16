package W9_BOJ_2146_다리만들기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	static int n;
	static int[][] map;
	static boolean[][] visited;
	static boolean[][] Qvisited;
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	static int cnt;
	static int ans;
	static int min = Integer.MAX_VALUE;

	public static void main(String[] args) throws IOException {
		/* 문제) 다리 만들기
		 * 지도가 주어질 때, 가장 짧은 다리 하나를 놓아 두 대륙을 연결하는 방법을 찾으시오.
		 */
		
		/* 설계)
		 * dfs 돌면서 구역마다 숫자를 다르게 설정하여 구역 나눠주기
		 * 구역 별로 bfs 돌기
		 * 해당 구역의 숫자이면 큐에 넣어주고, 탐색하며 0(바다)이면 위치와 다리 길이 1 증가시켜 큐에 저장
		 * 해당 구역 숫자가 아닌 곳을 만나면(다른 구역) 최소값 비교
		 */
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		
		map = new int[n][n];
		visited = new boolean[n][n];
		
		// 지도 입력받기
		for(int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<n; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		// 1씩 증가시키면서 dfs 돌아서 구역 나눠주기
		int num = 1;
		for(int i=0; i<n; i++) {
			for(int j=0; j<n; j++) {
				if (!visited[i][j] && map[i][j]!=0) {
					dfs(i, j, num);
					num++;
				}
			}
		}
		
		// 구역 개수만큼 bfs 호출
		for(int i=1; i<=num; i++) {
			bfs(i);
		}
		
		System.out.println(min);
		
	}
	
	// 구역 나눠주는 dfs 메소드
	static void dfs(int r, int c, int num) {
		visited[r][c] = true;
		map[r][c] = num;	// 현재 구역의 값들을 num으로 설정해주기
		
		for(int d=0; d<4; d++) {
			int nr = r+dr[d];
			int nc = c+dc[d];
			
			// 범위 내에 있고
			if (nr>=0 && nr<n && nc>=0 && nc<n) {
				// 방문한 적 없고, 0이 아니라면(육지라면)
				if (!visited[nr][nc] && map[nr][nc]!=0) {
					dfs(nr, nc, num);
				}
			}
		}
		
		
	}
	

	// 최단 길이의 다리 놓는 bfs 
	static void bfs(int num) {
		Queue<int[]> q = new LinkedList<>();
		// 큐에서 쓸 visited 초기화
		Qvisited = new boolean[n][n];
		
		// 넘겨준 num값과 같다면 해당 구역의 정보 큐에 저장해주기
		for(int i=0; i<n; i++) {
			for(int j=0; j<n; j++) {
				if (map[i][j] == num) {
					q.offer(new int[] {i, j, 0});
					Qvisited[i][j] = true;
				}
			}
		}
		
		while (!q.isEmpty()) {
			int[] cur = q.poll();
			
			for(int d=0; d<4; d++) {
				int nr = cur[0]+dr[d];
				int nc = cur[1]+dc[d];
				
				// 범위 확인
				if (nr>=0 && nr<n && nc>=0 && nc<n) {
					// 다른 섬에 도달했을 경우 cnt와 최소값 비교하여 최소값 저장
					if (map[nr][nc]!=0 && map[nr][nc]!=num) {
						if (cur[2]!=0) {
							min = Math.min(min, cur[2]);
						}
					}
					else {
						// 이동한 곳이 방문하지 않았고, 0인 곳이라면 큐에 추가
						if (!Qvisited[nr][nc] && map[nr][nc]==0) {
							q.add(new int[] {nr, nc, cur[2]+1});
							Qvisited[nr][nc] = true;
						}
					}
				}
			}
		}
	}

}
