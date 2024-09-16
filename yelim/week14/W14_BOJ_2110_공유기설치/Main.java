package W14_BOJ_2110_공유기설치;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		/* 문제) 2110_공유기 설치
		 * 집 N개, 공유기 C개
		 * 한 집에 하나의 공유기 설치. 가장 인접한 두 공유기 사이의 거리를 가능한 크게.
		 * C개의 공유기를 N개의 집에 적당히 설치해서, 가장 인접한 두 공유기 사이의 거리를 최대로 하는 프로그램
		 */
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());
		
		int[] house = new int[n];
		
		for(int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			house[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(house);
		
		// 최소 거리는 1
		int min = 1;
		// 최대 거리는 처음 집 ~ 마지막 집의 거리
		int max = house[n-1] - house[0];
		// 결과값 저장할 변수
		int result = 0;
		
		while(min <= max) {
			int mid = (min+max)/2;
			
			// 공유기 개수
			int cnt = 1;
			
			// 바로 전에 공유기 설치한 집 (처음 집에는 무조건 설치!?)
			int startHouse = house[0];
			
			for(int i=1; i<n; i++) {
				// mid보다 바로 전에 설치한 집과 현재 집의 거리가 긴 경우
				if (house[i] - startHouse >= mid) {
					// 공유기 설치 (개수 증가)
					cnt++;
					// 공유기 설치했으므로 갱신
					startHouse = house[i];
				}
			}
			
			// 공유기 개수가 c보다 크면 공유기 사이 거리 늘려서 다시 설치
			// result값 최대값 비교하여 갱신
			if (cnt >= c) {
				result = Math.max(result, mid);
				min = mid+1;
			}
			// 공유기 개수가 c보다 작으면 공유기 사이 거리 좁혀서 다시 설치
			else {
				max = mid-1;
			}
		}
		
		System.out.println(result);

	}

}
