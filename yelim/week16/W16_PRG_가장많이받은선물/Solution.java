package W16_PRG_가장많이받은선물;

import java.util.HashMap;
import java.util.Map;

public class Solution {

	public static void main(String[] args) {
		// 문제) 프로그래머스_가장 많이 받은 선물
		
		String[] friends = {"muzi", "ryan", "frodo", "neo"};
		String[] gifts = {"muzi frodo", "muzi frodo", "ryan muzi", "ryan muzi", "ryan muzi",
						"frodo muzi", "frodo ryan", "neo muzi"};
		
		Map<String, Integer> map = new HashMap<>();
		
		for(int i=0; i<friends.length; i++) {
			map.put(friends[i], i);
		}
		
		// 행은 friends 순서, 열은 준 선물-받은 선물-지수
//		int[][] point = new int[friends.length][3];
//		
//		for(int g=0; g<gifts.length; g++) {
//			String[] str = gifts[g].split(" ");
//			
//			for(int i=0; i<friends.length; i++) {
//				if (str[0].equals(friends[i])) {
//					point[i][0]++;
//				}
//				if (str[1].equals(friends[i])) {
//					point[i][1]++;
//				}
//			}
//		}
//		
//		// 지수 계산해서 point에 넣기
//		for(int i=0; i<friends.length; i++) {
//			point[i][2] = point[i][0] - point[i][1];
//		}
		
		
		// 쉽게 한 번에 해결..
		// 행은 friends 순서, 열은 지수
		int[] index = new int[friends.length];
		int[][] gat = new int[friends.length][friends.length];
		
		for(int g=0; g<gifts.length; g++) {
			String[] str = gifts[g].split(" ");
			
			index[map.get(str[0])]++;
			index[map.get(str[1])]--;
			gat[map.get(str[0])][map.get(str[1])]++;
		}
		
		int ans = 0;
		
		for(int i=0; i<friends.length; i++) {
			int cnt = 0;
			for(int j=0; j<friends.length; j++) {
				if (i==j) continue;
				
				if(gat[i][j] > gat[j][i]) {
					cnt++;
				}
				else if (gat[i][j] == gat[j][i] && index[i] > index[j]) {
					cnt++;
				}
			}
			ans = Math.max(ans, cnt);
		}
		
		System.out.println(ans);

	}

}
