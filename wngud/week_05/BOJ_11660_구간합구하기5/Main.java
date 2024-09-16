import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());
        int line = Integer.parseInt(st.nextToken());
        int total = Integer.parseInt(st.nextToken());

        // 1. 매트릭스 채우기
        // 왼쪽 위를 기준으로 구간합을 DP적으로 저장
        int[][] matrix = new int[line + 1][line + 1]; // 실제 숫자에 맞추기
        for (int i = 1; i < line + 1; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j < line + 1; j++) {
                int num = Integer.parseInt(st.nextToken());

                matrix[i][j] = num + matrix[i-1][j] + matrix[i][j-1] - matrix[i-1][j-1];
            }
        }

        // 2. 정답 찾기
        // 전체를 더한 후, 양쪽 2개 빼고, 중복 더해주기
        for (int t = 0; t < total; t++) {
            st = new StringTokenizer(br.readLine());
            int r1 = Integer.parseInt(st.nextToken());
            int c1 = Integer.parseInt(st.nextToken());
            int r2 = Integer.parseInt(st.nextToken());
            int c2 = Integer.parseInt(st.nextToken());

            int sum = matrix[r2][c2] - matrix[r2][c1 - 1] - matrix[r1 - 1][c2] + matrix[r1 - 1][c1 - 1];

            sb.append(sum).append('\n');
        }
        System.out.println(sb);

    }
}
