import java.lang.reflect.Array;
import java.util.*;

public class Main {

    /**
     * 디버깅
     * 1. 벽인데, 안가진 곳일 수 있음!
     */

    static int[][] matrix, answers;
    static int N, M;

    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        M = sc.nextInt();

        matrix = new int[N][M];
        answers = new int[N][M];
        int[] target = new int[2];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                int num = sc.nextInt();
                matrix[i][j] = num;

                // 2 찾기
                if (num == 2) {
                    target[0] = i;
                    target[1] = j;
                }

                // 벽은 미리 체크하기
                if (num == 0) {
                    answers[i][j] = -1;
                }
            }
        }

        // BFS
        bfs(target[0], target[1]);

        // -2(시작) -> 0, -1(벽) -> 0, 0 -> -1;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {

                if (answers[i][j] == -1 || answers[i][j] == -2) {
                    sb.append(0).append(" ");
                } else if (answers[i][j] == 0) {
                    sb.append(-1).append(" ");
                } else {
                    sb.append(answers[i][j]).append(" ");
                }
            }
            sb.append('\n');
        }

        System.out.println(sb);


    }

    public static void bfs (int r, int c) {

        Queue<int[]> queue = new LinkedList<>();
        int[][] visited = new int[N][M];

        queue.add(new int[]{r, c, 0}); // 0부터 시작
        visited[r][c] = 1;
        answers[r][c] = -2; // 처음 시작은 -2

        while (!queue.isEmpty()) {

            int[] cur = queue.poll();

            for (int i = 0; i < 4; i++) {
                int nr = cur[0] + dr[i];
                int nc = cur[1] + dc[i];

                if (nr < 0  || nr >= N || nc < 0 || nc >= M ||
                        visited[nr][nc] != 0 || matrix[nr][nc] == 0)continue;

                // 로직
                queue.add(new int[] {nr, nc, cur[2] + 1});
                visited[nr][nc] = 1;
                answers[nr][nc] = cur[2] + 1;
            }


        }


    }


}
