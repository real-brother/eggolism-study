import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;
import java.util.Set;

public class Main {

	/*
	 * 설계 방식 1. 궁수 자리 조합으로 3개 고름
	 * 
	 * 2. DFS로 적 공격 (좌표때문에 가장 가까운 것 바로 break 안됨?) -> DFS는 가능할지도? - 공격 범위 안에 있는 적 깊이와
	 * 좌표 저장 - 깊이 우선 > 열 숫자 적인 것 우선
	 * 
	 * 3. 적 이동 - stage를 구현하여 기준행을 N부터 올림 -> 매트릭스 복사는 시간 복잡도로 멋없어서 PASS - 올린 stage 행
	 * 칸에 적이 남아 있다면 게임 종료
	 */
	
	/*디버깅
	 * 1. 적이 성까지 오면 게임에서 제외
	 * - 게임이 중단되는 것으로 착각 -> 너무 게임을 많이했다...
	 * 
	 * 2. 매트릭스의 동시성 문제
	 * - 새로운 매트릭스 할당
	 * 
	 * 3. 중복으로 공격하는 경우
	 * - HashMap을 사용했으나 int[]는 주소값이 달라서 중복 처리를 하지 못함
	 * 
	 * 4. 테케 말고 제출에서 틀림
	 * - DFS visited 넣다 빼는 것 생각만 하고 놓침
	 * - DFS 오랜만이라.. ㅎ;
	 */

	static int[][] matrix;
	static int[][] matrix2;
	static int[] sel;
	static int[][] visited;

	static int N, M, D;
	static int answer, stage;

	static int[] dr = { 0, -1, 0 }; // 좌상우
	static int[] dc = { -1, 0, 1 };

	static List<int[]> enemyTmp;
	static List<int[]> enemySel;

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();
		M = sc.nextInt();
		D = sc.nextInt();

		// 입력 받기
		matrix2 = new int[N + 1][M];

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				int num = sc.nextInt();
				matrix2[i][j] = num;
			}
		}

		answer = 0;

		// 1. 궁수 조합 만들기
		sel = new int[3];
		comb(0, 0);

		// 결과 출력하기
		System.out.println(answer);
	}

	// 3명의 궁수의 위치 조합
	public static void comb(int idx, int sidx) {

		if (sidx == 3) {
			// 조합 완료
//			System.out.println("시작 -------> " + Arrays.toString(sel));
			stage = N; // 궁수의 위치는 N부터 시작

			// 2. 궁수 적 탐색하기 -> 한 게임 시작
			matrix = new int[N+1][M];
			for (int i = 0; i < matrix2.length; i++) {
				matrix[i] = matrix2[i].clone();
			}
			
			int count = attack();
//			System.out.println("결과: " + count);
			answer = Math.max(count, answer);

			return;
		}

		if (idx == M)
			return;

		// 선택
		sel[sidx] = idx;
		comb(idx + 1, sidx + 1);

		// 초기화
		comb(idx + 1, sidx);

	}

	// 궁수의 위치를 정해보고 게임해보기 -> 한 스테이지 시작
	public static int attack() {

		boolean is_on = true;
		int count = 0;

		while (is_on) {

			enemySel = new ArrayList<>(); // 중복 방지

			// 세명 공격 시작
			for (int i = 0; i < 3; i++) {

				// 한명 시작
				// 적 후보 찾기 -> 없는 경우 조심
				enemyTmp = new ArrayList<>();
				visited = new int[N + 1][M];
				visited[stage][sel[i]] = 1;
				dfs(stage, sel[i], 0);

				// 깊이 크기 > 왼쪽 순서로 적 후보 확정하기 (인당 적 한명)
				int nr = -1;
				int nc = -1;
				int dep = 987654321;
				for (int j = 0; j < enemyTmp.size(); j++) {
					if (enemyTmp.get(j)[2] < dep) {
						nr = enemyTmp.get(j)[0];
						nc = enemyTmp.get(j)[1];
						dep = enemyTmp.get(j)[2];
					} else if (enemyTmp.get(j)[2] == dep) {
						if (enemyTmp.get(j)[1] < nc) {
							nr = enemyTmp.get(j)[0];
							nc = enemyTmp.get(j)[1];
							dep = enemyTmp.get(j)[2];
						}
					}
				}

				// 없는 경우(-1,-1 최신화X) 제외 적 확정에 추가
				if (nr == -1 && nc == -1)
					continue;

				enemySel.add(new int[] { nr, nc });

			}

			// 세명 공격 확정 -> 여기서는 0으로 덮어쓰기 가능
			// 중복 처리
			for (int[] enemy : enemySel) {
//				System.out.println("적 타겟: + " + Arrays.toString(enemy));
				if (matrix[enemy[0]][enemy[1]] != 0) {
					count++;
					matrix[enemy[0]][enemy[1]] = 0;
				}
			}

			// 3. 적 이동하기 or 끝났는지 확인 -> 공격 끝
			// STAGE 이어 갈 것인지 확인 -> 한칸 올린(숫자는 감소) stage 행에 적이 없어져야 함
			if (stage > 0) {
				stage--;
			} else {
				is_on = false; // 끝까지 올라간 경우
				break;
			}

			// 적이 성에 오면 게임을 끝내야 하는 줄
			for (int i = 0; i < M; i++) {
				if (matrix[stage][i] == 1) {
					matrix[stage][i] = 0;
				}
			}
			// 공격과 이동 한번 끝
			
		} // while 끝
		
		return count;

	}

	// 위 방향으로만 dr dc
	public static void dfs(int r, int c, int depth) {

		// 공격 범위 제한
		if (depth == D) {
			return;
		}


		// 세 방향 가능
		for (int i = 0; i < 3; i++) {
			int nr = dr[i] + r;
			int nc = dc[i] + c;

			// 경계나 stage와 같으면 OUT (스테이지는 옆으로 가는 경우 발생)
			if (nr < 0 || nr >= stage || nc < 0 || nc >= M || visited[nr][nc] == 1)
				continue;

			// 적 발견
			if (matrix[nr][nc] == 1) {
				enemyTmp.add(new int[] { nr, nc, depth });
			}

			visited[nr][nc] = 1;
			dfs(nr, nc, depth + 1);
			visited[nr][nc] = 0;

		}

	}

}