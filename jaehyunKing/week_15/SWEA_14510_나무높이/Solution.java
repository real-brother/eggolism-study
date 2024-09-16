package SWEA_14510_나무높이;

/*
 *  길이가 가장 큰 나무를 max로 잡고 나무 배열을 만들어 max - 현재 나무 길이를 한 값을 저장해준다.
 *  그러면 나무 배열에는 채워야하는 길이만 남게 된다.
 *  홀수 날이면 길이가 1인 나무가 있으면 찾아서 지워주고 없으면
 *  길이가 3이상인 나무에서 1을 빼준다.
 *  이것도 없으면 홀수 날에 길이가 2인 나무가 2개 이상이면 지워준다 -> 2 2 -> 1 2 1 3일만에 처리가능
 *  짝수 날이면 길이가 2인 나무가 있으면 찾아서 지워주고 없으면
 *  길이가 3이상인 나무에서 2를 빼준다.
 */

import java.util.Scanner;

public class Solution {
	static int[] tree;
	static int max;
	static boolean complete;
	static int treeCount;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int T = sc.nextInt();

		for(int tc = 1; tc <= T; tc++) {
			int N = sc.nextInt();
			
			tree = new int[N];
			max = Integer.MIN_VALUE;
			for(int i = 0; i < N; i++) {
				tree[i] = sc.nextInt();
				if(max < tree[i]) max = tree[i];
			}
			
			treeCount = N;
			
			for(int i = 0; i < N; i++) {
				tree[i] = max - tree[i];
				if(tree[i] == 0) treeCount--;
			}
			
			complete = false;
			int day = 1;
			
			if(treeCount == 0) {
				day = 0;
				complete = true;
			}
			
			while(!complete) {
				oneDay(day);
				if(complete) break;
				else day++;
			}
			
			System.out.println("#" + tc + " " + day);
			
		}
		
	}

	static void oneDay(int day) {
		boolean flag = false;
		int water = 0;
		if(day % 2 == 0) water = 2;
		else water = 1;
		
		for(int i = 0; i < tree.length; i++) {
			
			if(tree[i] == 2) {
				if(water == 2) {
					tree[i] -= water;
					treeCount--;
					flag = true;
					break;
				}
			}
			
			if(tree[i] == 1) {
				if(water == 1) {
					tree[i] -= water;
					treeCount--;
					flag = true;
					break;
				}
			}	
		}
		
		if(!flag) {
			for(int i = 0; i < tree.length; i++) {
				if(tree[i] > 2) {
					tree[i] -= water;
					flag = true;
					break;
				}
			}
		}
		
		if(!flag && treeCount >= 2 && water == 1) {
			for(int i = 0; i < tree.length; i++) {
				if(tree[i] == 2) {
					tree[i] -= water;
					flag = true;
					break;
				}
			}
		}
		
		if(treeCount == 0) complete = true;
		
	
	}

}