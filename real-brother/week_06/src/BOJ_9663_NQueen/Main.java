package BOJ_9663_NQueen;

import java.util.Scanner;

public class Main {
    static Scanner sc = new Scanner(System.in);
    static int n;
    static int answer = 0;

    public static void main(String[] args) {
        n = sc.nextInt(); 

        int[] colPlacement = new int[n]; // 각 열에 퀸을 배치할 행을 저장하는 배열
        solveNQueens(0, colPlacement); // 첫 번째 열부터 시작

        System.out.println(answer);
    }

    // N-Queen 문제를 해결하기
    private static void solveNQueens(int row, int[] colPlacement) {
        if (row == n) {
            // 모든 행에 퀸을 배치한 경우
            answer++;
            for (int num: colPlacement) {
            	System.out.print(colPlacement[num] + " ");
            }
            System.out.println();
            return;
        }

        // 현재 행에 퀸을 배치할 수 있는 모든 열을 탐색
        for (int col = 0; col < n; col++) {
            if (isValid(colPlacement, row, col)) {
                colPlacement[row] = col; // 퀸을 현재 열에 배치
                // 다음 행으로 이동하여 재귀 호출
                solveNQueens(row + 1, colPlacement);
            }
        }
    }

    // 현재 위치에 퀸을 배치할 수 있는지 확인하는 메서드
    private static boolean isValid(int[] colPlacement, int row, int col) {
        // 현재 열의 이전 행들을 확인하여
        for (int i = 0; i < row; i++) {
        	// 같은 열 or 대각선 상에 퀸이 있는지 확인
            if (colPlacement[i] == col || Math.abs(row - i) == Math.abs(col - colPlacement[i])) {
                return false;
            }
        }
        return true;
    }
}
