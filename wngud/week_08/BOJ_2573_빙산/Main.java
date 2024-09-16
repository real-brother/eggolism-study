import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    /*설계방식
    * 1. 상하좌우 동시성 문제가 발생하지 않게 0의 개수를 찾아 빙산 녹임
    * 2. BFS 돌면서 나눠져 있는 것 찾기
    * 3. 나눠져있는게 2 이상이면 끝내고 반복한 횟수 출력
    * */

    /*실수
    * 1. 2차원 배열 clone을 얕은 복사 해버림
    * 2. bfs 파라미터와 static 변수의 이름이 중복
    * ... 자고 싶어서 급하게 풀려했더니 별일이 다 있네 오히려 늦었어...*/
    
    static int[][] matrix;
    static int[][] visited;

    static int r, c;
    static int divide;

    static int[] dr = {0, 0, 1, -1};
    static int[] dc = {1, -1, 0, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        // 매트릭스 받기
        matrix = new int[r][c];

        for (int i = 0; i < r; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < c; j++) {
                int num = Integer.parseInt(st.nextToken());
                matrix[i][j] = num;
            }
        }

        int answer = 0;
        boolean is_on = true; // divied == 0인 경우 중단하기 위해
        // 1. 빙산 녹이기
        // 나누는 것이 2개 이상이면 끝남
        // 끝까지 2개 이상으로 나눠지지 않는 예외 -> divide == 0
        // divied는 0 또는 1 또는 2 이상
        while (divide < 2 && is_on) {
            melting();

            // 2. BFS 돌리기
            visited = new int[r][c];
            divide = 0;

            for (int i = 0; i < r; i++) {
                for (int j = 0; j < c; j++) {
                    if (visited[i][j]==1 || matrix[i][j]==0) {
                        continue;
                    }
                    bfs(i, j);
                    divide++;
                }
            }

            // 한번 녹음
            answer++;

            // 아예 나눠지지 않는 경우
            if (divide == 0) {
                is_on = false;
                answer = 0;
            }
        }

        // 3. 결과 출력하기
        System.out.println(answer);

    }

    // 동시성 문제 조심
    public static void melting() {
//        int[][] matrix2 = matrix.clone(); // 얕은 복사

        // 깊은 복사
        int[][] matrix2 = new int[r][c];
        for (int i = 0; i < matrix.length; i++) {
            matrix2[i] = matrix[i].clone();
        }

        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (matrix[i][j] == 0) continue;

                // 한개 시작
                int count = 0;
                for (int k = 0; k < 4; k++) {

                     int nr = i + dr[k];
                     int nc = j + dc[k];

                     // 주변에 0이 있으면 추가해줘야 한다.
                     if (nr<0||nr>=r||nc<0||nc>=c) continue;
                     if (matrix[nr][nc]==0) {
                         count++;
                     }

                }
                // 4방향 탐색 완료
                matrix2[i][j] = Math.max(matrix[i][j] - count, 0); // 음수 방지
            }
        }

        // 전체 녹이기 완료
        matrix = matrix2;
    }

    // static이랑 메서드 파라미터랑 동일했었음...
    public static void bfs(int startR, int startC) {

        Queue<int[]> queue = new LinkedList<>(); // 위에서 추가해도 됨
        queue.add(new int[]{startR, startC});
        visited[startR][startC] = 1;

        while (!queue.isEmpty()) {

            int[] cur = queue.poll();

            for (int k = 0; k < 4; k++) {
                int nr = cur[0] + dr[k];
                int nc = cur[1] + dc[k];

                // 방문했거나 0은 무시해야 한다.
                if (nr < 0 || nr >= r || nc < 0 || nc >= c || visited[nr][nc] == 1 || matrix[nr][nc] == 0) continue;

                queue.add(new int[]{nr, nc});
                visited[nr][nc] = 1;
            }

        }

    }
}