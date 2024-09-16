package BOJ_11724_연결요소의개수;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
	// 인접 리스트
	static List<Integer>[] graph;
	static boolean[] visited;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		int M = sc.nextInt();
		
		graph = new ArrayList[N+1];
		visited = new boolean[N+1];
		
		for(int i = 1; i <= N; i++) graph[i] = new ArrayList<>();
		
		
		// 양방향 그래프
		for(int i = 0; i < M; i++) {
			int num1 = sc.nextInt();
			int num2 = sc.nextInt();
			graph[num1].add(num2);
			graph[num2].add(num1);
		}
		
		int count = 0;
		
		// dfs를 들어갈 때 마다 + 1 -> count가 곧 연결 요소의 개수
		for(int i = 1; i <= N; i++) {
			if(!visited[i]) {
				count++;
				dfs(i);
			}
		}
		
		System.out.println(count);
	}
	
	static void dfs(int start) {
		visited[start] = true;
		for(int i = 0; i < graph[start].size(); i++) {
			int next = graph[start].get(i);
			if(!visited[next]) dfs(next);
		}
	}

}