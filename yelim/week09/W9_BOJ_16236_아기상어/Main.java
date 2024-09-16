package W9_BOJ_16236_아기상어;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;


public class Main {
	
	static int n;
	static int[][] map;
	static boolean[][] visited;
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	static Fish shark;
	static int sharkSize = 2;
	static List<Fish> foodList = new ArrayList<>();
	static int cnt = 0;	// 물고기 잡아먹은 횟수
	static int result;

	public static void main(String[] args) throws IOException {
		// 문제) 아기 상어
		
		/* 설계)
		 * bfs 돌면서 큐와 리스트에 먹이 위치와 시간 누적하여 저장
		 * 비어있거나, 먹이가 상어크기와 같다면 큐에만 먹이 위치와 시간 누적하여 저장
		 * 먹이 리스트에 값이 있으면
		 * 우선순위 따라 정렬해주고, 상어 위치를 먹이 위치로 옮겨준 후,
		 * 먹이 횟수 증가시켜서 먹이 횟수와 상어 크기 비교
		 * 먹이가 있던 곳은 0으로 초기화
		 * 먹이 리스트가 빌 때까지 계속 반복할거니까
		 * 큐, 방문배열 다 초기화 시켜주기
		 */
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		
		map = new int[n][n];
		visited = new boolean[n][n];
		
		// 입력 받기
		for(int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<n; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				
				// 상어가 있는 곳이면 위치 저장
				if (map[i][j]==9) {
					shark = new Fish(i, j, 0);
					map[i][j] = 0;
				}
			}
		}
		
		bfs();
		
		System.out.println(result);
		

	}
	
	// 탐색
	static void bfs() {
		Queue<Fish> q = new LinkedList<>();
		// 큐에 상어 위치 넣어주기
		q.add(shark);
		// 상어가 있는 곳 방문 표시
		visited[shark.r][shark.c] = true;
		
		while (true) {
			while(!q.isEmpty()) {
				Fish cur = q.poll();
				// 지금까지 걸린 시간
				int time = cur.time;
				
				for(int d=0; d<4; d++) {
					int nr = cur.r+dr[d];
					int nc = cur.c+dc[d];
					
					// 범위 내에 있고, 방문하지 않은 곳
					if (nr>=0 && nr<n && nc>=0 && nc<n && !visited[nr][nc]) {
						// 상어의 크기보다 작은 물고기면 큐에 좌표와 시간+1 저장하고, 리스트에도 일단 저장
						if (map[nr][nc]!=0 && sharkSize>map[nr][nc]) {
							visited[nr][nc] = true;
							q.add(new Fish(nr, nc, time+1));
							foodList.add(new Fish(nr, nc, time+1));
						}
						
						// 비어있거나 크기가 같은 물고기면 지나가 -> 좌표, time+1
						else if (map[nr][nc]==0 || sharkSize==map[nr][nc]) {
							visited[nr][nc] = true;
							q.add(new Fish(nr, nc, time+1));
						}
					}
					
				}
			}	// bfs while 끝
			
			
			// 물고기 잡아먹기
			if (!foodList.isEmpty()) {
//				System.out.println(1);
				// 먹는 메소드
				eat();
				
				// 다시 bfs while문 돌거니까
				// 큐 초기화
				q.clear();
				// 방문도 초기화
				visited = new boolean[n][n];
				
				// 현재 상어 위치 큐에 추가
				q.add(shark);
				// 상어 위치 방문 표시
				visited[shark.r][shark.c] = true;
			}
			// 먹이가 더이상 없으면 반복 끝
			else {
				return;
			}
		}
		
	}
	
	// 잡아먹는 메소드
	static void eat() {
		// 우선순위 순으로 정렬 (시간 - 위 - 왼쪽)
		foodList.sort((o1, o2) -> {
			if (o1.time == o2.time) {
				if (o1.r == o2.r) {
					return o1.c - o2.c;
				}
				return o1.r - o2.r;
			}
			return o1.time - o2.time;
		});
		
		// 지금 물고기 위치와 시간
		Fish now = foodList.get(0);
		
		// 상어의 위치를 먹이의 위치로
		shark.r = now.r;
		shark.c = now.c;
		
		// 잡아먹은 횟수
		cnt++;
		
		// 잡아먹은 횟수와 상어의 크기가 같다면 상어 크기 1 증가
		// 잡아먹은 횟수 초기화
		if (cnt==sharkSize) {
			sharkSize++;
			cnt = 0;
		}
		
		// 결과값에 현재까지 걸린 시간 더해주기
		result += now.time;
		
		// 잡아먹었으니 해당 칸은 0으로 초기화
		map[now.r][now.c] = 0;
		// 다 먹었으면 먹이리스트도 초기화
		foodList.clear();
	}
	
	
	static class Fish {
		int r;
		int c;
		int time;
		
		public Fish(int r, int c, int time) {
			this.r = r;
			this.c = c;
			this.time = time;
		}
	}

}
