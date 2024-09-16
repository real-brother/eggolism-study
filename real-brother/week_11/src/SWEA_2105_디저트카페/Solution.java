package SWEA_2105_디저트카페;
//test
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Solution {
    static Scanner sc = new Scanner(System.in);
    static int TEST_CASE, n;
    static int[][] dessertCafe;
    static int[] dr = {-1, 1, 1, -1}; // 0        1         2      3
    static int[] dc = {1, 1, -1, -1}; // 오른쪽위 / 오른쪽아래 / 왼쪽위 / 왼쪽 아래
    static int direction;
    static int maxDessert;
    
    static int dessertCount = 0;
    static List<Integer> dessertType = new ArrayList<Integer>();
    
    public static void main(String[] args) {
    	TEST_CASE = sc.nextInt();
    	for (int tc = 1; tc <= TEST_CASE; tc++) {
			n = sc.nextInt();
			dessertCafe = new int [n][n];
			maxDessert = 0;
    		
    		for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					dessertCafe[i][j] = sc.nextInt();
				}
			}
    		
    		
    		for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					findRoute(i, j);
				}
			}
    		
    		System.out.printf("#%d %d\n", tc, maxDessert == 0 ? -1 : maxDessert);
		}
	}

	private static void findRoute(int i, int j) {
		int rightMax = n-j-1;
		int upMax =  i;
		
		if (rightMax == 0 || upMax == 0) return;
//		System.out.println(rightMax + "   " + upMax);
		
		for (int ru = 1; ru <= upMax; ru++) {
			nextDessert:
			for (int rd = 1; ru + rd <= rightMax; rd++) {
				if (ru + rd > rightMax || ru > upMax) continue;
				
				dessertCount = 0;
				dessertType = new ArrayList<Integer>();
				List<Integer> dirCount = new ArrayList<Integer>();
				dirCount.add(ru);
				dirCount.add(rd);
				dirCount.add(ru);
				dirCount.add(rd);
				
				int nowR = i;
				int nowC = j;
				for (int k = 0; k < 4; k++) {
					for (int c = 0; c < dirCount.get(k); c++) {
						nowR += dr[k];
						nowC += dc[k];
						if (nowR < 0 || nowR >= n || nowC < 0 || nowC >= n) continue nextDessert;
						int nowDessert = dessertCafe[nowR][nowC];
						if (dessertType.contains(nowDessert)) continue nextDessert;

						dessertType.add(nowDessert);
						dessertCount++;
					}
				}
				
				maxDessert = Math.max(maxDessert, dessertCount);
//				if (maxDessert == dessertType.size()) {
//					System.out.println(i + " " + j + " " + rightMax + " " + upMax);
//					System.out.println(dessertType);
//				}
				
			}
		}
		
		
	}

	
}
