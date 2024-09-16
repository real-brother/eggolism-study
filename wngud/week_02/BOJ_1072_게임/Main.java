import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {

		// 입력 받기 
		Scanner sc = new Scanner(System.in);
//		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

//		int N = Integer.parseInt(br.readLine());
//		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
//		List<Integer> list1 = new ArrayList<>();

		// 1. 숫자 구하기
		double x = sc.nextInt();
		double y = sc.nextInt();
		
		double z = ((int) ((long) y * 100 / x)); // 형변환 문제
			
		// 2. 이진 탐색
		int answer = -1;
		int pl = 0;
		int pr = 1000000000;
		int pc = 0;
		
		while (pl < pr) {
			pc = (pl + pr) / 2;
			
			if (z+1 <= (int) ((long) (y+pc) * 100 / (x+pc))) {
				pr = pc;
			} else {
				pl = pc+1;			
			}
			
		}
			
		System.out.println(pl);
			

		
	}

}