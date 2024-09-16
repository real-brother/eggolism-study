package BOJ_5639_이진검색트리;

import java.util.Scanner;

/*
 *  사실상 수업시간에 배운 내용이다. 중간 꺼보다 크면 오른쪽에, 작으면 왼쪽에 넣으면 됨
 *  이진트리이기 때문에 class node를 정의해서 사용해줬다.
 */
class Node {
	int data;
	Node left;
	Node right;
	
	Node(){}
	
	Node(int data){
		this.data = data;
	}
}

public class Main {
	static Scanner sc;

	public static void main(String[] args) {
		sc = new Scanner(System.in);
		
		// 처음 입력된 숫자는 루트노드(중위 순회)이기 때문에 바로 root를 만들어 넣어준다.
		Node root = new Node(sc.nextInt());
		
		// 입력값의 개수가 정해지지 않았기 때문에 while(sc.hasNext())를 통해 
		// 입력이 없을 때까지 계속 반복하게 한다.
		while(sc.hasNext())createNode(root, sc.nextInt());
		
		// 후위 순회를 한다.
		postorder(root);
	}
	
	/* 노드를 생성하는 메서드
	*  원래 재귀함수를 어떻게 잘 하면 한 번에 할 수 있지않을까 싶었는데
	*  잘 생각이 안 나서 루트노드부터 타고가서 작으면 왼쪽, 크면 오른쪽에 넣어주는 코드로 작성했다.
	*/ 
	static void createNode(Node node, int data) {
		// 현재 노드의 값보다 data가 작으면
		if(data < node.data) {
			// 왼쪽이 비어있으면 왼쪽에 그 값을 넣고,
			if(node.left == null) node.left = new Node(data);
			// 아니라면 왼쪽 노드로 이동해서 해당 과정을 반복한다.
			else createNode(node.left, data);
		}
		else {
			// 오른쪽 노드의 값이 data보다 클 때, 오른쪽 노드가 비어있으면 오른쪽에 값을 넣어주고,
			if(node.right == null) node.right = new Node(data);
			// 아니라면 오른쪽 노드로 이동해서 해당 과정을 반복한다.
			else createNode(node.right, data);
		}
	}
	
	//LRV
	static void postorder(Node node) {
		if(node == null) return;
		postorder(node.left);
		postorder(node.right);
		System.out.println(node.data);
	}
}