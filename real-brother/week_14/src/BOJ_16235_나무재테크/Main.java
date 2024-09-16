package BOJ_16235_나무재테크;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int n, m, k;
    static int[][] groundNourishment, robot;
    static LinkedList<Tree> treeStatus;

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        int nowYear = 1;

        groundNourishment = new int[n][n];
        robot = new int[n][n];
        treeStatus = new LinkedList<>();

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                groundNourishment[i][j] = 5;
                robot[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int treeR = Integer.parseInt(st.nextToken()) - 1;
            int treeC = Integer.parseInt(st.nextToken()) - 1;
            int treeAge = Integer.parseInt(st.nextToken());
            treeStatus.add(new Tree(treeR, treeC, treeAge));
        }


		// K년도 동안 시뮬레이션
		while (nowYear <= k) {
			// 봄
			// 1. 자신의 나이만큼 양분을 먹고, 나이가 1 증가
			// 2. 하나의 칸에 여러 나무가 있다면 나이가 어린 나무부터 먹는다.
			// 3. 양분이 부족해 사진의 나이만큼 먹을 수 없는 나무는 죽는다.
			// 여기서 Iterator를 쓰지않고 for문을 쓰면 시간초과남
			LinkedList<Tree> deadTreeList = new LinkedList<Tree>();
			Collections.sort(treeStatus);
			Iterator<Tree> iterator = treeStatus.iterator();
			while (iterator.hasNext()) {
				Tree tree = iterator.next(); 
				int r = tree.r;
				int c = tree.c;
				int age = tree.age;

				// 토양의 양분과 비교하여 행동 결정
				if (groundNourishment[r][c] >= age) {
					groundNourishment[r][c] -= age;
					tree.age++;
				} else {
					deadTreeList.add(tree);
					iterator.remove();
				}
			}

			// 여름
			// 1. 봄의 죽은 나무는 양분으로 변한다.
			// 2. 각각 죽은 나무마다 나이/2 만큼 땅 양분 추가
			for (Tree deadTree : deadTreeList) {
				int r = deadTree.r;
				int c = deadTree.c;
				int age = deadTree.age;
				groundNourishment[r][c] += age / 2;
			}

			// 가을
			// 1. 나이가 5의 배수인 나무 번식
			// 2. 인접한 범위안의 모든 땅에 나이가 1인 나무 8개 추가
			LinkedList<Tree> addTreeList = new LinkedList<Tree>();
			for (Tree tree : treeStatus) {
				int r = tree.r;
				int c = tree.c;
				int age = tree.age;
				if (age % 5 != 0)	continue;

				int[] dr = { 0, 1, 1, 1, 0, -1, -1, -1 };
				int[] dc = { -1, -1, 0, 1, 1, 1, 0, -1 };

				for (int k = 0; k < 8; k++) {
					int newR = r + dr[k];
					int newC = c + dc[k];
					if (newR < 0 || newR >= n || newC < 0 || newC >= n)	continue;
					addTreeList.add(new Tree(newR, newC, 1));
				}
			}
			treeStatus.addAll(0, addTreeList);

			// 겨울
			// 1. 로봇이 돌면서 땅에 양분을 추가
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					groundNourishment[i][j] += robot[i][j];
				}
			}

			nowYear++;
		}

		int answer = treeStatus.size();
		System.out.println(answer);
	}

	static class Tree implements Comparable<Tree> {
		int r;
		int c;
		int age;

		public Tree(int r, int c, int age) {
			this.r = r;
			this.c = c;
			this.age = age;
		}

		// Tree의 나이를 기준으로 오름차순으로 정렬
		public int compareTo(Tree otherTree) {
			return Integer.compare(this.age, otherTree.age);
		}

		public String toString() {
			return "Tree{" + "r=" + r + "c=" + c + "age=" + age + '}';
		}
	}
}
