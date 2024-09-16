package BOJ_2533_사회망서비스SNS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/*
 *  아이디어 리프노드의 부모노드는 무조건 얼리어답터여야됨
 *  단방향 그래프로 풀었음
 *  주변 노드가 다 얼리어답터면 isAdapter를 false로 바꿔줌
 */
public class Main {

	public static void main(String[] args) throws IOException {
		
		// 시간 초과나서 버퍼 써봤는데 버퍼 써서 해결될 문제가 아님
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int node_num = Integer.parseInt(br.readLine());
		
		// 얼리어답터인지 확인하는 배열
		boolean[] isAdapter = new boolean[node_num+1]; // 0번 인덱스 버림
		
		// 사용되었는지 확인하는 배열 -> 부모노드 판별
		boolean[] isUsed = new boolean[node_num+1]; // 0번 인덱스 버림
		ArrayList<Integer>[] graph = new ArrayList[node_num+1];
		
		// 입력받은 숫자를 순서대로 저장할 리스트 선언 -> 
		// 리스트를 뒤에서 부터 순회하면 리프노드부터 루트노드까지 올라온다.
		ArrayList<Integer> order_list = new ArrayList<>();
		
		for(int i = 1; i <= node_num; i++) graph[i] = new ArrayList<>();
		
		for(int i = 0; i < node_num-1; i++) {
			String[] nodes = br.readLine().split(" ");
			int node = Integer.parseInt(nodes[0]);
			int node2 = Integer.parseInt(nodes[1]);
			
			// 처음에는 둘 다 사용하지 않았기에 들어있지 않기 때문에 
			// 두 입력값 다 리스트와 배열에 넣어준다.
			if(order_list.size() == 0) {
				isUsed[node] = true;
				isUsed[node2] = true;
				order_list.add(node);
				order_list.add(node2);
			}
			
			// 첫 번 째 입력값(node)의 isUsed가 true면 이미 있는 노드 -> 즉, 부모노드
			// 두 번 째 입력값(node2)의  isUsed를 true로 바꿔주고 list에 추가해준다.
			if(isUsed[node] == true) {
				if(!isAdapter[node]) isAdapter[node] = true;
				graph[node].add(node2);
				isUsed[node2] = true;
				if(!order_list.contains(node2)) order_list.add(node2);
				
			}
			// 두 번 째 입력값(node2)의 isUsed가 true면 이미 있는 노드 -> 즉, 부모노드
			// 첫 번 째 입력값(node)의  isUsed를 true로 바꿔주고 list에 추가해준다.
			else {
				if(!isAdapter[node2]) isAdapter[node2] = true;
				graph[node2].add(node);
				isUsed[node] = true;
				if(!order_list.contains(node)) order_list.add(node);
			}
		}
		
		// 16.7min?? -> DP로 해결해야함 -> 모르겠음 + 구현은 맞는 거 같음
		// 리스트의 뒤에서부터 돌면서 특정 노드가 얼리어답터이면 주변 노드가 얼리어답터인지 확인한다.
		// 주변 노드가 다 얼리어답터라면 현재 노드를 얼리어답터가 아닌 것으로 바꿔준다 -> isAdaptor() = false;
		for(int i = order_list.size()-1; i >= 0; i--) {
			int order_number = order_list.get(i);
			if(isAdapter[order_number] == true) {
				for(int j = 0; j < graph[order_number].size(); j++) {
					int idx = graph[order_number].get(j);
					if(!isAdapter[idx])break;
					else if(j == graph[order_number].size() - 1)isAdapter[order_number] = false;
				}
			}
		}
		
		int adapter_count = 0;
		for(int i = 1; i <= node_num; i++) if(isAdapter[i] == true) adapter_count++;
		System.out.println(adapter_count);
	}

}