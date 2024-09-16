package BOJ_1753_최단경로;

/*
 *  다익스트라 + 우선순위 큐
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Main {
	static int V, E;
	static ArrayList<Node>[] list;
	static final int INF = Integer.MAX_VALUE;
	static int[] dist;
	static boolean[] visited;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		V = sc.nextInt();
		E = sc.nextInt();
		
		int start = sc.nextInt();
		
		list = new ArrayList[V+1];
		
		for(int i = 1; i <= V; i++) list[i] = new ArrayList<>();
		for(int i = 0; i < E; i++) list[sc.nextInt()].add(new Node(sc.nextInt(), sc.nextInt()));
		
		visited = new boolean[V+1];
		dist = new int[V+1];
		Arrays.fill(dist, INF);
		dijkstra(start);
		
		for(int i = 1; i <= V; i++) {
			if(dist[i] == INF) System.out.println("INF");
			else System.out.println(dist[i]);
		}
	}
	
	static void dijkstra(int start) {
		PriorityQueue<Node> queue = new PriorityQueue<>();
		dist[start] = 0;
		queue.offer(new Node(start, 0));
		while(!queue.isEmpty()) {
			Node curr = queue.poll();
			
			if(visited[curr.end]) continue;
			
			visited[curr.end] = true;
			
			for(Node node : list[curr.end]) {
				if(!visited[node.end] && dist[node.end] > dist[curr.end] + node.weight) {
					dist[node.end] = dist[curr.end] + node.weight;
					queue.offer(new Node(node.end, dist[node.end]));
				}
			}
		}
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