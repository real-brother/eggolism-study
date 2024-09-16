package BOJ_1043_거짓말;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class Main {
	static Scanner sc = new Scanner(System.in);
	static int n, m, knowCount, jiminExaggerationMax = 0;
	static Set<Integer> knowTruthPerson = new HashSet<Integer>();
	static Set<Integer> additionalKnowPerson = new HashSet<Integer>();
	static List<List<Integer>> partyPersonList = new ArrayList<List<Integer>>();
	static List<List<Integer>> graphList = new ArrayList<List<Integer>>(); 
	static int[] combPerson = new int[2];
	static boolean[] visited;

	public static void main(String[] args) {
		n = sc.nextInt();
		m = sc.nextInt();
		knowCount = sc.nextInt();
		
		for (int i = 0; i < knowCount; i++) knowTruthPerson.add(sc.nextInt());
		for (int i = 0; i < m; i++) partyPersonList.add(new ArrayList<Integer>());
		for (int i = 0; i <= n; i++) graphList.add(new ArrayList<Integer>());
		
		// 파티에 따라서 연결되는 사람들의 그래프를 만들기
		for (int party = 0; party < m; party++) {
			int partyPersonCount = sc.nextInt();
			List<Integer> tempPerson = new ArrayList<Integer>();
			for (int j = 0; j < partyPersonCount; j++) {
				int temp = sc.nextInt();
				tempPerson.add(temp);
				partyPersonList.get(party).add(temp);
			}
			if (partyPersonCount >= 2) {
				makeGraph(tempPerson);
			}
		}

		visited = new boolean[n+1];
		// 처음에 진실을 아는 사람들을 기점으로 DFS로 진실을 알게 되는 사람들 구하기
		additionalKnowPerson = new HashSet<Integer>();
		for (int start : knowTruthPerson) {
			dfs(start);
		}
		// 아는 사람들 반영
		for (int addPerson: additionalKnowPerson) {
			knowTruthPerson.add(addPerson);
		}
		
		// 파티들을 다시 처음부터 봐서 이야기를 해도 되는지 판단
		for (int party = 0; party < m; party++) {
			boolean canSpeak = true;
			for (int person : partyPersonList.get(party)) {
				if (knowTruthPerson.contains(person)) {
					canSpeak = false;
					break;
				}
			}
			
			if (canSpeak) jiminExaggerationMax++;
		}
		
		System.out.println(jiminExaggerationMax);
	}

	private static void dfs(int start) {
		visited[start] = true;
		for (int temp : graphList.get(start)) {
			if (!visited[temp]) {
				additionalKnowPerson.add(temp);
				dfs(temp);
			}
		}
		
	}

	private static void makeGraph(List<Integer> tempPerson) {
		if (tempPerson.size() == 2) {
			graphList.get(tempPerson.get(0)).add(tempPerson.get(1));
			graphList.get(tempPerson.get(1)).add(tempPerson.get(0));
		}
		// 3개 이상일 경우 조합으로 2개씩 짝 지어줘서 그래프에 추가
		else {
			visited = new boolean[tempPerson.size()+1];
			generateComb(0, 0, tempPerson);
		}
		
	}

	private static void generateComb(int start, int depth, List<Integer> tempPerson) {
		if (depth == 2) {
			// 그래프 중복 방지를 위한 if문
			if (!graphList.get(combPerson[0]).contains(combPerson[1])) {
				graphList.get(combPerson[0]).add(combPerson[1]);
			};
			return;
		}
		for (int i = 0; i < tempPerson.size(); i++) {
			if (!visited[i]) {
				visited[i] = true;
				combPerson[depth] = tempPerson.get(i);
				generateComb(i, depth+1, tempPerson);
				visited[i] = false;
			}
		}
	}
}