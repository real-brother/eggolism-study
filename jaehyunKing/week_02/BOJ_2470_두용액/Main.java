package BOJ_2470_두용액;

import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		List<Integer> num_list = new ArrayList<>();
		
		for(int i = 0; i < N; i++) num_list.add(Integer.parseInt(st.nextToken()));
		
		Collections.sort(num_list);
		
		long left = 0;
		long right = num_list.size() - 1;
		long min = Integer.MAX_VALUE;
		int min_index = 0;
		int min2_index = 0;
		
		// num_list.get(i)이 절댓값 min보다 크면 더 볼 이유가 없다 
		// -> ex) min이 5인데 num_list.get(i)가 5면 그 다음 숫자들은 어차피 양수이기 때문에 min보다 더 큰 수가 나온다
		for(int i = 0; num_list.get(i) < Math.abs(min) && i < num_list.size() - 1; i++) {
			left = i+1;
			while(left <= right) {
				
				long mid = (left + right) / 2;
				
				if(Math.abs(min) > Math.abs(num_list.get(i) + num_list.get((int)mid))) {
					min = num_list.get(i) + num_list.get((int)mid);
					min_index = i;
					min2_index = (int) mid;
				}
				
				if(num_list.get(i) + num_list.get((int)mid) <= 0) left = mid + 1;
				else right = mid - 1;
			}
			
		}
		System.out.print(num_list.get(min_index)+" "+ num_list.get(min2_index));
	}

}
