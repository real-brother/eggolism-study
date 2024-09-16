import java.io.IOException;
import java.util.Scanner;

public class Main {

		/*설계방식
		* 완전탐색으로 한칸씩 가면서 모든 경우의 수 고려
		*/

    static int[][] matrix;
    static int[] visited;
    static int n, answer;

    public static void main(String[] args) throws IOException {

        Scanner sc = new Scanner(System.in);

        // 입력 받기
        n = sc.nextInt();

        matrix = new int[n][2];
        for (int i = 0; i < n; i++) {
            matrix[i][0] = sc.nextInt();
            matrix[i][1] = sc.nextInt();
        }

        // DFS
        answer = 0;
        visited = new int[n];
        dfs(0, 0);

        // 정답 출력하기
        System.out.println(answer);


    }

    static void dfs(int idx, int price) {

        // n-1인데 마지막이 1인 경우 예외
        if (idx == n-1 && matrix[idx][0]==1) {
            answer = Math.max(answer, price+matrix[idx][1]);
            return;
        }
        // n까지 가능
        else if (idx == n) {
            answer = Math.max(answer, price);
            return;
        }
        else if (idx > n) {
            return;
        }
        answer = Math.max(answer, price);

        // dfs
        dfs(idx + matrix[idx][0], price + matrix[idx][1]);
        dfs(idx + 1, price);

    }
}