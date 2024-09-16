package W12_BOJ_15686_치킨배달;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	
	static class House {
		int r, c;
		
		public House(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
	
	static class Chicken {
		int r, c;
		
		public Chicken(int r, int c) {
			this.r = r;
			this.c = c;
		}

		@Override
		public String toString() {
			return "Chicken [r=" + r + ", c=" + c + "]";
		}
		
		
	}
	
	static int n, m;	// 도시 크기, 최대 치킨 집 수
	static int[][] map;
	static boolean[] visited;
	static List<Chicken> chicken;
	static List<House> house;
	static int min = Integer.MAX_VALUE;
	static int ans = Integer.MAX_VALUE;

	public static void main(String[] args) throws IOException {
		/* 문제) 15686_치킨배달
		 * 집과 치킨집 사이의 거리 = 치킨 거리
		 * 도시의 치킨 거리 = 모든 집의 치킨 거리의 합
		 * M개의 치킨 집을 고르고 나머지는 폐업
		 * 어떻게 골라야 도시의 치킨 거리가 가장 작을까
		 */
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		map = new int[n+1][n+1];
		
		chicken = new ArrayList<>();
		house = new ArrayList<>();
		
		for(int i=1; i<=n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=1; j<=n; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				// 1이면 house 리스트에 좌표 저장
				if (map[i][j] == 1) {
					house.add(new House(i, j));
				}
				// 2면 chicken 리스트에 좌표 저장
				if (map[i][j] == 2) {
					chicken.add(new Chicken(i, j));
				}
			}
		}
		
		// chicken 리스트의 크기로 방문 배열 생성
		visited = new boolean[chicken.size()];
		
		comb(0, 0);
		System.out.println(ans);

	}
	
	// 조합 메소드 (백트래킹)
	static void comb(int idx, int cnt) {
		// m개 다 골랐어!
		if (cnt==m) {
			// 도시의 치킨 거리 구하기
			distance();
			return;
		}
		
		// 탐색 안한 인덱스부터 chicken 리스트 돌며 방문 안한 치킨 집 선택(방문처리)
		for(int i=idx; i<chicken.size(); i++) {
			if (!visited[i]) {
				visited[i] = true;
				comb(i+1, cnt+1);
				visited[i] = false;
			}
		}
		
	}
	
	// 치킨 거리 구하여 도시 치킨 거리의 최소값 구하기
	static void distance() {
		// 도시 치킨 거리 저장할 변수
		int sum = 0;
		
		for(int i=0; i<house.size(); i++) {
			// 집과 치킨집 거리 최소값 초기화
			min = Integer.MAX_VALUE;
			
			for(int j=0; j<chicken.size(); j++) {
				// 선택된 치킨집이면
				if (visited[j]) {
					// 집과 치킨집 사이의 거리 구하기
					int d = Math.abs(chicken.get(j).r - house.get(i).r) + Math.abs(chicken.get(j).c - house.get(i).c);
					// 최소값 저장
					min = Math.min(min, d);
				}
			}
			// 도시 치킨 거리 변수에 더해주기
			sum += min;
			
		}
		// 도시 치킨 거리의 최소값 구해주기
		ans = Math.min(ans, sum);
	}

}
