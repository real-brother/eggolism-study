package BOJ_14940_쉬운최단거리;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    static int n, m;
    static int[][] distanceMap, visited, mapList;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        distanceMap = new int[n][m];
        visited = new int[n][m];
        mapList = new int[n][m];

        Queue<int[]> queue = new LinkedList<>();

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                mapList[i][j] = Integer.parseInt(st.nextToken());
                if (mapList[i][j] == 2) {
                    queue.add(new int[]{i, j});
                    visited[i][j] = 1;
                    distanceMap[i][j] = 0;
                } else if (mapList[i][j] == 0) {
                    distanceMap[i][j] = 0;
                } else {
                    distanceMap[i][j] = -1;
                }
            }
        }

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int nr = cur[0];
            int nc = cur[1];

            for (int i = 0; i < 4; i++) {
                int r = nr + dr[i];
                int c = nc + dc[i];

                if (r < 0 || r >= n || c < 0 || c >= m || visited[r][c] == 1) {
                    continue;
                }
                if (mapList[r][c] == 0) {
                    distanceMap[r][c] = 0;
                    visited[r][c] = 1;
                } else {
                    distanceMap[r][c] = distanceMap[nr][nc] + 1;
                    visited[r][c] = 1;
                    queue.add(new int[]{r, c});
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                sb.append(distanceMap[i][j]).append(" ");
            }
            sb.append("\n");
        }
        System.out.print(sb.toString());
    }
}
