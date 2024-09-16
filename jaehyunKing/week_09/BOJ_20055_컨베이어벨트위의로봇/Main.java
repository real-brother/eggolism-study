package BOJ_20055_컨베이어벨트위의로봇;

// 문제에서 시키는대로 구현

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		int K = sc.nextInt();
		
		int[][] graph = new int[2][N];
		boolean[] robot = new boolean[N];
		
		for(int c = 0; c < N; c++) graph[0][c] = sc.nextInt();
		for(int c = N-1; c >= 0; c--) graph[1][c] = sc.nextInt();
		
		int day = 0;
		
		while(true) {
			int temp = graph[1][0];
			
			// 1. 벨트가 각 칸 위에 있는 로봇과 함께 한 칸 회전한다.
			for(int c = 1; c < N; c++) graph[1][c-1] = graph[1][c];
			graph[1][N-1] = graph[0][N-1];

			for(int c = N-1; c > 0; c--) {
				graph[0][c] = graph[0][c-1];
				robot[c] = robot[c-1];
			}
			
			// 내리는 위치가 N-1 (문제에서는 N) -> 이거 안하면 답 이상하게 나옴
			robot[N-1] = false;
			
			// 2. 가장 먼저 벨트에 올라간 로봇부터, 벨트가 회전하는 방향으로 한 칸 이동할 수 있다면 이동한다. 
			// 만약 이동할 수 없다면 가만히 있는다.
			for(int c = N-1; c > 1; c--) {
				
				// 로봇이 이동하기 위해서는 이동하려는 칸에 로봇이 없으며, 그 칸의 내구도가 1 이상 남아 있어야 한다.
				if(graph[0][c] >= 1 && !robot[c] && robot[c-1]) {
					robot[c] = robot[c-1];
					robot[c-1] = false;
					graph[0][c]--;
				}
			}
			
			// 올리는 위치에 있는 칸의 내구도가 0이 아니면 올리는 위치에 로봇을 올린다.
			if(temp >= 1) {
				graph[0][0] = temp - 1;
				robot[0] = true;
			}
			else {
				graph[0][0] = temp;
				robot[0] = false;
			}
			
			int countZero = 0;
			for(int r = 0; r < 2; r++) for (int c = 0; c < N; c++) if(graph[r][c] == 0) countZero++;
				
			day++;
			
			// 내구도가 0인 칸의 개수가 K개 이상이라면 과정을 종료한다. 그렇지 않다면 1번으로 돌아간다.
			if(K <= countZero) {
				System.out.println(day);
				break;
			}
			
		}
		
	}

}