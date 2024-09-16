package BOJ_2468_안전영역;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

public class Main_bfs {
    static int n;
    static int start_r = -1, start_c = -1;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int count = 0;
    static int maxDivide = -1;
    static boolean[][] visited;
    static List<List<Integer>> area;
    static Queue<int[]> queue = new ArrayDeque<int[]>();;
    
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
    				if (!visited[r][c] && area.get(r).get(c) > rainHeight) {
    					bfs(r, c, rainHeight);
    					divide++;
    				}
    			}
    		}
        	maxDivide = Math.max(maxDivide, divide);
		}
        
        
        System.out.println(maxDivide);
    }
    
    // bfs로 연결되는 섬 모두 찾기
    static void bfs(int x, int y, int rainHeight) {
    	queue.add(new int[] {x,y});
    	visited[x][y] = true;
    	
    	while (!queue.isEmpty()) {
    		int[] temp = queue.poll();
    		x = temp[0];
    		y = temp[1];
    		
    		for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (nx < 0 || nx >= n || ny < 0 || ny >= n || visited[nx][ny]) continue;
                
                if (area.get(nx).get(ny) > rainHeight) {
    				queue.add(new int[] {nx, ny});
    			}
                visited[nx][ny] = true;
            }
    	}
    }
}