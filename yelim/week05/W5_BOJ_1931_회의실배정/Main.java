package W5_BOJ_1931_회의실배정;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Meeting {
	int start;
	int end;
}

public class Main {

	public static void main(String[] args) throws IOException {
		
		/* 문제)
		 * 각 회의가 겹치지 않게 하면서 회의실을 사용할 수 있는 회의의 최대 개수
		 */
		
		/* 설계)
		 * 입력받은 시작시간과 종료시간을 배열로 저장하기 위해 클래스 생성
		 * 객체 배열로 저장해주고, comparator 이용하여 종료시간 기준으로 정렬해준다.
		 * 처음 끝나는 시간을 0으로 초기화 하고, 
		 * 시작시간이 종료시간보다 크거나 같으면 그 타임의 종료시간을 다시 종료시간으로 설정해준다.
		 */
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		
		Meeting[] arr = new Meeting[n];
		
		for(int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			Meeting m = new Meeting();
			m.start = Integer.parseInt(st.nextToken());
			m.end = Integer.parseInt(st.nextToken());
			arr[i] = m;	
		}
		
		// 종료시간이 같으면 시작시간이 작은 것부터 정렬
		// 아니면 종료시간이 작은 것부터 정렬
		Arrays.sort(arr, (o1, o2)->{
			if (o1.end == o2.end) {
				return (o1.start-o2.start);
			}
			else {
				return (o1.end-o2.end);
			}
		});
	
		
		int endTime = 0;	// 종료 시간 초기화
		int cnt = 0;		// 회의 개수 셀 변수
		
		// 회의 개수만큼 반복
		for(int i=0; i<n; i++) {
			// 시작시간이 끝나는 시간보다 크거나 같으면
			if (arr[i].start>=endTime) {
				// 종료시간을 해당 회의의 종료시간으로 설ㅈㅈㅇ
				endTime = arr[i].end;
				cnt++;	// 개수 1 증가
				
			}
		}
		
		System.out.println(cnt);

	}

}
