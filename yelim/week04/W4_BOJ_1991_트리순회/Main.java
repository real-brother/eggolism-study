package W4_BOJ_1991_트리순회;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Node {
	char data;
	Node left;
	Node right;

	Node() {
	}

	Node(char data) {
		this.data = data;
	}

}

public class Main {

	static Node[] nodes;
	static int n;

	public static void main(String[] args) throws IOException {
		/*
		 * 문제) 이진 트리를 입력받아 전위 순회(preorder traversal), 중위 순회(inorder traversal), 후위
		 * 순회(postorder traversal)한 결과를 출력하는 프로그램을 작성하시오.
		 * 
		 * 첫째 줄에는 이진 트리의 노드의 개수 N(1 ≤ N ≤ 26)이 주어진다. 둘째 줄부터 N개의 줄에 걸쳐 각 노드와 그의 왼쪽 자식 노드,
		 * 오른쪽 자식 노드가 주어진다. 노드의 이름은 A부터 차례대로 알파벳 대문자로 매겨지며, 항상 A가 루트 노드가 된다. 자식 노드가 없는
		 * 경우에는 .으로 표현
		 */


		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());

		nodes = new Node[n+1];

		for (int i = 1; i <= n; i++) {
			String input[] = br.readLine().split(" ");
			
			char char1 = input[0].charAt(0);
			char char2 = input[1].charAt(0);
			char char3 = input[2].charAt(0);

			// 0번 노드 버리고 알파벳 순으로 노드 넣어주기
			if (nodes[char1-'A'+1] == null) {
				nodes[char1-'A'+1] = new Node(char1);
			}

			// 왼쪽 자식
			if (char2 != '.') {
				nodes[char2-'A'+1] = new Node(char2);
				nodes[char1-'A'+1].left = nodes[char2-'A'+1];
			}

			// 오른쪽 자식
			if (char3 != '.') {
				nodes[char3-'A'+1] = new Node(char3);
				nodes[char1-'A'+1].right = nodes[char3-'A'+1];
			}

		}

		// 전위 순회
		preorder(nodes[1]);
		System.out.println();

		// 중위 순회
		inorder(nodes[1]);
		System.out.println();

		// 후위 순회
		postorder(nodes[1]);
	}
	

	// 전위 순회
	static void preorder(Node node) {
		// 기저조건
		if (node == null) {
			return;
		}

		System.out.print(node.data);
		preorder(node.left);
		preorder(node.right);

	}

	// 중위 순회
	static void inorder(Node node) {
		// 기저조건
		if (node == null) {
			return;
		}

		inorder(node.left);
		System.out.print(node.data);
		inorder(node.right);
	}

	// 후위 순회
	static void postorder(Node node) {
		// 기저조건
		if (node == null) {
			return;
		}

		postorder(node.left);
		postorder(node.right);
		System.out.print(node.data);
	}

}
