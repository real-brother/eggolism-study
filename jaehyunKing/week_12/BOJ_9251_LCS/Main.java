package BOJ_9251_LCS;

/*
 *  모르겠어서 재영이의 도움을 받았습니다.
 *  AC와 CAPC는 2
 *  ACA와 CAPCA는 3
 */

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        String S2 = sc.next();
        String S1 = sc.next();
        
        int[][] D = new int[S1.length()+1][S2.length()+1];
        for(int r = 1; r <= S1.length(); r++) {
            for(int c = 1; c <= S2.length(); c++) {
                char c1 = S1.charAt(r-1);
                char c2 = S2.charAt(c-1);
                if(c1 == c2) {
                    D[r][c] = D[r-1][c-1] + 1;
                }
                else D[r][c] = Math.max(D[r-1][c], D[r][c-1]);
            }
        }

        System.out.println(D[S1.length()][S2.length()]);
    }

}
