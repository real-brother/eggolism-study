import java.util.Arrays;
import java.util.Scanner;

public class Main {
    // 공유 변수 만들기
    static int[][] graph;
    static int[][] visited;

    static int M;
    static int N;

    // dfs 돌리기
    static void dfs(int y, int x) {
        int[] dx = {-1,1,0,0,1,1,-1,-1}; // 상하좌우 + 대각선
        int[] dy = {0,0,-1,1,-1,1,1,-1};

        visited[y][x] = 1;

        // 8번 탐색해야 함
        for (int m = 0; m < 8; m++) {
             int nx = x + dx[m];
             int ny = y + dy[m];

             if (nx >= 0 && ny >= 0 && nx < N && ny < M && visited[ny][nx] != 1) {
                 if (graph[ny][nx] == 1) {
                     dfs(ny, nx);
                 }
             }

        }

    }


    // main
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);


        while (true) {

            // 순서 바뀜 주의 -> 바꿨으니 고려사항 X
            N = sc.nextInt();
            M = sc.nextInt();
            sc.nextLine();

            // 종료 조건
            if(M == 0 && N == 0) break;

            graph = new int[M][N];
            visited = new int[M][N];

            // 1. 그래프 채우기
            for (int i = 0; i < graph.length; i++) {
                String[] inputs = sc.nextLine().split(" ");
                for (int j = 0; j < graph[i].length; j++) {
                    int num = Integer.parseInt(inputs[j]);
                    graph[i][j] = num;
                }
            }

            // 2. 그래프 순환하기
            int count = 0;
            for (int i = 0; i < graph.length; i++) {
                for (int j = 0; j < graph[i].length; j++) {
                    if (visited[i][j] != 1 && graph[i][j] == 1) {
                        count++;
                        dfs(i,j);
                    }
                }
            }

            // 3. 정답 출력하기
            System.out.println(count);

        } // while 종료

    }
}