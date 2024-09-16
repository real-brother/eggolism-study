package BOJ_4358_생태학;

import java.util.*;

public class Main {
	
	/*
	 *  입력을 한 줄 씩 받아서 key가 map에 있으면 value+1을 해주고,
	 *  key값이 value에 없으면 key를 추가하고 value에 1을 넣어준다
	 *  입력받을 때마다 total(총 나무 개수)를 올려준다.
	 *  리스트틀 정렬해주고 for문을 순회하면서 리스트에서 사전 순으로 출력해준다.
	 *  
	 */
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		Map<String, Integer> trees_number = new HashMap<>();
		List<String> trees_list = new ArrayList<>();
		
		double total_tree = 0;
		
		// 놀랍게도 이클립스에서는 입력하고 ctrl + z를 눌러줘야 한다
		// 안 그러면 입력값을 계속 받음
		// 백준은 그냥 됨 
		while(sc.hasNextLine()) {
			String tree = sc.nextLine();
			
			// 맵에 key값이 없으면 key를 추가하고 value를 1로 만들어준다
			if(!trees_number.containsKey(tree)) {
				trees_number.put(tree, 1);
				trees_list.add(tree);
			}
			// 맵에 key값이 있으면 해당 key값의 value를 1 올려주고, total을(나무 총 개수) 1 올려준다.
			else trees_number.put(tree, trees_number.get(tree) + 1);
			total_tree++;
		}
		
		// 리스트를 정렬해준다
		Collections.sort(trees_list);
		
		// 정렬된 리스트의 이름을 가져와서 그 값을 맵의 key로 사용하고, value(나무 개수)를 가져와서
		// 백분율? 계산을 한다
		for(int i = 0; i < trees_list.size(); i++) {
			double percent = (trees_number.get(trees_list.get(i))* 100) / total_tree;
			System.out.printf("%s %.4f\n", trees_list.get(i), percent);
		}
	}

}