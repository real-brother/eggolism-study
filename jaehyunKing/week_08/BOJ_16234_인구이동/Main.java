package BOJ_16234_인구이동;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
	static int N, L, R, count, sum;
	static int[][] graph;
	static boolean[][] visited;
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	
	// 인구 여부를 확인하는 변수
	static boolean change;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		L = sc.nextInt();
		R = sc.nextInt();
		
		graph = new int[N][N];
		
		for(int r = 0; r < N; r++) for(int c = 0; c < N; c++) graph[r][c] = sc.nextInt();
		
		// 인구 이동이 며칠 동안 일어나는지 확인
		int day = 0;
		
		while(true) {
			
			// 새로운 날 마다 visited와 change를 초기화
			visited = new boolean[N][N];
			change = false;
			
			// bfs
			for(int r = 0; r < N; r++) for(int c = 0; c < N; c++) if(!visited[r][c]) bfs(r, c);
			
			// 인구 이동이 있다면 day 증가 
			// 없다면 while문을 빠져 나감
			if(change) day++;
			else break;
		}
		
		System.out.println(day);

	}
	
	static void bfs(int now_y, int now_x) {
		
		// bfs를 위한 queue
		// 인구 조정을 위한 queue2
		Queue<int[]> queue = new LinkedList<>();
		Queue<int[]> queue2 = new LinkedList<>();
		queue.offer(new int[] {now_y, now_x});
		queue2.offer(new int[] {now_y, now_x});
		visited[now_y][now_x] = true;
		
		while(!queue.isEmpty()) {
			int[] num = queue.poll();
			
			// 연합인 나라 개수와 sum(인구 합)을 갱신
			count++;
			sum += graph[num[0]][num[1]];
			
			for(int i = 0; i < 4; i++) {
				int move_y = num[0] + dr[i];
				int move_x = num[1] + dc[i];
				if(0 <= move_y && move_y < N && 0 <= move_x && move_x < N) {
					int diff = Math.abs(graph[num[0]][num[1]] - graph[move_y][move_x]);
					
					// 해당 국가에 방문하지 않았고, L <= 인구 차 <= R 이면 진행 
					if(!visited[move_y][move_x] && L <= diff && diff  <= R) {
						visited[move_y][move_x] = true;
						queue.offer(new int[] {move_y, move_x});
						queue2.offer(new int[] {move_y, move_x});
					}
				}
			}
		}
		// 다시 넣어줄 인구 수를 저장
		int people = sum / count;
		
		// 다음을 위해서 count와 sum을 초기화
		count = 0;
		sum = 0;
		
		while(!queue2.isEmpty()) {
			int[] num = queue2.poll();
			
			// 기존 인구 수랑 새로운 인구 수가 다르면
			// graph에 인구 수를 갱신해주고, 인구 수가 change 되었다고 표시
			if(graph[num[0]][num[1]] != people) {
				graph[num[0]][num[1]] = people;
				change = true;
			}
		}
		
	}

}