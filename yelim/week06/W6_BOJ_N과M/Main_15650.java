package W6_BOJ_N과M;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_15650 {
	
	static int n, m;
	static int[] arr;
	static boolean[] visited;

	public static void main(String[] args) throws IOException {
		/* 문제) N과 M (2)
		 * 자연수 N과 M이 주어졌을 때, 아래 조건을 만족하는 길이가 M인 수열을 모두 구하는 프로그램을 작성하시오.
		 * 1부터 N까지 자연수 중에서 중복 없이 M개를 고른 수열
		 * 고른 수열은 오름차순이어야 한다.
		 */
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		arr = new int[m];
		visited = new boolean[n];
		
		sequence(0, 0);
		
	}
	
	// cnt를 같이 넘겨줘서 현재 위치부터 탐색하도록 
	static void sequence(int cnt, int num) {
		if (num==m) {
			for(int x:arr) {
				System.out.print(x+" ");
			}
			System.out.println();
			return;
		}
		
		for(int i=cnt; i<n; i++) {
			if (!visited[i]) {
				visited[i] = true;
				arr[num] = i+1;
				sequence(i+1, num+1);
				visited[i] = false;
			}
		}
		
	}

}
