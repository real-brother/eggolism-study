package W7_BOJ_17070_파이프옮기기1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static int n;
	static int[][] arr;
	static int cnt;

	public static void main(String[] args) throws IOException {
		/* 문제) 파이프 옮기기1
		 * 가장 처음에 파이프는 (1, 1)와 (1, 2)를 차지하고 있고, 방향은 가로이다. 
		 * 파이프를 밀 수 있는 방향은 총 3가지가 있으며, →, ↘, ↓ 방향이다. 파이프는 밀면서 회전시킬 수 있다. 
		 * 회전은 45도만 회전시킬 수 있으며, 미는 방향은 오른쪽, 아래, 또는 오른쪽 아래 대각선 방향이어야 한다.
		 * 
		 * 파이프의 한쪽 끝을 (N, N)로 이동시키는 방법의 개수를 구해보자.
		 */
		
		/* 설계)
		 * 파이프의 뒤쪽 좌표만 체크해주기
		 * 파이프의 방향을 숫자로 임의 설정해주고(가로=0, 세로=1, 대각선=2)
		 * (y, x, 방향)을 매개변수로 담아서 탐색
		 * 파이프가 가로로 있을 때 갈 수 있는 방향은 가로와 대각선
		 * 세로로 있을 때 갈 수 있는 방향은 세로와 대각선
		 * 대각선으로 있을 때는 모든 방향으로 갈 수 있음.
		 * 각각 방향 별로 재귀호출해서 탐색
		 */
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		arr = new int[n+1][n+1];
		
		for(int i=1; i<=n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=1; j<=n; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		cnt = 0;
		
		search(1, 2, 0);
		
		System.out.println(cnt);
		

	}
	
	// 탐색 (y, x, 방향(가로=0, 세로=1, 대각선=2))
	static void search(int y, int x, int dir) {
		if (x==n && y==n) {
			cnt++;
			return;
		}
		
		// 파이프가 가로로 있을 때 (가로, 대각선)
		if (dir==0) {
			if (x+1<=n && arr[y][x+1]==0) {
				search(y, x+1, 0);	// 가로로
			}
		}
		
		// 파이프가 세로로 있을 때 (세로, 대각선)
		else if (dir==1) {
			if (y+1<=n && arr[y+1][x]==0) {
				search(y+1, x, 1);	// 세로로
			}
		}
		
		// 파이프가 대각선으로 있을 때 (가로, 세로, 대각선)
		else if (dir==2) {
			if (x+1<=n && arr[y][x+1]==0) {
				search(y, x+1, 0);	// 가로로
			}
			if (y+1<=n && arr[y+1][x]==0) {
				search(y+1, x, 1);	// 세로로
			}
		}
		
		
		// 대각선은 항상 가능해서 따로 빼줌
		if (x+1<=n && y+1<=n && 
				arr[y][x+1]==0 && arr[y+1][x]==0 && arr[y+1][x+1]==0) {
			search(y+1, x+1, 2);	// 대각선으로
		}
	}

}
