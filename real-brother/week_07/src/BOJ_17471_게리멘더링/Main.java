package BOJ_17471_게리멘더링;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

public class Main {
	static Scanner sc = new Scanner(System.in);
	static int n;
	static int[] population;
	static boolean[] populVisited;
	static boolean[] combVisited;
	static int nearCount;
	static List<List<Integer>> graph;
	static int diffMinPolulation;
	static List<Integer> redParty;
	static List<Integer> blueParty;
	static List<List<Integer>> combList;
	static List<Integer> tempComb;
	
    public static void main(String[] args) {
    	n = sc.nextInt();
    	population = new int[n];
    	for (int i = 0; i < n; i++) {
    		population[i] = sc.nextInt();
    	}
    	
    	graph = new ArrayList<List<Integer>>();
    	for (int i = 0; i <= n; i++) {
    		graph.add(new ArrayList<Integer>());
    	}
    	populVisited = new boolean[n+1];
    	combVisited = new boolean[n];
    	combList = new ArrayList<List<Integer>>();
    	tempComb = new ArrayList<Integer>();
    	diffMinPolulation = Integer.MAX_VALUE;
    	
    	for (int i = 1; i <= n; i++) {
    		nearCount = sc.nextInt();
    		for (int j = 0; j < nearCount; j++) {
    			int desti = sc.nextInt();
    			graph.get(i).add(desti);
    		}
    	}
    	
    	
    	// 빨간 선거구 조합 구하기 - 전부 다 구할 필요 없음
    	// 절반까지만 해도 반대 경우가 자동으로 계산됨
    	for (int i = 1; i <= n / 2; i++) {
    		combVisited = new boolean[n+1];
    		generateRedCombinatons(1, i);
		}
    	
    	// 빨간 선거구 외 나머지는 파란 선거구라고 가정
    	for (List<Integer> comb : combList) {
			redParty = comb;
			blueParty = generateBlueParty(redParty);
			// 빨간, 파란 선거구 별로 bfs돌리기
			boolean redConnected = findConnectBfs(redParty);
			boolean blueConnected = findConnectBfs(blueParty);
			
			// 모든 선거구가 방문이 가능한지 확인
			boolean isAllConnected = (redConnected && blueConnected);
			
			// 방문이 가능하다면			
			if (isAllConnected) {
				// 각 인구수를 구해서 -> 차이를 구하기
				int redPopu = countPopulation(redParty);
				int bluePopu = countPopulation(blueParty);
				int diffPopu = Math.abs(redPopu - bluePopu);
				
				// 최솟값 갱신
				if (diffPopu < diffMinPolulation) diffMinPolulation = diffPopu;
			}
		}
    	
    	// 모든 선거구가 방문이 불가능하다면 = 나눌 수 없음 -> -1
    	if (diffMinPolulation == Integer.MAX_VALUE) System.out.println(-1);
    	else System.out.println(diffMinPolulation);
    	
    	
    	
    }
    
    // 인구수를 count하는 함수
    private static int countPopulation(List<Integer> team) {
		int total = 0;
		for (int t : team) {
			total += population[t-1];
		}
		return total;
	}

	// 팀의 숫자들을 받아서 그래프에 연결이 되어있는지 확인하는 함수
    private static boolean findConnectBfs(List<Integer> team) {
    	populVisited = new boolean[n+1];
    	Queue<Integer> queue = new ArrayDeque<Integer>();
    	int startNode = team.get(0);
		queue.add(startNode);
		populVisited[startNode] = true;
		
		while (!queue.isEmpty()) {
			int now = queue.poll();
			populVisited[now] = true;
			for (int dest: graph.get(now)) {
				if (!populVisited[dest] && team.contains(dest)) {
					queue.add(dest);					
				}
			}
		}
		
		for (int t : team) {
			if (populVisited[t] == false) return false;
		}
    	
		return true;
	}

    // 백트래킹을 활용한 빨간 팀 조합 구하기
    private static void generateRedCombinatons(int depth, int r) {
    	if (tempComb.size() == r) {
    		List<Integer> copyTempComb= new ArrayList<Integer>(tempComb);
    		combList.add(copyTempComb);
    		return;
    	}
    	
    	for (int i = depth; i <= n; i++) {
    		if (!combVisited[i]) {
    			combVisited[i] = true;
    			tempComb.add(i);
    			generateRedCombinatons(i + 1, r);
    			combVisited[i] = false;
    			tempComb.remove(tempComb.size()-1);
    		}
    	}
    }
    
    // 나머지 파란팀 만들어내기
	private static List<Integer> generateBlueParty(List<Integer> red) {
		List<Integer> initList = new ArrayList<Integer>();
		for (int i = 1; i <= n; i++) {
			initList.add(i);
		}
		for (int r : red) {
			initList.remove(initList.indexOf(r));
		}
		return initList;
	}
    
}