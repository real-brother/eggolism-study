package WEEK_23;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_14267_회사문화1 {

	public static void main(String[] args) throws IOException {
		/* 문제) 14267_회사 문화1
		 * 
		 * 상사가 한 직속 부하를 칭찬하면 그 부하의 모든 부하들이 칭찬 받는다.
		 * 칭찬의 정도를 의미하는 수치 또한 부하들에게 똑같이 칭찬 받는다.
		 * 직속 상사와 직속 부하관계에 대해 주어지고, 칭찬에 대한 정보가 주어질 때, 각자 얼마의 칭찬을 받았는지 출력
		 */
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		
		// 인덱스의 상사 번호 저장
		int[] boss = new int [n+1];
		// 칭찬
		int[] sum = new int[n+1];
		
		// 1번은 사장 (-1 값 신경 안쓰기 위해 따로 빼주기)
		Integer.parseInt(st.nextToken());
		
		for(int i=2; i<=n; i++) {
			// i의 상사
			boss[i] = Integer.parseInt(st.nextToken());
		}
		
		
		for(int k=0; k<m; k++) {
			st = new StringTokenizer(br.readLine());
			int i = Integer.parseInt(st.nextToken());	// 직원 번호
			int w = Integer.parseInt(st.nextToken());	// 칭찬의 수치
			
			sum[i] += w;	// 칭찬 합 저장하는 배열에 해당 직원 칭찬 합 더해주기
		}
		
		// 상사의 칭찬 합을 본인 칭찬 합과 더해주기
		for(int i=0; i<=n; i++) {
			sum[i] += sum[boss[i]];
		}
		
		for(int i=1; i<=n; i++) {
			System.out.print(sum[i]+" ");
		}
		

	}

}
