package BOJ_1967_트리의지름;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;


// 길이(가중치)를 저장하기 위해서 Node class에서 length를 만들어준다.
class Node {
	int data;
	int length;
	
	Node(){}
	
	Node(int data, int length){
		this.data = data;
		this.length = length;
	}
}

public class Main {
	// 인접리스트의 type을 Node로 선언
	static ArrayList<Node>[] graph;
	static int max;
	static int sum;
	static int N;
	static int index;

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		
		graph = new ArrayList[N+1];
		
		for(int i = 1; i <= N; i++) graph[i] = new ArrayList<>();
		
		// 양방향 그래프로 만들어준다.
		for(int i = 1; i < N; i++) {
			int node1 = sc.nextInt();
			int node2 = sc.nextInt();
			int length = sc.nextInt();
			graph[node1].add(new Node(node2, length));
			graph[node2].add(new Node(node1, length));
		}
		
		// max를 0으로 해준다. 노드의 개수가 1이라면 길이가 0이어서
		// Integer.MIN_VALUE x
		max = 0;
		index = 1;
		
		// bfs를 아무 정점에서 한 번 돌았을 때 가장 먼 곳에서 
		// bfs를 한 번 더 돌면 최대 길이(트리의 지름)가 나온다.
		bfs(1);
		bfs(index);
	
		System.out.println(max);
	}
	
	// bfs로 탐색
	static void bfs(int start) {
		Queue<int[]> queue = new LinkedList<>();
		boolean[] visited = new boolean[N+1];
		visited[start] = true;
		
		// 큐에 크기가 2인 배열로 넣어준다.(앞에꺼는 노드 번호, 뒤에꺼는 길이의 합)
		// ex) 1번 노드에서 시작하면 1번 노드에서의 합은 0
		// 3번 노드에서의 합은 2
		// 5번 노드에서의 합은 13, 6번 노드에서의 합은 11 이런식
		queue.offer(new int[]{start, 0});

		while(!queue.isEmpty()) {
			int[] now_node = queue.poll();
			int node_num = now_node[0];
			int before_sum = now_node[1];
			for(int i = 0; i < graph[node_num].size(); i++) {
				int next_node = graph[node_num].get(i).data;
				if(!visited[next_node]) {
					// sum값을 이전 sum값 5, 6번 노드면 5, 6번 노드의 길이 + 3번 노드까지의 합 
					sum = graph[node_num].get(i).length + before_sum;
					queue.offer(new int[]{next_node, sum});
					visited[next_node] = true;
				}
				// max보다 sum이 크면 sum을 저장하고 그 index를 저장해준다.
				if(max < sum) {
					max = sum;
					index = next_node;
				}
			}
		}
		
	}
}