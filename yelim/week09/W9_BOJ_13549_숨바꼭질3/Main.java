package W9_BOJ_13549_숨바꼭질3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	static int n, k;
	static boolean[] visited;
	static int min = Integer.MAX_VALUE;

	public static void main(String[] args) throws IOException {
		/* 문제) 숨바꼭질 3
		 * 수빈이는 현재 점 N(0 ≤ N ≤ 100,000)에 있고, 동생은 점 K(0 ≤ K ≤ 100,000)에 있다. 
		 * 수빈이의 위치가 X일 때 걷는다면 1초 후에 X-1 또는 X+1로 이동하게 된다. 
		 * 순간이동을 하는 경우에는 0초 후에 2*X의 위치로 이동하게 된다.
		 * 
		 * 수빈이가 동생을 찾을 수 있는 가장 빠른 시간이 몇 초 후인지 구하는 프로그램을 작성
		 */
		
		/* 반례)
		 * 4 6 => 1 나와야 함
		 * 4-3-6
		 * 45%?? 에서 틀림
		 */
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());

		visited = new boolean[100001];
		
		bfs(n);
		System.out.println(min);
		
	}
	

	static void bfs(int num) {
		Queue<Loc> q = new LinkedList<>();
		// 큐에 수빈이의 현재 위치와 걸린 시간 저장
		q.offer(new Loc(num, 0));
		
		while (!q.isEmpty()) {
			Loc cur = q.poll();
			// 방문체크
			visited[cur.loc] = true;
			
			// 동생 만났으면 최소값 비교해서 구해주기
			if (cur.loc == k) {
				min = Math.min(min, cur.time);
			}
			
			// 순간이동 할 경우
			// 현재 수빈이의 위치에 2를 곱한 곳이 범위 내에 있어야 하고, 방문한 적이 없어야 함
			if (cur.loc*2 <= 100000 && !visited[cur.loc*2]) {
				// 큐에 추가, 방문체크
				q.offer(new Loc(cur.loc*2, cur.time));
				visited[cur.loc*2] = true;
			}
			
			// 순서... 아래 +1과 순서 바꾸니ㅣ까 반례 맞음..
			// 현재 위치-1로 가는 경우 마찬가지로 범위 내에 있고, 방문한 적 없어야 함
			// 시간 +1초 
			if (cur.loc-1 >= 0 && !visited[cur.loc-1]) {
				q.offer(new Loc(cur.loc-1, cur.time+1));
				visited[cur.loc-1] = true;
			}
			
			// +1 하는 경우도 같음.
			// 시간 +1초
			if (cur.loc+1 <= 100000 && !visited[cur.loc+1]) {
				q.offer(new Loc(cur.loc+1, cur.time+1));
				visited[cur.loc+1] = true;
			}
			
			
		}
		
	}
	
	static class Loc {
		int loc;
		int time;
		
		public Loc(int loc, int time) {
			this.loc = loc;
			this.time = time;
		}
	}

}
