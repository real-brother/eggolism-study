import java.util.*;

class Solution {
    
    static int n;
    static int[] weak, dist;
    static List<Integer> weakList;
    static boolean[] visited;
    static List<Integer> distList;
    static int ans = Integer.MAX_VALUE;
    
    public int solution(int n, int[] weak, int[] dist) {
        this.n = n;
        this.weak = weak;
        this.dist = dist;
        
        weakList = new ArrayList<>();
        visited = new boolean[dist.length];
        distList = new ArrayList<>();
        
        // 한 방향으로만 계산하도록 리스트 만들어주기
        // [1, 5, 6, 10]
        // {1, 5, 6, 10, 13, 17, 18, 22}
        for(int i=0; i<weak.length; i++) {
            weakList.add(weak[i]);
        }
        
        for(int i=0; i<weak.length; i++) {
            weakList.add(weak[i]+n); // 더해줘야 함
        }
        
        perm(0);
        
        if (ans == Integer.MAX_VALUE) {
            return -1;
        } else {
            return ans;
        }
        
    }
    
    // 친구 거리 순열 만들고 완탐
    static void perm(int cnt) {
        if (cnt == dist.length) {
            // 완탐
            search(distList);
            return;
        }
        
        for(int i=0; i<dist.length; i++) {
            if (!visited[i]) {
                visited[i] = true;
                distList.add(dist[i]);
                perm(cnt+1);
                distList.remove(distList.size()-1);
                visited[i] = false;
            }
        }
    }
    
    // 완탐
    // 친구 순서 정하기 -> 
    static void search(List<Integer> distList) {
        for(int i=0; i<weak.length; i++) {
            int personCnt = 0;  // 검사한 친구
            boolean check = true;
            int s = weakList.get(i);

            for(int j=i; j<(i+weak.length); j++) {
                // 두 지점이 이동 가능 거리보다 크면 점검 불가, 다음 친구로 넘어감
                if (distList.get(personCnt) < weakList.get(j)-s) {
                    s = weakList.get(j);
                    personCnt++;

                    // 검사한 친구가 끝이면 break, 다음으로
                    if (personCnt == distList.size()) {
                        check = false;
                        break;
                    }
                }
            }
            // 검사 끝났으면(다 점검 가능)
            if (check) {
                ans = Math.min(personCnt+1, ans);
            }
        }
    }
}