package BOJ_4179_불;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

public class Main {
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	static int[][] graph;
	
	// j_count -> 지훈이의 개수 -> 0이면 지훈이가 미로에 없음
	// result -> count값을 담는다
	static int R, C, j_count, result;
	static Queue<int[]> queue;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		R = sc.nextInt();
		C = sc.nextInt();
		
		graph = new int[R][C];
		queue = new LinkedList<>();
		List<int[]> fire_list = new LinkedList<>();
		
		// 시작 위치를 저장할 변수
		int start_y = 0;
		int start_x = 0;
		
		// . -> 0, J -> 1, # -> 2, F -> 3
		for(int r = 0; r < R; r++) {
			String S = sc.next();
			for(int c = 0; c < C; c++) {
				
				if('.' == S.charAt(c)) graph[r][c] = 0;
				
				// 입력이 J(지훈)면 queue에 값을 저장하고 시작 위치를 저장
				else if('J' == S.charAt(c)) {
					graph[r][c] = 1;
					queue.offer(new int[] {r, c, 0, 1});
					start_y = r;
					start_x = c;
				}
				
				else if('#' == S.charAt(c)) graph[r][c] = 2; 
				
				// 입력이 F(불)면 리스트에 저장
				// queue에 안 넣고 리스트에 저장하는 이유는
				// 지훈이를 맨 먼저 넣기 위해서
				else if('F' == S.charAt(c)) {
					graph[r][c] = 4;
					
					// 순서대로 r, c, 이동 횟수, 어떤 종류인지 구분 
					fire_list.add(new int[]{r, c, 0, 4});
				}
			}
		}

		// 불을 queue에 넣어줌
		for(int i = 0; i < fire_list.size(); i++) queue.offer(fire_list.get(i));
		
		if(bfs(start_y, start_x)) System.out.println(result);
		else System.out.println("IMPOSSIBLE");

	}
	
	// . -> 0, J -> 1, # -> 2, F -> 3
	static boolean bfs(int start_y, int start_x) {
		
		// 지훈이의 개수를 1로 초기화
		j_count = 1;
		while(!queue.isEmpty()) {
			int[] num = queue.poll();
			
			// 현재 위치의 값과 queue에서 뽑은 값이 다르면 continue
			// -> 불에 의해서 덮어씌워진 것임
			if(num[3] != graph[num[0]][num[1]]) continue;
			
			for(int i = 0; i < 4; i++) {
				int move_y = num[0] + dr[i];
				int move_x = num[1] + dc[i];
				
				// 현재 자리가 지훈이고, 
				// 이동하는 곳이 배열의 범위를 벗어나면 -> 탈출
				if(num[3] == 1 && (0 > move_y || move_y >= R || 0 > move_x || move_x >= C)) {
					result = num[2]+1;
					return true;
				}
				
				// 인덱스 범위 내부일 때
				if(0 <= move_y && move_y < R && 0 <= move_x && move_x < C) {
					
					// 현재 자리가 지훈이고 주변 위치가 빈 공간이면
					if(num[3] == 1 && graph[move_y][move_x] == 0) {
						
						// queue에 이동 횟수 + 1 해준 값을 넣어준다
						queue.offer(new int[] {move_y, move_x, num[2] + 1, 1});
						
						// 이동하는 위치를 지훈이로 바꿔준다
						graph[move_y][move_x] = 1;
						
						// 지훈이의 개수를 하나 늘려준다
						j_count++;
					}
					
					// 현재 자리가 불일 때 주변 자리가 지훈이나 빈 공간이면 
					if(num[3] == 4 && (graph[move_y][move_x] == 1 || graph[move_y][move_x] == 0)) {
						
						// 주변 자리가 지훈이면 지훈이의 개수를 감소
						if(graph[move_y][move_x] == 1)j_count--;
						
						// 지훈이가 배열에 없으면 false(impossible)
						if(j_count == 0) return false;
						
						// queue에 이동 횟수 + 1 해준 값을 넣어준다
						queue.offer(new int[] {move_y, move_x, num[2] + 1, 4});
						
						// 이동하는 칸을 불로 바꿔준다
						graph[move_y][move_x] = 4;
					}
				}
			}
		}
		return false;
	}

}