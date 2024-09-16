package W9_BOJ_14502_연구소;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	static int n, m;
	static int[][] map;
	static int[][] copyMap;
	static boolean[][] visited;
	
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	static int safe;
	static int safeMax = Integer.MIN_VALUE;

	public static void main(String[] args) throws IOException {
		/* 문제) 연구소
		 * 연구소는 크기가 N×M인 직사각형으로 나타낼 수 있으며, 직사각형은 1×1 크기의 정사각형으로 나누어져 있다. 
		 * 연구소는 빈 칸, 벽으로 이루어져 있으며, 벽은 칸 하나를 가득 차지한다.
		 * 일부 칸은 바이러스가 존재하며, 이 바이러스는 상하좌우로 인접한 빈 칸으로 모두 퍼져나갈 수 있다. 
		 * 새로 세울 수 있는 벽의 개수는 3개이며, 꼭 3개를 세워야 한다.
		 * 
		 * 벽을 3개 세운 뒤, 바이러스가 퍼질 수 없는 곳을 안전 영역이라고 한다. 
		 * 연구소의 지도가 주어졌을 때 얻을 수 있는 안전 영역 크기의 최댓값을 구하는 프로그램을 작성하시오.
		 */
		
		/* 설계)
		 * dfs 돌면서 벽 설치해주기
		 * 벽 3개 설치하면 bfs 호출
		 * 카피한 map에 바이러스 확산 표시
		 * bfs 돌고나서, 0인 개수 세줘서 안전영역 개수 구하기
		 * 최대값 갱신!
		 */

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		map = new int[n][m];
		
		// 지도 입력 받기
		for(int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<m; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		// 벽 세우는 dfs 호출
		wallDfs(0);
		
		System.out.println(safeMax);
		
	}

	// 탐색하면서 벽 세우는 dfs
	static void wallDfs(int wall) {
		
		// 벽이 3개 세워졌으면 bfs 호출
		if (wall == 3) {
			bfs();
			return;
		}
		
		// 지도 돌면서 벽 설치해주고 재귀호출
		for(int i=0; i<n; i++) {
			for(int j=0; j<m; j++) {
				if (map[i][j] == 0) {
					map[i][j] = 1;
					wallDfs(wall+1);
					map[i][j] = 0;	// dfs 한번 쭉 끝나면 다시 값 돌려주기
				}
			}
		}

	}
	
	// 바이러스 전파하는 bfs
	static void bfs() {
		Queue<int[]> q = new LinkedList<>();
		visited = new boolean[n][m];
		
		// 바이러스가 있는 곳 큐에 넣어주기
		for(int i=0; i<n; i++) {
			for(int j=0; j<m; j++) {
				if (map[i][j]==2) {
					q.add(new int[] {i, j});
				}
			}
		}
		
		// "동시성문제"(feat.주형) 피하기 위한 배열 복사
		copyMap = new int[n][m];
		for(int i=0; i<n; i++) {
			copyMap[i] = map[i].clone();
		}
		
		// bfs 돌려주기
		while(!q.isEmpty()) {
			int[] cur = q.poll();
			
			for(int d=0; d<4; d++) {
				int nr = cur[0]+dr[d];
				int nc = cur[1]+dc[d];
				
				if (nr>=0 && nr<n && nc>=0 && nc<m) {
					if (!visited[nr][nc] && copyMap[nr][nc]==0) {
						// 바이러스 확산!
						// 복사 배열의 [nr][nc]에 2 넣어주기
						copyMap[nr][nc] = 2;
						// 큐에 값 저장
						q.add(new int[] {nr, nc});
						// 방문체크
						visited[nr][nc] = true;
					}
				}
			}
		}
		
		// 안전영역 구하기
		safe = 0;	// 영역 개수 초기화
		for(int i=0; i<n; i++) {
			for(int j=0; j<m; j++) {
				
				// 복사 배열의 값이 0이면 안전영역 개수 1 증가
				if (copyMap[i][j]==0) {
					safe++;
				}
			}
		}
		
		// 최대값 갱신
		safeMax = Math.max(safeMax, safe);
	}
	
}
