package W7_BOJ_1939_중량제한;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;


public class Main {
	
	static int n, m;
	static List<bridge> graph = new ArrayList<>(); 
	static boolean[] check;

	public static void main(String[] args) throws IOException {
		/* 문제) 중량제한
		 * 각각의 다리마다 중량제한이 있기 때문에 무턱대고 물품을 옮길 순 없다. 
		 * 한 번의 이동에서 옮길 수 있는 물품들의 중량의 최댓값을 구하는 프로그램을 작성하시오.
		 */
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		
		int left = 0;
		int right = 0;
		
		for(int i=0; i<m; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			
			graph.add(a, new bridge(b, w));
			graph.add(b, new bridge(a, w));
			
			// right에 최대값 저장
			right = Math.max(right, w);
		}
		
		st = new StringTokenizer(br.readLine());
		int f1 = Integer.parseInt(st.nextToken());
		int f2 = Integer.parseInt(st.nextToken());
		
		
		// 이분탐색
		while (left <= right) {
			int mid = (left+right)/2;
			int ans = -1;
			check = new boolean[n+1];
			
		}

	}
	
	
	

}

class bridge {
	int dep;
	int weight;
	
	public bridge(int dep, int weight) {
		this.dep = dep;
		this.weight = weight;
	}

}
