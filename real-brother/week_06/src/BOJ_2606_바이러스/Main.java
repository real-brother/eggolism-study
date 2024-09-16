package BOJ_2606_바이러스;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
	static Scanner sc = new Scanner(System.in);
	static int computerCount;
	static int linkCount;
	static int ansCount = 0;
	static List<List<Integer>> virusGraph;
	static boolean[] visited;
	static int startVirus = 1;
	
    public static void main(String[] args) {
        computerCount = sc.nextInt();
        linkCount = sc.nextInt();
        virusGraph = new ArrayList<List<Integer>>();
        visited = new boolean[computerCount + 1];
        for (int i = 0; i <= computerCount; i++) {
        	virusGraph.add(new ArrayList<Integer>());
		}
        
        // 그래프 구축
        for (int i = 0; i < linkCount; i++) {
        	int n = sc.nextInt();
        	int m = sc.nextInt();
        	virusGraph.get(n).add(m);
        	virusGraph.get(m).add(n);
		}
        
        // 시작지점부터 dfs돌리기
        dfs(startVirus);
        
        // 1번 컴퓨터를 제외한 컴퓨터수 세기
        System.out.println(ansCount-1);
        
    }
    
    
	private static void dfs(int start) {
		// 방문표시하며 dfs돌때마다 +1
		visited[start] = true;
		ansCount += 1;
		// 연결되어 있으며 방문하지 않았다면 dfs더 돌리기
		for (int node : virusGraph.get(start)) {
			if (visited[node] == false) dfs(node);
		}
	}
}
