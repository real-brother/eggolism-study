package BOJ_15686_치킨배달;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    static Scanner sc = new Scanner(System.in);
    static int n, m, restaurantCount, minChickenDistance = Integer.MAX_VALUE;;
    static int[][] chickenMap;
    static List<int[]> chickenLoc = new ArrayList<int[]>();
    static int[] chickenIndex, perm;
    static boolean[] visited;

    public static void main(String[] args) {
    	n = sc.nextInt();
    	m = sc.nextInt();
    	restaurantCount = 0;
    	chickenMap = new int[n][n];
    	for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				chickenMap[i][j] = sc.nextInt();
				if (chickenMap[i][j] == 2) {
					chickenLoc.add(new int[] {i, j});
					restaurantCount++;
				}
			}
		}
    	visited = new boolean[restaurantCount];
    	chickenIndex = new int[restaurantCount];
    	for (int i = 0; i < restaurantCount; i++) {
    		chickenIndex[i] = i;
		}
    	perm = new int[m];
    	
    	chickenComb(0,0);
    	
    	System.out.println(minChickenDistance);
    }
    
    // 조합으로 치킨집 고르기
	private static void chickenComb(int depth, int start) {
		if (depth == m) {
			int cityChickenDis = calculateCityChickenDistance();
			minChickenDistance = Math.min(minChickenDistance, cityChickenDis);
			return;
		}
		
		for (int i = start; i < restaurantCount; i++) {
			if (!visited[i]) {
				perm[depth] = chickenIndex[i];
				visited[i] = true;
				chickenComb(depth+1, i+1);
				visited[i] = false;
			}
		}
	}
	
	// 도시의 치킨 거리를 계산하기
	private static int calculateCityChickenDistance() {
		int cityChichenDistance = 0;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (chickenMap[i][j] == 1) {
					cityChichenDistance += getChickenDistance(i, j);
				}
			}
		}
		return cityChichenDistance;
	}
	
	// 각 집에서부터 치킨최소거리 계산하기
	private static int getChickenDistance(int i, int j) {
		int minDis = Integer.MAX_VALUE;
		for (int resIndex : perm) {
			int checkenR = chickenLoc.get(resIndex)[0];
			int checkenC = chickenLoc.get(resIndex)[1];
			int distance = Math.abs(i-checkenR) + Math.abs(j-checkenC);
			minDis = Math.min(distance, minDis);
		}
		return minDis;
	}
}
