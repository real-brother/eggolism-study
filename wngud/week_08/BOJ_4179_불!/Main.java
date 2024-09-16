import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    /*문제점
     * 1. while(!queue.isEmpty)왜 안했냐 -> 순간 한칸 간다는 것을 큐를 한번만 함
     * 2. fire은 완탐해서 시간 복잡도 증가 -> 큐 2개로
     * 3. 불을 먼저가다 보니, 사람이 가장자리에 있는 경우 예외
     * 4. n과 m이 1인 경우 예외*/
    
    static String[][] matrix;
    static int[][] visited;
    static Queue<int[]> personIdx;
    static Queue<int[]> fireIdx;

    static int n, m;
    static boolean finished;
    static int[] dr = {1, -1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        matrix = new String[n][m];
        visited = new int[n][m];
        personIdx = new LinkedList<>();
        fireIdx = new LinkedList<>();
        // 매트릭스 채우기
        for (int i = 0; i < n; i++) {
            String inputs = br.readLine();
            for (int j = 0; j < m; j++) {
                String input = inputs.substring(j, j + 1);
                matrix[i][j] = input;

                // 불 위치 저장
                if (input.equals("F")) {
                    fireIdx.add(new int[]{i, j});
                }
                // 사람 위치 저장
                if (input.equals("J")) {
                    personIdx.add(new int[]{i, j});
                }

            }
        }

        finished = false;
        int answer = 0;
        // 가장 자리로 바로 끝나는 예외
        int[] cur = personIdx.peek();
        if (cur[0] == 0 || cur[0] == n - 1 || cur[1] == 0 || cur[1] == m - 1) {
            finished = true;
        }
        
        // 시작
        // IMPOSSIBLE의 예외 -> 갈 곳이 없는 경우 -> 큐가 빈 경우
        while (!finished) {
            // 1. 불 먼저 이동
            fire();

            // 2. 사람 가능한 이동 위치 이동
            bfs();

            // 하나의 min 끝
            answer++;

            // 가장자리 위치 추가 한게 있어야 함
            if (personIdx.isEmpty()) {
                finished = true;
                answer = -1;
            }
        }

        // 정답 출력하기
        if (n == 1 && m == 1) {
            System.out.println(1);
        } else if (answer >= 0) {
            System.out.println(answer + 1); // 마지막 밖으로 나가기
        } else {
            System.out.println("IMPOSSIBLE");
        }
    }

    public static void fire() {

        int rotation = fireIdx.size();
        for (int g = 0; g < rotation; g++) {
            // 바로 불이 오는 경우 조심
            int[] cur = fireIdx.poll();
            visited[cur[0]][cur[1]] = 1;

            for (int k = 0; k < 4; k++) {

                int nr = cur[0] + dr[k];
                int nc = cur[1] + dc[k];

                // 불이거나 벽이거나 방문했거나
                if (nr < 0 || nr >= n || nc < 0 || nc >= m || visited[nr][nc] == 1
                        || matrix[nr][nc].equals("F") || matrix[nr][nc].equals("#")) continue;

                matrix[nr][nc] = "F";
                fireIdx.add(new int[]{nr, nc});
                visited[nr][nc] = 1;

            } // 4번 끝

        }
    }

    public static void bfs() {

        int rotation = personIdx.size();
        for (int g = 0; g < rotation; g++) {
            // 바로 불이 오는 경우 조심
            int[] cur = personIdx.poll();
            visited[cur[0]][cur[1]] = 1;

            for (int k = 0; k < 4; k++) {

                int nr = cur[0] + dr[k];
                int nc = cur[1] + dc[k];

                // 불이거나 벽이거나 방문했거나
                if (nr < 0 || nr >= n || nc < 0 || nc >= m || visited[nr][nc] == 1
                        || matrix[nr][nc].equals("F") || matrix[nr][nc].equals("#")) continue;

                // 가장자리 도착 (n과 m 조심!!!)
                if (nr == 0 || nr == n - 1 || nc == 0 || nc == m - 1) {
                    finished = true;
                }

                matrix[nr][nc] = "J";
                personIdx.add(new int[]{nr, nc});
                visited[nr][nc] = 1;

            } // 4번 끝

        }
    }
}