import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_BOJ_1202_보석도둑 {
	
	static class Treasure {
		int m;
		int v;
		
		public Treasure(int m, int v) {
			this.m = m;
			this.v = v;
		}
	}

	public static void main(String[] args) throws IOException {
		/* 문제) 1202_보석도둑
		 * 
		 * 상덕이가 털 보석점에는 보석이 총 N개 있다. 각 보석은 무게 Mi와 가격 Vi를 가지고 있다. 
		 * 상덕이는 가방을 K개 가지고 있고, 각 가방에 담을 수 있는 최대 무게는 Ci이다. 
		 * 가방에는 최대 한 개의 보석만 넣을 수 있다.
		 * 상덕이가 훔칠 수 있는 보석의 최대 가격을 구하는 프로그램을 작성하시오.
		 */
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());	// 보석 개수
		int k = Integer.parseInt(st.nextToken());	// 가방 개수
		
		Treasure[] t = new Treasure[n];	// 보석 정보
		for(int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			t[i] = new Treasure(a, b);
		}
		
		int[] bags = new int[k];	// 가방 최대 무게 담을 배열
		for(int i=0; i<k; i++) {
			st = new StringTokenizer(br.readLine());
			bags[i] = Integer.parseInt(st.nextToken());
		}
		
		// 가방 최대 무게 오름차순 정렬
		Arrays.sort(bags);
		
		// 보석 정렬 (무게 오름차순, 같으면 가격 내림차순)
		// 같을 경우는 정렬 안해도 됨 어차피 pq에서 하니까
		Arrays.sort(t, new Comparator<Treasure>() {
			@Override
			public int compare(Treasure o1, Treasure o2) {
				if (o1.m == o2.m) {
					return o2.v - o1.v;
				}
				return o1.m - o2.m;
			}
			
		});
		
		
		// 가격 기준 내림차순 정렬
		PriorityQueue<Integer> pq = new PriorityQueue<>((o1, o2) -> o2-o1);
		
		long sum = 0;
		int idx = 0;

		for(int i=0; i<k; i++) {
			// 중복 저장 방지
			while (idx<n && t[idx].m<=bags[i]) {
				pq.add(t[idx++].v);
			}
			

			// 내림차순 정렬된 우선순위 큐에서 처음 값(가장 큰 값)을 sum에 더해준다.
			if (!pq.isEmpty()) {
//				System.out.println(pq.peek());
				sum += pq.poll();
			}
		}
		
		System.out.println(sum);

	}

}
