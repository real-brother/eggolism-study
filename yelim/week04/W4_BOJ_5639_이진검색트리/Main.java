package W4_BOJ_5639_이진검색트리;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Node {
	int data;
	Node left;
	Node right;
	
	Node() {}
	
	Node(int data) {
		this.data = data;
	}
}

public class Main {
	
	static Node root;

	public static void main(String[] args) throws IOException {
		
		/* 이진 검색 트리
		 * 노드의 왼쪽 서브트리에 있는 모든 키는 노드의 키보다 작다.
		 * 노드의 오른쪽 서브트리에 있는 모든 키는 노드의 키보다 크다.
		 * 왼쪽, 오른쪽 서브트리도 이진 검색 트리이다.
		 */
		
		/* 문제)
		 * 이진 검색 트리를 전위 순회한 결과가 주어졌을 때, 이 트리를 후위 순회한 결과를 구하는 프로그램을 작성하시오.
		 * 
		 * 트리를 전위 순회한 결과가 주어진다. 
		 * 노드에 들어있는 키의 값은 106보다 작은 양의 정수이다. 
		 * 모든 값은 한 줄에 하나씩 주어지며, 노드의 수는 10,000개 이하이다. 같은 키를 가지는 노드는 없다.
		 */
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		root = new Node(Integer.parseInt(br.readLine()));
		
		String s;
		
		while (true) {
			s = br.readLine();
			if (s==null || s.equals("")) {
				break;
			}
			add(Integer.parseInt(s));
		}
		
		postorder(root);

	}	// main 끝 -----------------------------
	
	
	// 데이터 노드에 추가하는 메소드 - 내부에서 addNode 메소드 호출
	static void add(int n) {
		Node newNode = new Node();
		newNode.data = n;
		
		if (root == null) {
			root = newNode;
		}
		else {
			root = addNode(root, newNode);
		}
	}
	
	// 데이터를 비교하며 탐색하여 자식 노드에 데이터 넣어주는 메소드
	static Node addNode(Node node, Node newNode) {
		if (node == null) {
			return newNode;
		}
		else if (node.data > newNode.data) {
			node.left = addNode(node.left, newNode);
		}
		else {
			node.right = addNode(node.right, newNode);
		}
		
		return node;
	}
	
	// 후위 순회
	static void postorder(Node node) {
		if (node == null) {
			return;
		}
		
		postorder(node.left);
		postorder(node.right);
		System.out.println(node.data);
	}

}
