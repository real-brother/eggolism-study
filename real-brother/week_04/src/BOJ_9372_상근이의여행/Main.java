package BOJ_9372_상근이의여행;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Main {
	static int count = 0;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int testCase = sc.nextInt();
		sc.nextLine();
		for (int t = 0; t < testCase; t++) {
			int nationNum = sc.nextInt();
			int airplaneNum = sc.nextInt();
			List<List<Integer>> connectionGraph = new ArrayList();
			// 비행기개수 + 1개의 비어있는 이중리스트 추가
			for (int i = 0; i <= nationNum; i++) {
				connectionGraph.add(new ArrayList<>());
			}
			
			// 비행기의 각 목적지 추가
			for (int i = 0; i < airplaneNum; i++) {
				int a = sc.nextInt();
				int b = sc.nextInt();
		        connectionGraph.get(a).add(b);
		        connectionGraph.get(b).add(a);
			}
	        
			boolean[] visited = new boolean[nationNum + 1];
			// 1번부터 시작
            dfs(connectionGraph, visited, 1);

            System.out.println(count - 1); // 시작점을 제외한 비행 횟수 출력
            count = 0; // 초기화
			
		}
	}
	
    public static void dfs(List<List<Integer>> graph, boolean[] visited, int start) {
        if (visited[start])
            return;

        visited[start] = true;
        count++;

        for (int next : graph.get(start)) {
            if (!visited[next]) {
                dfs(graph, visited, next);
            }
        }
    }

	
}