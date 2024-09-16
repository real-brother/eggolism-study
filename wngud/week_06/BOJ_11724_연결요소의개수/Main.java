import java.util.ArrayList;
import java.util.Scanner;

public class Main {
	static int[] visited; 
	static ArrayList<ArrayList<Integer>> graph; 
	
	static int n, m, answer;
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		n = sc.nextInt();
		m = sc.nextInt();
		
		// 연결 리스트 만들기
		graph = new ArrayList<>();
		for (int i = 0; i <= n; i++) {
			graph.add(new ArrayList<Integer>());
		}
		
		for (int i = 0; i < m; i++) {
			int a = sc.nextInt();
			int b = sc.nextInt();
			graph.get(a).add(b);
			graph.get(b).add(a); // 방향이 없기에 두 방향 추가
			
		}
		
		// DFS
		visited = new int[n+1];
		answer = 0;
		for (int i = 1; i <= n; i++) {
			if (visited[i] != 1) {
				answer++;
				dfs(i);
			}
		}
		
		// 결과 출력하기
		System.out.println(answer);
		
	}
	
	static void dfs(int idx) {
		visited[idx] = 1;
		
		for (int i = 0; i < graph.get(idx).size(); i++) {
			int tmp = graph.get(idx).get(i);
			if (visited[tmp] != 1) {
				dfs(tmp);
			}
		}
	}
	
	
	
}