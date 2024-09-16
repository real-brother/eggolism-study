package BOJ_1068_트리;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
	// 인접 리스트 사용
	static ArrayList<Integer>[] graph;
	static boolean[] visited;

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		
		graph = new ArrayList[N];
		visited = new boolean[N];
		
		int size_one = 0;
		
		// 삭제할 숫자를 먼저 알기 위해서 입력값을 temp[i] 입력값 저장
		int[] temp = new int[N];
		for(int i = 0; i < N; i++) {
			graph[i] = new ArrayList<>();
			temp[i] = sc.nextInt();
		}
		
		// 삭제할 숫자
		int del_num = sc.nextInt();
		
		// 삭제할 숫자와 i가 같으면 그 인덱스를 저장해준다.
		for(int i = 0; i < N; i++) {
			int parent = temp[i];
			if(parent != -1) {
				graph[parent].add(i);
				if(del_num == i) size_one = parent;
			}
		}
		
		// dfs로 순회하면서 del_num의 자식 노드를 visted true로 만든다.
		dfs(del_num);

		int count = 0;
		
		// 삭제할 숫자의 부모의 자식 노드가 삭제할 숫자 하나 뿐이라면 넘어간다.
		// 그 외의 사이즈가 0이 아니거나 방문한 적이 있는 노드이면 count++를 한다.
		for(int i = 0; i < graph.length; i++) {
			if(graph[i].size() == 1 && size_one == i)continue;
			else if(graph[i].size() != 0) count++;
			else if(visited[i]) count++;
		}
		
		// 전체 노드 N개에서 리프노드가 아닌 것들을 빼준다.
		System.out.println(N - count);
		
	}
	
	static void dfs(int start) {
		visited[start] = true;
		for(int i = 0; i < graph[start].size(); i++) {
			int idx = graph[start].get(i);
			if(!visited[idx]) dfs(idx);
		}
	}
}
