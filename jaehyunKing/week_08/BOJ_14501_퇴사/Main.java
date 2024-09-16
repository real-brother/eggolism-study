package BOJ_14501_퇴사;

import java.util.Scanner;

public class Main {
	static int N, max;
	static boolean[] visited;
	static int[] time;
	static int[] score;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		
		visited = new boolean[N+1];
		//점수와 시간을 따로 저장
		time = new int[N+1];
		score = new int[N+1];
		
		for(int i = 1; i <= N; i++) {
			time[i] = sc.nextInt();
			score[i] = sc.nextInt();
		}
		
		max = Integer.MIN_VALUE;
		combination(1);
		
		System.out.println(max);
	}
	
	// 조합 메서드
	static void combination(int idx) {
		
		// 기저 조건
		if(idx == N+1) {
			int sum = 0;
			int before = 0;
			
			
			for(int i = 1; i <= N; i++) {
				
				// 반복문을 돌면서 boolean 배열이 true면서
				if(visited[i]) {
					
					// 이전 상담 종료 시간이 현재 상담 시작 시간과 같거나 작으면서
					// 종료 시간이 N+1이전이면 
					// score를 더하고, 이전 상담 종료시간을 갱신해준다
					// N+1인 이유는 N = 7일 때 하루 걸리는 상담은
					// 당일 처리가 가능한데 i + time[i] = 7 + 1 = 8이기 때문
					if(before <= i && i + time[i] <= N+1) {
						sum += score[i];
						before = i + time[i];
					}
					else break;
				}
			}
			if(sum > max) max = sum;
			return;
		}
		
		visited[idx] = true;
		combination(idx+1);
		visited[idx] = false;
		combination(idx+1);
	}

}