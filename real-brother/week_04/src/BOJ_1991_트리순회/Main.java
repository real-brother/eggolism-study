package BOJ_1991_트리순회;

import java.util.List;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		sc.nextLine();
		
		for (int t = 0; t < n; t++) {
			
		}

	}

	static class TreeNode {
		int val;
		TreeNode left, right;

		public TreeNode(int val) {
			this.val = val;
			this.left = this.right = null;
		}
	}

	static class BinaryTree {
		static int index = 0;

		public TreeNode constructTree(List<Integer> preorder) {
			return constructTreeUtil(preorder, Integer.MIN_VALUE, Integer.MAX_VALUE);
		}
		
		// 숫자를 비교하며 트리 만들기
		private TreeNode constructTreeUtil(List<Integer> preorder, int min, int max) {
			if (index >= preorder.size()) {
				return null;
			}

			int val = preorder.get(index);
			if (val < min || val > max) {
				return null;
			}

			TreeNode node = new TreeNode(val);
			index++;

			node.left = constructTreeUtil(preorder, min, val);
			node.right = constructTreeUtil(preorder, val, max);

			return node;
		}
		
		// 후위순회 출력하기
		public void postOrderTraversal(TreeNode root) {
			if (root != null) {
				postOrderTraversal(root.left);
				postOrderTraversal(root.right);
				System.out.println(root.val);
			}
		}
	}
}