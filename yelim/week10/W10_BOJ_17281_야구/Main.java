package W10_BOJ_17281_야구;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static int n;	// 이닝 수
	static int[][] player;	// 각 선수의 결과
	static boolean[] visited;	// 순열을 위해
	static int[] order;	// 타순
	static int max = 0;

	public static void main(String[] args) throws IOException {
		/* 문제) 야구
		 * 1. 타순 정하기 (1번 타자는 4번 고정)
		 * 2. 게임 시작
		 * 	1) 3아웃이면 다음 이닝 넘어가는데, 타순 이어서 감
		 * 	2) 9번 타자까지 했는데 3아웃이 아니면 다시 1번 타자로 와야 함
		 * 	3) 결과대로 움직여서 점수 내기
		 */
		
		/* 순열 해주어야 함
		 * 순서가 달라지면 점수도 달라지니까!
		 */
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		
		player = new int[n][10];
		order = new int[10];
		visited = new boolean[10];
		
		for(int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=1; j<=9; j++) {
				player[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		// 4번 타자는 1	번 선수로 고정
		order[4] = 1;
		visited[4] = true;
		
		perm(2);
		
		System.out.println(max);

	}
	
	// 타순 정하는 순열
	static void perm(int cnt) {
		// 10명 다 했으면 시작
		if (cnt==10) {
			// 게임 시작
			game();
			return;
		}
		
		for(int i=1; i<=9; i++) {
			if (!visited[i]) {
				visited[i] = true;
				// 순서 배열에 넣어주기
				order[i] = cnt;
				perm(cnt+1);
				visited[i] = false;
			}
		}
	}
	
	// 게임 진행
	static void game() {
		int score = 0;
		int start = 1;	// 이닝 이어질 때 타순 이어지게 하기 위해 저장
		
		// 이닝 수 만큼 반복
		for(int i=0; i<n; i++) {
			int out = 0;
			boolean[] base = new boolean[4];	// 각 루수에 선수 있는지
			
			while(out < 3) {
				// 타자가 몇루타 쳤는지
				int hit = player[i][order[start++]];
				
				// 만약 타순이 9번 넘어가면 1번으로 돌려주기
				if (start > 9) {
					start = 1;
				}
				// 0이면 아웃카운트 증가
				if (hit == 0) {
					out++;
				}
				// 1루타면 3루만 점수 획득, 나머지 이동, 1루 체크
				else if (hit == 1) {
					if (base[3]) score++;
					base[3] = base[2];
					base[2] = base[1];
					base[1] = true;
				}
				// 2루타면 2루, 3루 점수 획득, 나머지 이동, 2루 체크, 1루 비어있음
				else if (hit == 2) {
					if (base[2]) score++;
					if (base[3]) score++;
					base[3] = base[1];
					base[2] = true;
					base[1] = false;
				}
				// 3루타면 1,2,3루 모두 점수 획득, 다 들어가서 이동 없음
				// 3루 체크, 1루와 2루 비어있음
				else if (hit == 3) {
					if (base[1]) score++;
					if (base[2]) score++;
					if (base[3]) score++;
					base[3] = true;
					base[2] = false;
					base[1] = false;
				}
				// 홈런이면 1,2,3루 + 방금 친 타자까지 점수 획득
				// 이동 없음, 모두 비어있음
				else if (hit == 4) {
					if (base[1]) score++;
					if (base[2]) score++;
					if (base[3]) score++;
					score++;
					base[3] = false;
					base[2] = false;
					base[1] = false;
				}
				
			}
		}
		
		max = Math.max(max, score);
	}
	

}
