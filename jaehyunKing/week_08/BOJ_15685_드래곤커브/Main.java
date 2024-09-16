package BOJ_15685_드래곤커브;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
	// 끝점의 좌표
	static int end_y, end_x;
	
	// 문제의 방향에 맞게 설정 
	// 우 상 좌 하
	static int[] dr = {0, -1, 0, 1};
	static int[] dc = {1, 0, -1, 0};
	static boolean[][] visited;
	
	// 방향을 저장하기 위한 배열
	static List<Integer> dir_save;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		
		// 해당 자리에 드래곤 커브가 지나가는지 확인하는 배열
		visited = new boolean[101][101];
		
		// 드래곤 커브 N번 생성
		for(int i = 0; i < N; i++) {
			
			// 초기 세팅
			dir_save = new ArrayList<>();
			int input_x = sc.nextInt();
			int input_y = sc.nextInt();
			int dir = sc.nextInt();
			int ver = sc.nextInt();
			end_y = input_y+dr[dir];
			end_x = input_x+dc[dir];
			
			// 시작점에서 끝점으로 이동하는 방향을 저장
			dir_save.add(dir);
			
			// 처음은 0단계로 주어지는데
			// 이 때 시작점과 끝점(0단계는 2개)을 저장
			visited[input_y][input_x] = true;
			visited[end_y][end_x] = true;
			
			// ver(세대)이 0보다 클 동안 rotate 메서드를 반복
			while(ver-->0) rotate(end_y, end_x);
		}
		
		int count = 0;
		for(int r = 0; r <= 99; r++) {
			for(int c = 0; c <= 99; c++) {
				if(visited[r][c] && visited[r+1][c] && visited[r][c+1] && visited[r+1][c+1]) count++;
			}
		}
		
		System.out.println(count);

	}
	
	static void rotate(int now_y, int now_x) {
		
		// 끝점(end_y, end_x)에서
		// 방향을 저장한 리스트의 뒤에서부터 꺼내와서 
		for(int i = dir_save.size()-1; i >= 0; i--) {
			
			// 해당 방향에 1을 더한다 = 시계 방향으로 회전
			int dir = dir_save.get(i)+1;
			
			// dir이 4면 0으로 바꿔줌
			if(dir == 4) dir = 0;
			
			// 해당 방향으로 이동하면서 드래곤 커브가 지나간다고 체크
			end_y += dr[dir];
			end_x += dc[dir];
			visited[end_y][end_x] = true;
			
			// 이 때 이동한 방향을 리스트에 추가
			// 다음 세대(ver)가 있다면 다시 사용가능
			// 맨 처음 시작점(입력값)에서 리스트를 처음부터 순서대로 이동하면 끝점에 도달
			dir_save.add(dir);
		}
	}

}