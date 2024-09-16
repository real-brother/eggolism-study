package BOJ_17144_미세먼지안녕;

import java.util.Scanner;

public class Main {
	static Scanner scanner = new Scanner(System.in);
	static int r;
	static int c;
	static int t;
	static int[][] room;
	static int[][] machinePositions;
	static int[] dr = { 0, -1, 0, 1 };
	static int[] dc = { 1, 0, -1, 0 };

	public static void main(String[] args) {
		r = scanner.nextInt();
		c = scanner.nextInt();
		t = scanner.nextInt();

		room = new int[r][c];
		for (int i = 0; i < r; i++) {
			for (int j = 0; j < c; j++) {
				room[i][j] = scanner.nextInt();
			}
		}

		machinePositions = new int[2][2];
		int machineCount = 0;
		for (int i = 0; i < r; i++) {
			for (int j = 0; j < c; j++) {
				if (room[i][j] == -1) {
					machinePositions[machineCount][0] = i;
					machinePositions[machineCount][1] = j;
					machineCount++;
					if (machineCount == 2)
						break;
				}
			}
			if (machineCount == 2)
				break;
		}

		int up = machinePositions[0][0];
		int down = machinePositions[1][0];

		for (int s = 0; s < t; s++) {
			// 먼지를 퍼뜨리고
			spread(room, r, c);
			// 순환 2개를 돌려준다
			upMachine(room, up, c);
			downMachine(room, down, c);
		}

		int answer = calculateDust(room, r, c);
		System.out.println(answer);
	}

	private static void spread(int[][] room, int r, int c) {

		int[][] tmpArr = new int[r][c];
		for (int i = 0; i < r; i++) {
			for (int j = 0; j < c; j++) {
				if (room[i][j] == -1)
					continue;

				int tmp = 0;
				for (int d = 0; d < 4; d++) {
					int nx = i + dr[d];
					int ny = j + dc[d];
					if (nx < 0 || nx >= r || ny < 0 || ny >= c) continue;

					if (room[nx][ny] != -1) {
						tmpArr[nx][ny] += room[i][j] / 5;
						tmp += room[i][j] / 5;
					}
				}
				room[i][j] -= tmp;
			}
		}

		for (int i = 0; i < r; i++) {
			for (int j = 0; j < c; j++) {
				room[i][j] += tmpArr[i][j];
			}
		}
	}

	private static void upMachine(int[][] room, int up, int c) {

		int d = 0;
		int before = 0;
		int x = up;
		int y = 1;

		while (true) {
			int nx = x + dr[d];
			int ny = y + dc[d];

			if (x == up && y == 0)
				break;

			if (nx < 0 || nx >= room.length || ny < 0 || ny >= c) {
				d = (d + 1) % 4;
				continue;
			}

			int temp = room[x][y];
			room[x][y] = before;
			before = temp;

			x = nx;
			y = ny;
		}
	}

	private static void downMachine(int[][] room, int down, int c) {
		int[] dx = { 0, 1, 0, -1 };
		int[] dy = { 1, 0, -1, 0 };

		int d = 0;
		int before = 0;
		int x = down;
		int y = 1;

		while (true) {
			int nx = x + dx[d];
			int ny = y + dy[d];

			if (x == down && y == 0)
				break;

			if (nx < 0 || nx >= room.length || ny < 0 || ny >= c) {
				d = (d + 1) % 4;
				continue;
			}

			int temp = room[x][y];
			room[x][y] = before;
			before = temp;

			x = nx;
			y = ny;
		}
	}

	private static int calculateDust(int[][] room, int r, int c) {
		int sum = 0;
		for (int i = 0; i < r; i++) {
			for (int j = 0; j < c; j++) {
				if (room[i][j] > 0) {
					sum += room[i][j];
				}
			}
		}
		return sum;
	}
}