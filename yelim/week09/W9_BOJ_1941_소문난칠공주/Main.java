package W9_BOJ_1941_소문난칠공주;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
	
	static char[][] arr;
	static boolean[][] visited;
	static int[] selectR;
	static int[] selectC;
	static char[] sel;
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	static int ans;

	public static void main(String[] args) throws IOException {
		/* 문제) 소문난 칠공주
		 * 총 25명의 여학생들로 이루어진 여학생반은 5×5의 정사각형 격자 형태로 자리가 배치되었다.
		 * 
		 * 이름이 이름인 만큼, 7명의 여학생들로 구성되어야 한다.
		 * 강한 결속력을 위해, 7명의 자리는 서로 가로나 세로로 반드시 인접해 있어야 한다.
		 * 화합과 번영을 위해, 반드시 ‘이다솜파’의 학생들로만 구성될 필요는 없다.
		 * 그러나 생존을 위해, ‘이다솜파’가 반드시 우위를 점해야 한다. 
		 * 따라서 7명의 학생 중 ‘이다솜파’의 학생이 적어도 4명 이상은 반드시 포함되어 있어야 한다.
		 * 여학생반의 자리 배치도가 주어졌을 때, ‘소문난 칠공주’를 결성할 수 있는 모든 경우의 수를 구하는 프로그램을 작성하시오.
		 */
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		arr = new char[5][5];
		visited = new boolean[5][5];
		selectR = new int[5];
		selectC = new int[5];
		sel = new char[7];
		
		for(int i=0; i<5; i++) {
			String s = br.readLine();
			for(int j=0; j<5; j++) {
				arr[i][j] = s.charAt(j);
			}
		}
		
		comb(0, 0);
		System.out.println(ans);
		

	}	// main 끝
	
	// 조합 메소드
	static void comb(int depth, int start) {
		if (depth==7) {
			// 자리 연결되어있는지 확인하는 bfs
			if (bfs()) ans++;
		}
		
		for(int i=start; i<25; i++) {
			visited[i/5][i%5] = true;
			comb(depth+1, i+1);
			visited[i/5][i%5] = false;
		}
	}
	
	
	// 자리 연결되었는지 확인하는 bfs
	static boolean bfs() {
		boolean[][] Qvisited = new boolean[5][5];
		
		// 방문배열 복사
		for(int i=0; i<5; i++) {
			Qvisited[i] = visited[i].clone();
		}
		
		Queue<int[]> q = new LinkedList<>();
		
		int r = 0;
		int c = 0;
		
		for(int i=0; i<5; i++) {
			for(int j=0; j<5; j++) {
				if(Qvisited[i][j]) {
					r = i;
					c = j;
				}
			}
		}
		
		q.add(new int[] {r, c});
		
		int cnt = 0;
		int cntS = 0;
		
		while(!q.isEmpty()) {
			int[] cur = q.poll();
			
			for(int d=0; d<4; d++) {
				int nr = cur[0]+dr[d];
				int nc = cur[1]+dc[d];
				
				if (nr>=0 && nr<5 && nc>=0 && nc<5) {
					
					if(Qvisited[nr][nc]) {
						if (arr[nr][nc] == 'S') {
							cntS++;
						}
						
						cnt++;
						Qvisited[nr][nc] = false;
						q.add(new int[] {nr, nc});
					}
				}
			}
		}
		
		if (cnt==7 && cntS >= 4) {
			return true;
		}
		return false;
	}

}
