package BOJ_1072_게임;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(System.in);
		int x = sc.nextInt();
		int y = sc.nextInt();
		sc.nextLine();

		int initOdds = getPercent(x,y);

		int start = 0;
		int end = Integer.MAX_VALUE;
		
		if (initOdds >= 99) {
			System.out.println(-1);
		} else {
			out: 
			while (start < end) {
				int mid = (end + start) / 2;
				int test_x = x + mid;
				int test_y = y + mid;
				int odds = getPercent(test_x, test_y);

				if (odds > initOdds) {
					end = mid;
				} else {
					start = mid + 1;
				}
			}
			
			System.out.println(end);
		}
	}
	
	static int getPercent(int x, int y) {
        return (int) ((double) y * 100 / x);
    }

}
