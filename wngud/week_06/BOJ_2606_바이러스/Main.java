import java.util.Scanner;

public class Main {
	
	
	static boolean[][] graph; //static이 들어가야 함 -> psvm 다른 곳 이용
	static boolean[] visited;
	
	static int total_computer;
	static int total_match;
	
	
	static int answer = 0;
	
	// [1]
	public static void dfs(int idx) {
		visited[idx] = true;
		answer++;
		
		for (int i = 1; i <= total_computer; i++) {
			if (visited[i] == false && graph[idx][i]) {
				dfs(i);
			}
		}
	}
	

	
	// [2]
	public static void main(String[] args) {	
		Scanner sc = new Scanner(System.in);
		
		
		// 0. 입력 및 초기화
		total_computer = sc.nextInt();
		total_match = sc.nextInt();
		
		graph = new boolean[total_computer+1][total_computer+1];
		visited = new boolean[total_computer+1];
		
		
		
		// 1. 그래프 정보 입력
		for (int i = 0; i < total_match; i++) {
			int a = sc.nextInt();
			int b = sc.nextInt();
			
			graph[a][b] = graph[b][a] = true;
			
		}
		
		
		// 2. DFS 및 결과 출력	
		dfs(1);
		
		System.out.println(answer - 1); // 1 제외
		
		
		
		
	}
}
