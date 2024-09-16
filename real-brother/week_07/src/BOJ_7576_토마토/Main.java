package BOJ_7576_토마토;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Scanner;

public class Main {
	static Scanner sc = new Scanner(System.in);
	static int m;
	static int n;
	static int[][] tomato;
	static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};
    static Queue<int[]> queue;
	
    public static void main(String[] args) {
        m = sc.nextInt();
        n = sc.nextInt();
        tomato = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                tomato[i][j] = sc.nextInt();
            }
        }

        queue = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (tomato[i][j] == 1) {
                    queue.add(new int[]{i, j});
                }
            }
        }

        bfs();

        int maxDays = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (tomato[i][j] == 0) {
                    System.out.println(-1);
                    return;
                }
                maxDays = Math.max(maxDays, tomato[i][j]);
            }
        }
        System.out.println(maxDays - 1); 
    }

    public static void bfs() {
        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int x = current[0];
            int y = current[1];

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                
                if (nx < 0 || nx >= n || ny < 0 || ny >= m) continue;
                if (tomato[nx][ny] == 0) {
                    tomato[nx][ny] = tomato[x][y] + 1;
                    queue.add(new int[]{nx, ny});
                }
            }
        }
    }
}