package BOJ_1992_쿼드트리;

import java.util.Scanner;

public class Main {
	static Scanner sc = new Scanner(System.in);
	static int[][] quadTree;
	static int n;
    static StringBuilder compressed = new StringBuilder();
	
    public static void main(String[] args) {
        n = sc.nextInt();
        quadTree = new int[n][n];
        for (int i = 0; i < n; i++) {
        	String[] strArr = sc.next().split("");
        	for (int j = 0; j < n; j++) {        		
        		quadTree[i][j] = Integer.parseInt(strArr[j]);
    		}
		}
        	
        divideAndConquer(n, 0, 0);
        
        System.out.println(compressed);
        
    }
    
    // 분할 정복~
	private static void divideAndConquer(int size, int start, int end) {
		// 사이즈 크기만큼 돌았는데 -> 모두 같은 색이라면
		// 더이상 분할을 하지 않아도됨

		
		if(isSameColor(size, start, end)) {
			int nowColor = quadTree[start][end];
			if (nowColor == 1) {
				compressed.append(1);
			} else {
				compressed.append(0);
			}
		} 
		// 다른 색이 있다면 4개로 분할해주자!
		else {
			// 괄호를 넣는 타이밍 중요함
			compressed.append("(");
			// 크기를 1/2로 줄이고
			int updateSize = size / 2;
			// 2*2 각 인덱스별로 start, end 조절
			// 1 2
			// 3 4
			// 이 순서대로 탐색해야 정답이 나옴
			divideAndConquer(updateSize, start, end);
			divideAndConquer(updateSize, start, end + updateSize);
			divideAndConquer(updateSize, start + updateSize, end);
			divideAndConquer(updateSize, start + updateSize, end + updateSize);
			compressed.append(")"); // 괄호를 빼는 타이밍 중요함
		}
	}
	
	// 모두 같은 색인지 판단하는 함수
	private static boolean isSameColor(int size, int start, int end) {
		int defaultColor = quadTree[start][end];
		for (int i = start; i < start + size; i++) {
			for (int j = end; j < end + size; j++) {
				if (defaultColor != quadTree[i][j]) return false;
			}
		}
		return true;
	}
}
