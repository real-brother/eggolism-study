package BOJ_20061_모노미노도미노2;

// 문제에서 시키는대로 구현한다.

import java.util.Scanner;

public class Main {
	static int score, blockCnt;
	static int[][] blue;
	static int[][] green;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		
		// 그림에 있는대로 블루랑 그린을 만들어준다
		// 빨강은 필요 x
		blue = new int[4][6];
		green = new int[6][4];
		
		// 모노미노도미노를 N번 실행시킨다
		while(N-->0) monominoDomino(sc.nextInt(), sc.nextInt(), sc.nextInt());
		
		// 남아있는 블록의 개수를 센다
		calBlockCount();
		System.out.println(score);
		System.out.println(blockCnt);
	}
	
	// 모노미노도미노 메서드
	static void monominoDomino(int type, int nowY, int nowX) {
		setBlue(type, nowY);
		setGreen(type, nowX);
		reBuildBlue();
		reBuildGreen();
	}
	
	// 파란색 보드에 입력에 맞게 세팅
	static void setBlue(int type, int nowY) {
		
		// 입력에 따라 블록 선택
		if(type == 1) {
			// 게임 방식과 동일하게
			// 앞에 뭐가 있을 때까지 가서 그 전 자리에 놓는다.
			for(int i = 2; i <= 5; i++) {
				if(blue[nowY][i] == 1) {
					blue[nowY][i-1] = 1;
					return;
				}
			}
			// 끝까지 왔다면 맨 끝에 놓을 수 있는 것이므로 놓아준다
			blue[nowY][5] = 1;
		}
		else if(type == 2) {
			for(int i = 2; i <= 5; i++) {
				if(blue[nowY][i] == 1) {
					blue[nowY][i-1] = 1;
					blue[nowY][i-2] = 1;
					return;
				}
			}
			blue[nowY][5] = 1;
			blue[nowY][4] = 1;
			
		}
		else {
			for(int i = 2; i <= 5; i++) {
				if(blue[nowY][i] == 1 || blue[nowY+1][i] == 1) {
					blue[nowY][i-1] = 1;
					blue[nowY+1][i-1] = 1;
					return;
				}
			}
			blue[nowY][5] = 1;
			blue[nowY+1][5] = 1;
		}
	}
	
	// 초록색 보드에 입력에 맞게 세팅, 파랑이랑 동일한 로직
	static void setGreen(int type, int nowX) {
		if(type == 1) {
			for(int i = 2; i <= 5; i++) {
				if(green[i][nowX] == 1) {
					green[i-1][nowX] = 1;
					return;
				}
			}
			green[5][nowX] = 1;
		}
		else if(type == 2) {
			for(int i = 2; i <= 5; i++) {
				if(green[i][nowX] == 1 || green[i][nowX+1] == 1) {
					green[i-1][nowX] = 1;
					green[i-1][nowX+1] = 1;
					return;
				}
			}
			green[5][nowX] = 1;
			green[5][nowX+1] = 1;
		}
		else {
			for(int i = 2; i <= 5; i++) {
				if(green[i][nowX] == 1) {
					green[i-1][nowX] = 1;
					green[i-2][nowX] = 1;
					return;
				}
			}
			green[5][nowX] = 1;
			green[4][nowX] = 1;
		}
	}
	
	// 파란색 보드 후처리
	static void reBuildBlue() {
		// 4칸이 완성되었다면 없애고 1점 추가하고
		// 블록을 없애고 한 칸씩 떙겨온다
		// 아직 검사하지 않은 칸을 땡겨왔으므로 c++을해서 현재 행 다시 검사
		for(int c = 5; c >= 2; c--) {
			int count = 0;
			for(int r = 0; r < 4; r++) if(blue[r][c] == 1) count++;
			if(count == 4) {
				score++;
				for(int c2 = c; c2 >= 1; c2--) {
					for(int r = 0; r < 4; r++) {
						if(blue[r][c2-1] == 1) {
							blue[r][c2] = 1;
							blue[r][c2-1] = 0;
						}
						else blue[r][c2] = 0;
					}
				}
				c++;
			}
		}
		
		// 연한색 칸을 몇 칸이나 블록이 차지하고 있는지 확인
		int count = 0;
		for(int c = 0; c <= 1; c++) {
			for(int r = 0; r < 4; r++) {
				if(blue[r][c] == 1) {
					count++;
					break;
				}
			}
		}
		
		// 1칸이면 1칸씩 떙기고
		if(count == 1) {
			for(int c = 5; c >= 2; c--) {
				for(int r = 0; r < 4; r++) {
					if(blue[r][c-1] == 1) {
						blue[r][c] = 1;
						blue[r][c-1] = 0;
					}
					else blue[r][c] = 0;
				}
			}
		}
		// 2칸이면 2칸씩 땡긴다
		else if(count == 2) {
			for(int c = 5; c >= 2; c--) {
				for(int r = 0; r < 4; r++) {
					if(blue[r][c-2] == 1) {
						blue[r][c] = 1;
						blue[r][c-2] = 0;
					}
					else blue[r][c] = 0;
				}
			}
		}
	}
	
	
	// 초록색 보드 후처리, 파랑이랑 동일한 로직
	static void reBuildGreen() {
		for(int r = 5; r >= 2; r--) {
			int count = 0;
			for(int c = 0; c < 4; c++) if(green[r][c] == 1) count++;
			if(count == 4) {
				score++;
				for(int r2 = r; r2 >= 1; r2--) {
					for(int c = 0; c < 4; c++) {
						if(green[r2-1][c] == 1) {
							green[r2][c] = 1;
							green[r2-1][c] = 0;
						}
						else green[r2][c] = 0;
					}
				}
				r++;
			}
		}

		int count = 0;
		for(int r = 0; r <= 1; r++) {
			for(int c = 0; c < 4; c++) {
				if(green[r][c] == 1) {
					count++;
					break;
				}
			}
		}
		
		if(count == 1) {
			for(int r = 5; r >= 2; r--) {
				for(int c = 0; c < 4; c++) {
					if(green[r-1][c] == 1) {
						green[r][c] = 1;
						green[r-1][c] = 0;
					}
					else green[r][c] = 0;
				}
			}
		}
		else if(count == 2) {
			for(int r = 5; r >= 2; r--) {
				for(int c = 0; c < 4; c++) {
					if(green[r-2][c] == 1) {
						green[r][c] = 1;
						green[r-2][c] = 0;
					}
					else green[r][c] = 0;
				}
			}
		}
	}
	
	// 남은 블록 개수 카운팅
	static void calBlockCount(){
		for(int r = 2; r < 6; r++) {
			for(int c = 0; c < 4; c++) {
				if(green[r][c] == 1) blockCnt++;
				if(blue[c][r] == 1) blockCnt++;
			}
		}
	}
}