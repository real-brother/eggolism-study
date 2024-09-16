package BOJ_20056_마법사상어와파이어볼;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
	static Scanner sc = new Scanner(System.in);
	static int N, M, K;
	static ArrayList<Fireball> map[][];
	static ArrayList<Fireball> list = new ArrayList<>();
	static int[] dr = { -1, -1, 0, 1, 1, 1, 0, -1 };
	static int[] dc = { 0, 1, 1, 1, 0, -1, -1, -1 };

	public static void main(String[] args) {
		N = sc.nextInt();
		M = sc.nextInt();
		K = sc.nextInt();
		map = new ArrayList[N + 1][N + 1];
		for (int i = 0; i < N + 1; i++) {
			for (int j = 0; j < N + 1; j++) {
				map[i][j] = new ArrayList<Fireball>();
			}
		}
		for (int i = 0; i < M; i++) {
			int r = sc.nextInt();
			int c = sc.nextInt();
			int m = sc.nextInt();
			int s = sc.nextInt();
			int d = sc.nextInt();
			map[r][c].add(new Fireball(r, c, m, s, d));
			list.add(new Fireball(r, c, m, s, d));
		}

		// K 번 명령하기
		while (K > 0) {
			moveFireball();
			for (int i = 1; i < N + 1; i++) {
				for (int j = 1; j < N + 1; j++) {
					// 2개 이상 파이어볼이 있으면 합쳐주기
					if (map[i][j].size() >= 2) {
						mergeFireball(i, j);
					}
				}
			}
			makeList();
			K--;
		}
		
		// 남이있는 파이어볼 질량의 합 구하기
		int ans = 0;
		for (int i = 1; i < N + 1; i++) {
			for (int j = 1; j < N + 1; j++) {
				if (map[i][j].size() > 0) {
					for (Fireball cur : map[i][j]) {
						ans += cur.mass;
					}
				}
			}
		}

		System.out.println(ans);
	}
	
	// 모든 파이어볼이 자신의 방향으로 속력만큼 이동
	public static void moveFireball() {
		for (int i = 1; i < N + 1; i++) {
			for (int j = 1; j < N + 1; j++) {
				map[i][j] = new ArrayList<Fireball>();
			}
		}

		for (Fireball now : list) {
			now.r = now.r + dr[now.dir] * (now.speed % N);
			now.c = now.c + dc[now.dir] * (now.speed % N);

			if (now.r > N)
				now.r %= N;
			if (now.c > N)
				now.c %= N;
			if (now.r <= 0)
				now.r = N - Math.abs(now.r);
			if (now.c <= 0)
				now.c = N - Math.abs(now.c);

			map[now.r][now.c].add(now);
		}

	}



	// 파이어볼이 합쳐질 경우
	public static void mergeFireball(int r, int c) {
		int sumMass = 0, sumSpeed = 0, sumCount = 0;
		boolean isEven = true;
		boolean isOdd = true;
		for (Fireball cur : map[r][c]) {
			sumMass += cur.mass;
			sumSpeed += cur.speed;
			sumCount++;
			if (cur.dir % 2 == 0) {
				isOdd = false;
			} else {
				isEven = false;
			}
		}

		int mass = sumMass / 5;
		int speed = sumSpeed / sumCount;

		map[r][c] = new ArrayList<>();
		if (mass <= 0) {
			return;
		}
		// 합쳐진 파이어볼 방향 설정하기
		if (isEven || isOdd) {
			for (int i = 0; i < 4; i++) {
				map[r][c].add(new Fireball(r, c, mass, speed, i * 2));
			}
		} else {
			for (int i = 0; i < 4; i++) {
				map[r][c].add(new Fireball(r, c, mass, speed, i * 2 + 1));
			}
		}
	}
	
	// 다음턴에 사용할 파이어볼 리스트 만들기
	public static void makeList() {
		list = new ArrayList<>();
		for (int i = 1; i < N + 1; i++) {
			for (int j = 1; j < N + 1; j++) {
				if (map[i][j].size() > 0) {
					for (Fireball now : map[i][j]) {
						list.add(now);
					}
				}
			}
		}
	}

	static class Fireball {
		int r; // 행
		int c; // 열
		int mass; // 질량
		int speed; // 속력
		int dir; // 방향

		Fireball(int r, int c, int mass, int speed, int dir) {
			this.r = r;
			this.c = c;
			this.mass = mass;
			this.speed = speed;
			this.dir = dir;
		}
	}

}
