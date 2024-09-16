package W7_BOJ_14889_스타트와링크;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static int n;
	static int[][] arr;
	static int min;
	static boolean[] visited;

	public static void main(String[] args) throws IOException {
		/* 문제) 스타트와 링크
		 * . 팀의 능력치는 팀에 속한 모든 쌍의 능력치 Sij의 합이다. 
		 * Sij는 Sji와 다를 수도 있으며, i번 사람과 j번 사람이 같은 팀에 속했을 때, 
		 * 팀에 더해지는 능력치는 Sij와 Sji이다.
		 * 
		 * 스타트 팀의 능력치와 링크 팀의 능력치의 차이를 최소로 하려고 한다.
		 */
		
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		arr = new int[n][n];
		
		for(int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<n; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		// 최소값 초기화
		min = Integer.MAX_VALUE;
		// 방문 배열 초기화
		visited = new boolean[n];
	
		comb(0, 0);

		System.out.println(min);
		
		
	}
	
	// 팀 조합 메소드
	static void comb(int num, int cnt) {
		// 조합 완성되면 재귀 빠져나가기
		if (cnt == n/2) {
			find();
			return;
		}
		
		// 방문하지 않았으면 true, 재귀호출
		// 재귀 호출 후 다시 방문 false 처리
		for (int i=num; i<n; i++) {
			if (!visited[i]) {
				visited[i] = true;
				comb(i+1, cnt+1);
				visited[i] = false;
			}
		}
	}
	
	// 두 팀의 능력치 차이 구해서 최소값 찾는 메소드
	static void find() {
		int start = 0;
		int link = 0;
		int diff = 0;
		
		// visited true인 팀과 fasle인 팀 각각 능력치 합 구하기
		for(int i=0; i<n; i++) {
			for(int j=i+1; j<n; j++) {
				if(visited[i] && visited[j]) {
					start += arr[i][j];
					start += arr[j][i];
				}
				else if (!visited[i] && !visited[j]) {
					link += arr[i][j];
					link += arr[j][i];
				}
			}
		}
		
		// 두 팀 값 차이
		diff = Math.abs(start-link);
		
		// 최소값 구하기
		if (diff<min) {
			min = diff;
		}
	}

}
