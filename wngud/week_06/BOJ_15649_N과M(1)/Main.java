import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main2 {
	static int[] sel;
	static int[] visited;
	static int n, m;

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		n = sc.nextInt();
		m = sc.nextInt();

		// 재귀 출력
		sel = new int[m];
		visited = new int[n];

		dfs(0);

	}

	public static void dfs(int sidx) {

		if (sidx == m) {
			for (int i = 0; i < m; i++) {
				System.out.print(sel[i] + " ");
			}
			System.out.println();
			return;
		}

		// 전체를 순회
		for (int i = 0; i < n; i++) {
			if (visited[i] == 0) {
				visited[i] = 1;
				sel[sidx] = i + 1;
				dfs(sidx + 1);
				
				// visited 초기화
				visited[i] = 0;

			}
		}

	}

}