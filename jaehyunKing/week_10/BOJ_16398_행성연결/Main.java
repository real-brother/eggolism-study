package BOJ_16398_행성연결;

/*
 *  입력을 2차원 배열로 줘서 -> 2차원 배열 프림 사용
 */

import java.util.Arrays;
import java.util.Scanner;

public class Main {
	static int N;
	static int[][] graph;
	static int[] dist;
	static boolean[] visited;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		graph = new int[N+1][N+1];
		
		for(int r = 1; r <= N; r++) for(int c = 1; c <= N; c++) graph[r][c] = sc.nextInt();
		
		dist = new int[N+1];
		Arrays.fill(dist, Integer.MAX_VALUE);
		dist[1] = 0; 
		visited = new boolean[N+1];
		
		System.out.println(prim(0));
	}
	
	static long prim(int start) {
		long sum = 0;
		int count = 0;
		
		while(true) {
			
			int min = Integer.MAX_VALUE;
			int idx = -1;
			for(int i = 1; i <= N; i++) {
				if(min > dist[i] && !visited[i]) {
					min = dist[i];
					idx = i;
				}
			}
			
			visited[idx] = true;
			sum += dist[idx];
			count++;
			if(count == N) break;
			
			for(int i = 1; i <= N; i++) {
				if(!visited[i] && graph[idx][i] != 0 && graph[idx][i] < dist[i]) {
					dist[i] = graph[idx][i];
				}
			}
		}
		
		return sum;
	}

}