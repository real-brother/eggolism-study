package BOJ_1043_거짓말;

/*
 * 진실을 아는 사람과 함께 파티에 간 사람들에게는 거짓말을 하면 안 된다.
 * -> 진실을 아는 사람을 visited true처리
 * 파티에 참석하는 인원을 리스트에 저장
 * 리스트를 순회하면서 true가 하나라도 있다면 해당 파티에 속한 인원을 다 true로 변경
 * 변경이 없을 때까지 반복
 * 거짓말이 가능한 파티를 count
 */

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
	static List<Integer>[] party;
	static boolean[] visited;
	static int M, partyCount;
	static boolean change;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		M = sc.nextInt();
		int K = sc.nextInt();
	
		visited = new boolean[N+1];
		for(int i = 0; i < K; i++) visited[sc.nextInt()] = true;
		
		party = new ArrayList[M];
		for(int i = 0; i < M; i++) {
			party[i] = new ArrayList<>();
			int partyMemberCount = sc.nextInt();
			// 0 ~ M-1(파티 개수 M개)번 째 파티에 참가하는 구성원을 입력
			for(int j = 0; j < partyMemberCount; j++) party[i].add(sc.nextInt());
		}
		
		partyCount = M;
		change = true;
		
		// 변화가 있었다면 계속 진행
		while(change) {
			change = false;
			party();			
		}
		partyCheck();
		System.out.println(partyCount);
	}
	
	static void party() {	
		for(int i = 0; i < M; i++) {
			for(int j = 0; j < party[i].size(); j++) {
				int num = party[i].get(j);
				// 파티를 하나씩 순회하면서 visited = true인 사람이 있으면
				if(visited[num]){
					// 해당 파티를 다 visited = true로 변경
					for(int k = 0; k < party[i].size(); k++) {
						num = party[i].get(k);
						// 이 때 visited = false인 사람이 있었다면
						// change = true로 바꾼다
						if(!visited[num]) {
							change = true;
							visited[num] = true;
						}
					}
					break;
				}
			}
		}
	}
	
	// 거짓말 가능한 파티의 개수를 세는 메서드
	static void partyCheck() {
		for(int i = 0; i < M; i++) {
			for(int j = 0; j < party[i].size(); j++) {
				int num = party[i].get(j);
				// visited가 true인 사람이 있는 파티는 count를 뺀다
				if(visited[num]){
					partyCount--;
					break;
				}
			}
		}
	}
}