package BOJ_1629_곱셈;

import java.util.Scanner;

public class Main {
	static int C;
	
	public static void main(String[] args) {
	
	Scanner sc = new Scanner(System.in);
	
	int A = sc.nextInt();
	int B = sc.nextInt();
	C = sc.nextInt();
	
	// A의 나머지를 가지고 간다
	A %= C;
	
	System.out.println(pow(A, B));
	}
	
	static long pow(int num, int count) {
		// 1이면 num을 반환 num == A % C
		if(count == 1) return num;
		
		// 매 연산 이후 항상 C로 나눠준다
		long tmp = pow(num, count/2) % C;
		
		// 짝수면 tmp를 2번 곱하기 (수업 때 배운 거)
		if(count % 2 == 0) {
			return tmp * tmp % C;
		}
		// 홀수면 tmp를 2번 곱하고 num을 곱해준다
		else {
			return tmp * tmp % C * num % C;
		}
	}
	
}