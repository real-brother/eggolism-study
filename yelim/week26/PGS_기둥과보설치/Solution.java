import java.util.*;

class Solution {
    
    static int n;
    static boolean[][] tower;
    static boolean[][] bo;
    
    public int[][] solution(int n, int[][] build_frame) {
        this.n = n;
        
        tower = new boolean[n+1][n+1];
        bo = new boolean[n+1][n+1];
        
        int cnt = 0;
        
        for(int i=0; i<build_frame.length; i++) {
           
            int x = build_frame[i][0];
            int y = build_frame[i][1];
            int a = build_frame[i][2];  // 구조물의 종류 0=기둥/1=보
            int b = build_frame[i][3];  // 0=삭제 / 1=설치

            // 기둥일 때
            if (a == 0) {
                // 설치
                if (b == 1) {
                    if (towerCheck(x, y)) {
                        tower[x][y] = true;
                        cnt++;
                    }
                } else { // 삭제
                    tower[x][y] = false;
                    if (!del()) {
                        tower[x][y] = true;
                    } else {
                        cnt--;
                    }
                }
            }
            // 보일 때
            else {
                // 설치
                if (b == 1) {
                    if (boCheck(x, y)) {
                        bo[x][y] = true;
                        cnt++;
                    }
                } else { // 삭제
                    bo[x][y] = false;
                    if (!del()) {
                        bo[x][y] = true;
                    } else {
                        cnt--;
                    }
                }
            }
            
            
        }
        
        // 결과 저장
        System.out.println(cnt);
        
        int[][] answer = new int[cnt][3];
        
        int idx = 0;
        for(int i=0; i<=n; i++) {
            for(int j=0; j<=n; j++) {
                if (tower[i][j]) {
                    answer[idx][0] = i;
                    answer[idx][1] = j;
                    answer[idx++][2] = 0;
                }
                if (bo[i][j]) {
                    answer[idx][0] = i;
                    answer[idx][1] = j;
                    answer[idx++][2] = 1;
                }
            }
        }
        
        
        return answer;
    }
    
    // 기둥 조건 체크
    static boolean towerCheck(int x, int y) {
        // 바닥 위에 있거나
        if (y == 0) {
            return true;
        }
        // 보의 한 쪽 끝 위에 있거나
        else if (x>0 && bo[x-1][y] || bo[x][y]) {
            return true;
        }
        // 또 다른 기둥 위에 있거나
        else if (y>0 && tower[x][y-1]) {
            return true;
        }
        
        return false;
    }
    
    // 보 조건 체크
    static boolean boCheck(int x, int y) {
        // 한 쪽 끝 부분이 기둥 위에 있거나
        if (y>0 && tower[x][y-1] || tower[x+1][y-1]) {
            return true;
        }
        // 양쪽 끝 부분이 다른 보와 동시에 연결 되어 있거나
        else if (x>0 && bo[x-1][y] && bo[x+1][y]) {
            return true;
        }
        
        return false;
    }
    
    // 삭제할 때 조건 체크
    static boolean del() {
        
        for(int i=0; i<=n; i++) {
            for(int j=0; j<=n; j++) {
                if (tower[i][j] && !towerCheck(i, j)) {
                    return false;
                }
                else if (bo[i][j] && !boCheck(i, j)) {
                    return false;
                }
            }
        }
        
        return true;
    }
}