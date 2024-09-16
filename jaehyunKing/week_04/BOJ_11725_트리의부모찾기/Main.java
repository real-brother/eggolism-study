package BOJ_11725_트리의부모찾기;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
	// 인접리스트로 생성
	// 부모노드를 저장해 줄 parents 배열을 생성
	static ArrayList<Integer>[] graph;
	static int[] parents;
	static boolean[] visited;

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		
		// 0번 인덱스 안 씀
		graph = new ArrayList[N+1];
		parents = new int[N+1];
		visited = new boolean[N+1];
		
		// 배열 안에 리스트 생성
		for(int i = 1; i <= N; i++) graph[i] = new ArrayList<>();
		
		//  입력 값을 양방향 그래프로 만들어준다.
		for(int i = 0; i < N-1; i++) {
			int M = sc.nextInt();
			int K = sc.nextInt();
			graph[M].add(K);
			graph[K].add(M);
		}
		
		// 1번 노드(루트 노드)부터 dfs로 탐색
		dfs(1);
		for(int i = 2; i <= N; i++)System.out.println(parents[i]);

	}
	
	// dfs로 탐색하면서 이전 값을 parent 변수에 저장
	// 그 parent값을 현재 값의 parents 배열에 저장
	static void dfs(int start) {
		visited[start] = true;
		int parent = start;
		
		for(int i = 0; i < graph[start].size(); i++) {
			int idx = graph[start].get(i);
			if(!visited[idx]) {
				parents[idx] = parent;
				dfs(idx);
			}
		}
	}
}
