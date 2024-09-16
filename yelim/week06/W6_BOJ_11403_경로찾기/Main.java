package W6_BOJ_11403_경로찾기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		/* 문제) 경로 찾기
		 * 가중치 없는 방향 그래프 G가 주어졌을 때, 모든 정점 (i, j)에 대해서, 
		 * i에서 j로 가는 길이가 양수인 경로가 있는지 없는지 구하는 프로그램을 작성하시오.
		 */
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		
		// 인접행렬 선언 및 그래프 값 입력받기
		int[][] graph = new int[n][n];
		for(int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<n; j++) {
				graph[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		// 답 저장할 그래프
		int[][] ans = new int[n][n];
		
		// 정답 배열에 그래프 초기값 넣어주기 (아무 정점도 거치지 않았을 때의 길)
		for(int i=0; i<n; i++) {
			for(int j=0; j<n; j++) {
				ans[i][j] = graph[i][j];
			}
		}
		
		// 시작 정점 ~ 거쳐가는 정점 ~ 종료 정점까지 '0'이 없어야 이어지는 길
		// k: 거쳐가는 정점
		for(int k=0; k<n; k++) {
			// i: 시작 정점
			for(int i=0; i<n; i++) {
				// j: 종료 정점
				for(int j=0; j<n; j++) {
					boolean check = true;	// 가는 길에 0이 있는지 체크해 줄 변수 true로 초기화
					// 만약 가는 길에 0이 있다면
					if(ans[i][k]==0 || ans[k][j]==0) {
						check = false;	// false 저장
					}
					// check가 true이면 (0을 만나지 않았으면) 정답 배열에 1 저장
					if (check) {
						ans[i][j] = 1;
					}
				}
			}
		}
		
		// 출력
		for(int i=0; i<n; i++) {
			for(int j=0; j<n; j++) {
				System.out.print(ans[i][j]+" ");
			}
			System.out.println();
		}
		

	}

}
