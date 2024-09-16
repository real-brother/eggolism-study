package BOJ_13460_구슬탈출2;

/*
 *  bfs를 사용하기 위해서 배열에 빨간 공과 파란 공을 입력하는 것이 아닌 좌표로 들고 있는다
 *  빨간 공을 먼저 움직이고 파란 공을 움직이게 했다 
 *  만약 진행 방향에서 파란 공이 빨간 공보다 앞에 있으면 그 때는 파란 공을 움직이게하고 그 바로 뒤에 빨간 공을 위치시킨다
 *  4차원 visited를 통해 빨간 공, 파란 공이 같은 위치에 있는 경우는 백트래킹(visited에는 도착지만 저장함)
 *  파란 공이 구멍에 빠진 경우에는 넘어간다
 */
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };
	static char[][] graph;
	static boolean[][][][] visited;
	static int N, M, blueY, blueX;
	static boolean cant;

	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();
		M = sc.nextInt();

		graph = new char[N][M];

		int redY = 0;
		int redX = 0;

		// 입력에서 파란 공 빨간 공의 좌표를 기억
		// 배열에는 빈 칸을 넣어준다.
		for (int r = 0; r < N; r++){
			String S = sc.next();
			for (int c = 0; c < M; c++){
				char input = S.charAt(c);
				if(input == 'R'){
					redY = r;
					redX = c;
					graph[r][c] = '.';
				} 
				else if(input == 'B'){
					blueY = r;
					blueX = c;
					graph[r][c] = '.';
				} 
				else graph[r][c] = input;
			}
		}

		System.out.println(bfs(redY, redX));
	}

	static int bfs(int nowY, int nowX){
		Queue<int[]> queue = new LinkedList<>();
		visited = new boolean[N][M][N][M];
		// 빨간 공 Y, X, 파란 공 Y, X, count
		queue.offer(new int[] { nowY, nowX, blueY, blueX, 0});
		cant = false;
		while(!queue.isEmpty()){
			int[] num = queue.poll();
			
			// 10회를 넘어가면 백트래킹
			if(num[4] >= 10) return -1;
			for(int i = 0; i < 4; i++){
				int j = 1;
				blueY = num[2];
				blueX = num[3];
				
				// while(true) 내부가 한 번의 이동
				while (true) {
					int moveY = num[0] + j * dr[i];
					int moveX = num[1] + j * dc[i];

					// 빨간 공이 벽을 만났다면
					// 잠깐 빨간 공을 생성해주고( 왜 이렇게 했는지는 모르겠는데 그냥 좌표로 해도된다)
					// 파란 공이 이동하게 해준다
					if(graph[moveY][moveX] == '#'){
						moveY -= dr[i];
						moveX -= dc[i];
						graph[moveY][moveX] = 'R';
						moveBlue(blueY, blueX, i);
						graph[moveY][moveX] = '.';
						if(cant){
							cant = false;
							break;
						}
						if(!visited[moveY][moveX][blueY][blueX]){
							visited[moveY][moveX][blueY][blueX] = true;
							queue.offer(new int[] { moveY, moveX, blueY, blueX, num[4] + 1});
						}
						break;
					} 
					// 빨간 공이 잘 가다가 파란 공을 만났다면
					else if(moveY == blueY && moveX == blueX){
						// 파란 공을 옮겨주고
						moveBlue(blueY, blueX, i);
						if(cant){
							cant = false;
							break;
						}
						// 빨간 공을 파란 공 바로 뒤칸으로 땡겨온다
						moveY = blueY - dr[i];
						moveX = blueX - dc[i];
						if(!visited[moveY][moveX][blueY][blueX]){
							visited[moveY][moveX][blueY][blueX] = true;
							queue.offer(new int[] { moveY, moveX, blueY, blueX, num[4] + 1});
						}
						break;

					}
					// 빨간 공이 구멍에 들어가면 파란 공을 움직여보고
					// 파란 공이 구멍에 빠지지 않았다면 현재 count를 return
					else if(graph[moveY][moveX] == 'O'){
						moveBlue(blueY, blueX, i);
						if(cant){
							cant = false;
							break;
						}
						return num[4] + 1;
					} 
					// 빈 공간(.)이 있다면 계속 이동
					else j++;
				}
			}
		}
		// 어차피 실패하면 여기로 온다
		return -1;
	}

	// 파란 공이 움직이는 메서드
	static void moveBlue(int nowY, int nowX, int dir) {
		int j = 1;
		while(true){
			int moveY = blueY + j * dr[dir];
			int moveX = blueX + j * dc[dir];
			if(0 <= moveY && moveY < N && 0 <= moveX && moveX < M){
				if(graph[moveY][moveX] == '.') j++;
				// 구멍에 빠지면 불가능하다고 판정 -> 밖에서 break
				else if(graph[moveY][moveX] == 'O'){
					cant = true;
					return;
				}
				else{
					// 빈 칸과 구멍 외에 다른 것을 만나면 그 전 자리에 위치시킨다
					moveY -= dr[dir];
					moveX -= dc[dir];
					blueY = moveY;
					blueX = moveX;
					return;
				}
			}
		}
	}
}