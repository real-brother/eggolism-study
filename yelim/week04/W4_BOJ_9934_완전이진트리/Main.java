package W4_BOJ_9934_완전이진트리;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	
	static int k;
	static int[] arr;
	static List<List<Integer>> nodes = new ArrayList<>();

	public static void main(String[] args) throws IOException {
		/* 문제)
		 * 가장 처음에 상근이는 트리의 루트에 있는 빌딩 앞에 서있다.
		 * 현재 빌딩의 왼쪽 자식에 있는 빌딩에 아직 들어가지 않았다면, 왼쪽 자식으로 이동한다.
		 * 현재 있는 노드가 왼쪽 자식을 가지고 있지 않거나 왼쪽 자식에 있는 빌딩을 이미 들어갔다면, 
		 * 현재 노드에 있는 빌딩을 들어가고 종이에 번호를 적는다.
		 * 현재 빌딩을 이미 들어갔다 온 상태이고, 오른쪽 자식을 가지고 있는 경우에는 오른쪽 자식으로 이동한다.
		 * 현재 빌딩과 왼쪽, 오른쪽 자식에 있는 빌딩을 모두 방문했다면, 부모 노드로 이동한다.
		 * 
		 * 상근이가 종이에 적은 순서가 모두 주어졌을 때, 각 레벨에 있는 빌딩의 번호를 구하는 프로그램을 작성하시오.
		 */
		
		/* 설계)
		 * 상근이가 종이에 적은 순서 => 중위순회한 순서
		 * 중위순회한 값에서 노드번호를 구해야 함...
		 * 노드 개수는 2^k-1개
		 * 노드개수/2 +1 = 루트노드 (1번부터 시작함)
		 * 루트노드는 늘 가운데 값임을 이용
		 */
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		k = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		int num = (int)Math.pow(2, k)-1;	// 노드의 수
		
		// 상근이가 종이에 적은 순서 = 중위순회한 순서
		arr = new int[num];
		
		// 노드 번호 저장할 리스트 -> 레벨의 수=행의 수
		for(int i=0; i<k; i++) {
			nodes.add(new ArrayList<>());
		}
		
		// 배열에 값 입력받기
		for(int i=0; i<num; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		search(0, num, 0);
		
		for(int i=0; i<k; i++) {
			for(int j=0; j<nodes.get(i).size(); j++) {
				System.out.print(nodes.get(i).get(j)+" ");
			}
			System.out.println();
		}
		

	}
	
	// 탐색하는 메소드
	static void search(int start, int end, int level) {
		// 레벨이 입력받은 레벨의 수와 같을 때 재귀 탈출
		if (level == k) {
			return;
		}
		
		// 루트 노드는 항상 가운데 값
		int root = (start+end)/2;
		
		// 레벨의 첫 값에 루트 값 넣어주기
		nodes.get(level).add(arr[root]);
		
		// 왼쪽 트리 순회
		search(start, root-1, level+1);
		// 오른쪽 트리 순회
		search(root+1, end, level+1);
	}
	

}
