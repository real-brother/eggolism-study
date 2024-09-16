package BOJ_1149_RGB거리;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int[][] RGBstreet = new int[n][3];
        
        for (int i = 0; i < RGBstreet.length; i++) {
			for (int j = 0; j < 3; j++) {
				RGBstreet[i][j] = sc.nextInt();
			}
		}
        
        // 한줄씩 쌓아가면서 저장하기 - 매 줄 마다 최소값이 되도록
        for (int i = 1; i < RGBstreet.length; i++) {
        	// 다른 색 2개 중에서 최소가 되는 값을 찾아서, 더하기
			RGBstreet[i][0] = Math.min(RGBstreet[i-1][1], RGBstreet[i-1][2]) + RGBstreet[i][0];
			RGBstreet[i][1] = Math.min(RGBstreet[i-1][0], RGBstreet[i-1][2]) + RGBstreet[i][1];
			RGBstreet[i][2] = Math.min(RGBstreet[i-1][0], RGBstreet[i-1][1]) + RGBstreet[i][2];
		}
        
        
        int minNum = Arrays.stream(RGBstreet[n-1]).min().getAsInt();
        
        System.out.println(minNum);
    }
}
