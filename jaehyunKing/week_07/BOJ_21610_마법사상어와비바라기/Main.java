package BOJ_21610_마법사상어와비바라기;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

	/* 그냥 문제에서 시키는대로 흐름대로 그대로 구현하면 됨
	 * 
	 * 델타 배열 0번 칸 버림 -> 문제에서 시키는대로 순서 적음
	 * graph는 물의 양을 저장할 2차원 배열
	 * cloud는 말 그대로 구름을 저장
	 * before_cloud는 비바라기 효과를 받은 구름(= 이동했을 때의 구름) 
	 */
	static int[] dr = {0, 0, -1, -1, -1, 0, 1, 1, 1};
	static int[] dc = {0, -1, -1, 0, 1, 1, 1, 0, -1};
	static int[][] graph;
	static List<int[]> cloud;
	static boolean[][] before_cloud;
	static int N;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		int M = sc.nextInt();
		graph = new int[N+1][N+1];
		before_cloud = new boolean[N+1][N+1];
		
		for(int r = 1; r <= N; r++) for(int c = 1; c <= N; c++) graph[r][c] = sc.nextInt();
		
		// 구름 초기 설정, 각각 y x 좌표
		cloud = new ArrayList<>();
		cloud.add(new int[]{N-1, 1});
		cloud.add(new int[]{N-1, 2});
		cloud.add(new int[]{N, 1});
		cloud.add(new int[]{N, 2});
		
		for(int i = 0; i < M; i++) move(sc.nextInt(), sc.nextInt());

		int sum = 0;
		for(int r = 1; r <= N; r++) for(int c = 1; c <= N; c++) sum += graph[r][c];
			
		System.out.println(sum);
	}

	static void move(int dir, int distance) {
		
		// 이동 거리가 N보다 클 때를 생각해서 %연산자로 이동 거리를 조정
		distance %= N;
		
		// 구름 이동, index 조정, 비바라기
		for(int i = 0; i < cloud.size(); i++) {
			
			// 구름 이동, 각각 y x 좌표
			cloud.get(i)[0] += distance*dr[dir];
			cloud.get(i)[1] += distance*dc[dir];
			
			//index 조정 
			index(i, 0);
			index(i, 1);
			
			//비바라기 
			graph[cloud.get(i)[0]][cloud.get(i)[1]]++;
			//구름이 사라진 칸을 저장(= 구름이 생기지 않을 칸)
			before_cloud[cloud.get(i)[0]][cloud.get(i)[1]] = true;
		}

		// 물복버
		for(int i = 0; i < cloud.size(); i++) {
			// dr, dc의 2 4 6 8 index가 대각선
			// for(int j = 1; j <= 8; j++) 
			// if(j % 2 == 0) 이랑 같음 근데 그냥 for문 도는 횟수를 줄이고 싶었음
			for(int j = 2; j <= 8; j+=2) {
				if(1 <= cloud.get(i)[0] + dr[j] && cloud.get(i)[0] + dr[j] <= N && 1 <= cloud.get(i)[1] + dc[j] && cloud.get(i)[1] + dc[j] <= N) {
					if(graph[cloud.get(i)[0] + dr[j]][cloud.get(i)[1] + dc[j]] != 0) graph[cloud.get(i)[0]][cloud.get(i)[1]]++;
				}
			}
		}
		
		// 구름 재생성
		
		// 구름 리스트 재생성
		cloud = new ArrayList<>();
		for(int r = 1; r <= N; r++) {
			for(int c = 1; c <= N; c++) {
				// 물의 양이 2이상이고, 구름이 사라진 칸이 아니라면 구름 리스트에 추가
				// 물의 양 -2
				if(graph[r][c] >= 2 && !before_cloud[r][c]) {
					cloud.add(new int[]{r, c});
					graph[r][c] -= 2;
				}
				// before_cloud 배열을 초기화해주기 위함
				else if(before_cloud[r][c]) before_cloud[r][c] = false;
			}
		}
		
	}
	
	// index 조정 메서드
	static void index(int i, int j) {
		// 1보다 작거나 N보다 크면 index를 조정
		if(cloud.get(i)[j] < 1 || cloud.get(i)[j] > N) {
			if(cloud.get(i)[j] < 1) cloud.get(i)[j] += N;
			else if(cloud.get(i)[j] > N) cloud.get(i)[j] -= N;
		}
	}
}