package BOJ_1991_트리순회;

import java.util.Scanner;

/*
 *  사실상 수업시간에 한 문제에서 문자를 알파벳으로 받는 것만 어떻게든 해결하면 되는 문제
 *  이진 트리이기 때문에 class Node를 생성해주었다. 이진 트리가 아니라면 그래프로 푸는 것이 훨씬 나은 거 같다.
 *  nodes에 각 노드를 저장, charAt()와 아스키코드를 이용하여 입력받는 알파벳 값을 숫자로 바꿔서 -> index
 *  nodes 배열에 저장해준다.
 *  수업 시간에 사용한 preorder, inorder, postorder를 사용해준다.
 */
class Node {
	char data;
	Node left, right;
	
	Node(){}
	
	Node(char data){
		this.data = data;
	}
}

public class Main {
	// node들을 저장할 배열 nodes을 Node타입으로 선언
	static Node[] nodes;

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		nodes = new Node[N]; // 0번 버림
		
		for(int i = 1; i <= N; i++) {
			//스캐너로 입력받은 문자열을 char형으로 바꾼다.
			char data = sc.next().charAt(0);
			char left = sc.next().charAt(0);
			char right = sc.next().charAt(0);
			
			// char형 data에서 - 'A'를 해주면 아스키코드로 65를 빼준 효과가 나타난다.
			// data = A라면 data - 'A' ==  data - 65 == 0
			if(nodes[data-'A'] == null)nodes[data-'A'] = new Node(data);
			
			// left, right 동일하게 '.'이 아니라면 left, right 노드를 만들어주고 
			// 이를 해당 노드의 left, right로 만들어 연결해준다.
			if(left != '.') {
				nodes[left-'A'] = new Node(left);
				nodes[data-'A'].left = nodes[left-'A'];
			}
			if(right != '.') {
				nodes[right-'A'] = new Node(right);
				nodes[data-'A'].right = nodes[right-'A'];
			}
		}

		preorder(nodes[0]);
		System.out.println();
		inorder(nodes[0]);
		System.out.println();
		postorder(nodes[0]);

	}
	
	//VLR
	public static void preorder(Node node) {
		if(node == null) return;
		System.out.print(node.data);
		preorder(node.left);
		preorder(node.right);
	}
	
	//LVR
	public static void inorder(Node node) {
		if(node == null) return;
		inorder(node.left);
		System.out.print(node.data);
		inorder(node.right);
	}
	
	//LRV
	public static void postorder(Node node) {
		if(node == null) return;
		postorder(node.left);
		postorder(node.right);
		System.out.print(node.data);
	}
		

}
