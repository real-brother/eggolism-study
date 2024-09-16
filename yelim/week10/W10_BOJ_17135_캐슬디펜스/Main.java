package W10_BOJ_17135_캐슬디펜스;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	static int n, m, d;
	static int[][] map;
	static int[][] copyMap;
	static boolean[][] check;
	static int[] dr = {-1, 0, 0};
	static int[] dc = {0, -1, 1};
	
	static int max;

	public static void main(String[] args) throws IOException {
		/* 문제) 캐슬 디펜스
		 * n*m 격자 판. n+1행의 모든 칸에 성이 있음. 궁수는 성에 배치.
		 * 궁수가 공격하는 적은 거리가 d 이하인 적 중에서 가장 가까운 적,
		 * 여럿이면 가장 왼쪽에 있는 적 공격
		 * 1. 궁수 공격
		 * 2. 공격 받은 적 제외, 적은 아래로 한 칸 이동.
		 * 	(범위 벗어나면 (성이 있는 칸으로 이동한 경우) 게임에서 제외)
		 * 3. 모든 적이 제외되면 게임 끝
		 * 
		 * 두 위치의 거리는 |r1-r2| + |c1-c2|
		 */
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		d = Integer.parseInt(st.nextToken());
		
		map = new int[n][m];
//		copyMap = new int[n][m];
		
		for(int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<m; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
//				copyMap[i][j] = map[i][j];
			}
		}

		int[] arr = new int[m];
		
		setting(0, 0, arr);
		
		System.out.println(max);
	}
	
	// 공격수 3명 세우는 조합 메소드
	static void setting(int start, int idx, int[] archer) {
		
		// 3명 채웠으면 공격하기
		if (idx==3) {
			map();	// 지도 초기화
			// 3명 세웠으면 공격
			attack(archer);
			return;
		}
		
		for(int i=start; i<m; i++) {
			archer[idx] = i;
			setting(start+1, idx+1, archer);
		}
	}
	
	
	// 원래 지도 복사
	static void map() {
		copyMap = new int[n][m];
		for(int i=0; i<n; i++) {
			copyMap[i] = map[i].clone();
		}
	}
	
	// 공격 메소드
	static void attack(int[] archer) {
		int count = 0;
		
		for(int i=0; i<n; i++) {
			check = new boolean[n][m];
			for(int j=0; j<3; j++) {
				int archerC = archer[j];	// 궁수의 위치
				
				// 최소거리와 좌표 저장 변수
				int minD = 11;	// 10이 최대임
				int minX = 16;	// 15가 최대임
				int minY = 16;	// 15가 최대임
				
				for(int r=0; r<n; r++) {
					for(int c=0; c<m; c++) {
						// 적이 있으면
						if (copyMap[r][c]==1) {
							// 적과 궁수 사이 거리 계산
							int dist = Math.abs(r-n) + Math.abs(c-archerC);
						
							// 최소 거리 갱신하며 위치 저장
							if (dist<minD) {
								minD = dist;
								minX = c;
								minY = r;
							} 
							// 최소 거리와 같다면 왼쪽에 있는 값 저장
							else if (dist==minD){
								if (c<minX) {
									minX = c;
									minY = r;
								}
							}
						}
					}
				}
				
				// 궁수의 공격 가능 거리보다 적과의 최소거리가 작거나 같으면 체크해주기
				if (minD <= d) {
					check[minY][minX] = true;
				}
			}
			
			// 공격 받은 적 제외시켜주기
			for(int r=0; r<n; r++) {
				for(int c=0; c<m; c++) {
					if (check[r][c]) {
						copyMap[r][c] = 0;
						count++;
					}
				}
			}
			
			// 한 칸씩 후진
			for(int r=n-1; r>0; r--) {
				for(int c=0; c<m; c++) {
					copyMap[r][c] = copyMap[r-1][c];
				}
			}
			
			// 0번째 행은 0으로 채워주기
			Arrays.fill(copyMap[0], 0);
		
		}
		
		max = Math.max(max, count);
		
	}

}
