import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main {

	static ArrayList<ArrayList<Integer>> graph;
	static int[] visited;
	static int[] parents; // 부모 찾기용
	static int count;

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();

		int total = sc.nextInt();

		// 1. 그래프 채우기
		visited = new int[total + 1];
		parents = new int[total + 1];
		
		// 두번 채워야 함
		graph = new ArrayList<>();
		for (int i = 0; i <= total; i++) {
			graph.add(new ArrayList<>());
		}
		
		for (int i = 0; i < total - 1; i++) {
			int a = sc.nextInt();
			int b = sc.nextInt();

			graph.get(a).add(b);
			graph.get(b).add(a);
		}

//		for (int i = 0; i < graph.size(); i++) {
//			System.out.println(graph.get(i).toString());
//		}

		// 2. dfs 탐색하기
		// 루트 노드는 1
		dfs(1);

		// 3. 답 출력하기
		// 2부터 출력하면 되니까 2부터 하면 됨 (1은 애초에 처음에 visited됨)
		for (int i = 2; i < parents.length; i++) {
			sb.append(parents[i]).append("\n");
		}
		System.out.println(sb);

	}

	static void dfs(int idx) {
//		System.out.println("탐색 완료: " + idx);

		visited[idx] = 1;

		// 각 숫자마다 접근해서 dfs 가능여부 확인
		for (int i = 0; i < graph.get(idx).size(); i++) {
			int target = graph.get(idx).get(i);
			
			if (visited[target] != 1) {
				parents[target] = idx; // 부모 저장
				dfs(target);
			}
		}

	}

}