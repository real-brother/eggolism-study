package BOJ_10816_숫자카드2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		
		/* 문제)
		 * 갖고있는 숫자카드 개수 n, n 값 입력받아 배열에 저장
		 * 비교할 숫자 개수 m, m 값 입력받아 배열에 저장
		 * n에 각 m이 몇 개 있는지 출력
		 */
		
		// 시간초과....... 으으으으으ㅡ으
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		// 갖고있는 숫자카드 개수 n
		int n = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		
		// 배열에 숫자카드 저장
		int[] nArr = new int[n];
		
		for(int i=0; i<n; i++) {
			nArr[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(nArr);
		
		st = new StringTokenizer(br.readLine());
		
		// 비교할 숫자 개수 m
		int m = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		
		StringBuilder sb = new StringBuilder();

		// upperBound와 lowerBound의 차이(중복값 개수) 차례로 출력
		for(int i=0; i<m; i++) {
			int key = Integer.parseInt(st.nextToken());
			sb.append(upperBound(nArr, key) - lowerBound(nArr, key)).append(" ");
//			System.out.print((upperBound(nArr, key) - lowerBound(nArr, key))+" ");
		}
		System.out.println(sb);
		
	}
	
	// 중복값 중의 최소 인덱스 반환하는 메소드
	public static int lowerBound(int[] arr, int key) {
		int left = 0;
		int right = arr.length;
		
		while (left < right) {
			int mid = (left+right)/2;
			
			if (key <= arr[mid]) {
				right = mid;
			}
			else {
				left = mid+1;
			}
		}
		return left;
	}
	
	
	// 중복값 중의 최대 인덱스 반환하는 메소드
	public static int upperBound(int[] arr, int key) {
		int left = 0;
		int right = arr.length;
		
		while (left < right) {
			int mid = (left+right)/2;
			
			if (key < arr[mid]) {
				right = mid;
			}
			else {
				left = mid+1;
			}
		}
		return left;
	}
	

}
