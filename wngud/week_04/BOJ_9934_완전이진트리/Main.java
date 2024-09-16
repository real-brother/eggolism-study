import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main {

	static int[] tree;
	static int[] inputs;
	static int count = 0;

	/*
	 * 문제 설명 
   * 1. 중위 순회를 의미하는 것 같다.
	 * 
	 */

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		
		// 전체 트리의 원소의 개수를 구하고, 트리를 위한 배열을 만든다.
		int total = sc.nextInt();
		int total2 = (int) (Math.pow(2, total) - 1);
		
		tree = new int[total2 + 1];
		
		// 번호를 넣어줄 input을 미리 배열로 만들어 놓는다.
		inputs = new int[total2];
		for (int i = 0; i < total2; i++) {
			inputs[i] = sc.nextInt();
		}

		// 중위 순회하기
		inorder(1);
		
		// 정답 출력하기
		// 2의 (n-1)승 씩 출력
		// 0부터 트리 길이 끌까지 가야함
//		System.out.println(Arrays.toString(tree));
		
		int temp = 0;
		for (int i = 1; i <= total; i++) {
			for (int j = (int) Math.pow(2,i-1); j < (int) Math.pow(2,i); j++) {
				System.out.print(tree[j] + " ");
			}
			System.out.println();
		}

	}
	
	/*inorder
	 * 1. 중위 순회를 진행한다.
	 * 2. 중위 순회를 탐색하게 디면 input에 넣는다.
	 */

	static void inorder(int idx) {

		// 여기서는 완전 이진 탐색이라서 모두 트리의 원소가 존재 
		// (오른쪽 자식 없는 조건 필요 없음)
		// 인덱스가 tree 끝까지 가거나 넘으면 종료
		if (tree.length <= idx) return;

		inorder(idx * 2);
//		System.out.println(idx + " ");
		tree[idx] = inputs[count++];
		inorder(idx * 2 + 1);
	}

}