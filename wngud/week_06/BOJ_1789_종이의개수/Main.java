import java.util.Arrays;
import java.util.Scanner;

public class Main {
    static int[][] matrix;
    static int[] answers;

    static StringBuilder sb;

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        matrix = new int[n][n];

        // 매트릭스 채우기
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                matrix[i][j] = sc.nextInt();
            }
        }

        // 재귀 돌리기
        answers = new int[3];
        dfs(n, 0, 0);

        // 결과 출력하기
        for (int i = 0; i < 3; i++) {
            System.out.println(answers[i]);

        }

    }


    // 파라미터로, 길이와 시작 rc를 넣어줌
    public static void dfs(int num, int r, int c) {

        // 기저 조건
        if (num == 1) {
            // -1 0 1 을 인덱스 0 1 2로 변환하여 저장
            answers[matrix[r][c]+1] += 1;
            return;
        }

        // 주어진 것 탐색 재귀
        // 기준점(standard)을 만들고 다른 것이 하나라도 있으면 9개로 재귀 돌림
        int standard = matrix[r][c];
        for (int i = r; i < r+num; i++) {
            for (int j = c; j < c+num; j++) {
                // 다른 것 존재시 9개로 넣음
                // 각각 좌표값을 넣어줌
                if(matrix[i][j] != standard) {
                    for (int k = 0; k < num; k += num / 3) {
                        for (int s = 0; s < num; s += num / 3) {
                            dfs(num / 3,r+k, c+s);
                        }
                    }
                    // 다른 것이 존재하니 이 재귀는 더 이상 돌지 않게 함
                    return;
                }
            }
        }

        // return 되지 않으면
        // -1 0 1 을 인덱스 0 1 2로 변환하여 저장
        answers[standard+1] += 1;


    }
}