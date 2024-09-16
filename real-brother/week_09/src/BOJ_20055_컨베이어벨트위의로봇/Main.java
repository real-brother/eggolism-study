package BOJ_20055_컨베이어벨트위의로봇;

import java.util.Scanner;

public class Main {
	static Scanner sc = new Scanner(System.in);
	static int n;
	static int k;
	static boolean[] robotIndexList; // 로봇이 어디에 있는지 나타내는 리스트
	static int step = 0;
	static int[][] conveyorBelt;

	public static void main(String[] args) {
		n = sc.nextInt();
		k = sc.nextInt();
		robotIndexList = new boolean[n];
		conveyorBelt = new int[2][n];
		for (int i = 0; i < n; i++) {
			conveyorBelt[0][i] = sc.nextInt();
		}
		for (int i = n - 1; i >= 0; i--) {
			conveyorBelt[1][i] = sc.nextInt();
		}

		// 내구도가 0인 칸의 개수가 k개 미만일 동안 수행
		while (countZero() < k) {
			// 벨트 회전 (로봇도 같이가야지)
			rotateBelt(conveyorBelt);
			for (int i = n-1; i >= 0; i--) {
				if (robotIndexList[i] == true) {
					if (i+1 < n) {
						robotIndexList[i+1] = true;
					}
					robotIndexList[i] = false;
				}
			}

			// 로봇이 한칸 앞으로 이동 가능한가? - 앞에 있는 로봇부터 뒤에 있는 로봇
			for (int i = n-1; i >= 0; i--) {
				if (robotIndexList[i] == true) 	{
					// 로봇을 움직일 수 있따면 처리하기
					// 로봇이 끝에 있따면 그냥 떨궈주고
					if (i == n -1) {
						robotIndexList[i] = false;
					} // 아니라면 옆으로 옮기기
					else if (isMovable(i)){
						robotIndexList[i+1] = true;
						robotIndexList[i] = false;
						conveyorBelt[0][i+1]--;
					}
				}
			}

			// 올리는 곳의 내구도가 남아있따면 로봇을 올리자
			if (conveyorBelt[0][0] > 0) {
				conveyorBelt[0][0]--;
				robotIndexList[0] = true;
			}
			
			step++;
		}
		System.out.println(step);

	}
	
	// 다음 벨트가 내구도가 남아있고, 앞에 로봇이 없다면 이동 가능
	private static boolean isMovable(int n) {
		if (conveyorBelt[0][n+1] >= 1 && !robotIndexList[n+1]) return true;

		return false;
	}
	
	// 벨트를 돌리자 
	public static void rotateBelt(int[][] conveyorBelt) {
	    int n = conveyorBelt[0].length;
	    
	    int lastElementFirstRow = conveyorBelt[0][n-1];
	    int firstElementSecondRow = conveyorBelt[1][0];
	    
	    // Shift elements in the first row
	    for (int i = n - 1; i > 0; i--) {
	        conveyorBelt[0][i] = conveyorBelt[0][i - 1];
	    }
	    conveyorBelt[0][0] = firstElementSecondRow;
	    
	    // Shift elements in the second row
	    for (int i = 0; i < n - 1; i++) {
	        conveyorBelt[1][i] = conveyorBelt[1][i + 1];
	    }
	    conveyorBelt[1][n - 1] = lastElementFirstRow;
	}

	// 벨트에서 0의 개수를 세자
	private static int countZero() {
		int result = 0;
		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < n; j++) {
				if (conveyorBelt[i][j] == 0)
					result++;
			}
		}
		return result;
	}
}