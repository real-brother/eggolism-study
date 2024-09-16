import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {
	
	static int n, check;
	static int[] num;
	static boolean[] visited;
	static List<Integer> ans;

	public static void main(String[] args) throws IOException {
		/* 문제) 2668_숫자 고르기
		 * 
		 */

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		n = Integer.parseInt(br.readLine());
		
		num = new int[n+1];
		for(int i=1; i<=n; i++) {
			num[i] = Integer.parseInt(br.readLine());
		}
		
		visited = new boolean[n+1];
		ans = new ArrayList<>();
		
		for(int i=1; i<=n; i++) {
			visited[i] = true;
			check = i;
			dfs(i);
			visited[i] = false;
		}
		
		Collections.sort(ans);
		System.out.println(ans.size());
		for(int x : ans) {
			System.out.println(x);
		}

	}

	static void dfs(int x) {
		if (num[x] == check) {
			ans.add(check);
		}
		
		if (!visited[num[x]]) {
			visited[num[x]] = true;
			dfs(num[x]);
			visited[num[x]] = false;
		}
	}
	
}
