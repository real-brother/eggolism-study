package BOJ_21608_상어초등학교;

import java.util.Scanner;

public class Main {
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		
		int[][] graph = new int[N+1][N+1];
		
		// 좋아하는 친구를 저장할 배열(friend의 f)
		int[][] f = new int[N*N+1][4];
		
		for(int i = 0; i < N*N; i++) {
			int seat = sc.nextInt();
			f[seat][0] = sc.nextInt();
			f[seat][1] = sc.nextInt();
			f[seat][2] = sc.nextInt();
			f[seat][3] = sc.nextInt();
			int seat_y = 0;
			int seat_x = 0;
			
			// 이거를 0으로 하니까 주변에 빈 공간도 없고, 좋아하는 친구도 없으니까
			// 자리에 들어가지를 않았다..(귀찮아서 테케만 하다가 바로 n=4넣으니까 찾았다)
			// 둘 중 하나만 -1을 해줘도 된다
			int blank_before = -1;
			int f_max = -1;
			
			loop : for(int r = 1; r <= N; r++) {
				for(int c = 1; c <= N; c++) {
					
					// 현재 위치가 빈 칸이면
					if(graph[r][c] == 0) {
						int blank_count = 0;
						int f_count = 0;
						
						for(int j = 0; j < 4; j++) {
							int move_y = r + dr[j];
							int move_x = c + dc[j];
							if(1 <= move_y && move_y <= N && 1 <= move_x && move_x <= N) {
								int around_num = graph[move_y][move_x];
								
								// 주변 숫자가 0이면 blank_count 증가
								if(around_num == 0) blank_count++;
								
								// 주변 숫자가 좋아하는 친구(숫자)면 f_count 증가
								else if(around_num == f[seat][0] || around_num == f[seat][1] || around_num == f[seat][2] || around_num == f[seat][3]) {
									f_count++;
								}
								
								// 현재 위치에서 좋아하는 친구 수가 가장 많거나
								// 좋아하는 친구 수는 같은데 주변에 빈 칸이 많으면 자리 갱신
								if(f_max < f_count || (f_max == f_count && blank_count > blank_before)){
									f_max = f_count;
									seat_y = r;
									seat_x = c;
									blank_before = blank_count;
									// 좋아하는 친구가 4명이면 더 볼 가치가 없어서 break
									if(f_max == 4) break loop;
								}
							}
						}
					}
				}
			}
			// 저장된 자리에 배치
			graph[seat_y][seat_x] = seat;
		}
				
		int sum = 0;
		
		// 2차원 배열을 순회하면서 점수 계산
		for(int r = 1; r <= N; r++) {
			for(int c = 1; c <= N; c++) {
				int count = 0;
				for(int i = 0; i < 4; i++) {
					int move_y = r + dr[i];
					int move_x = c + dc[i];
					if(1 <= move_y && move_y <= N && 1 <= move_x && move_x <= N) {
						int around_num = graph[move_y][move_x];
						if(around_num == f[graph[r][c]][0] || around_num == f[graph[r][c]][1] || around_num == f[graph[r][c]][2] || around_num == f[graph[r][c]][3]){
							count++;
						}
					}
				}
				sum += Math.pow(10, count-1);
			}
		}
		System.out.println(sum);

	}

}