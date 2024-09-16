package BOJ_10816_숫자카드2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_fail {

	public static void main(String[] args) throws IOException {
		
		/* 문제)
		 * 갖고있는 숫자카드 개수 n, n 값 입력받아 배열에 저장
		 * 비교할 숫자 개수 m, m 값 입력받아 배열에 저장
		 * n에 각 m이 몇 개 있는지 출력
		 */
		
		// 아니 테스트케이스는 맞았는데 틀렸대ㅠㅠ
		
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
		
		st = new StringTokenizer(br.readLine());
		
		// 비교할 숫자 개수 m
		int m = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		
		// 배열에 비교할 숫자카드 저장
		int[] mArr = new int[m];
		for(int i=0; i<m; i++) {
			mArr[i] = Integer.parseInt(st.nextToken());
		}
		
		// 갖고있는 숫자카드 배열 정렬
		Arrays.sort(nArr);
		
		// 카운트 저장할 배열
		int[] count = new int[m];
		
		
		// 비교할 카드들 반복 돌기
		for(int i=0; i<m; i++) {
			// 최소, 최대 인덱스 초기화
			int left = 0;
			int right = n-1;
			
			while (left < right) {
				// 중간값 저장
				int mid = (left+right)/2;
				
				// 같은 값이라면
				if (mArr[i] == nArr[mid]) {
					// 중복검사
					// 같은 동안 반복
					while (nArr[mid] == mArr[i]) {
						// 카운트 1 증가
						// 중간값 오른쪽으로 이동
						count[i]++;
						mid++;
						// n보다 커지면 벗어나기
						if (mid >= n) break;
					}
					// 중간값 다시 초기화 (이미 카운트된 값 이전부터 비교해야 해서 -1)
					mid = (left+right)/2-1;
					// 중간값이 0보다 클 때
					if (mid >= 0) {
						// 중복검사
						// 같은 동안 반복
						while (nArr[mid] == mArr[i]) {
							// 카운트 1 증가
							// 중간값 왼쪽으로 이동
							count[i]++;
							mid--;
							// 중간값이 0보다 작으면 벗어나기
							if (mid < 0) break;
						}
					}
					// 중간값 초기화
					mid = (left+right)/2;
					// 작으면 최소인덱스 오른쪽으로 옮겨주기
					if (nArr[mid] < mArr[i]) {
						left = mid+1;
					}
					// 크면 최대인덱스 왼쪽으로 옮겨주기
					else {
						right = mid-1;
					}
				}
				// 작으면 최소인덱스 오른쪽으로 옮겨주기
				else if (nArr[mid] < mArr[i]) {
					left = mid+1;
				}
				// 크면 최대인덱스 왼쪽으로 옮겨주기
				else {
					right = mid-1;
				}
			}
		}
		
		for(int x:count) {
			System.out.print(x+" ");
		}

	}

}
