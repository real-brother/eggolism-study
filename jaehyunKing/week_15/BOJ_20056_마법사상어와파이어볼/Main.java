package BOJ_20056_마법사상어와파이어볼;

/*
 *  문제에서 시키는대로 구현한다.
 *  문제 설명에는 방향, 속력 순서였는데 입력은 속력, 방향 순서여서 헤맸다.
 */

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
	static int N;
	static ArrayList<int[]> fireballs;
	static int[] dr = {-1, -1, 0, 1, 1, 1, 0, -1};
	static int[] dc = {0, 1, 1, 1, 0, -1, -1, -1};
	static int graph[][][];

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);	
		
		N = sc.nextInt();
		int M = sc.nextInt();
		int K = sc.nextInt();
		
		fireballs = new ArrayList<>();
		
		for(int i = 0; i < M; i++) {
			fireballs.add(new int[] {sc.nextInt()-1, sc.nextInt()-1, sc.nextInt(), sc.nextInt(), sc.nextInt()});
		}
				
		while(K-->0) {
			move();
		}
		
		int result = 0;
		for(int i = 0; i < fireballs.size(); i++) {
			result += fireballs.get(i)[2];
		}
		
		System.out.println(result);
	}
	
	// 0 y, 1 x, 2 질량, 3 속도, 4 방향
	// graph -> 0 개수 1 질량 2 방향(짝수) 3 방향(홀수) 4 속력 5 방향의 합
	static void move() {
		graph = new int[N][N][6];
		for(int i = 0; i < fireballs.size(); i++) {
			
			// 1. 모든 파이어볼이 자신의 방향으로 속력만큼 이동한다.
			fireballs.get(i)[0] += fireballs.get(i)[3] * dr[fireballs.get(i)[4]];
			fireballs.get(i)[1] += fireballs.get(i)[3] * dc[fireballs.get(i)[4]];

			while(fireballs.get(i)[0] >= N || fireballs.get(i)[0] < 0) {
				if(fireballs.get(i)[0] >= N) fireballs.get(i)[0] -= N;
				else if(fireballs.get(i)[0] < 0) fireballs.get(i)[0] += N;
			}
			while(fireballs.get(i)[1] >= N || fireballs.get(i)[1] < 0) {
				if(fireballs.get(i)[1] >= N) fireballs.get(i)[1] -= N;
				else if(fireballs.get(i)[1] < 0) fireballs.get(i)[1] += N;
			}
			
			// 2-1. 같은 칸에 있는 파이어볼은 모두 하나로 합쳐진다.
			graph[fireballs.get(i)[0]][fireballs.get(i)[1]][0]++;
			graph[fireballs.get(i)[0]][fireballs.get(i)[1]][1] += fireballs.get(i)[2];
			if(fireballs.get(i)[4] % 2 == 0) graph[fireballs.get(i)[0]][fireballs.get(i)[1]][2]++;
			else graph[fireballs.get(i)[0]][fireballs.get(i)[1]][3]++;
			graph[fireballs.get(i)[0]][fireballs.get(i)[1]][5] += fireballs.get(i)[4];
			graph[fireballs.get(i)[0]][fireballs.get(i)[1]][4] += fireballs.get(i)[3];
		}
		
		fireballs = new ArrayList<>();
		// 2-2. 나누어진 파이어볼의 질량, 속력, 방향은 다음과 같다.
		for(int r = 0; r < N; r++) {
			for(int c = 0; c < N; c++) {
				if(graph[r][c][0] != 0) {
					int count = graph[r][c][0];
					if(count == 1) {
						fireballs.add(new int[] {r, c, graph[r][c][1], graph[r][c][4], graph[r][c][5]});
					}
					else {
						// 2-3. 나누어진 파이어볼의 질량, 속력, 방향은 다음과 같다.
						int mass = graph[r][c][1] / 5;
						int speed = graph[r][c][4] / count;
						int dir = 0; 
						if(graph[r][c][2] == 0 || graph[r][c][3] == 0) dir = 0;
						else dir = 1;
						for(int j = 0; j < 4; j++) {
							if(j != 0) dir += 2;
							// 2-4 질량이 0인 파이어볼은 소멸되어 없어진다.
							if(mass != 0) fireballs.add(new int[] {r, c, mass, speed, dir});
						}
					}
				}
			}
		}
	}

}