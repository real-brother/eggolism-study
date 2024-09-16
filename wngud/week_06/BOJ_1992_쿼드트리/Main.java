import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int[][] matrix;
    static StringBuilder sb;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        // 매트릭스 채우기
        // 전부 같은 경우 예외
        matrix = new int[n][n];
        for (int i = 0; i < n; i++) {
            String input = br.readLine();
            for (int j = 0; j < n; j++) {
                int num = input.charAt(j) - '0';
                matrix[i][j] = num;
            }
        }

        // DFS
        sb = new StringBuilder();
        dfs(n, 0, 0, 0, 0);

        // 정답 출력하기
        System.out.println(sb);
    }


    public static void dfs(int length, int r, int c, int count1, int count2) {

        if (length == 1) {
            if (count1 >= 1) {
                for (int i = 0; i < count1; i++) {
                    sb.append("(");
                }
            }

            sb.append(matrix[r][c]);


            if (count2 >= 1) {
                for (int i = 0; i < count2; i++) {
                    sb.append(")");
                }
            }
            return;
        }

        // 재귀
        int standard = matrix[r][c];
        for (int i = r; i < r + length; i++) {
            for (int j = c; j < c + length; j++) {
                if (matrix[i][j] != standard) {
                    // 4개 영역 나눠서 재귀
                    dfs(length / 2, r, c, count1 + 1, 0);
                    dfs(length / 2, r, c + length / 2, 0, 0);
                    dfs(length / 2, r + length / 2, c, 0, 0);
                    dfs(length / 2, r + length / 2, c + length / 2, 0, count2 + 1);
                    return;
                }
            }
        }
//        System.out.println("length = " + length + ", r = " + r + ", c = " + c + ", count = " + count);
//        System.out.println("일반 추가");

        if (count1 >= 1) {
            for (int i = 0; i < count1; i++) {
                sb.append("(");
            }
        }

        sb.append(standard);

        if (count2 >= 1) {
            for (int i = 0; i < count2; i++) {
                sb.append(")");
            }
        }


    }

}