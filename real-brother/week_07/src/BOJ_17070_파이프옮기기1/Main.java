package BOJ_17070_파이프옮기기1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int n, ans;
    static int[][] room = null;

    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        room = new int[n][n];
        for(int i = 0; i < n; i++) {
        	StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j = 0; j < n; j++) {
                room[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dfs(0, 1, 0);
        System.out.println(ans);
    }
    
    // direction 0: 가로, 1: 세로, 2: 대각선
    public static void dfs(int r, int c, int direction) {
    	if(r == n-1 && c == n-1) {
    		ans += 1;
    		return;
    	}
    	
    	if(direction == 0) {  // 가로일때 -> 가로, 대각 이동 가능
    		if(c+1 < n && room[r][c+1] == 0) {
    			dfs(r, c+1, 0);
    		}
    		if(r+1 < n && c+1 < n && room[r+1][c] == 0 && room[r][c+1] == 0 && room[r+1][c+1] == 0) {
    			dfs(r+1, c+1, 2);
    		}
    	}else if(direction == 1) {  // 세로일때 -> 세로, 대각 이동 가능
    		if(r+1 < n && room[r+1][c] == 0) {
    			dfs(r+1, c, 1);
    		}
    		if(r+1 < n && c+1 < n && room[r+1][c] == 0 && room[r][c+1] == 0 && room[r+1][c+1] == 0) {
    			dfs(r+1, c+1, 2);
    		}
    	}else {  // 대각선일때 -> 가로, 세로, 대각 이동 가능
    		if(c+1 < n && room[r][c+1] == 0) {
    			dfs(r, c+1, 0);
    		}
    		if(r+1 < n && room[r+1][c] == 0) {
    			dfs(r+1, c, 1);
    		}
    		if(r+1 < n && c+1 < n && room[r+1][c] == 0 && room[r][c+1] == 0 && room[r+1][c+1] == 0) {
    			dfs(r+1, c+1, 2);
    		}
    	}
    }
}