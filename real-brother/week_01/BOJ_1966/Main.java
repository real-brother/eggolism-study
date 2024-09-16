package BOJ_1966;

import java.io.*;
import java.util.*;


public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int tc = Integer.parseInt(br.readLine());

		for (int i = 0; i < tc; i++) {

			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int n = Integer.parseInt(st.nextToken());
			int m = Integer.parseInt(st.nextToken());
			StringTokenizer stNums = new StringTokenizer(br.readLine(), " ");
			Deque<Integer> printQueue = new ArrayDeque<Integer>();
			for (int j = 0; j < n; j++) {
				printQueue.add(Integer.parseInt(stNums.nextToken()));
			}

			int answer = whenDocumentPrint(n, m, printQueue);
			System.out.println(answer);

		}
		br.close();

	}

	public static int whenDocumentPrint(int n, int when, Deque<Integer> printQueue) {
		int ans = 0;
		while (printQueue.size() > 0) {
			// 우선순위 가장 높은거 구하기
			int maxValue = printQueue.stream().mapToInt(Integer::intValue).max().orElseThrow();
			// 맨 앞 값 가져오기
			int front = printQueue.pollFirst();
			when -= 1; // 하나씩 줄어드니까 target위치도 -1
			// 꺼낸 값이 최대 값이라면
			if (maxValue == front) {
				ans += 1;
				if (when < 0) { // 꺼내어진 값이 target인덱스와 같다면 큐 종료 -> 몇번째로 출력되는지 리턴
					return ans;
				}
			} //꺼낸 값이 우선순위에 맞지 않다면 
			else {
				printQueue.addLast(front); // 프린트큐의 맨뒤로 보내기
				if (when < 0) { // target값이 꺼내졌는데 우선순위가 아님 -> when 값을 배열의 마지막 index로 바꿔주기
					when = printQueue.size() - 1;
				}
			}
			
		}
		return 0;
	}
}
