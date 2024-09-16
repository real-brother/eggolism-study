import java.util.Arrays;
import java.util.Scanner;

public class Main {
    // 공유 변수 만들기
    static int[][] graph;
    static int[][] visited;

    static int M;
    static int N;

    // dfs
    static void dfs(int y, int x) {
        int[] dx = {-1,1,0,0};
        int[] dy = {0,0,-1,1};

        visited[y][x] = 1;

        for (int m = 0; m < 4; m++) {
             int nx = x + dx[m];
             int ny = y + dy[m];

             if (nx >= 0 && ny >= 0 && nx < N && ny < M && visited[ny][nx] != 1) {
                 if (graph[ny][nx] == 0) {
                     dfs(ny, nx);
                 }
             }

        }

    }


    // main
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        M = sc.nextInt();
        N = sc.nextInt();
        sc.nextLine(); // dfs는 한 줄로 주기 때문에 자주 이렇게 쓰임!

        graph = new int[M][N];
        visited = new int[M][N]; // 마지막라인을 통해 전류 파악

        // 1. 그래프 채우기
        // charAt을 사용하니 다른 숫자 나옴
        for (int i = 0; i < graph.length; i++) {
            String inputs = sc.nextLine();
            for (int j = 0; j < graph[i].length; j++) {
                int num = inputs.charAt(j) - '0'; // charAt 형변환 조심
                graph[i][j] = num;
            }
        }

        // 2. 그래프 순환하기
        // 위쪽을 기준으로 돌기
        for (int i = 0; i < N; i++) {
            if (graph[0][i] == 0) {
                dfs(0, i);
            }
        }

        // 3. 정답 출력하기
        boolean is_on = false;
        for (int i = 0; i < visited[0].length; i++) {
            if (visited[M-1][i] == 1) is_on = true;
        }

        if (is_on) System.out.println("YES");
        else System.out.println("NO");

    }
}