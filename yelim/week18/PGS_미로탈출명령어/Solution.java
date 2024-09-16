import java.util.*;

/*
* 아니 30개 다 맞는데 31번만 시간초과 뭐냐..
* 어디서 나지... 걸러줄 조건이 더 있나..
* 뭘까 ..
* 
*/

class Solution {
    
    static int[][] map;
    static int[] dr = {1, 0, 0, -1};    // d-l-r-u
    static int[] dc = {0, -1, 1, 0};
    static String[] dir = {"d", "l", "r", "u"};
    
    static StringBuilder road;
    static List<String> roadList;
    static String answer = null;
    
    static int sr, sc, er, ec, rSize, cSize;
        
    public String solution(int n, int m, int x, int y, int r, int c, int k) {
        road = new StringBuilder();
        
        rSize = n;
        cSize = m;
        sr = x-1;
        sc = y-1;
        er = r-1;
        ec = c-1;
        
        map = new int[n][m];
        
        map[er][ec] = 1;
        
        // 최단 경로를 먼저 계산해서 k보다 크면 dfs 수행 안함
        // 최단 경로랑 k가 짝수거나 홀수거나! 둘이 동일해야 함.. (이거 생각하긴 어려웠다..)
        if (dist(sr, sc, er, ec) <= k) {
            if (dist(sr, sc, er, ec)%2 == k%2) {
                dfs(0, sr, sc, k);
            }
        }
        
        
        if (answer == null) {
            answer = "impossible";
        }
        
        return answer;
    }
    
    
    // 백트래킹 해보자
    static void dfs(int depth, int r, int c, int k) {
        if (answer != null) return;
        
        // 내가 지금까지 온 거리 + 남은 최단 거리가 k보다 크면 안됨
        if (depth + dist(r, c, er, ec) > k) return;
        
        if (depth == k && map[r][c]==1) {
            answer = road.toString();
            return;
        }
        
        for(int d=0; d<4; d++) {
            int nr = r+dr[d];
            int nc = c+dc[d];
            
            if (nr>=0 && nr<rSize && nc>=0 && nc<cSize) {
                // if (d==0) {
                //     road.append("d");
                // } else if (d==1) {
                //     road.append("l");
                // } else if (d==2) {
                //     road.append("r");
                // } else if (d==3) {
                //     road.append("u");
                // }
                road.append(dir[d]);
                
                dfs(depth+1, nr, nc, k);
                road.delete(depth, depth+1);
                
            }
        }
    }
    
    static int dist(int r1, int c1, int r2, int c2) {
        return (Math.abs(r1-r2) + Math.abs(c1-c2));
    }
    
}