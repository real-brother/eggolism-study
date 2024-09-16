package BOJ_1541;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String S = sc.next();
		String[] num_plus = S.split("-");
		
		int sum = 0;
		
		for(int i = 0; i < num_plus.length; i++) {
			String[] num = num_plus[i].split("\\+");
			if(i == 0) {
				for(int j = 0; j < num.length; j++) {
					sum += Integer.parseInt(num[j]);
				}
			}
			else {
				for(int j = 0; j < num.length; j++) {
					sum -= Integer.parseInt(num[j]);
				}
			}
		}
		System.out.println(sum);
	}
}

//처음 나오는 - 이후로는 다 빼기 취급
//split("-")해서 넣은 배열의 0번인덱스를 제외하고는 다 빼주면됨
//split("+") -> 에러 -> Split("\\+")로 해야됨