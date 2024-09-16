package BOJ_1967_트리의지름;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;


public class Main {

	static List<List<TreeNode>> tree;
	static boolean[] visited;
	static int maxDiameter;
	static int farthestNode;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		sc.nextLine();
		
		tree = new ArrayList<>();
		visited = new boolean[n + 1];
		maxDiameter = 0;

		for (int i = 0; i <= n; i++) {
			tree.add(new ArrayList<>());
		}

		for (int i = 0; i < n - 1; i++) {
			int parent = sc.nextInt();
			int child = sc.nextInt();
			int weight = sc.nextInt();
			tree.get(parent).add(new TreeNode(child, weight));
			tree.get(child).add(new TreeNode(parent, weight));
		}

		dfs(1, 0); // 임의의 1에서 시작하여 가장 멀리있는 노드 찾기기
		Arrays.fill(visited, false); // 방문 배열 초기화
		dfs(farthestNode, 0); // 가장 먼 노드에서 다시 시작하여 트리 전체에서 가장 최대인 지름을 찾음

		System.out.println(maxDiameter);
	}
	
	// 노드들을 돌면서 지름 구하기 - 최대일 경우 갱신
	static void dfs(int node, int diameter) {
		visited[node] = true;

		if (diameter > maxDiameter) {
			maxDiameter = diameter;
			farthestNode = node;
		}

		for (TreeNode child : tree.get(node)) {
			if (!visited[child.node]) {
				dfs(child.node, diameter + child.weight);
			}
		}
	}
	
	// 트리 클래스 구현 - 가중치 추가
	static class TreeNode {
		int node;
		int weight;

		public TreeNode(int node, int weight) {
			this.node = node;
			this.weight = weight;
		}
	}

}