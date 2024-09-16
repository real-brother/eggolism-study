package BOJ_11403_경로찾기;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
	static Scanner sc = new Scanner(System.in);
	static int n;
	static int m;
	static int[][] inputGraph;
	static int[][] ansArray;
	static List<List<Integer>> graph;
	static List<List<Integer>> islands;
	static List<Integer> tempIsland;
	static boolean[] visited;
	static int ansCount = 0;
	
    public static void main(String[] args) {
    	n = sc.nextInt();
    	inputGraph = new int[n][n];
    	ansArray = new int[n][n];
    	visited = new boolean[n+1];
    	islands = new ArrayList<List<Integer>>();
    	
    	graph = new ArrayList<List<Integer>>();
    	for (int i = 0; i <= n; i++) {
    		graph.add(new ArrayList<Integer>());
		}
    	
    	
    	for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				inputGraph[i][j] = sc.nextInt();
				if (inputGraph[i][j] == 1) {
					// 방향 그래프
					graph.get(i+1).add(j+1);
				}
			}
		}
    	
    	// 모든 루트로부터 갈 수 있는지 찾기
    	for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				boolean isConnected = findRoute(i+1,j+1);
				if (isConnected) ansArray[i][j] = 1;
			}
		}
    	
    	for (int i = 0; i < n; i++) {
    		for (int j = 0; j < n; j++) {
    			System.out.printf("%d ", ansArray[i][j]);
			}
			System.out.println();
		}
        
    }
    
    // i부터 j까지 길이 있는지?
    // dfs돌려서 j의 visited가 false가면 길이 없음
	private static boolean findRoute(int i, int j) {
		dfs(i);
		if (visited[j]) {
			visited = new boolean[n+1];
			return true;
		} else {
			visited = new boolean[n+1];
			return false;
		}
	}

	private static void dfs(int start) {
		for (int index : graph.get(start)) {
			if (!visited[index]) {
				// 목적지만 visited로 바꿔줘야 
				// 출발점으로 돌아올 수 있는지 체크할 수 있음
				visited[index] = true;
				dfs(index);
			}
		}
		
	}
    
}
