package W6_BOJ_N과M;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_15649 {
	
	static int n, m;
	static int[] arr;
	static boolean[] visited;

	public static void main(String[] args) throws IOException {
		/* 문제) N과 M (1)
		 * 자연수 N과 M이 주어졌을 때, 아래 조건을 만족하는 길이가 M인 수열을 모두 구하는 프로그램을 작성하시오.
		 * 1부터 N까지 자연수 중에서 중복 없이 M개를 고른 수열
		 */
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		arr = new int[m];
		visited = new boolean[n];
		
		sequence(0);
		
	}
	
	static void sequence(int num) {
		// 만약 num이 m과 같다면 (m 크기만큼 저장되었다면) 수열 완성 => 출력
		if (num == m) {
			for(int x:arr) {
				System.out.print(x+" ");
			}
			System.out.println();
			return;
		}
		
		// 숫자 크기만큼 돌면서 방문 안했으면 방문 후 값 넣어주고, 재귀호출
		// 재귀 끝나면 방문 초기화
		for(int i=0; i<n; i++) {
			if (!visited[i]) {
				visited[i] = true;
				arr[num] = i+1;
				sequence(num+1);
				visited[i] = false;
			}
		}
		
	}

}
