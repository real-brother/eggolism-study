package BOJ_test.copy;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Main {
	static Scanner sc = new Scanner(System.in);
	static List<Integer> numList = new ArrayList<>();
	static List<Integer> tempNumList = new ArrayList<>();
	static List<List<Integer>> combNumList = new ArrayList<>();
	static List<List<Integer>> permNumList = new ArrayList<>();
	static int n;
	static int k;
	static boolean[] visited;
	
	
	public static void main(String[] args) {
		for (int i = 1; i <= 5; i++) {
			numList.add(i);			
		}
		n = numList.size();
		k = 2;
		visited = new boolean[n];
//		generateCombination(0);
//		printComb();
		
		generatePermutation(0);
		printPerm();
	}


	private static void printComb() {
		for (int i = 0; i < combNumList.size(); i++) {
			for (int j = 0; j < k; j++) {
				System.out.print(combNumList.get(i).get(j) + " ");
			}
			System.out.println();
		}
	}
	
	private static void printPerm() {
		for (int i = 0; i < permNumList.size(); i++) {
			for (int j = 0; j < k; j++) {
				System.out.print(permNumList.get(i).get(j) + " ");
			}
			System.out.println();
		}
	}


	private static void generateCombination(int start) {
		if (tempNumList.size() == k) {
			List<Integer> clonedList = new ArrayList<>(tempNumList); // 깊은 복사
	        combNumList.add(clonedList);
			return;
		}
		
		for (int i = start; i < numList.size(); i++) {
			if (!visited[i]) {
				visited[i] = true;
				tempNumList.add(numList.get(i));
				generateCombination(i + 1);
				visited[i] = false;
		        tempNumList.remove(tempNumList.size()-1);
			}
		}
		
	}
	
	private static void generatePermutation(int start) {
		if (tempNumList.size() == k ) {
			List<Integer> clonedList = new ArrayList<>(tempNumList); // 깊은 복사
			permNumList.add(clonedList);
			return;
		}
		
		for (int i = 0; i < numList.size(); i++) {
			if (!visited[i]) {
				visited[i] = true;
				tempNumList.add(numList.get(i));
				generatePermutation(i + 1);
				visited[i] = false;
		        tempNumList.remove(tempNumList.size()-1);
			}
		}
		
	}
	
}