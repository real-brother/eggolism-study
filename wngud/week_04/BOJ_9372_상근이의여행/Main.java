import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main {

	static ArrayList<ArrayList<Integer>> graph;
	static int[] visited;
	static ArrayList<Integer> answer;
	static int count = 0;
	
	/*문제 설명
	 * 1. 나라는 노드를 의미하고
	 * 2. 간선은 비행기의 종류를 의미한다.
	 * 노드에서 노드를 이동할 때 비행기 종류를 한 번 타는 것과 동일하고,
	 * 이를 최소화하는 것이 문제의 핵심이다.
	 */
	

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		int Test = sc.nextInt();
		
		for (int t = 0; t < Test; t++) {
			


		int N = sc.nextInt();
		int M = sc.nextInt();

		// 1. 인접 리스트 만들기
		graph = new ArrayList<>();
		visited = new int[N + 1];
		answer = new ArrayList<>();

		for (int i = 0; i < N + 1; i++) {
			graph.add(new ArrayList<Integer>());
		}

		// 2. 인접 리스트 채우기
		for (int i = 0; i < M; i++) {
			int a = sc.nextInt();
			int b = sc.nextInt();

			graph.get(a).add(b);
			graph.get(b).add(a);
		}

//		for (int i = 0; i < N + 1; i++) {
//			System.out.println(graph.get(i).toString());
//		}

		// dfs 순회하기
		for (int i = 1; i < N + 1; i++) {
			// 초기화
			visited = new int[N+1];
			count = 0;
			
			dfs(i);
			answer.add(count);
		}
			
			System.out.println(answer.get(0));
		}

	}

	static void dfs(int idx) {
//		System.out.println("탐색: " + idx);

		visited[idx] = 1;

		for (int i = 0; i < graph.get(idx).size(); i++) {
			int target = graph.get(idx).get(i);
			
			if (visited[target] != 1) {
				count++; // dfs 탐색한 수만큼 count++
				dfs(target);
			}
		}
	}
	
}