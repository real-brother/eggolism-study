package BOJ_1629_곱셈;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;
 
public class Main {
	static long a;
	static long b;
	static long c;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		a = Long.parseLong(st.nextToken());
		b = Long.parseLong(st.nextToken());
		c = Long.parseLong(st.nextToken());
		
		System.out.println(pow(a, b));
	}
	
	public static long pow(long A, long exponent) {
		// 지수가 1일 경우 A를 그대로 리턴
		if(exponent == 1) return A % c;
		
		// 지수의 절반에 해당하는 값으로 분할
		long temp = pow(A, exponent / 2);
		
		// 현재 지수가 홀수라면 A^(e / 2) * A^(e / 2) * A 로 분할
		if(exponent % 2 == 1) return (temp * temp % c) * A % c;
		
		return temp * temp % c;
	}
}