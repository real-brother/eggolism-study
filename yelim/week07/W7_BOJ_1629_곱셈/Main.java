package W7_BOJ_1629_곱셈;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static long a;
	static long b;
	static long c;

	public static void main(String[] args) throws IOException {
		/* 문제)
		 * 자연수 A를 B번 곱한 수를 알고 싶다. 
		 * 단 구하려는 수가 매우 커질 수 있으므로 이를 C로 나눈 나머지를 구하는 프로그램을 작성하시오.
		 */
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		a = Long.parseLong(st.nextToken());
		b = Long.parseLong(st.nextToken());
		c = Long.parseLong(st.nextToken());
		
		System.out.println(pow(a, b, c));
		
	}
	
	// 제곱 구하는 메소드
	static long pow(long a, long b, long c) {
		// b가 1이면 c로 나눈 나머지 그대로 리턴
		if (b==1) {
			return a%c;
		}
		
		// 짝수이면 b를 2로 나눠서 재귀
		if (b%2 == 0) {
			long num = pow(a, b/2, c);
			return (num*num)%c;
		}
		// 홀수이면 b-1을 2로 나눠서 재귀, a 한 번 더 곱해주기
		else {
			long num = pow(a, (b-1)/2, c);
			return ((num*num)%c*a)%c;
			// num*num이 long 범위를 벗어나는 경우가 있는 듯. 여기서 한번 나머지 계산 해줘야 함..
		}
	}

}
