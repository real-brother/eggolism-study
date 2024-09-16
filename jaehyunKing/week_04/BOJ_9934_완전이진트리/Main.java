package BOJ_9934_완전이진트리;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/*
 *  결국 중위순회(LRV)로 확인한 빌딩을 트리 순서?에 맞게 출력하는 문제
 *  입력의 중간 값이 루트 노드, 그렇게 절반으로 나눠진 2개의 부분의 가운데가 각각 루트 노드의 자식 노드
 *  -> 4개로 나눠진 부분이 2개의 자식 노드의 자식노드 -> 무한 반복 -> 재귀 함수를 통해 해결
 */
public class Main {
	// 인접리스트로 생성, 입력 값을 tree에 저장
	static List<Integer>[] answer;
	static int[] tree;
	static int K;

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		K = sc.nextInt();
		
		tree = new int[(int)Math.pow(2, K)]; //0번 버림
		for(int i = 1; i < tree.length; i++) tree[i] = sc.nextInt();
		
		answer = new ArrayList[K];
		for(int i = 0; i < K; i++) answer[i] = new ArrayList<>();
		
		// left를 1, right을 전체 길이 - 1, idx를 0부터 시작하면서 답을 배열에 저장
		recursion(1, tree.length-1, 0);
		
		// 레벨 별로 다른 배열에 저장되어있는데 그것을 각각 출력한다.
		for(int i = 0; i < K; i++) {
			for(int j = 0; j < answer[i].size(); j++)System.out.print(answer[i].get(j)+ " ");
			System.out.println();
		}
	}
	
	// 왼쪽이 오른쪽보다 커지면 빠져나온다.
	static void recursion(int left, int right, int idx) {
		if(left > right) return;
		int mid = (left + right) / 2;
		// 답을 배열의 idx에 맞는 리스트에 저장
		answer[idx].add(tree[mid]);
		
		recursion(left, mid-1, idx+1);
		recursion(mid+1, right, idx+1);
	}
}