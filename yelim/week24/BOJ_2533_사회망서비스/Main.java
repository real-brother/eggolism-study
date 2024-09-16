package W24_BOJ_2533_사회망서비스;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	
	static int n;
	static List<Integer>[] graph;
	static boolean[] visited;
	static int[][] dp;

	public static void main(String[] args) throws IOException {
		/* 문제) 2533_사회망 서비스(SNS)
		 * 
		 * 사람은 정점으로 표현되고, 두 정점을 잇는 에지는 두 정점으로 표현되는 두 사람이 서로 친구 관계임을 표현
		 * 친구 관계 그래프를 이용하면 사회망 서비스에서 어떤 새로운 아이디어가 전파되는 과정을 이해하는데 도움을 줄 수 있다. 
		 * 사회망 서비스에 속한 사람들은 얼리 아답터이거나 얼리 아답터가 아니다.
		 * 얼리 아답터가 아닌 사람들은 자신의 모든 친구들이 얼리 아답터일 때만 이 아이디어를 받아들인다. 
		 * 가능한 한 최소의 수의 얼리 아답터를 확보하여 모든 사람이 이 아이디어를 받아들이게 하는  문제
		 * 
		 * 모든 두 정점 사이에 이들을 잇는 경로가 존재하면서 사이클이 존재하지 않는 경우만 고려
		 * 모든 개인이 새로운 아이디어를 수용하기 위하여 필요한 최소 얼리 어답터의 수를 구하는 프로그램
		 */
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		visited = new boolean[n+1];
		
		graph = new ArrayList[n+1];
		for(int i=1; i<=n; i++) {
			graph[i] = new ArrayList<>();
		}
		 
		for(int i=1; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			graph[a].add(b);
			graph[b].add(a);
		}
		
		dp = new int[n+1][2];
		
		find(1);
		
		System.out.println(Math.min(dp[1][0], dp[1][1]));

	}
	
	static void find(int x) {
		visited[x] = true;
		
		dp[x][0] = 0;
		dp[x][1] = 1;
		for(int i=0; i<graph[x].size(); i++) {
			int ch = graph[x].get(i);
			if (!visited[ch]) {
				find(ch);
				// x가 일반인일 때, 자식은 얼리어답터
				dp[x][0] += dp[ch][1];
				// x가 얼리어답터일 때, 자식은 일반인일 수도, 얼리어답터일 수도 있음
				dp[x][1] += Math.min(dp[ch][0], dp[ch][1]);
			}
		}
	}

}
