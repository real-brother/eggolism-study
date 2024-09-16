package BOJ_15649_N과M1;

import java.util.Scanner;

public class Main {
	static int N, M;
	static int[] nums;
	static boolean[] visited;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		M = sc.nextInt();
		
		visited = new boolean[N+1];
		nums = new int[M];
		
		// 0번 index부터 순열을 돌린다.
		perm(0);

	}
	
	static void perm(int idx) {
		
		//idx == M(길이가 M)에 도달하면 출력
		if(idx == M) {
			for(int i = 0; i < M; i++) {
				System.out.print(nums[i]+ " ");
			}
			System.out.println();
			return;
		}
		
		// 중복이 없는 순열을 만들기 위해서 visited를 사용
		// for문을 작은 숫자부터 돌리면 자동으로 오름차순으로 출력
		// 큰 숫자부터 하면 내림차순
		for(int i = 1; i <= N; i++) {
			if(!visited[i]) {
				visited[i] = true;
				nums[idx] = i;
				perm(idx+1);
				visited[i] = false;
			}
			
		}
		
	}

}