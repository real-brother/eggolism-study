package BOJ_17471_게리맨더링;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
	
	// 인접리스트 사용
	static int N, min;
	static List<Integer>[] graph;
	static boolean[] visited;
	static int[] score;
	static int[] vote;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		
		// 0번 자리 다 안 씀
		graph = new ArrayList[N+1];
		score = new int[N+1];
		
		// 인구 수가 score
		for(int i = 1; i <= N; i++) {
			graph[i] = new ArrayList<>();
			score[i] = sc.nextInt();
		}

		for(int i = 1; i <= N; i++) {
			int K = sc.nextInt();
			for(int j = 0; j < K; j++) graph[i].add(sc.nextInt());
		}
		
		// 두 선거구 중 어디를 골랐는지
		vote = new int[N+1];
		
		min = Integer.MAX_VALUE;
		
		combination(1);
		
		// 두 선거구로 나눌 수 없는 경우 -> min값이 갱신된 적 없는 경우
		if(min == Integer.MAX_VALUE) System.out.println(-1);
		else System.out.println(min);
	}


	static void combination(int idx) {
		
		// idx가 N+1일 때
		if(idx == N+1) {
			int count = 0;
			visited = new boolean[N+1];
			for(int i = 1; i <= N; i++) {
				if(!visited[i]) {
					count++;
					
					// count(선거구)가 2개를 넘어간다면 break
					if(count > 2) break;
					
					// dfs를 통해 2개의 선거구로 나눌 수 있는지 확인
					dfs(i, vote[i]);
				}
			}
			
			// count가 2라면(2개의 선거구로 나눌 수 있다면)
			if(count == 2) {
				int sum0 = 0;
				int sum1 = 0;
				for(int i = 1; i <= N; i++) {
					// 조합을 통해 뽑은 선거구에 인원 수를 더함
					if(vote[i] == 0) sum0 += score[i];
					else sum1 += score[i];
				}
				
				// min값을 갱신
				min = Math.min(min , Math.abs(sum0 - sum1));
				
			}
			
			// count값을 초기화
			count = 0;
			return;
		}
		
		// 각 사람이 0과 1을 고르는 모든 경우의 수(조합)
		vote[idx] = 0;
		combination(idx+1);
		
		vote[idx] = 1;
		combination(idx+1);
		
		
	}
	
	static void dfs(int start, int num) {
		visited[start] = true;

		for(int i = 0; i < graph[start].size(); i++) {
			int next = graph[start].get(i);
			
			//같은 선거 번호일 때만 이동
			if(!visited[next] && vote[next] == num) dfs(next, num);
		}
	}

}