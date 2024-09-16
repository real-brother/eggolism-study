package W8_BOJ_16234_인구이동;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	// 나라의 위치 저장할 클래스
	static class Location {
		int r;
		int c;
		
		public Location(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
	
	
	static int n, L, R;
	static int[][] population;
	static boolean[][] visited;
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	static int sum;

	public static void main(String[] args) throws IOException {
		/* 문제) 인구 이동
		 * 국경선을 공유하는 두 나라의 인구 차이가 L명 이상, R명 이하라면, 두 나라가 공유하는 국경선을 오늘 하루 동안 연다.
		 * 위의 조건에 의해 열어야하는 국경선이 모두 열렸다면, 인구 이동을 시작한다.
		 * 국경선이 열려있어 인접한 칸만을 이용해 이동할 수 있으면, 그 나라를 오늘 하루 동안은 연합이라고 한다.
		 * 연합을 이루고 있는 각 칸의 인구수는 (연합의 인구수) / (연합을 이루고 있는 칸의 개수)가 된다. 편의상 소수점은 버린다.
		 * 연합을 해체하고, 모든 국경선을 닫는다.
		 * 
		 * 각 나라의 인구수가 주어졌을 때, 인구 이동이 며칠 동안 발생하는지 구하는 프로그램을 작성하시오.
		 */
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		
		population = new int[n][n];
		visited = new boolean[n][n];
		
		
		// 인구 수 입력받아서 저장
		for(int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<n; j++) {
				population[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int result = 0;	// 인구 이동 횟수
		
		// 인구이동이 더이상 일어나지 않을 때 반복문을 벗어나기 위한 check
		boolean check = true;	
		
		while(check) {
			if (move()==0) check = false;	// 연합이 없으면 check false로 바꾸기
			else result++;	// 연합이 있으면 인구 이동 횟수 증가
		}
		
		System.out.println(result);
		
	}
	

	// bfs 탐색
	static int move() {
		int cnt = 0;	// 연합 형성되면 증가시킬거임.
		
		for(int i=0; i<n; i++) {
			for(int j=0; j<n; j++) {
				
				// 방문한 적이 없다면 bfs 탐색
				if (!visited[i][j]) {
					Queue<Location> queue = new LinkedList<>();
					
					// 위치 클래스에 현재 위치 저장
					Location location = new Location(i, j);
					
					// 큐에 현재 나라의 위치 저장
					queue.add(location);
					
					// 연합이 형성된 나라 위치 저장해둘 리스트
					// 리스트에도 현재 나라의 위치 저장
					List<Location> list = new ArrayList<>();
					list.add(location);
					
					// 방문 표시
					visited[i][j] = true;
					
					// 현재 나라 인구 저장
					sum = population[location.r][location.c];
					
					while(!queue.isEmpty()) {
						// 현재 위치
						Location cur = queue.poll();
						
						for(int d=0; d<4; d++) {
							int nr = cur.r+dr[d];
							int nc = cur.c+dc[d];
							
							// 범위 안에 있을 때
							if (nr>=0 && nr<n && nc>=0 && nc<n) {
								// 인구 차
								int diff = Math.abs(population[cur.r][cur.c]-population[nr][nc]);
								
								// 방문한 적이 없고, 인구 차가 L이상 R이하일 때
								if (!visited[nr][nc] && L <= diff && R >= diff) {
									// 큐와 리스트에 각각 현재 위치 담아주기
									queue.add(new Location(nr, nc));
									list.add(new Location(nr, nc));
									
									// 방문 표시
									visited[nr][nc] = true;
									
									// 연합 형성되었으니 1 증가
									cnt++;
									
									// 해당 나라 인구 더해주기
									sum += population[nr][nc];
								}
							}
						}
					}
					
					// 연합이 형성되었으면 인구 분배
					if (cnt > 0) {
						// 평균 구해서
						int avg = sum/list.size();
						
						// 리스트 돌면서 인구 분배해주기
						for(int x=0; x<list.size(); x++) {
							Location cur = list.get(x);
							population[cur.r][cur.c] = avg;
						}
					}
					
				}
			}
		}
		
		// visted 배열 초기화
		for(int i=0; i<n; i++) {
			for(int j=0; j<n; j++) {
				visited[i][j] = false;
			}
		}
		
		return cnt;	// 연합 개수 반환
		
	}
	

}
