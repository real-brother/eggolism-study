package BOJ_1504_특정한최단경로;

/*
 *  처음에 바보같이 다익스트라 메서드 내에 뭘 추가하려고 했는데
 *  생각해보니까 두 정점 사이의 최단거리를 구하는 알고리즘이니까
 *  1 ~ point1 ~ point2 ~ N 또는 1 ~ poin2 ~ point1 ~ N
 *  이렇게 2가지 중 작은 것을 답으로 출력하면 됐다.
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Main {
	static int N;
	static ArrayList<Node>[] nodes;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		int E = sc.nextInt();
		
		nodes = new ArrayList[N+1];
		for(int i = 0; i <= N; i++) nodes[i] = new ArrayList<>();
		for(int i = 0; i < E; i++) {
			int a = sc.nextInt();
			int b = sc.nextInt();
			int c = sc.nextInt();
			nodes[a].add(new Node(b, c));
			nodes[b].add(new Node(a, c));
		}

		int point1 = sc.nextInt();
		int point2 = sc.nextInt();
		
		// 찾지 못할 시 return값을 Integer.MAX_VALUE로 해서 long으로 선언
		long temp = dijkstra(point1, point2);
		long one = dijkstra(1, point1) + temp + dijkstra(point2 , N);
		long two = dijkstra(1, point2) + temp + dijkstra(point1 , N);
		long result = Math.min(one, two);
		
		if(result >= Integer.MAX_VALUE) System.out.println(-1);
		else System.out.println(result);
			
	}
	
	// 그냥 다익스트라
	static int dijkstra(int start, int end) {
		boolean[] visited = new boolean[N+1];
		int[] dist = new int[N+1];
		Arrays.fill(dist, Integer.MAX_VALUE);
		dist[start] = 0;
		PriorityQueue<Node> queue = new PriorityQueue<>();
		queue.offer(new Node(start, 0));
		
		while(!queue.isEmpty()) {
			Node curr = queue.poll();
			
			if(visited[curr.end]) continue;
			visited[curr.end] = true;
			
			// end(도착지)에 도달하면 거리를 반환
			if(curr.end == end) return dist[end];
			
			for(Node node : nodes[curr.end]) {
				if(!visited[node.end] && dist[node.end] > dist[curr.end] + node.weight) {
					dist[node.end] = dist[curr.end] + node.weight;
					queue.offer(new Node(node.end, dist[node.end]));
				}
			}
		}
		return Integer.MAX_VALUE;
	}

}

class Node implements Comparable<Node>{
	int end;
	int weight;
	
	Node(int end, int weight){
		this.end = end;
		this.weight = weight;
	}

	@Override
	public int compareTo(Node o) {
		return this.weight - o.weight;
	}
}