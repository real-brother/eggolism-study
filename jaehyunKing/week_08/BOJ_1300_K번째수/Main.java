package BOJ_1300_K번째수;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		// N * N에서 overflow가 발생하기 때문에 Long타입으로 선언
		long N = sc.nextLong();
		int K = sc.nextInt();
		
		int left = 1;
		// Math함수를 쓰면 long 형태로 반환해주기 때문에 int로 캐스팅
		int right = (int) Math.min(N*N, Math.pow(10, 9));

		while(left <= right) {
			// k번째를 만족하는 숫자를 이분탐색
			int mid = (left + right) / 2;
			
			int count = 0;
			
			// 1 2 3
			// 2 4 6
			// 3 6 9
			// mid(숫자)가 4이라면 3 + 2 + 1 -> count = 6
			// count는 이 mid가 몇 번 째까지 있는지 확인
			for(int i = 1; i <= N; i++) {
				if(N > mid / i) count += mid / i;
				else count += N;
			}
			
			/* left를 기준으로 생각했다
			 * 예제 3 7 기준으로 count = 7을 찾아야한다
			 * mid가 4, 5면 count = 6
			 * mid가 6, 7, 8이면 count = 8
			 * 
			 * ex) 예제가 3 7이라면? ->
			 * 	   mid(숫자)가 5일 때 count가 6이므로 left는 6이 된다 
			 *     이후 mid는 6보다 크거나 같으므로 count는 8이상이다
			 *     그러므로 left는 6의 자리에서 끝나게 된다
			 *     
			 *     예제가 3 8이라면? -> 위의 글을 한 번 더 읽어보면 똑같다는 것을 알 수 있다
			 *     쉽게(?) 생각하면  mid가 4, 5일 때 count가 6인데, mid가 6일 때 count가 8이다
			 *     -> 숫자 6이 2개가 있다
			 *     찾아보니까 Lower bound라고 한다
			 */
			if(count < K) left = mid + 1;
			else right = mid - 1;
		}
		
		System.out.println(left);
		
	}

}