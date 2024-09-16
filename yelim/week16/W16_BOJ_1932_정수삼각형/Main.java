package W16_BOJ_1932_정수삼각형;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		/* 문제) 1932_정수 삼각형
		 * 한 층씩 내려오면서 수를 하나 선택하여 합을 구해 최대값을 구한다.
		 */
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		int[][] arr = new int[n][];
		
		for(int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			arr[i] = new int[i+1];
			for(int j=0; j<i+1; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for(int i=n-1; i>=1; i--) {
			for(int j=0; j<i; j++) {
				arr[i-1][j] = Math.max(arr[i][j], arr[i][j+1]) + arr[i-1][j];
			}
		}
		
		System.out.println(arr[0][0]);
	}

}
