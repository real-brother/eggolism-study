package BOJ_14889_스타트와링크;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N, min, idx;
	static boolean[] visited;
	static int[][] graph;

	// 버퍼를 썼는데 280ms인걸 봐서는 scanner써도 통과할 꺼 같다
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		
		graph = new int[N+1][N+1];
		visited = new boolean[N+1];
		
		for(int r = 1; r <= N; r++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for(int c = 1; c<= N; c++) {
				graph[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		
		min = Integer.MAX_VALUE;
		
		// 조합을 할 때 end를 N/2로 해도 모든 조합을 볼 수 있다
		// 4개 중 2개를 고르면 -> 1 2, 1 3, 1 4, 2 3, 2 4, 3 4인데
		// 사실 1 2 == 3 4, 1 3 == 2 4, 1 4 == 2 3이다. 
		idx = 0;
		combination(1, N/2, 1);
		
		System.out.println(min);
	}
	
	
	// 조합을 사용
	static void combination(int start, int end, int count) {
		// min이 0이면 더 작은 값이 나올 수 없기 때문에 죄다 return
		if(min == 0) return;
		
		if(count == N/2+1) {
			int sum = 0;
			int sum2 = 0;
		
			for(int r = 1; r <= N; r++) {
				for(int c = 1; c <= N; c++) {
					//visited true, false로 조합을 구분해서 했으므로
					//visited r c가 일치하면 더 해준다
					// ex 1 2 가 둘 다 true이면 graph[1][2]를 더하고
					// 차후에 graph[2][1]을 더한다.
					if(visited[r] && visited[c]) {
						sum += graph[r][c];
						System.out.println(r + " " +c);
					}
					if(!visited[r] && !visited[c]) sum2 += graph[r][c];	
				}
			}
			
			if(min > Math.abs(sum - sum2)) min = Math.abs(sum - sum2);
			return;
		}
		
		// 여기가 가장 중요한 거 같다
		// 조합을 start i+1 부터 만든다
		// combination에서 end + 1인 이유는
		// 4개 중 2개를 고른다고 했을 때 그냥 end면 1 2만 고르고 만다 
		// 하지만 end + 1로 하면 1 2, 1 3, 2 3을 골라서 모든 경우를 확인할 수 있다. 
		for(int i = start; i <= end; i++) {
			if(!visited[i]) {
				visited[i] = true;
				combination(i+1, end+1, count+1);
				visited[i] = false;
			}
		}
		
	}

}