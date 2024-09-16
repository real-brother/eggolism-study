import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    /*벽 만드는 기준
     * 1. 완탐 --> 선택
     * 2. 바이러스 기준
     * 3. 벽 기준*/

    /*설계 방식
    * 1. 벽 좌표 3가지를 조합을 통해 구한다.
    * - 1,2는 벽 좌표로 포함시키지 않는 백트래킹을 진행하여 조합을 구한다.
    * - 조합을 구할때 n*m으로 구하고, 인덱스에서 %와 /을 사용함으로써 2차원 배열을 구현한다.
    * 
    * 2. 0의 개수는 전체에서 1과 2를 뺀 수로 구한다.
    * - 처음에 벽 3개를 둘 것이니 미리 추가한다.
    * - matrix를 채울 때 벽이면 count의 개수를 증가시킨다.
    * - 이후에 바이러스 BFS를 할 때 count를 증가시킨다.
    * - 한 매트릭스 탐색을 완료하면, n*m에서 전체 count를 빼서 0의 개수를 찾는다.
    * - Math.max로 최신화 한다.*/

    static int n, m;

    static int[][] matrix;
    static List<int[]> virus;
    static int[] sel;

    static int[] dr = {0, 0, -1, 1};
    static int[] dc = {1, -1, 0, 0};

    static int count, answer, countWall;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        matrix = new int[n][m];
        virus = new ArrayList<>();
        countWall = 3; // 벽 3개 추가할 예정
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                int num = Integer.parseInt(st.nextToken());
                matrix[i][j] = num;

                // 바이러스 리스트에 추가
                if (num == 2) virus.add(new int[]{i, j});
                // 벽의 개수 추가
                if (num == 1) countWall++;
            }
        }

        // 벽 좌표 3개 찾기
        sel = new int[3];
        dfs(0, 0);

        // 정답 출력
        System.out.println(answer);

    }

    // 숫자 1 n*m개로 3개 뽑기
    public static void dfs(int at, int depth) {

        // 조합 완성
        if (depth == 3) {

            // 바이러스 BFS 돌리기
            count = countWall; //  바이러스 퍼지기 전 기본적으로 벽 3개로 시작
            bfs(sel[0] / m, sel[0] % m, sel[1] / m, sel[1] % m, sel[2] / m, sel[2] % m);
            return;
        }

        for (int i = at; i < n * m; i++) {
            // 제외 조건
            // 매트릭스의 번호가 1이나 2이면 조합에 성사되지 않는다.
            if (matrix[i / m][i % m] != 0) continue;

            // 선택
            sel[depth] = i;
            dfs(i + 1, depth + 1);
        }

    }


    public static void bfs(int r1, int c1, int r2, int c2, int r3, int c3) {

        // 매트릭스 채워보기 -> 다시 되돌려야 함
        matrix[r1][c1] = 1;
        matrix[r2][c2] = 1;
        matrix[r3][c3] = 1;

        Queue<int[]> queue = new LinkedList<>();
        int[][] visited = new int[n][m];

        // 바이러스 저장한 곳 모두 bfs 돌리기
        for (int k = 0; k < virus.size(); k++) {
            int[] cur = virus.get(k);

            if (visited[cur[0]][cur[1]] == 1) continue;

            count++;
            queue.add(cur);
            visited[cur[0]][cur[1]] = 1;

            while (!queue.isEmpty()) {
                int[] cur2 = queue.poll();

                for (int s = 0; s < 4; s++) {
                    int nr = cur2[0] + dr[s];
                    int nc = cur2[1] + dc[s];

                    if (nr < 0 || nr >= n || nc < 0 || nc >= m || visited[nr][nc] == 1||matrix[nr][nc] == 1) continue;

//                     matrix[nr][nc] = 1; // 단순히 카운트만 할 수 있겠다.
                    count++; // 추가 1
                    queue.add(new int[]{nr, nc});
                    visited[nr][nc] = 1;
                }
            }
        }

        // 바이러스 전부 돌기 완료
        answer = Math.max(answer, (n*m - count));

        matrix[r1][c1] = 0;
        matrix[r2][c2] = 0;
        matrix[r3][c3] = 0;

    }
}
