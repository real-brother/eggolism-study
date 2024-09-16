package W9_BOJ_20055_컨베이어벨트;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		/* 문제) 컨베이어 벨트 위의 로봇
		 * 로봇을 올리는 위치에 올리거나 로봇이 어떤 칸으로 이동하면 그 칸의 내구도는 즉시 1만큼 감소한다.
		 * 
		 * 벨트가 각 칸 위에 있는 로봇과 함께 한 칸 회전한다.
		 * 가장 먼저 벨트에 올라간 로봇부터, 벨트가 회전하는 방향으로 한 칸 이동할 수 있다면 이동한다. 
		 * 로봇이 이동하기 위해서는 이동하려는 칸에 로봇이 없으며, 그 칸의 내구도가 1 이상 남아 있어야 한다.
		 * 올리는 위치에 있는 칸의 내구도가 0이 아니면 올리는 위치에 로봇을 올린다.
		 * 내구도가 0인 칸의 개수가 K개 이상이라면 과정을 종료한다. 그렇지 않다면 1번으로 돌아간다.
		 * 
		 * 종료되었을 때 몇 번째 단계가 진행 중이었는지 구하기
		 * 
		 * n = 컨베이어벨트 길이 / k = 내구도가 0인 칸의 최대 개수
		 */
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		
		// 컨베이어벨트 배열
		int[] belt = new int[2*n];
		
		// 벨트에 내구도 입력받기
		for(int i=0; i<2*n; i++) {
			belt[i] = Integer.parseInt(st.nextToken());
		}
		
		// 해당 칸에 로봇이 있는지 없는지 체크 할 배열
		boolean[] robot = new boolean[n];
		
		// 단계 1로 초기화
		int level = 1;
		
		// 
		while(true) {
			// 1. 벨트+로봇 회전
			// 옆으로 한 칸씩 옮겨주기 (거꾸로!)
			int tmp = belt[2*n-1];
			for(int i=2*n-1; i>0; i--) {
				belt[i] = belt[i-1];
			}
			belt[0] = tmp;
			
			// 로봇도 한 칸씩 옮겨주기
			for(int i=n-1; i>0; i--) {
				robot[i] = robot[i-1];
			}
			// 옮기면 0에는 있을 수 없음.
			robot[0] = false;
			
			// 내리는 위치에 로봇이 있으면 false
			if(robot[n-1]) {
				robot[n-1] = false;
			}
			
			
			// 2. 먼저 벨트에 올라간 로봇부터 이동 가능하다면 이동하기
			// 이동하려는 칸에 로봇 없고, 내구성 1 이상
			for(int i=n-2; i>=0; i--) {
				if (robot[i] && !robot[i+1] && belt[i+1]>=1) {
					belt[i+1]--;	// 해당 칸 내구성 1 감소
					// 로봇 위치 체크
					robot[i] = false;
					robot[i+1] = true;
				}
			}
			
			// 내리는 위치에 로봇이 있으면 false
			if(robot[n-1]) {
				robot[n-1] = false;
			}
			
			// 3. 올리는 위치에 있는 칸의 내구성이 1이상이면 로봇 올리기
			if (belt[0] >= 1) {
				robot[0] = true;	// 위치 체크
				belt[0]--;	// 내구성 1 감소
			}
			
			// 4. 내구도가 0인 칸의 개수가 k개 이상이면 종료
			int cnt = 0;
			for(int i=0; i<2*n; i++) {
				// 내구도 0이면 카운트 증가
				if (belt[i]==0) {
					cnt++;
				}
			}
			
			// 내구도가 0인 개수가 k개 이상이면
			if (cnt >= k) {
				// 현재 단계 출력
				System.out.println(level);
				break;
			}
			
			// 단계 1 증가
			level++;
		}
	}
 
}
