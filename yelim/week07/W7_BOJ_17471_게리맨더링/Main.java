package W7_BOJ_17471_게리맨더링;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	static int n;
	static List<List<Integer>> graph = new ArrayList<>();
	static int[] population;
	static boolean[] selected;
	static boolean[] visited;
	static int min;

	public static void main(String[] args) throws IOException {
		/* 문제) 게리맨더링
		 * 공평하게 선거구를 나누기 위해 두 선거구에 포함된 인구의 차이를 최소로 하려고 한다. 
		 * 백준시의 정보가 주어졌을 때, 인구 차이의 최솟값을 구해보자.
		 * 
		 * 첫째 줄에 구역의 개수 N이 주어진다. 
		 * 둘째 줄에 구역의 인구가 1번 구역부터 N번 구역까지 순서대로 주어진다. 인구는 공백으로 구분되어져 있다.
		 * 셋째 줄부터 N개의 줄에 각 구역과 인접한 구역의 정보가 주어진다. 
		 * 각 정보의 첫 번째 정수는 그 구역과 인접한 구역의 수이고, 이후 인접한 구역의 번호가 주어진다. 
		 * 모든 값은 정수로 구분되어져 있다.
		 * 
		 * 구역 A가 구역 B와 인접하면 구역 B도 구역 A와 인접하다. 인접한 구역이 없을 수도 있다.
		 */
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		
		min = Integer.MAX_VALUE;
		
		population = new int[n];
		selected = new boolean[n];
		visited = new boolean[n];
		
		// 인구 수 입력받기
		for(int i=0; i<n; i++) {
			population[i] = Integer.parseInt(st.nextToken());
		}
		
		// 내부 리스트 선언
		for(int i=0; i<n; i++) {
			graph.add(new ArrayList<>());
		}
		
		// 연결되어있는 구역 표시
		for(int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			int num = Integer.parseInt(st.nextToken());
			for(int j=0; j<num; j++) {
				int x = Integer.parseInt(st.nextToken());
				graph.get(i).add(x-1);
			}
		}
		
		comb(0);
		if (min == Integer.MAX_VALUE) {
			System.out.println(-1);
		}
		else {
			System.out.println(min);
		}

	}
	
	static void comb(int idx) {
		if (idx==n) {
			List<Integer> a = new ArrayList<>();
			List<Integer> b = new ArrayList<>();
			
			for(int i=0; i<n; i++) {
				if (selected[i]) {
					a.add(i);
				}
				else {
					b.add(i);
				}
			}
			
			// 한 선거구에 구역이 하나도 없으면 리턴
			if (a.size()==0 || b.size()==0) {
				return;
			}
			
			// 연결되어 있으면
			if (check(a) && check(b)) {
				getDiff();
			}
			return;
		}
		
		selected[idx] = true;
		comb(idx+1);
		
		selected[idx] = false;
		comb(idx+1);
	}
	
	// 구역들이 연결되어있는지 확인 BFS
	static boolean check(List<Integer> list) {
		Queue<Integer> q = new LinkedList<>();
		visited = new boolean[n];
		visited[list.get(0)] = true;
		q.offer(list.get(0));
		
		int cnt = 1;
		while(!q.isEmpty()) {
			int cur = q.poll();
			
			for(int i=0; i<graph.get(cur).size(); i++) {
				int x = graph.get(cur).get(i);
				if (list.contains(x) && !visited[x]) {
					q.offer(x);
					visited[x] = true;
					cnt++;
				}
			}
		}
		
		if (cnt == list.size()) return true;
		else return false;
		
	}
	
	// 인구차 구하기
	static void getDiff() {
		int pa = 0;
		int pb = 0;
		
		for(int i=0; i<n; i++) {
			if (selected[i]) {
				pa += population[i];
			}
			else {
				pb += population[i];
			}
		}
		
		int diff = Math.abs(pa-pb);
		min = Math.min(diff, min);
	}

}
