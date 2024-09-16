package W17_BOJ_9205_맥주마시면서;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	static class loc {
		int x, y;
		
		public loc(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	static int n;	// 편의점 수
	static List<loc> store;	// 편의점
	static loc home;	// 집
	static loc rock;	// 도착지

	public static void main(String[] args) throws IOException {
		/* 문제) 9205_맥주 마시면서 걸어가기
		 * 
		 * 맥주는 최대 20병, 50m에 한 개씩
		 */
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		
		for(int t=0; t<T; t++) {
			n = Integer.parseInt(br.readLine());	// 편의점 수

			store = new ArrayList<>();
			
			// 좌표 저장
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			// 집 좌표
			int hx = Integer.parseInt(st.nextToken());
			int hy = Integer.parseInt(st.nextToken());
			home = new loc(hx, hy);
			
			// 편의점 좌표
			for(int i=0; i<n; i++) {
				st = new StringTokenizer(br.readLine());
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				store.add(new loc(x, y));
			}
			
			// 락 페스티벌 좌표
			st = new StringTokenizer(br.readLine());
			int rx = Integer.parseInt(st.nextToken());
			int ry = Integer.parseInt(st.nextToken());
			rock = new loc(rx, ry);
			
			String result = bfs();
			System.out.println(result);
			
		}	// 테케 끝

	}
	
	static String bfs() {
		Queue<loc> q = new LinkedList<>();
		boolean[] visited = new boolean[n];
		
		q.add(home);
		while (!q.isEmpty()) {
			int x = q.peek().x;
			int y = q.peek().y;
			q.remove();
			
			if (Math.abs(x-rock.x) + Math.abs(y-rock.y) <= 1000) {
				return "happy";
			}
			
			// 편의점 들러!
			for(int i=0; i<n; i++) {
				if (!visited[i]) {
					int dist = Math.abs(x-store.get(i).x) + Math.abs(y-store.get(i).y);
					if (dist <= 1000) {
						visited[i] = true;
						q.add(store.get(i));
					}
				}
			}
		}
		
		return "sad";
		
	}

}
