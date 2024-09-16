package BOJ_9251_LCS;

import java.util.Scanner;

public class Main {
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
    	String str1 = sc.nextLine();
    	String str2 = sc.nextLine();
    	int answer = lcs(str1, str2);
    	System.out.println(answer);
    }
    
    // 두 문자열의 LCS를 계산하는 메서드
    public static int lcs(String s1, String s2) {
        int m = s1.length();
        int n = s2.length();
        
        // LCS를 저장하기 위한 테이블 생성
        int[][] lcsTest = new int[m + 1][n + 1];

        /*
         * 최장 공통 부분수열 계산
         * 1. 문자열A, 문자열B의 한글자씩 비교
         * 2. 두 문자가 다르다면 LCS[i - 1][j]와 LCS[i][j - 1] 중에 큰값을 표시
         * 3. 두 문자가 같다면 LCS[i - 1][j - 1] 값을 찾아 +1
         * 4. 위 과정을 반복
         */
        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                if (i == 0 || j == 0) // 초기 설정
                    lcsTest[i][j] = 0;
                else if (s1.charAt(i - 1) == s2.charAt(j - 1))
                    lcsTest[i][j] = lcsTest[i - 1][j - 1] + 1;
                else
                    lcsTest[i][j] = Math.max(lcsTest[i - 1][j], lcsTest[i][j - 1]);
            }
        }
        
//        for (int i = 0; i <= m; i++) {
//			for (int j = 0; j <= n; j++) {
//				System.out.print(lcsTest[i][j] + " ");
//			}
//			System.out.println();
//		}
        /*  0 0 0 0 0 0 0 
			0 0 1 1 1 1 1 
			0 1 1 1 2 2 2 
			0 1 2 2 2 3 3 
			0 1 2 2 2 3 3 
			0 1 2 2 2 3 4 
			0 1 2 3 3 3 4  */

        return lcsTest[m][n];

        /*
         *  LCS 역으로 추적하여 본래 수열이 정확히 어떻게 구성되는지 파악하기
         *  lcsSequence에 본래 수열이 담긴다.
         */
        
//        int index = lcsTest[m][n];
//        char[] lcsSequence = new char[index];
//        int i = m, j = n;
//        while (i > 0 && j > 0) {
//            if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
//                lcsSequence[index - 1] = s1.charAt(i - 1);
//                i--;
//                j--;
//                index--;
//            } else if (lcsTest[i - 1][j] > lcsTest[i][j - 1]) {
//                i--;
//            } else {
//                j--;
//            }
//        }

    }

}
