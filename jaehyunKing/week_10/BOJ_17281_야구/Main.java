package BOJ_17281_야구;

/*
 *  어째선지 순열 메서드 안이나 밖에 4번째 자리를 1로 고정시켜주면 시간 초과가 나옴
 *  순열을 통해 선수들의 순서를 정해주고 플레이 
 */

import java.util.Scanner;

public class Main {
	static int[][] playerResult;
	static int[] player;
	static boolean[] visited, base;
	static int N, order, point, outCount, max;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		
		// 선수들의 순서 (순열)
		player = new int[10];
		// 입력값, 선수들의 결과값
		playerResult = new int[N][10];
		visited = new boolean[10];
		
		for(int r = 0; r < N; r++) for(int c = 1; c <= 9; c++) playerResult[r][c] = sc.nextInt();
		
		max = 0;
		perm(1);
		System.out.println(max);

	}
	
	static void perm(int idx) {
		if(idx == 9) {
			int innings = 0;
			// order -> 타순
			order = 1;
			point = 0;
			while(innings != N){
				base = new boolean[4];
				outCount = 0;
				while(outCount != 3) {
					if(order == 4) onePlayer(playerResult[innings][1]);
					else if(order >= 5)onePlayer(playerResult[innings][player[order-1]]);
					else onePlayer(playerResult[innings][player[order]]);
				}
				innings++;
			}
			if(max < point) max = point;
			return;
		}

		for(int i = 2; i <= 9; i++) {
			if(!visited[i]) {
				visited[i] = true;
				player[idx] = i;
				perm(idx+1);
				visited[i] = false;
			}
			
		}
	}
	
	// 한 타석을 처리하는 메서드
	// 베이스(base) 배열 [홈, 1루, 2루, 3루]를 사용
	static void onePlayer(int result) {
		// 홈에 타자를 올려둔다
		base[0] = true;
		if(result == 0) outCount++;
		else {
			for(int i = 3; i >= 0; i--) {
				if(base[i]) {
					if(i + result >= 4) point++;
					else base[i+result] = true;
					base[i] = false;
				}
			}
		}
		order++;
		if(order == 10) order = 1;
	}

}