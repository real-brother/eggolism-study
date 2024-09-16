package BOJ_2468_안전영역;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main_dfs {
    static int n;
    static int start_r = -1, start_c = -1;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int count = 0;
    static int maxDivide = -1;
    static boolean[][] visited;
    static List<List<Integer>> area;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        sc.nextLine();
        visited = new boolean[n][n];
        area = new ArrayList<>();

        for (int i = 0; i < n; i++) {
			List<Integer> tempArea = new ArrayList<>();
			String text = sc.nextLine();
			String[] splitText = text.split(" ");
			for (String s : splitText) {
				int tempNum = Integer.parseInt(s);
				tempArea.add(tempNum);
			}

			area.add(tempArea);
		}
        
        // 비의 높이가 0부터 100까지 전부다 dfs돌려보기
        for (int rainHeight = 0; rainHeight < 101; rainHeight++) {
        	visited = new boolean[n][n];
        	int divide = 0;
        	
        	for (int r = 0; r < n; r++) {
    			for (int c = 0; c < n; c++) {
    				if (!visited[r][c] && area.get(r).get(c) > rainHeight)
    					divide += dfs(r, c, rainHeight);
    			}
    		}
        	
        	maxDivide = Math.max(maxDivide, divide);
		}
        
        
        System.out.println(maxDivide);
    }
    
    // dfs가 끝나면 나눠진 섬 +1 해주기
    static int dfs(int x, int y, int rainHeight) {
        visited[x][y] = true;

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (nx < 0 || nx >= n || ny < 0 || ny >= n || visited[nx][ny]) continue;
            
            if (area.get(nx).get(ny) > rainHeight) {
				dfs(nx, ny, rainHeight);
			}
        }
        
        return 1;
    }
}
