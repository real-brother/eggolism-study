package W15_BOJ_20056_상어파이어볼;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	
	static class fireBalls {
		int r, c, m, s, d;
		
		public fireBalls(int r, int c, int m, int s, int d) {
			this.r = r;
			this.c = c;
			this.m = m;
			this.s = s;
			this.d = d;
		}
	}
	
	static int n, m, k;		// n*n, 파이어볼 m개, 이동 횟수 k
	static int[][] map;
	static List<fireBalls> fireBall;	// 위치 r c, 질량 m, 속력 s, 방향 d
	static int[] dr = {-1, -1, 0, 1, 1, 1, 0, -1};	// 방향 번호 위부터 시계방향
	static int[] dc = {0, 1, 1, 1, 0, -1, -1, -1};

	public static void main(String[] args) throws IOException {
		/* 문제) 20056_마법사 상어와 파이어볼
		 * 
		 * 1. 모든 파이어볼이 자신의 방향 d로 속력 s만큼 이동
		 * 	- 이동하는 중에는 같은 칸에 여러 개의 파이어볼이 있을 수 있음
		 * 2. 이동이 끝난 뒤, 2개 이상의 파이어볼이 있는 칸에서
		 * 	- 같은 칸에 있는 파이어볼 하나로 합쳐짐
		 * 	- 파이어볼은 4개로 나누어짐
		 * 	- 나누어진 파이어볼의 질량, 속력, 방향은
		 * 	  - 질량: (합쳐진 파이어볼 질량 합)/5
		 * 	  - 속력: (속력의 합)/(합쳐진 파이어볼 개수)
		 *    - 방향: 합쳐지는 방향이 모두 홀수거나 짝수면 방향은 0, 2, 4, 6
		 *           그렇지 않으면 1, 3, 5, 7
		 *    - 질량이 0인 파이어볼은 소멸
		 */
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		
		map = new int[n][n];
		fireBall = new ArrayList<>();
		
		for(int i=0; i<m; i++) {
			st = new StringTokenizer(br.readLine());
			
			int r = Integer.parseInt(st.nextToken())-1;
			int c = Integer.parseInt(st.nextToken())-1;
			int m = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			
			fireBall.add(new fireBalls(r, c, m, s, d));
			map[r][c]++;
		}
		
		// k번 이동
		for(int i=0; i<k; i++) {
			move();
		}
		
		int sum = 0;
		for(int i=0; i<fireBall.size(); i++) {
			fireBalls fb = fireBall.get(i);
			sum += fb.m;
		}
		
		System.out.println(sum);
		
	}
	
	static void move() {
		int[][] copy = new int[n][n];
		for(int i=0; i<n; i++) {
			copy[i] = map[i].clone();
		}
		
		// 모든 파이어볼 이동
		for(int k=0; k<fireBall.size(); k++) {
			fireBalls fb = fireBall.get(k);
			
			// 원래 파이어볼이 있던 자리에서 빼주기
			copy[fb.r][fb.c]--;
			
			// 이동시키기
			// 여기서 속력을 mod n 안해주니까 인덱스 에러 뜸!
			// 속력이 n을 넘어가면 음수값이 나올 수 있음
			fb.r = (fb.r + n + dr[fb.d]*(fb.s%n)) % n;
			fb.c = (fb.c + n + dc[fb.d]*(fb.s%n)) % n;
			
			
			copy[fb.r][fb.c]++;
			
		}
		
		// 2개 이상의 파이어볼이 있는지 확인
		for(int i=0; i<n; i++) {
			for(int j=0; j<n; j++) {
				// 2개 이상이면
				if (copy[i][j] >= 2) {

					// 합쳐질 변수들
					int mass = 0;	// 질량 합
					int speed = 0;	// 속력 합
					List<Integer> dir = new ArrayList<>();
					int cnt = 0;	// 합쳐진 파이어볼 개수
					
					// r, c가 i, j인 파이어볼 찾기...
					for(int k=fireBall.size()-1; k>=0; k--) {
						fireBalls fb = fireBall.get(k);
						// 해당 칸에 있는 파이어볼이면 질량, 속력 더해서 저장, 방향 리스트에 저장
						if (fb.r==i && fb.c==j) {
							mass += fb.m;
							speed += fb.s;
							cnt++;
							dir.add(fb.d);
							fireBall.remove(k);	// 파이어볼 삭제 (뒤에부터 해줘서 인덱스 문제 없을걸 아마?)
						}
					}
					copy[i][j] = 1;
					
					int check = 0;
					boolean isAllDir = true;	// 방향이 모두 홀수이거나 짝수인지 확인
					
					// 합쳐진 파이어볼 0번째 방향이 짝수면 1 저장
					if (dir.get(0)%2 == 0) {
						check = 1;
					}
					// 홀수면 2 저장
					else check = 2;
					
					// 방향 모두 홀수이거나 짝수인지 확인
					for(int k=1; k<dir.size(); k++) {
						if(dir.get(k)%2 == 0) {
							if (check != 1) {
								isAllDir = false;
								break;
							}
						}
						else {
							if(check != 2) {
								isAllDir = false;
								break;
							}
						}
					}
					
					// 방향 새로 넣어주기 위해 배열에 저장
					int[] dirArr;
					if (isAllDir) {
						dirArr = new int[] {0, 2, 4, 6};
					}
					else {
						dirArr = new int[] {1, 3, 5, 7};
					}
					
					// 파이어볼 4개로 나누어짐
					// 각각 질량, 속력, 방향 저장해서 fireBall 리스트에 넣어주기
					for(int k=0; k<4; k++) {
						int newM = mass/5;
						if (newM >= 1) {
							int newS = speed/cnt;
							int newD = dirArr[k];
							fireBall.add(new fireBalls(i, j, newM, newS, newD));
						}
						else {
							copy[i][j] = 0;
							break;
						}
						
						copy[i][j] = 4;
					}
					
				}
			}
		}
		
		// 지도 저장
		for(int i=0; i<n; i++) {
			map[i] = copy[i].clone();
		}
		
	}

}
