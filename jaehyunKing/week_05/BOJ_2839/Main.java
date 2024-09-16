package BOJ_2839;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
    
		int five_num = 0;
		int three_num = 0;
    
		for(int i = 0; 5 * i <= N; i++) {
			if(((N - 5 * i) % 3) == 0) {
				five_num = i;
				three_num = ((N - 5 * i) / 3);
			}
		}
        if(five_num == 0 && three_num == 0)System.out.println("-1");
        else System.out.println(five_num + three_num);
	}
}