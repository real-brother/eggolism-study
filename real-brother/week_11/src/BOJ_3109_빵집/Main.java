package BOJ_3109_빵집;

import java.util.Scanner;

public class Main {
	static Scanner sc = new Scanner(System.in);

	static char[][] breadMap;
	static int r;
	static int c;
	static int val;

	public static void main(String[] args) {
		r = sc.nextInt();
		c = sc.nextInt();
		sc.nextLine();

		breadMap = new char[r][c];
		for (int i = 0; i < r; i++) {
    		String temp = sc.nextLine();
			for (int j = 0; j < c; j++) {
				breadMap[i][j] = temp.charAt(j);
			}
		}

		for (int i = 0; i < r; i++)
			if (check(i, 0))
				val++;
		System.out.println(val);
	}
	
	// 재귀적으로 관을 놓아보면서 된다면 계속 놓기
	// 오른쪽 위, 오른쪽, 오른쪽 아래 순서로 탐색하며 재귀를 호출하고,
	// 탐색하는 길은 체크를 해 놓고, 탐색 실패 후에 다시 체크 해제를 하지 않아도됨.
	
	// 탐색 성공한 길은 이미 파이프가 설치되므로 체크 해제를 하지 않고,
    // 탐색 실패한 길은 다른 파이프가 탐색해도 실패이므로 체크 해제를 하지 않는 것.
	public static boolean check(int x, int y) {
		breadMap[x][y] = '-'; // 관이 놓여져 있다는 표시

		if (y == c - 1) // 마지막 열(원웅이 빵집)에 도착했으면
			return true;

		if (x > 0 && breadMap[x - 1][y + 1] == '.') // 오른쪽 위
			if (check(x - 1, y + 1))
				return true;
		if (breadMap[x][y + 1] == '.') // 오른쪽
			if (check(x, y + 1))
				return true;
		if (x + 1 < r && breadMap[x + 1][y + 1] == '.') // 오른쪽 아래
			if (check(x + 1, y + 1))
				return true;
		return false;
	}
}