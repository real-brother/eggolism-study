package BOJ_2606_바이러스;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

	static ArrayList<Integer>[] network = new ArrayList[101];
	static List<Boolean> visited = new ArrayList<>();
	static int count = -1;
	
	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		
		for(int i = 0; i <= N; i++) visited.add(false);
		
		int M = sc.nextInt();
		
		for (int i = 0; i < network.length; i++) network[i] = new ArrayList<>();
        
		for(int i = 0; i < M; i++) {
			int a = sc.nextInt();
			int b = sc.nextInt();
			network[a].add(b);
			network[b].add(a);
		}

		System.out.print(dfs(1));

	}
	
	
	public static int dfs(int start) {
		visited.set(start, true);
		count++;
		for(int i = 0; i < network[start].size(); i++) {
			int y = network[start].get(i);
			if(!visited.get(y)) dfs(y);
		}
		return count;
	}

}