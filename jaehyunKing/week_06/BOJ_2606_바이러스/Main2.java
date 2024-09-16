package BOJ_2606_바이러스;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main2 {
	// 인접 리스트로 생성
	static List<Integer>[] graph;
	static boolean[] visited;
	static int count;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		int K = sc.nextInt();
		
		graph = new ArrayList[N+1];
		visited = new boolean[N+1];
		
		for(int i = 1; i <= N; i++) graph[i] = new ArrayList<>();
		
		// 양방향 그래프로 만들어준다.
		for(int i = 0; i < K; i++) {
			int num1 = sc.nextInt();
			int num2 = sc.nextInt();
			graph[num1].add(num2);
			graph[num2].add(num1);
		}
		
		// 1번 노드가 감염될 때 감염되는 노드 개수를 구하는 문제이기 때문에 
		// 시작 노드를 1번 노드로 잡아준다.
		dfs(1);
		System.out.println(count);
	}
	
	static void dfs(int start) {
		visited[start] = true;
		for(int i = 0; i < graph[start].size(); i++) {
			int next = graph[start].get(i);
			if(!visited[next]) {
				// 1번 노드는 감염된 숫자에서 빼기 위해 dfs를 재귀를 타 줄 때 count++를 해준다.
				count++;
				dfs(next);
			}
		}
	}
}