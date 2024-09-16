package BOJ_17472_다리만들기2;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;

public class Main {
    static Scanner sc = new Scanner(System.in);
    static int n, m;
    static int[][] island;
    static boolean[][] islandVisited;
    static int isLandCount = 0;
    static List<List<Edge>> graph;
    static int[] dr = {1, 0, -1, 0};
    static int[] dc = {0, -1, 0, 1};
    static int minMST = Integer.MAX_VALUE;
    
    // 모든 섬끼리 다리를 놓아보며 다리 길이를 가중치로 가지는 트리 구성하기 
    // 전체 섬이 연결되어 있을 경우만
    // 최소 스패닝트리 구하기

    public static void main(String[] args) {
    	n = sc.nextInt();
    	m = sc.nextInt();
    	island = new int[n][m];
    	graph = new ArrayList<List<Edge>>();
    	
    	
    	for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				island[i][j] = sc.nextInt();
			}
		}
    	
    	// BFS활용 -> 섬마다 번호 먹이기
    	initIslandNum();
    	
    	for (int i = 0; i <= isLandCount; i++) {
    		graph.add(new ArrayList<Edge>());
		}
    	
    	// 각 출발섬(num)에서부터 다른 섬까지 다리를 놓을 수 있는지?
    	for (int num = 1; num <= isLandCount; num++) {
			findBridge(num);
		}
    	
    	// 프림알고리즘으로 MST구하기
    	prim();
    	
    	// 다리를 놓을 수 없다면 -1 출력 or minMST 출력
    	System.out.println(minMST == Integer.MAX_VALUE ? -1 : minMST);
    	
    }

	private static void prim() {
		int[] parent = new int[isLandCount + 1];
        int[] key = new int[isLandCount + 1];
        boolean[] mstSet = new boolean[isLandCount + 1];

        PriorityQueue<Edge> pq = new PriorityQueue<>(isLandCount, Comparator.comparingInt(e -> e.length));

        Arrays.fill(key, Integer.MAX_VALUE);
        Arrays.fill(parent, -1);

        key[1] = 0; // 시작 노드를 1로 가정
        pq.offer(new Edge(1, key[1]));

        while (!pq.isEmpty()) {
            int u = pq.poll().dest;
            mstSet[u] = true;

            for (Edge e : graph.get(u)) {
                int v = e.dest;
                int length = e.length;

                if (!mstSet[v] && length < key[v]) {
                    parent[v] = u;
                    key[v] = length;
                    pq.offer(new Edge(v, key[v]));
                }
            }
        }

        int answer = 0;
        for (int i = 1; i <= isLandCount; i++) {
            answer += key[i];
            // 그 섬까지 갈 수 없다면
            if (key[i] == Integer.MAX_VALUE) {
            	answer = Integer.MAX_VALUE;
            	break;
            }
        }
        
        minMST = Math.min(minMST, answer);
	}

	private static void findBridge(int startNum) {
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (island[i][j] == startNum) {
					// 목적지와 해당 목적지까지의 최소 다리 길이를 저장할 맵
				    Map<Integer, Integer> minBridgeLengthMap = new HashMap<>(); 
				    int[][] testIsland = cloneArray();

				    // 시작하는 섬을 찾으면 4방으로 다리를 놓을 수 있는지 탐색
				    out:
				    for (int k = 0; k < 4; k++) {
				        int dest = -1;
				        int bridgeLength = 0;
				        int nr = i + dr[k];
				        int nc = j + dc[k];

				        if (nr < 0 || nr >= n || nc < 0 || nc >= m) continue;
				        if (testIsland[nr][nc] > 0) continue;

				        // 다리를 해당 방향으로 연장하기
				        while (testIsland[nr][nc] == 0) {
				            nr += dr[k];
				            nc += dc[k];
				            if (nr < 0 || nr >= n || nc < 0 || nc >= m) continue out;

				            bridgeLength++;
				        }
				        // 다리 길이조건 거르기
				        if (bridgeLength <= 1) continue;

				        // 다리 생성 완료
				        dest = testIsland[nr][nc];

				        // 현재 목적지(dest)가 맵에 없거나 현재 다리 길이가 기존에 저장된 길이보다 작으면 업데이트
				        // 각 목적지에 최소 거리를 저장하기 위함
				        if (!minBridgeLengthMap.containsKey(dest) || bridgeLength < minBridgeLengthMap.get(dest)) {
				            minBridgeLengthMap.put(dest, bridgeLength);
				        }
				    }

				    // 최소 길이의 다리만을 graph에 추가
				    for (Map.Entry<Integer, Integer> entry : minBridgeLengthMap.entrySet()) {
				    	graph.get(startNum).add(new Edge(entry.getKey(), entry.getValue()));
				    }
				}
			}
		}
	}

	private static void initIslandNum() {
		islandVisited = new boolean[n][m];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (island[i][j] == 1 && !islandVisited[i][j]) {
					isLandCount++;
					setBFS(i, j);
				}
			}
		}
	}
	
	private static void setBFS(int i, int j) {
		Queue<int[]> queue = new ArrayDeque<int[]>();
		queue.add(new int[] {i, j});
		islandVisited[i][j] = true;
		island[i][j] = isLandCount;
		
		while (!queue.isEmpty()) {
			int[] now = queue.poll();
			int nowR = now[0];
			int nowC = now[1];
			
			for (int k = 0; k < 4; k++) {
				int nr = nowR + dr[k];
				int nc = nowC + dc[k];
				if (nr < 0 || nr >= n || nc < 0 || nc >= m) continue;
				if (island[nr][nc] == 1 && !islandVisited[nr][nc]) {
					islandVisited[nr][nc] = true;
					island[nr][nc] = isLandCount;
					queue.add(new int[] {nr, nc});
				}
			}
		}
	}

	static class Edge{
		int dest;
		int length;
		
		public Edge(int dest, int length) {
			this.dest = dest;
			this.length = length;
		}

		@Override
		public String toString() {
			return "[dest=" + dest + ", length=" + length + "]";
		}
	}
	
	private static int[][] cloneArray() {
		int[][] returnArray = new int [n][m];
		for (int i = 0; i < n; i++)	returnArray[i] = island[i].clone();
		return returnArray;
	}
}
