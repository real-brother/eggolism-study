import java.util.Arrays;
import java.util.Scanner;

public class Main {
    // 그래프 만들기
    static int[][] graph;
    static int[][] visited;

    // 변수 받기
    static int row;
    static int col;
    static int count = 0;

    // dfs 돌리기
    static void dfs(int y, int x) {
        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};

        visited[y][x] = 1;

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            // visited를 경계값으로 했더니 index 오류
            if (nx >= 0 && ny >= 0 && nx < col && ny < row) {
                if (graph[ny][nx] == 2 & visited[ny][nx] != 1) {
                    count++;
//                    System.out.println("추가: " + x + " " + y);
                }
                if (graph[ny][nx] != 0 & visited[ny][nx] != 1) {
                    dfs(ny, nx);
                }
            }
        }

    }


    // main
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        row = sc.nextInt();
        col = sc.nextInt();
        sc.nextLine();

        int y = 0;
        int x = 0;

        graph = new int[row+1][col+1];
        visited = new int[row][col];

        // 1. 그래프 만들기
        for (int i = 0; i < row; i++) {
            String inputs = sc.nextLine();
            for (int j = 0; j < col; j++) {
                char input = inputs.charAt(j);
                if (input == 'I') {
                    graph[i][j] = 1;
                    y = i;
                    x = j;
                } else if (input == 'P') {
                    graph[i][j] = 2;
                } else if (input == 'O') {
                    graph[i][j] = 3;
                } else {
                    graph[i][j] = 0;
                }
            }
        }

        // 2. 그래프 돌리기
        dfs(y, x);
        
        // 3. 정답 출력하기
        if (count == 0) {
            System.out.println("TT");
        } else {
            System.out.println(count);
        }

    }
}
