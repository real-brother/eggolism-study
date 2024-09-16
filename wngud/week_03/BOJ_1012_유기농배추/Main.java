import java.util.Arrays;
import java.util.Scanner;

public class Main {
    // 변수 설정하기
    static int[][] graph;
    static int[][] visited;

    static int M;
    static int N;
    static int start;

    static int count;
    static int[] dfs_list;

    static void dfs(int y, int x) {
        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};
        
        visited[y][x] = 1;

        // 사방향 탐색
        for (int j = 0; j < 4; j++) {
            int nx = x + dx[j];
            int ny = y + dy[j];
            // 여기서도 추가해줘야 함
            // 안해주면 조건없이 재귀가 됨
            if (graph[ny][nx] == 1 && visited[ny][nx] == 0) {
                dfs(ny, nx);
            }

        }
    }


    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int Test = sc.nextInt();

        for (int t = 0; t < Test; t++) {

        // 0. 입력값 받기
        M = sc.nextInt();
        N = sc.nextInt();
        int K = sc.nextInt();

        graph = new int[N + 2][M + 2]; // 여유롭게
        visited = new int[N + 2][M + 2];

        count = 0;

        // 1. 그래프 만들기
        for (int i = 0; i < K; i++) {
            int x = sc.nextInt();
            int y = sc.nextInt();
            graph[y + 1][x + 1] += 1; // 0행과 0열이 자동으로 0으로 경계
        }
        
        // 2. DFS 탐색하기
        // 1부터 시작해도 됨 ~ 끝은 +1
        for (int y = 1; y < N + 1; y++) {
            for (int x = 1; x < M + 1; x++) {

                if (graph[y][x] == 1 && visited[y][x] == 0) {
                    dfs(y, x);
                    count++;
                }
            }
        }

        // 3. 정답 출력하기
        System.out.println(count);

        }
    }

}
