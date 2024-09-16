package BOJ_21736_헌내기는친구가필요해;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main_dfs {
    static int n, m;
    static int start_r = -1, start_c = -1;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int count = 0;
    static boolean[][] visited;
    static List<List<String>> campus;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        sc.nextLine();
        visited = new boolean[n][m];
        campus = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            List<String> tempCampus = new ArrayList<>();
            String text = sc.nextLine();
            for (int j = 0; j < text.length(); j++) {
                tempCampus.add(Character.toString(text.charAt(j)));
                if (text.charAt(j) == 'I') {
                    start_r = i;
                    start_c = j;
                }
            }
            campus.add(tempCampus);
        }

        dfs(start_r, start_c);

        if (count > 0) {
            System.out.println(count);
        } else {
            System.out.println("TT");
        }
    }

    static void dfs(int x, int y) {
        visited[x][y] = true;

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (nx < 0 || nx >= n || ny < 0 || ny >= m || visited[nx][ny]) continue;
            
            if (campus.get(nx).get(ny).equals("X")) {
                visited[nx][ny] = true;
            } else if (campus.get(nx).get(ny).equals("P")) {
            	visited[nx][ny] = true;
            	count++;
            	dfs(nx, ny);
            } else {
            	visited[nx][ny] = true;
            	dfs(nx, ny);
            }
            
            
        }
    }
}
