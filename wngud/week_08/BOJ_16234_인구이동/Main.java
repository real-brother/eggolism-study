import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int[][] matrix;
    static int[][] newMatrix;
    static int[][] visited;

    static int N, L, R, is_on;

    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {-0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        // 매트릭스 받기
        matrix = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                int num = Integer.parseInt(st.nextToken());
                ;
                matrix[i][j] = num;
            }
        }

        // 1. BFS 돌기
        // visited가 아닌지 확인하며 전체 돌기
        // 매트릭스 전체 순회를 인구 이동이 없는 경우 전까지 새롭게 반복해야 함 (day)
        // 인구 이동이 없는 경우는 모두 것의 차이가 L과 R 사이가 없는 것 -> 어떻게 없는 것을 찾을까.
        // -> BFS를 전체 다 돌았으면 이동한 것이 없는 경우니 종료
        // -> 전부 이동하는 예외 발생 -> union(size)가 1인 경우의 횟수로 변경
        int day = 0; // day0 처리 조심

        // days 시작
        while (true) {
            newMatrix = new int[N][N]; // 새로운 메모리 할당
            visited = new int[N][N];
            is_on = 0;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (visited[i][j] == 1) continue;
                    bfs(i, j);
                }
            }

            // 하루 끝
            matrix = newMatrix; // 새로운 할당!
            // BFS를 전체 다 돌았으면서 동시에 union의 사이즈가 1인 경우의 수가 전체이면 변경X 상황임
            if (is_on == N * N) {
                break;
            } else {
                day++;
            }
        } // while 완료

        // 2. 정답 출력하기
        System.out.println(day);
    }


    public static void bfs(int r, int c) {

        int union = 0;
        int unionPopulation = 0;
        List<int[]> memory = new ArrayList<>(); // 인접한 매트릭스 기록용

        // 초기 큐에 넣기
        Queue<int[]> queue = new LinkedList<>(); // 추가
        queue.add(new int[]{r, c});
        memory.add(new int[]{r, c});
        visited[r][c] = 1;
        unionPopulation += matrix[r][c];
        union++;

        // bfs 돌리기
        // 한번 끝나면 day 추가
        while (!queue.isEmpty()) {

            int[] cur = queue.poll();

            // 4방향 탐색
            for (int i = 0; i < 4; i++) {
                int nr = cur[0] + dr[i];
                int nc = cur[1] + dc[i];

                if (nr < 0 || nr >= N || nc < 0 || nc >= N || visited[nr][nc] == 1) continue;

                int dispNum = Math.abs(matrix[nr][nc] - matrix[cur[0]][cur[1]]);
                if (dispNum < L || dispNum > R) continue;

                queue.add(new int[]{nr, nc}); // 추가
                memory.add(new int[]{nr, nc});
                visited[nr][nc] = 1;
                unionPopulation += matrix[nr][nc];
                union++;
            }

        } // 인접한 한 구역 완료

        // 인접한 구역 인구 최신화
        // 미리 값을 할당해버리면 이후 bfs에 동시성 문제 발생 --> 임시로 메트릭스 데이터 저장하는 효율적인 방법 찾기!!
        int resize = unionPopulation / union;
        if (union == 1) is_on++; // 전체를 한 단위로만 돌고 union들이 1만 있어 인구이동이 없는 횟수 저장

        for (int i = 0; i < memory.size(); i++) {
            int nr = memory.get(i)[0];
            int nc = memory.get(i)[1];

            newMatrix[nr][nc] = resize;
        }
    }


}