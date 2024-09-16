import java.util.*;

/*
라이언이 가장 큰 점수 차이로 우승할 수 있는 방법이 여러 가지 일 경우, 
가장 낮은 점수를 더 많이 맞힌 경우를 return 해주세요.
*/

class Solution {
    
    static int[] sel = new int[11];
    static int max = Integer.MIN_VALUE;
    static int[] answer = new int[11];
    
    public int[] solution(int n, int[] info) {
        
        back(n, info, 0);
        
        if (max == -1) {
            answer = new int[1];
            answer[0] = -1;
        }
        
        return answer;
    }
    
    // 백트래킹
    static void back(int n, int[] info, int cnt) {
        if (cnt == n) {
            // 점수 차 계산, 최대값 갱신
            int diff = diff(info);
            
            // max < diff로 하면 같을 때 낮은 점수가 더 많은 경우를 충족 못시키는 듯
            // 어차피 백트래킹이라 앞에부터 차례로 하니까, = 처리 해주면 마지막에 max==diff인 경우가 낮은 점수가 제일 많은 경우가 된다.
            if (max <= diff) {
                max = diff;
                answer = sel.clone();
            }
            
            return;
        }
        
        for(int i=0; i<info.length; i++) {
            if (sel[i] <= info[i]) {
                sel[i]++;
                back(n, info, cnt+1);
                sel[i]--;
            } else {
                break;
            }
        }
    }
    
    // 점수 차 계산
    static int diff(int[] info) {
        int a = 0;
        int l = 0;
        
        for(int i=0; i<sel.length; i++) {
            if (info[i]==0 && sel[i]==0) {
                continue;
            } 
            else if (info[i] >= sel[i]) {
                a += (10-i);
            }
            else if (info[i] < sel[i]) {
                l += (10-i);
            }
        }
        
        int diff = l - a;
        
        if (diff <= 0) {
            return -1;
        } else {
            return diff;
        }
        
        
    }
}