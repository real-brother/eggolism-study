package BOJ_9934_완전이진트리;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Scanner;


// 중위순회를 한 결과가 주어짐 -> 그것을 바탕으로 트리를 구성 -> 각 층 마다 출력해주기
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int k = sc.nextInt();
		sc.nextLine();
		int[] buildingNum = new int[(int) (Math.pow(2, k) - 1)];
		for (int i = 0; i < Math.pow(2, k) - 1; i++) {
			buildingNum[i] = sc.nextInt();
		}
		
		printTreeStructure(buildingNum);
	}
	
	// 중위 순회 결과를 이용하여 이진 트리의 구조를 출력하는 메소드
    public static void printTreeStructure(int[] inorder) {
        // 중위 순회 결과를 트리로 변환
        TreeNode root = buildTreeFromInorder(inorder, 0, inorder.length - 1);

        // 각 층별로 트리의 노드를 출력
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size(); // 각 층의 크기 - 1, 2, 4, 8, 16 ...
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                System.out.print(node.val + " ");
                if (node.left != null) queue.offer(node.left);
                if (node.right != null) queue.offer(node.right);
            }
            System.out.println();
        }
    }

    // 중위 순회 결과를 이용하여 트리를 생성하는 메소드
    private static TreeNode buildTreeFromInorder(int[] inorder, int start, int end) {
        if (start > end) return null;

        int mid = (start + end) / 2;
        TreeNode root = new TreeNode(inorder[mid]);

        root.left = buildTreeFromInorder(inorder, start, mid - 1);
        root.right = buildTreeFromInorder(inorder, mid + 1, end);

        return root;
    }

	static class TreeNode {
	    int val;
	    TreeNode left;
	    TreeNode right;
	    
	    // 트리생성자 - 루트의 값을 넣기
	    TreeNode(int x) {
	        val = x;
	    }
	}
	
	
	
}