import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	
	static class flower implements Comparable<flower> {
		int start;
		int end;
		
		public flower(int start, int end) {
			this.start = start;
			this.end = end;
		}

		// (빨리 피는 순서, 피는 날이 같으면 빨리 지는 순서)
		@Override
		public int compareTo(flower f) {
			if (this.start == f.start) {
				return (this.end-f.end);
//				return (f.end-this.end);
			}
			else {
				return (this.start-f.start);
			}
		}
	}

	public static void main(String[] args) throws IOException {
		/* 문제) 2457_공주님의 정원
		 * 3월 1일부터 11월 30일까지 매일 꽃이 한 가지 이상 피어 있도록
		 * 꽃들의 수는 최소로
		 */
		
		/* 설계)
		 * 회의실 배정과 비슷
		 * 날짜를 3/1 = 301, 11/30 = 1130 이런 식으로 저장해주기
		 * 
		 * ++ 클래스에서 comparator로 안하고, Arrays.sort에서 람다로 정렬하니까 시간초과..
		 */
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());	// 꽃들의 총 개수
		
		flower[] flowers = new flower[n];
		
		for(int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			// 피고 지는 각 월, 일 입력
			int sm = Integer.parseInt(st.nextToken());
			int sd = Integer.parseInt(st.nextToken());
			int em = Integer.parseInt(st.nextToken());
			int ed = Integer.parseInt(st.nextToken());
			
			
			// flower 객체 배열에 날짜 값 저장
			int a = (sm*100)+sd;
			int b = (em*100)+ed;
			flowers[i] = new flower(a, b);
		}
		
		
		// 정렬 
		Arrays.sort(flowers);
		
		// 이렇게 하니까 시간초과가 나네
//		Arrays.sort(flowers, (o1, o2) -> {
//			if (o1.start == o2.start) {
//				return (o1.end-o2.end);
//			}
//			else {
//				return (o1.start-o2.start);
//			}
//		});
		
		int cnt = 0;	// 꽃 선택 개수
		int max = 0;	// 
		
		int day = 301;	// 조건 시작일
		int idx = 0;
		
				
		// 12월 1일보다 작은 경우 반복
		while (day < 1201) {
			boolean check = false;
			for(int i=idx; i<n; i++) {
				// 피는 날이 지는 날보다 이후면 끝내기
				if (flowers[i].start > day) {
					break;
				}
				// 피는 날이 지는 날 전이고, max 값보다 작으면
				if (flowers[i].start<=day && max<flowers[i].end) {
					max = flowers[i].end;	// max에 지는 날 저장
					idx++;
					check = true;	// 체크
				}
			}
			
			// 조건에 맞는 꽃이 있으면 day 갱신, 카운트+1
			if (check) {
				day = max;
				cnt++;
			} else {
				break;
			}
		}
		
		if (max < 1201) {
			System.out.println(0);
		} else {
			System.out.println(cnt);
		}
		
		

	}
	

}
