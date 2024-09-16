package BOJ_17472_다리만들기2;

/*
 *  1. 영역 별로 숫자를 다르게 해주면서 바다와 인접한 지역을 startBridge 큐에 넣어준다
 *  2. 큐에 담긴 것들을 하나하나 돌려보면서 길이가 2 이상인 다리를 모두 인접리스트에 담아준다
 *  3. 수업시간에 배운 프림을 쓴다
 */

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
	static int N, M, sum;
	static int[][] board;
	static boolean[][] visited;
	static boolean[] areaVisited;
	static int[] dist;
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	static Queue<int[]> startBridge = new LinkedList<>();
	static ArrayList<int[]>[] graph;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		M = sc.nextInt();
		board = new int[N][M];
		
		for(int r = 0; r < N; r++) for(int c = 0; c < M; c++) board[r][c] = sc.nextInt();
		
		visited = new boolean[N][M];
		int areaNum = 1;
		for(int r = 0; r < N; r++) {
			for(int c = 0; c < M; c++) {
				if(!visited[r][c] && board[r][c] == 1) {
					areaBfs(r, c, areaNum);
					areaNum++;
				}
			}
		}
		graph = new ArrayList[areaNum];
		dist = new int[areaNum];		
		areaVisited = new boolean[areaNum];		
		for(int i = 1; i < areaNum; i++) {
			graph[i] = new ArrayList<>();
			dist[i] = Integer.MAX_VALUE;
		}
		dist[1] = 0;
		
		makeAllBridge();
		
		sum = 0;
		prim(1);
		
		// 방문하지 않은 섬이 있다면 -1을 출력
		for(int i = 1; i < areaVisited.length; i++) {
			if(!areaVisited[i]) {
				sum = -1;
				break;
			}
		}
		System.out.println(sum);
	}
	
	// 영역 별로 숫자를 다르게 배정
	static void areaBfs(int nowY, int nowX, int areaNum) {
		Queue<int[]> queue = new LinkedList<>();
		queue.offer(new int[] {nowY, nowX});
		while(!queue.isEmpty()) {
			int[] num = queue.poll();
			board[num[0]][num[1]] = areaNum;
			for(int i = 0; i < 4; i++) {
				int moveY = num[0] + dr[i];
				int moveX = num[1] + dc[i];
				
				if(0 > moveY || moveY >= N || 0 > moveX || moveX >= M) continue;
				
				// 옆에 바다가 있는 곳을 startBridge 큐에 넣음
				if(board[moveY][moveX] == 0) {
					startBridge.offer(new int[] {num[0], num[1], i});
					continue;
				}
				
				if(!visited[moveY][moveX] && board[moveY][moveX] == 1) {
					queue.offer(new int[] {moveY, moveX});
					visited[moveY][moveX] = true;
				}
			}
		}
	}
	
	// 모든 다리 만들기
	// startBridge 큐에서 다리를 꺼내서 길이를 계산하고 2가 넘는다면 인접리스트에 저장해준다
	static void makeAllBridge() {
			
		while(!startBridge.isEmpty()) {
			int[] num = startBridge.poll();
			int moveY = num[0];
			int moveX = num[1];
			int count = 0;
			
			while(true) {
				moveY += dr[num[2]];
				moveX += dc[num[2]];
				if(0 > moveY || moveY >= N || 0 > moveX || moveX >= M) break;
					
				if(board[moveY][moveX] == 0) count++;
				else {
					if(count >= 2) graph[board[num[0]][num[1]]].add(new int[] {board[moveY][moveX], count});
					break;
				}
			}		
		}
	}
	
	//프림
	static void prim(int start) {
		int tmp = dist.length;
		
		while(tmp-->0) {
			int min = Integer.MAX_VALUE;
			int idx = 0;
			for(int i = 1; i < dist.length; i++) {
				if(min > dist[i] && !areaVisited[i]) {
					min = dist[i];
					idx = i;
				}
			}
			
			areaVisited[idx] = true;
			sum += dist[idx];
			if(idx == 0) return;
			
			for(int i = 0; i < graph[idx].size(); i++) {
				int next = graph[idx].get(i)[0];
				int distance = graph[idx].get(i)[1];
				if(!areaVisited[next] &&  distance < dist[next]) {
					dist[next] = distance;
				}
			}
		}
	}

}