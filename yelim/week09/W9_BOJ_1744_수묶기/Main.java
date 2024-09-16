package W9_BOJ_1744_수묶기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		/* 문제) 수 묶기
		 * 길이가 N인 수열이 주어졌을 때, 그 수열의 합을 구하려고 한다. 
		 * 하지만, 그냥 그 수열의 합을 모두 더해서 구하는 것이 아니라, 수열의 두 수를 묶으려고 한다. 
		 * 어떤 수를 묶으려고 할 때, 위치에 상관없이 묶을 수 있다.
		 * 어떤 수를 묶게 되면, 수열의 합을 구할 때 묶은 수는 서로 곱한 후에 더한다.
		 * 수열의 모든 수는 단 한번만 묶거나, 아니면 묶지 않아야한다.
		 * 수열이 주어졌을 때, 수열의 각 수를 적절히 묶었을 때, 그 합이 최대가 되게 하는 프로그램을 작성하시오.
		 */
		
		/* 양수와 음수 리스트 각각 만들어서 정렬
		 * 범위 내에 있고, 양수의 경우 1이 아닌 경우 두 수를 곱해준다.
		 * 나머지는 더해준다.
		 * 음수는 카운트+1이 리스트사이즈보다 작으면 곱해준다.
		 */
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		
		int[] arr = new int[n];
		
		List<Integer> plus = new ArrayList<>();
		List<Integer> minus = new ArrayList<>();
		
		
		// 수 입력받으면서 음수, 양수 나눠서 리스트에 저장
		for(int i=0; i<n; i++) {
			arr[i] = Integer.parseInt(br.readLine());
			
			if (arr[i] > 0) {
				plus.add(arr[i]);
			}
			else {
				minus.add(arr[i]);
			}
		}
		
		// 양수는 내림차순 정렬
		plus.sort(Comparator.reverseOrder());
		// 음수는 오름차순 정렬
		minus.sort(null);
		
		// 합 초기화
		int sum = 0;
		
		// 인덱스 초기화
		int idx = 0;
		
		// idx가 양수 리스트의 사이즈보다 작은동안 반복
		while (idx < plus.size()) {
			// idx+1이 사이즈보다 작고, 1이 아닌 경우
			// 둘 중 하나라도 1이면 더해주는게 더 큼
			if (idx+1<plus.size() && plus.get(idx)!=1 && plus.get(idx+1)!=1) {
				sum += (plus.get(idx++)*plus.get(idx++));
			}
			else {
				sum += plus.get(idx++);
			}
		}
		
		// idx 다시 0으로 초기화
		idx = 0;
		// idx가 음수 리스트의 사이즈보다 작은동안 반복
		while (idx < minus.size()) {
			if (idx+1<minus.size()) {
				sum += (minus.get(idx++)*minus.get(idx++));
			}
			else {
				sum += minus.get(idx++);
			}
		}
		
		
		System.out.println(sum);
	
	}

}
