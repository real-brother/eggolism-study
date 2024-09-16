package BOJ_1197_최소스패닝트리;

/*
 *   수업시간에 배운 크루스칼 알고리즘 사용
 */

import java.util.Arrays;
import java.util.Scanner;

public class Main {
	static int[] p;
	static int[][] graph;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int V = sc.nextInt();
		int E = sc.nextInt();
		
		graph = new int[E][3];
		for(int i = 0; i < E; i++) {
			graph[i][0] = sc.nextInt();
			graph[i][1] = sc.nextInt();
			graph[i][2] = sc.nextInt();
		}
		
		p = new int[V+1];
		for(int i = 1; i <= V; i++) p[i] = i;
		
		Arrays.sort(graph, (o1, o2) -> Integer.compare(o1[2], o2[2]));
		
		long sum = 0;
		int count = 0;
		
		for(int i = 0; i < E; i++) {
			int num1 = findSet(graph[i][0]);
			int num2 = findSet(graph[i][1]);
			if(num1 != num2) {
				union(num1, num2);
				sum += graph[i][2];
				count++;
			}
			
			if(count == V-1) break; 
		}
		
		System.out.println(sum);
		
	}
	
	static int findSet(int num) {
		if(num != p[num]) p[num] = findSet(p[num]);
		return p[num];
	}
	
	static void union(int num1, int num2) {
		p[num2] = p[num1];
	}

}