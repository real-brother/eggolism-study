package W7_BOJ_21610_마법사상어;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static int n, m;
	static int[][] arr;
	static boolean[][] cloud;
	static int d;
	static int s;
	static int[] dr = {0, -1, -1, -1, 0, 1, 1, 1};
	static int[] dc = {-1, -1, 0, 1, 1, 1, 0, -1};
	
	
	
	public static void main(String[] args) throws IOException {
		 /* 문제) 마법사 상어와 비바라기
		  * 
		  */
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		arr = new int[n][n];
		
		for(int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<n; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		// 구름 배열 생성하고, 처음 구름 위치 표시
		cloud = new boolean[n][n];
		cloud[n-1][0] = cloud[n-1][1] = cloud[n-2][0] = cloud[n-2][1] = true;
		
		for(int i=0; i<m; i++) {
			st = new StringTokenizer(br.readLine());
			d = Integer.parseInt(st.nextToken());
			s = Integer.parseInt(st.nextToken());
			
			magic(d-1, s%n);
			
		}
		
		int sum = 0;
		
		// 물의 양의 합
		for(int i=0; i<n; i++) {
			for(int j=0; j<n; j++) {
				sum += arr[i][j];
			}
		}
		
		System.out.println(sum);

	}
	
	static void magic(int dir, int move) {
		boolean[][] check = new boolean[n][n];
		
		// 구름 이동, 물의 양 1 증가
		for(int i=0; i<n; i++) {
			for(int j=0; j<n; j++) {
				if (cloud[i][j]) {
					int nr = (n+i+dr[dir]*move)%n;
					int nc = (n+j+dc[dir]*move)%n;
					
					check[nr][nc] = true;	// 이동한 구름 칸
					arr[nr][nc]++;	// 구름이 있는 칸 물 1 증가
				}
			}
		}
		
		// 구름 배열을 이동한 구름으로 카피
		cloud = check;
		
		// 대각선 물의 양 증가
		for(int i=0; i<n; i++) {
			for(int j=0; j<n; j++) {
				// 구름이 있는 위치면
				if (cloud[i][j]) {
					for(int d=1; d<8; d+=2) {
						int nr = i+dr[d];
						int nc = j+dc[d];
						
						// 범위 확인, 물 있는지 확인
						if (nr>=0 && nr<n && nc>=0 && nc<n && arr[nr][nc]>0) {
							arr[i][j]++;
						}
					}
				}
			}
		}
		
		// 물의 양이 2 이상인 모든 칸에 구름, 물의 양 2 감소
		for(int i=0; i<n; i++) {
			for(int j=0; j<n; j++) {
				// 이전에 없어진 구름 초기화
				if (cloud[i][j]) {
					cloud[i][j] = false;
				}
				else if (arr[i][j]>=2) {
					arr[i][j] -= 2;
					cloud[i][j] = true;
				}
			}
		}
		
	}

}
