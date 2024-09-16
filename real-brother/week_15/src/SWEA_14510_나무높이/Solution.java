package SWEA_14510_나무높이;

import java.util.Scanner;

public class Solution {
	static Scanner sc = new Scanner(System.in);
	static int TEST_CASE, n, maxHeight = Integer.MIN_VALUE;
	static int[] treeList;
	
	public static void main(String[] args) {
		TEST_CASE = sc.nextInt();
		for (int tc = 1; tc <= TEST_CASE; tc++) {
			int days = 0;
			n = sc.nextInt();
			treeList = new int[n];
			for (int i = 0; i < n; i++) {
				treeList[i]= sc.nextInt();
				maxHeight = Math.max(maxHeight, treeList[i]);
			}
			
			while (!allMaxHeight()) {
				days++;
				int watering = (days % 2 == 1) ? 1 : 2;
				
				watered:
				for (int i = 0; i < treeList.length; i++) {
					if (treeList[i] + watering <= maxHeight) {
						treeList[i] += watering;
						break watered;
					}
				}
			}
			System.out.printf("#%d %d\n", tc, days);
		}
	}

	private static boolean allMaxHeight() {
		for (int i = 0; i < treeList.length; i++) {
			if (treeList[i] != maxHeight) return false;
		}
		return true;
	}
}