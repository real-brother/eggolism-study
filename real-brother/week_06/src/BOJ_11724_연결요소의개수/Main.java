package BOJ_11724_연결요소의개수;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
	static Scanner sc = new Scanner(System.in);
	static int n;
	static int m;
	static List<List<Integer>> graph;
	static boolean[] visited;
	static int ansCount = 0;
	
    public static void main(String[] args) {
    	n = sc.nextInt();
    	m = sc.nextInt();
    	visited = new boolean[n+1];
    	graph = new ArrayList<List<Integer>>();
    	for (int i = 0; i <= n; i++) {
    		graph.add(new ArrayList<Integer>());
		}
    	for (int i = 0; i < m; i++) {
			int a = sc.nextInt();
			int b = sc.nextInt();
			graph.get(a).add(b);
			graph.get(b).add(a);
		}
    	
    	// 첫번째부터 마지막까지 index를 잡고, 돌리보기
    	// 방문한적 없는 노드일때 덩어리 찾기 시작 -> 답 +1
    	for (int i = 1; i <= n; i++) {
			if (!visited[i]) {
				dfs(i);
				ansCount++;
			}
		}
    	
        System.out.println(ansCount);
        
    }
    
    
    // dfs로 돌면서 한 덩어리 찾기
	private static void dfs(int start) {
		visited[start] = true;
		for (int index : graph.get(start)) {
			if (!visited[index]) {
				visited[index] = true;
				dfs(index);
				
			}
		}
	}
}
