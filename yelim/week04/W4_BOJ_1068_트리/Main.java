package W4_BOJ_1068_트리;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	
	static int n;
	static int[] parents;
	static boolean[] visited;
	static int root;
	static int delete;
	static int count;

	public static void main(String[] args) throws IOException {
		/* 문제)
		 * 트리가 주어졌을 때, 노드 하나를 지울 것이다. 
		 * 그 때, 남은 트리에서 리프 노드의 개수를 구하는 프로그램을 작성하시오. 노드를 지우면 그 노드와 노드의 모든 자손이 트리에서 제거된다.
		 * 
		 * 첫째 줄에 트리의 노드의 개수 N이 주어진다. N은 50보다 작거나 같은 자연수이다. 
		 * 둘째 줄에는 0번 노드부터 N-1번 노드까지, 각 노드의 부모가 주어진다. 
		 * 만약 부모가 없다면 (루트) -1이 주어진다. 셋째 줄에는 지울 노드의 번호가 주어진다.
		 */
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		
		parents = new int[n];
		
		// 부모노드 번호 입력받기
		for(int i=0; i<n; i++) {
			parents[i] = Integer.parseInt(st.nextToken());
			
			// -1이면 해당 인덱스가 루트
			if (parents[i] == -1) {
				root = i;
			}
		}
		st = new StringTokenizer(br.readLine());
		
		// 삭제할 노드 번호
		delete = Integer.parseInt(st.nextToken());
		
		// 삭제 메소드 호출
		deleteNode(delete);
//		System.out.println(Arrays.toString(parents));
		
		// 리프노드 수 저장할 변수
		count = 0;
		
		// 방문체크 배열
		visited = new boolean[n];
		// dfs 호출
		dfs(root);
		
		System.out.println(count);

	}
	
	// 삭제 메소드
	static void deleteNode(int idx) {
		// 삭제할 노드번호의 값을 임의의 값(-2)으로 설정
		parents[idx] = -2;
		// 반복 돌면서 재귀 호출하여 연쇄삭제
		for(int i=0; i<n; i++) {
			if (parents[i] == idx) {
				deleteNode(i);
			}
		}
	}
	
	// dfs 탐색하며 삭제한 값이 아닌 경우 반복 돌며 리프노드 체크해주고 리프노드인 경우 count 증가
	static void dfs(int root) {
		visited[root] = true;
		boolean check = true;
		if (parents[root] != -2) {
			for(int i=0; i<n; i++) {
				if (parents[i]==root && visited[i]==false) {
					dfs(i);
					check = false;
				}
			}
			if (check) count++;
		}
		
	}

}
