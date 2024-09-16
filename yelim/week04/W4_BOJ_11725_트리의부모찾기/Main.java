package W4_BOJ_11725_트리의부모찾기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	
	static List<List<Integer>> graph = new ArrayList<>();	// 인접 리스트
	static boolean[] visited;	// 방문 표시
	static int[] parents;	// 인덱스 노드의 부모노드 저장할 배열

	public static void main(String[] args) throws IOException {
		
		/* 문제)
		 * 루트 없는 트리가 주어진다. 이때, 트리의 루트를 1이라고 정했을 때, 
		 * 각 노드의 부모를 구하는 프로그램을 작성하시오.
		 * 
		 * 첫째 줄에 노드의 개수 N (2 ≤ N ≤ 100,000)이 주어진다. 
		 * 둘째 줄부터 N-1개의 줄에 트리 상에서 연결된 두 정점이 주어진다.
		 */
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		
		// 인접리스트 만들 리스트 생성
		for(int i=0; i<n+1; i++) {
			graph.add(new ArrayList<>());
		}
		
		// 연결된 두 정점 입력 받고 인접리스트에 저장
		for(int i=1; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			graph.get(a).add(b);
			graph.get(b).add(a);
		}
		
		// 방문 표시할 배열
		visited = new boolean[n+1];
		// 부모노드 저장할 배열
		parents = new int[n+1];
		
		// dfs 호출 - 루트노드부터
		dfs(1);
		
		// 출력
		for(int i=2; i<parents.length; i++) {
			System.out.println(parents[i]);
		}

	}
	
	// dfs 메소드
	static void dfs(int num) {
		visited[num] = true;	// 방문 체크
		
		/* 인접 리스트에 저장되어 있는 num번 리스트의 사이즈만큼 반복
		 * idx에 num번 리스트의 i번째 값 저장해주고,
		 * idx를 방문한 적이 없으면 idx의 부모노드는 num
		 * idx를 루트로 다시 dfs 호출
		 */
		for(int i=0; i<graph.get(num).size(); i++) {
			int idx = graph.get(num).get(i);
			
			if (!visited[idx]) {
				parents[idx] = num;
				dfs(idx);
			}
		}
	}
	
	// bfs??
	// https://velog.io/@darak/BJ-11725-%ED%8A%B8%EB%A6%AC%EC%9D%98-%EB%B6%80%EB%AA%A8-%EC%B0%BE%EA%B8%B0-Java

}
