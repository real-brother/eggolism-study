import java.util.*;

class Solution {
    
    static List<Integer>[] graph;
    static int max = 0;
    static boolean[][][] visited;   // 3차원 방문체크..?
    
    public int solution(int[] info, int[][] edges) {
        int answer = 0;
        graph = new ArrayList[info.length];
        visited = new boolean[info.length][info.length+1][info.length+1];
        
        // 인접 리스트
        for(int i=0; i<info.length; i++) {
            graph[i] = new ArrayList<>();
        }
        
        // edges로 인접 리스트 만들어주기
        for(int i=0; i<edges.length; i++) {
            int a = edges[i][0];
            int b = edges[i][1];
            
            graph[a].add(b);
            graph[b].add(a);
        }
        
        dfs(info, 0, 0, 0);
        answer = max;
        
        return answer;
    }
    
    // 백트래킹
    static void dfs(int[] info, int n, int s, int w) {
        if (info[n] == 0) s++;
        else if (info[n] == 1) w++;
        
        if (w >= s) return;
        max = Math.max(max, s);
        
        for(int i=0; i<graph[n].size(); i++) {
            int next = graph[n].get(i);
            int cur = info[n];
            
            // [현재 노드][양][늑대] 방문 체크
            if (!visited[n][s][w]) {
                visited[n][s][w] = true;
                info[n] = -1;
                dfs(info, next, s, w);
                visited[n][s][w] = false;
                info[n] = cur;
            }
        }
        
    }
}