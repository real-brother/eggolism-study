import java.lang.reflect.Array;
import java.util.*;

public class Main {
    /*디버깅...
    * 1. 모서리 처리
    * 2. 동시성 문제
     */

    static int[][] matrix;
    static int[][] matrix2;
    static int[][] info;
    static int[] sel;
    static int[] visited;

    static int N,M,K, answer;

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        M = sc.nextInt();
        K = sc.nextInt();

        matrix2 = new int[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                int num = sc.nextInt();
                matrix2[i][j] = num;
            }
        }

        info = new int[K][3];
        for (int i = 0; i < K; i++) {
            info[i][0] = sc.nextInt();
            info[i][1] = sc.nextInt();
            info[i][2] = sc.nextInt();
        }

        // 조합 만들기
        answer = Integer.MAX_VALUE;

        sel = new int[K];
        visited = new int[K];
        game(0);

        // 정답 출력하기
        System.out.println(answer);

    }

    public static void game(int idx) {

        if (idx == K) {
//            System.out.println(Arrays.toString(sel));

            // 깊은 복사
            matrix = new int[N][M];
            for (int i = 0; i < N; i++) {
                matrix[i] = matrix2[i].clone();
            }

            // 1. 회전하기
            for (int i = 0; i < K; i++) {
                int cenR = info[sel[i]][0]-1; // 숫자 조심
                int cenC = info[sel[i]][1]-1;
                int total = info[sel[i]][2];


                int startR = cenR - total;
                int startC = cenC - total;
                int len = total * 2 + 1;

                while (len != 1) {

                    circle(startR, startC, len);
                    startR += 1;
                    startC += 1;
                    len -= 2;

                }

//                System.out.println();
//                for (int j = 0; j < N; j++) {
//                    System.out.println(Arrays.toString(matrix[j]));
//                }
            }

            // 2. 최소 행 값 구하기
            for (int i = 0; i < N; i++) {
                int sum = 0;
                for (int j = 0; j < M; j++) {
                    sum += matrix[i][j];
                }
                answer = Math.min(answer, sum);
            }
            return;
        }


        // 조합 만들기
        for (int i = 0; i < K; i++) {
            if (visited[i] != 1) {
                visited[i] = 1;
                sel[idx] = i;
                game(idx+1);

                visited[i] = 0;
            }
        }

    }

    // 하드코딩 안하는 방법?
    public static void circle(int r, int c, int len) {

        int[] tmp = new int[4]; // 모서리용
        tmp[0] = matrix[r][c+len-1];
        tmp[1] = matrix[r+len-1][c+len-1];
        tmp[2] = matrix[r+len-1][c];
        tmp[3] = matrix[r][c];

        // 상
        for (int i = 0; i < len-2; i++) {
            matrix[r][c+len-1-i] = matrix[r][c+len-1-(i+1)];
        }

        // 우
        for (int i = 0; i < len-2; i++) {
            matrix[r+len-1-i][c+len-1] = matrix[r+len-1-(i+1)][c+len-1];
        }

        // 하
        for (int i = 0; i < len-2; i++) {
            matrix[r+len-1][c+i] = matrix[r+len-1][c+1+i];
        }

        // 좌
        for (int i = 0; i < len-2; i++) {
            matrix[r+i][c] = matrix[r+1+i][c];
        }

        // 모서리 처리
        matrix[r+1][c+len-1] = tmp[0];
        matrix[r+len-1][c+len-2] = tmp[1];
        matrix[r+len-2][c] = tmp[2];
        matrix[r][c+1] = tmp[3];

    }

}
