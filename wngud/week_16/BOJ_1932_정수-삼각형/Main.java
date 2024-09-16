import java.util.*;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();

        int[][] matrix = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j <= i; j++) {
                int num = sc.nextInt();
                matrix[i][j] = num;
            }
        }

        // 출력해보기
//        for (int i = 0; i < N; i++) {
//            System.out.println(Arrays.toString(matrix[i]));
//        }

        // 바텀업 (맨위는 X)
        for (int i = N-1; i > 0; i--) {

            // 한 행
            for (int j = 0; j < i; j++) {
                matrix[i-1][j] += Math.max(matrix[i][j], matrix[i][j+1]);
            }
        }

        // 출력해보기
//        for (int i = 0; i < N; i++) {
//            System.out.println(Arrays.toString(matrix[i]));
//        }

        // 정답 출력
        System.out.println(matrix[0][0]);

    }
}
