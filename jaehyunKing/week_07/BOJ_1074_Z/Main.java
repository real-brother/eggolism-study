package BOJ_1074_Z;

import java.util.Scanner;

public class Main {
	static int r, c;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		r = sc.nextInt();
		c = sc.nextInt();
		
		int num = (int) Math.pow(2, N);
		
		divide(0, num-1, 0, num-1, 0, num*num-1);
	}
	
	// x, y start, end는 각각 인덱스, start end num은 숫자를 저장하고 있다
	static void divide(int y_start, int y_end, int x_start, int x_end, int start_num, int end_num) {
		if(y_end == r && x_end == c) {
			System.out.println(end_num);
			return;
		}

		//인덱스의 중간값 계산
		int x_mid = (x_start + x_end) / 2;
		int y_mid = (y_start + y_end) / 2;
		
		// 뒤에 diff * 2, diff * 3에서 오버플로우나서 long으로 선언
		long diff = end_num - start_num;
		
		// y, ㅌ_mid 와 r, c를 대소비교해서 해당하는 사분면으로 이동한다.
		if(r <= y_mid && c <= x_mid) divide(y_start, y_mid, x_start, x_mid, start_num, start_num + (int)(diff / 4));
		else if(r <= y_mid && c > x_mid) divide(y_start, y_mid, x_mid+1, x_end, start_num + (int)(diff / 4) + 1, start_num + (int)(diff * 2 / 4));
		else if(r > y_mid && c <= x_mid) divide(y_mid+1, y_end, x_start, x_mid, start_num + (int)(diff * 2 / 4) + 1, start_num + (int)(diff * 3 / 4));
		else if(r > y_mid && c > x_mid) divide(y_mid+1, y_end, x_mid+1, x_end, start_num + (int)(diff * 3 / 4) + 1, end_num);
	}

}