import java.util.*;

class Solution {
    
    /** 
    /* 플로이드와샬?
    /* 모든 정점에서 다른 정점으로 가는 최소비용 
    /* 거쳐가는 정점 s-k, k-a, k-b 구할 수 있음
    **/
    
    
    public int solution(int n, int s, int a, int b, int[][] fares) {
        int inf = 100000000;
        int answer = inf;
        
        int[][] cost = new int[n+1][n+1];
        
        for(int i=0; i<=n; i++) {
            for(int j=0; j<=n; j++) {
                if (i != j) {
                    cost[i][j] = inf;
                } 
            }
        }
        
        
        for(int i=0; i<fares.length; i++) {
            int x = fares[i][0];
            int y = fares[i][1];
            int z = fares[i][2];
            
            cost[x][y] = z;
            cost[y][x] = z;
        }
        
        // 거쳐가는 정점을 기준으로
        // k를 거쳐갈 때 (i-j까지)모든 정점의 최소비용 구하기
        for(int k=1; k<=n; k++) {
            for(int i=1; i<=n; i++) {
                for(int j=1; j<=n; j++) {
                    cost[i][j] = Math.min(cost[i][j], cost[i][k]+cost[k][j]);
                }
            }
        }
        
        // 처음부터 따로 가는 경우
        // s->a + s->b
        answer = Math.min(answer, cost[s][a] + cost[s][b]);
        System.out.println(answer);
        
        // a, b까지 갔다가 나머지 목적지 가는 경우
        // s->a + a->b || s->b  + b->a
        answer = Math.min(answer, Math.min(cost[s][a]+cost[a][b], cost[s][b]+cost[b][a]));
        System.out.println(answer);
        
        // 중간까지 같이 갔다가 따로 가는 경우
        // s->k + k->a + k->b
        for(int k=1; k<=n; k++) {
            answer = Math.min(answer, cost[s][k]+cost[k][a]+cost[k][b]);
        }
        
        return answer;
    }
}