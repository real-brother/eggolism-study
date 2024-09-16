package BOJ_17825_주사위윷놀이;


//  중복 순열을 통해 몇 번 말을 이동해 줄 지를 선택 + 구현
 

import java.util.Scanner;

public class Main {
	static int maxScore;
	// 나올 주사위를 받을 배열
	// 어떤 말을 이동할 지 선택하는 배열
	static int[] dice = new int[11];
	static int[] sel = new int[11];

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		for(int i = 1; i <= 10; i++) dice[i] = sc.nextInt();

		
		maxScore = 0;
		combination(1);
		System.out.println(maxScore);
	}
	
	// 중복 순열을 통해 몇 번 말을 이동할 지 선택
	static void combination(int idx) {
		if(idx == 11) {
			startGame();
			return;
		}
		
		sel[idx] = 0;
		combination(idx+1);
		
		sel[idx] = 1;
		combination(idx+1);
		
		sel[idx] = 2;
		combination(idx+1);
		
		sel[idx] = 3;
		combination(idx+1);
	}
	
	static void startGame(){
		int score = 0;
		// tokenPos -> 말의 현재 위치를 저장하는 배열
		// tokenBefore -> 말의 이전 위치를 저장하는 배열
		// tokenIn -> 말이 파란색 화살표를 탔는지 확인(외곽에 있는지 안쪽에 있는지 여부를 저장)
		int[] tokenPos = new int[4];
		int[] tokenBefore = new int[4];
		boolean[] tokenIn = new boolean[4];
		
		for(int i = 1; i <= 10; i++) {
			int move = dice[i];
			int token = sel[i];
			
			// 현재 말이 50(도착지)이라면 이미 도착한 상태 return
			// 조합 중에 이것보다 큰 경우가 무조건 있음
			if(tokenPos[token] == 50) return;
			
			// 주사위 한 번 던졌을 때를 표현
			while(move-->0) {
				int temp = tokenPos[token];
				
				// 윷놀이 안 쪽에 있다면 말을 특정 위치로 이동
				if(tokenIn[token]) {
					if(tokenPos[token] == 10) tokenPos[token] = 13; 
					else if(tokenPos[token] == 13) tokenPos[token] = 16; 
					else if(tokenPos[token] == 16) tokenPos[token] = 19; 
					else if(tokenPos[token] == 19) tokenPos[token] = 25; 
					else if(tokenPos[token] == 20) tokenPos[token] = 22;
					else if(tokenPos[token] == 22) tokenPos[token] = 24; 
					else if(tokenPos[token] == 24) tokenPos[token] = 25; 
					else if(tokenPos[token] == 25) tokenPos[token] = 30; 
					else if(tokenPos[token] == 26) tokenPos[token] = 25; 
					else if(tokenPos[token] == 27) tokenPos[token] = 26; 
					else if(tokenPos[token] == 28) tokenPos[token] = 27; 
					else if(tokenPos[token] == 30 && tokenBefore[token] == 25) tokenPos[token] = 35;
					else if(tokenPos[token] == 30 && tokenBefore[token] == 28) tokenPos[token] = 28;
					else if(tokenPos[token] == 35) tokenPos[token] = 40;
					else if(tokenPos[token] == 40) {
						tokenPos[token] = 50;
						break;
					}
				}
				else {
					if(tokenPos[token] == 40) {
						tokenPos[token] = 50;
						break;
					}
					else if(tokenPos[token] <= 38) tokenPos[token] += 2;
				}
				// 이전값을 저장
				tokenBefore[token] = temp;
				
			}
			
			// 한 번의 이동 끝에 10, 20, 30위치에 있으면 다음 이동에 안 쪽으로 들어감
			if(tokenPos[token] == 10 || tokenPos[token] == 20 || tokenPos[token] == 30) {
				tokenIn[token] = true;
			}
			
			// 이동을 마치는 칸에 다르 말이 있는 것을 방지
			if(tokenPos[token] != 50) {
				for(int j = 0; j < 4; j++) {
					if(token != j) {
						// 현재 숫자가 같은 지, 이전 숫자가 같은지, 안쪽 바깥 쪽 여부를 확인 (같은 숫자가 몇 개있는데 이렇게 하면 다 걸러짐) 
						if(tokenPos[token] == tokenPos[j] && tokenBefore[token] == tokenBefore[j] && tokenIn[token] == tokenIn[j]) return;
						// 40은 tokenIn인 경우도 있고 아닌 경우도 있음 40이 중복되면 return
						else if(tokenPos[token] == tokenPos[j] && tokenPos[j] == 40) return;
					}
				}
			}
			// 도착지는 점수가 없음
			if(tokenPos[token] != 50) score += tokenPos[token];
		}
		if(maxScore < score) maxScore = score; 
	}
}