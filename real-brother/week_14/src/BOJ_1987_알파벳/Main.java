package BOJ_1987_알파벳;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int R, C;
    static char[][] board;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    static int answer = 1;

    public static void main(String[] args) throws IOException {
        String[] rc = br.readLine().split(" ");
        R = Integer.parseInt(rc[0]);
        C = Integer.parseInt(rc[1]);
        
        board = new char[R][C];

        for (int i = 0; i < R; i++) {
            String line = br.readLine();
            for (int j = 0; j < C; j++) {
                board[i][j] = line.charAt(j);
            }
        }

        bfs(0, 0);
        System.out.println(answer);
    }

    static void bfs(int r, int c) {
        Set<String> alphaSet = new HashSet<>();
        alphaSet.add(r + "," + c + "," + board[r][c]);

        while (!alphaSet.isEmpty()) {
            String[] parts = alphaSet.iterator().next().split(",");
            int nowR = Integer.parseInt(parts[0]);
            int nowC = Integer.parseInt(parts[1]);
            String alphaAns = parts[2];
            alphaSet.remove(nowR + "," + nowC + "," + alphaAns);

            for (int i = 0; i < 4; i++) {
                int nr = nowR + dr[i];
                int nc = nowC + dc[i];
                if (nr < 0 || nr >= R || nc < 0 || nc >= C) continue;

                if (!alphaAns.contains(String.valueOf(board[nr][nc]))) {
                    alphaSet.add(nr + "," + nc + "," + (alphaAns + board[nr][nc]));
                    answer = Math.max(answer, alphaAns.length() + 1);
//                    System.out.println(ans.toString());
                }
            }
        }
    }
}