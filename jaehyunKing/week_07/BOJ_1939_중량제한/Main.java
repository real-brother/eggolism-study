package BOJ_1939_중량제한;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
	// 인접리스트에서 리스트의 원소를 배열로 선언 -> 가중치(중량제한) 저장
	static ArrayList<int[]>[] graph;
	static boolean[] visited;
	static boolean can;
	static int N, end;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		int M = sc.nextInt();
		
		graph = new ArrayList[N+1];
		
		for(int i = 1; i <= N; i++) graph[i] = new ArrayList<>();
		
		int max = 0;
		for(int i = 1; i <= M; i++) {
			int num1 = sc.nextInt();
			int num2 = sc.nextInt();
			int weight = sc.nextInt();
			
			// 양방향 그래프 + 중량제한 저장
			graph[num1].add(new int[] {num2, weight});
			graph[num2].add(new int[] {num1, weight});
			
			// 최대 중량제한을 저장 -> 이분 탐색의 right값
			if(max < weight) max = weight;
		}
		
		int start = sc.nextInt();
		end = sc.nextInt();
		
		
		// 옮길 수 있는 중량의 최댓값을 이분 탐색
		// -> 당연하다 왜? 중량제한의 범위가 10억이어서
		int left = 1;
		int right = max;
		int max_weight = Integer.MIN_VALUE;
		while(left <= right) {
			int mid = (left + right) / 2;
			
			bfs(start, mid);
			
			// end에 도착했다면 max_weight 갱신
			if(visited[end]) {
				left = mid + 1;
				if(max_weight < mid) max_weight = mid;
			}
			else right = mid - 1;
		}
		
		System.out.println(max_weight);
	}
	
	static void bfs(int start, int weight) {
		Queue<Integer> queue = new LinkedList<>();
		visited = new boolean[N+1];
		
		visited[start] = true;
		queue.offer(start);
		
		while(!queue.isEmpty()) {
			int now = queue.poll();
			for(int i = 0; i < graph[now].size(); i++) {
				int next = graph[now].get(i)[0];
				
				// 중량 제한보다 weight(=mid)가 작다면 큐에 넣음 
				if(!visited[next] && weight <= graph[now].get(i)[1]) {
					
					// break 전에 visited를 넣어주어야 
					// 이후 if문에서 도착 지점에 도달했는지 판단 가능
					visited[next] = true;
					
					//조금이라도 시간을 줄여주기 위해 사용
					if(next == end) return;
					
					queue.offer(next);
				}
			}
		}
	}
}