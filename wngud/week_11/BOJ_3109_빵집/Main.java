import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    /*디버깅
    * 1. 도달한 이후 마지막 칸에서 시도라도 해서 visited를 남김
    * - complete로 성공 이후 마무리 지을 때 rc = 0으로 visited 만들기
    * 2. 시간초과
    * - 최적의 하나만 찾으면 되니까 visited[][] = 0은 불필요
    * - 계속 아래서 쌓이면서 탐색하기 때문에 가능*/

    static int[][] matrix;
    static int[][] visited;

    static int R, C;
    static int answer;
    static boolean complete;

    static int[] dr = {1, 0, -1}; // 하중상
    static int[] dc = {1, 1, 1};

    public static void main(String[] args) throws IOException {

//        Scanner sc = new Scanner(System.in);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        matrix = new int[R][C];
        for (int i = 0; i < R; i++) {
            String inputs = br.readLine();
            for (int j = 0; j < C; j++) {
                char input = inputs.charAt(j);
                if (input == '.') {
                    matrix[i][j] = 0;
                } else if (input == 'x') {
                    matrix[i][j] = 1;
                }
            }
        }


        // 아래서부터 파이프라인 돌리기
        answer = 1;

        visited = new int[R][C];
        for (int i = R-1; i >= 0; i--) {
            complete = false;
            dfs(i, 0);

        }

        // 정답 출력하기
        System.out.println(answer-1);

    }


    public static void dfs(int r, int c) {

        // 도달한 이후 나머지는 실행하지 않기
        if (complete) {
            visited[r][c] = 0; // 시도라도 한 visited는 제거!!
            return;
        }

        // 끝까지 도달한 경우
        if (c == C - 1) {
            answer++;
            complete = true;

            return;
        }

        for (int i = 0; i < 3; i++) {

            int nr = r + dr[i];
            int nc = c + dc[i];

            // 벽과 이전에 방문했으면 통과 불과
            if (nr < 0 || nr >= R || nc < 0 || nc >= C ||
                    matrix[nr][nc] == 1 || visited[nr][nc] != 0) continue;

            visited[nr][nc] = answer;
            dfs(nr, nc);
//            visited[nr][nc] = 0; // 최적의 하나만 찾으면 된다.

        }
    }

}
