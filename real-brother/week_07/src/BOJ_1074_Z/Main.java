package BOJ_1074_Z;

import java.util.Scanner;

public class Main {
	static Scanner sc = new Scanner(System.in);
	static int N;
	static int r;
	static int c;
	
    public static void main(String[] args) {
    	N = sc.nextInt();
    	r = sc.nextInt();
    	c = sc.nextInt();
    	
    	int ans = 0;
    	while (N != 0) {
    		N--; // N을 하나씩 줄인다는 것 == 절반으로 나누는 것
    		int sq = (int) Math.pow(2, N);
    		
    		// 어디 사분면에 위치해있는 지 찾고 -> ans, r, c 업데이트
    		// 가장 큰 사각형에서 1/4씩 안으로 들어간다고 생각하면 EASY
    		// 당연히 사각형 들어가면서 ans를 맞춰서 업데이트 해주면됨
    		if (r < sq && c < sq) { // 1사분면
				ans += 0;
			} else if (r < sq && c >= sq) { // 2사분면
				ans += sq * sq * 1;
				c -= sq;
			} else if (r >= sq && c < sq) { // 3사분면
				ans += sq * sq * 2;
				r -= sq;
			} else if (r >= sq && c >= sq) { // 4사분면
				ans += sq * sq * 3;
				r -= sq;
				c -= sq;
			}
    	}
    	System.out.println(ans);
    }
}