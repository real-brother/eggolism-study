package BOJ_3190_뱀;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;
import java.util.Scanner;

public class Main {
	static Scanner sc = new Scanner(System.in);
	static int N, K, L, nowSecond = 0;
	static List<int[]> appleCoord = new ArrayList<int[]>();
	static List<Integer> snakeSecond = new ArrayList<Integer>();
	static List<Character> snakeDirection = new ArrayList<Character>();
	static Snake snake = new Snake(0);
	// 방향 오른쪽(0), 아래(1), 왼쪽(2), 위(3)
	 
	public static void main(String[] args) {
		N = sc.nextInt();
		K = sc.nextInt();
		for (int i = 0; i < K; i++) {
			int r = sc.nextInt() - 1;
			int c = sc.nextInt() - 1;
			appleCoord.add(new int[] {r, c});
		}
		L = sc.nextInt();
		for (int i = 0; i < L; i++) {
			int s = sc.nextInt();
			char d = sc.next().charAt(0);
			snakeSecond.add(s);
			snakeDirection.add(d);
		}
		snake.coordList.push(new int[] {0, 0});
		// 방향 바꾸는 Index
		int transIndex = 0;
		// 뱀이 범위를 나가면 게임이 종료
		out: while (true) {
			nowSecond++;
			int nowHeadR = snake.coordList.getFirst()[0];
			int nowHeadC = snake.coordList.getFirst()[1];
			// 뱀의 몸길이를 늘려 머리를 다음칸에 위치
			switch (snake.dir){
			case 0: { nowHeadC++;	break; }
			case 1: { nowHeadR++;	break; }
			case 2: { nowHeadC--;	break; }
			case 3: { nowHeadR--;	break; }}
			int[] newCoord = new int[] {nowHeadR, nowHeadC};
			// 새로운 좌표가 자기자신의 몸과 부딪히면 게임 끝
			if (!snakeCollision(newCoord)) break out;
			snake.coordList.push(newCoord);
			
			// 벽에 부딪히면 게임이 끝
			if(!isSnakeOut(newCoord)) break out;
			
			// 사과가 있는지 확인
			int[] nowHead = snake.coordList.getFirst();
			boolean isApple = false;
			for (int[] coord : appleCoord) {
				// 사과가 있다면
			    if (Arrays.equals(coord, nowHead)) {
			    	// 사과 제거
			        appleCoord.remove(coord);
			        isApple = true;
			        break; 
			    } else {
			    }
			}
			// 사과를 못먹었다면
			if (!isApple) {
		    	// 꼬리 하나 제거
		    	snake.coordList.pollLast();
			}
			
			// 빙향이 바뀔때가 되었으면 바꾸기
			if (transIndex < L && nowSecond == snakeSecond.get(transIndex)) {
				// 왼쪽 오른쪽 구분해서 뱀 방향 바꿔주기
				if (snakeDirection.get(transIndex) == 'L') {
					snake.dir -= 1;
					if (snake.dir == -1) snake.dir += 4;
				} else {
					snake.dir += 1;
					if (snake.dir == 4) snake.dir -= 4;
				}
				transIndex++;
			}
		}
		
		System.out.println(nowSecond);
	}
	
	
	// 새로운 좌표가 뱀 자기자신과 겹치는지 확인
	private static boolean snakeCollision(int[] newCoord) {
		for (int[] coord : snake.coordList) {
			// 좌표가 겹친다면 충돌
		    if (Arrays.equals(coord, newCoord)) {
		    	return false;
		    }
		}
		return true;
	}

	// 뱀이 게임 범위 밖으로 나갔는지 확인
	private static boolean isSnakeOut(int[] newCoord) {
		int headR = newCoord[0];
		int headC = newCoord[1];
		if (headR < 0 || headR >= N || headC < 0 || headC >= N) return false;
		return true;
	}

	static class Snake {
		Deque<int[]> coordList = new ArrayDeque<int[]>();
		int dir;
		public Snake(int dir) {
			this.dir = dir;
		}
	}
}