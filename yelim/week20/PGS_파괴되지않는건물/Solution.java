import java.util.*;

class Solution {
    public int solution(int[][] board, int[][] skill) {
        int answer = 0;
        
        // 누적합 저장할 배열 생성
        int[][] tmp = new int[board.length+1][board[0].length+1];
        
        // 누적합 배열에 degree 넣어주기
        for(int i=0; i<skill.length; i++) {
            int degree = skill[i][5];
            // type이 1일 때 (감소)20 23
            if (skill[i][0] == 1) {
                tmp[skill[i][1]][skill[i][2]] -= degree;
                tmp[skill[i][1]][skill[i][4]+1] += degree;
                tmp[skill[i][3]+1][skill[i][2]] += degree;
                tmp[skill[i][3]+1][skill[i][4]+1] -= degree;
            }
            
            // type이 2일 때 (증가)
            else {
                tmp[skill[i][1]][skill[i][2]] += degree;
                tmp[skill[i][1]][skill[i][4]+1] -= degree;
                tmp[skill[i][3]+1][skill[i][2]] -= degree;
                tmp[skill[i][3]+1][skill[i][4]+1] += degree;
            }
        }
        
        // 확인
        // for(int i=0; i<tmp.length; i++) {
        //     System.out.println(Arrays.toString(tmp[i]));
        // }
        
        // 행 기준 누적합
        for(int i=0; i<tmp.length; i++) {
            for(int j=0; j<tmp[0].length-1; j++) {
                tmp[i][j+1] += tmp[i][j];
            }
        }
        
        // 열 기준 누적합
        for(int i=0; i<tmp[0].length; i++) {
            for(int j=0; j<tmp.length-1; j++) {
                tmp[j+1][i] += tmp[j][i];
            }
        }
        
        
        // 기존 배열과 합치기
        for(int i=0; i<board.length; i++) {
            for(int j=0; j<board[0].length; j++) {
                board[i][j] += tmp[i][j];
                if (board[i][j] >= 1) answer++;
            }
        }
        
        return answer;
    }
}