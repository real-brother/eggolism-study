package W6_BOJ_1992_쿼드트리;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static int n;
	static int[][] arr;
	static StringBuilder ans;

	public static void main(String[] args) throws IOException {
		/* 문제) 쿼드트리
		 * 주어진 영상이 모두 0으로만 되어 있으면 압축 결과는 "0"이 되고, 
		 * 모두 1로만 되어 있으면 압축 결과는 "1"이 된다. 
		 * 만약 0과 1이 섞여 있으면 전체를 한 번에 나타내지를 못하고, 
		 * 왼쪽 위, 오른쪽 위, 왼쪽 아래, 오른쪽 아래, 이렇게 4개의 영상으로 나누어 압축하게 되며, 
		 * 이 4개의 영역을 압축한 결과를 차례대로 괄호 안에 묶어서 표현한다
		 */

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		n = Integer.parseInt(br.readLine());
		
		arr = new int[n][n];
		
		// 영상 점 입력받아서 저장
		for(int i=0; i<n; i++) {
			String s = br.readLine();
			for(int j=0; j<n; j++) {
				arr[i][j] = s.charAt(j)-'0';
			}
		}
		ans = new StringBuilder();
		
		divide(0, 0, n);
		
		System.out.println(ans);
		
	}
	
	static void divide(int r, int c, int size) {
		
		// 같은 색깔이면 해당 색깔 저장
		if (colorCheck(r, c, size)) {
			ans.append(arr[r][c]);
		}
		else {
			int newSize = size/2;
			
			ans.append('(');
			
			divide(r, c, newSize);
			divide(r, c+newSize, newSize);
			divide(r+newSize, c, newSize);
			divide(r+newSize, c+newSize, newSize);
			
			ans.append(')');
		}
		
		
	}
	
	static boolean colorCheck(int r, int c, int size) {
		// size가 1이면 같은 색이므로 true
		if (size==1) {
			return true;
		}
		else {
			// 현재 색깔
			int check = arr[r][c];
			
			// size만큼 돌며 현재색깔과 같은지 확인
			for(int i=r; i<r+size; i++) {
				for(int j=c; j<c+size; j++) {
					if (arr[i][j] != check) {
						return false;
					}
				}
			}
			return true;
		}
	}

}
