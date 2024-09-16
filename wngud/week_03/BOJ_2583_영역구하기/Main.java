import java.util.Arrays;
import java.util.Scanner;

public class Main {

    static int[][] graph;
    static int[][] visited;
    static int[][] counts;

    static int M;
    static int N;

    // 대각선의 숫자가 문제
    // 탐색 범위는 정확히 사각형
    // 1. 델타배열에 length를 곱했더니 대각선 부분만 구함
    // 2. 사각형을 구하고, 길이를 늘려주는 방식

    static void bfs(int y, int x) {
        int[] dx = {-1, 1, 0, 0, 1, 1, -1, -1};
        int[] dy = {0, 0, -1, 1, -1, 1, 1, -1};

        boolean is_on = true;
        int count = 1;
        int length = 1;

        do {
            for (int m = -length; m <= length; m++) {
                for (int n = -length; n <= length; n++) {
                    int nx = x + m;
                    int ny = y + n;

                    if (nx >= 0 && ny >= 0 && nx < N && ny < M) {
                        if (graph[ny][nx] == 1) is_on = false;
                    }

                }

            }

            // 8개 돌고 나서 결과 판별
            if (is_on) {
                count++;
                length++;
            }
        } while (is_on);

        counts[y][x] = count;

    }


    // main
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        M = sc.nextInt();
        N = sc.nextInt();
        sc.nextLine();

        graph = new int[M][N];
        visited = new int[M][N];
        counts = new int[M][N]; // 정답 기록용

        // 1. 그래프 채우기
        for (int i = 0; i < graph.length; i++) {
            String[] inputs = sc.nextLine().split(" ");
            for (int j = 0; j < graph[i].length; j++) {
                int input = Integer.parseInt(inputs[j]);
                graph[i][j] = input;
            }
        }

        // 2. 그래프 순회하기
        // 0 이면 순회하고
        // 1이면 -1 넣기 -> 최대값 구하는 것이라서 상관 없음
        for (int i = 0; i < graph.length; i++) {
            for (int j = 0; j < graph[i].length; j++) {
                if (graph[i][j] == 1) {
                    counts[i][j] = -1; // 수정 필요
                } else {
                    bfs(i, j);
                }
            }
        }

        // 3. 정답 출력하기
        int answer = 0;
        for (int i = 0; i < counts.length; i++) {
            for (int j = 0; j < counts[i].length; j++) {
                if (answer < counts[i][j]) {
                    answer = counts[i][j];
                }
            }
        }

        System.out.println(answer);

    }
}
