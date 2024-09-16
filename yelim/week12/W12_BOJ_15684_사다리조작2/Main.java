package W12_BOJ_15684_사다리조작2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int n, m, h;	// 세로선, (중간)가로선, 가로선 개수
	static int[][] ladder;
	static int answer = Integer.MAX_VALUE;
	
	public static void main(String[] args) throws IOException {
		/* 문제) 15684_사다리조작
		 * i 세로선의 결과가 i번이 나오도록 하기 위해 추가해야 할 가로선 개수의 최소값
		 * 3 이상이거나 불가능하면 -1 출력
		 */
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		h = Integer.parseInt(st.nextToken());
		
		ladder = new int[h+1][n+1];
		
		for(int i=0; i<m; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			ladder[a][b] = 1;
		}
		
		// 3보다 크면 -1이니까
		for(int i=0; i<=3; i++) {
			dfs(1, 0, i);
		}
		
		// answer이 그대로 max값이면 불가능이므로 -1 출력
		if (answer==Integer.MAX_VALUE) {
			answer = -1;
		}
		System.out.println(answer);
		
	}
	
	static void dfs(int r, int cnt, int size) {
		if (cnt == size) {
			if (go()) {
				answer = Math.min(answer, size);
			}
			return;
		}
		
		// 반복
		for(int i=r; i<=h; i++) {
			for(int j=1; j<n; j++) {
				// 연속하거나 접하면 continue
				if (ladder[i][j]==1 || ladder[i][j-1]==1 || ladder[i][j+1]==1) {
					continue;
				}
				// 아니면 선 그어주기
				ladder[i][j] = 1;
				// dfs 호출
				dfs(i, cnt+1, size);
				// 초기화
				ladder[i][j] = 0;
			}
		}
	}
	
	// 사다리 타기
	static boolean go() {
		for(int i=1; i<=n; i++) {
			// 현재 위치(세로선)
			int cur = i;
			// 시작 행
			int s = 1;
			
			// 마지막행까지
			while(s <= h) {
				// 가로선이 오른쪽으로 있으면
				if (ladder[s][cur] == 1) {
					cur++;	// 현재 위치 오른쪽으로 이동
					s++;	// 행 하나 아래로
				}
				// 가로선이 왼쪽으로 있으면
				else if (ladder[s][cur-1] == 1) {
					cur--;	// 현재 위치 왼쪽으로 이동
					s++;	// 행 하나 아래로
				}
				// 가로선이 없으면
				else {
					s++;	// 행만 하나 아래로 이동
				}
			}
			// 시작한 세로 선과 현재 위치의 세로 선이 다르면 fasle
			if (i != cur) return false;
		}
		return true;
	}

}
