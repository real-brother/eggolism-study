package BOJ_1780_종이의개수;

import java.util.Scanner; 

public class Main {
	static Scanner sc = new Scanner(System.in);
	static int[][] paper;
	static int n;
	static int[] ansPaperCount;
	
    public static void main(String[] args) {
        n = sc.nextInt();
        ansPaperCount = new int[3];
        paper = new int[n][n];
        for (int i = 0; i < n; i++) {
        	for (int j = 0; j < n; j++) {
    			paper[i][j] = sc.nextInt();
    		}
		}
        
        divideAndConquer(n, 0, 0);
        
        for (int i = 0; i < ansPaperCount.length; i++) {
			System.out.println(ansPaperCount[i]);
		}
        
    }
    
    // 분할 정복~
	private static void divideAndConquer(int size, int start, int end) {
		// 사이즈 크기만큼 돌았는데 -> 모두 같은 색이라면
		// 더이상 분할을 하지 않아도됨
		if(isSameColor(size, start, end)) {
			int nowColor = paper[start][end];
			switch (nowColor) {
			case -1:
				ansPaperCount[0]++;
				break;
			case 0:
				ansPaperCount[1]++;
				break;
			case 1:
				ansPaperCount[2]++;				
				break;
			default:
				break;
			}
		} 
		// 다른 색이 있다면 9개로 분할해주자!
		else {
			// 크기를 1/3로 줄이고
			int updateSize = size / 3;
			// 3*3 각 인덱스별로 start, end 조절
			divideAndConquer(updateSize, start, end);
			divideAndConquer(updateSize, start + updateSize, end);
			divideAndConquer(updateSize, start, end + updateSize);
			divideAndConquer(updateSize, start + updateSize, end + updateSize);
			divideAndConquer(updateSize, start + updateSize * 2, end);
			divideAndConquer(updateSize, start, end + updateSize * 2);
			divideAndConquer(updateSize, start + updateSize * 2, end + updateSize);
			divideAndConquer(updateSize, start + updateSize, end + updateSize * 2);
			divideAndConquer(updateSize, start + updateSize * 2, end + updateSize * 2);
		}
	}
	
	// 모두 같은 색인지 판단하는 함수
	private static boolean isSameColor(int size, int start, int end) {
		int defaultColor = paper[start][end];
		for (int i = start; i < start + size; i++) {
			for (int j = end; j < end + size; j++) {
				if (defaultColor != paper[i][j]) return false;
			}
		}
		return true;
	}
}
