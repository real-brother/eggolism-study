package W8_BOJ_14501_퇴사;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		/* 문제) 퇴사
		 * 오늘부터 N+1일째 되는 날 퇴사를 하기 위해서, 남은 N일 동안 최대한 많은 상담을 하려고 한다.
		 * 상담을 완료하는데 걸리는 기간 Ti와 상담을 했을 때 받을 수 있는 금액 Pi
		 * 얻을 수 있는 최대 수익
		 */
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		
		int[] t = new int[n+1];
		int[] p = new int[n+1];
		int[] dp = new int[n+1];	// dp 배열 생성
		
		for(int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			t[i] = Integer.parseInt(st.nextToken());
			p[i] = Integer.parseInt(st.nextToken());
		}
		
		// 상담을 완료하는 날의 값에 그 날의 dp값과 현재 dp값+해당 상담 금액 중 최대값을 저장해준다. 
		for(int i=0; i<n; i++) {
			if (i+t[i] <= n) {
				dp[i+t[i]] = Math.max(dp[i+t[i]], dp[i]+p[i]);
			}
			// 누적하여 저장해주기 위해 이전 값과 비교하여 최대값 넣어주기
			dp[i+1] = Math.max(dp[i+1], dp[i]);
		}
		
		System.out.println(dp[n]);

	}

}
