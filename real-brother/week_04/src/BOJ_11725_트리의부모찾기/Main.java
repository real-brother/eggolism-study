package BOJ_11725_트리의부모찾기;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
//1
public class Main {
	static int n;
    static ArrayList<Integer>[] graph;
    static boolean[] visited;
    static int[] answer;
    
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		sc.nextLine();

		graph = new ArrayList[n + 1];
		visited = new boolean[n + 1];
        answer = new int[n + 1];

        for (int i = 1; i <= n; i++) {
        	graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < n - 1; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            graph[a].add(b);
            graph[b].add(a);
        }
        
        dfs(1);

        for (int i = 2; i <= n; i++) {
            System.out.println(answer[i]);
        }
	}
	
	// DFS를 이용하여 각 정점의 부모를 찾는 함수
    private static void dfs(int index) {
        visited[index] = true; // 현재 정점을 방문했음을 표시
        // 현재 정점과 연결된 모든 정점에 대해
        for (int i : graph[index]) {
            if (!visited[i]) { // 방문하지 않은 정점인 경우
                answer[i] = index; // 현재 정점이 연결된 정점의 부모로 설정
                dfs(i); // 연결된 정점에서 다시 DFS 호출
            }
        }
    }
}