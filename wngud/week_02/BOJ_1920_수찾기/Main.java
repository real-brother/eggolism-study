import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {

//		Scanner sc = new Scanner(System.in);

		/* 설계 아이디어
		 * 1. 시간적으로 제일 유리한 이진 탐색을 이용
		 * 2. 비교 기준은 set으로 중복이 없게 만들려고..
		 * -> 그랬다가, 이분탐색을 위해 list로 변경
		 * -> 중복 제거가 더 시간 복잡도 높일 것 같아서 제외
		 * 3. 비교 대상은 list로 판별
		 */

		// 입력 받기 
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		// 1. N 받기
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		List<Integer> list1 = new ArrayList<>();

		for (int i = 0; i < N; i++) {
			list1.add(Integer.parseInt((st.nextToken())));
		}
		
		// 이진 탐색을 위한 배열
		Collections.sort(list1);

		// 2. M 받기
		int M = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine(), " ");

		List<Integer> list2 = new ArrayList<>();

		for (int i = 0; i < M; i++) {
			list2.add(Integer.parseInt(st.nextToken()));
		}
		
		// 3. 순회하기
		// 이진탐색으로 순회하기
		// 단순히 contains 쓰면 시간초과 날듯
		for (int i = 0; i < list2.size(); i++) {
			Boolean result = binarySearch(list2.get(i), list1, list1.size());
			
			if (result) {
				sb.append(1).append("\n");
			} else {
				sb.append(0).append("\n");
			}
		}
		
		System.out.println(sb);
		
	}
	
	// 타겟 넘버와 기준이 되는 list를 받음
	static boolean binarySearch(int targetNum, List<Integer> list, int length) {
		
		int pl = 0;
		int pr = length-1;
		
		while(pl <= pr) {
			
			int pc = (pl+pr) / 2;
			
			if (targetNum == list.get(pc)) {
				return true;
			} else if (targetNum > list.get(pc)) {
				pl = pc + 1;
			} else {
				pr = pc - 1;
			}
			
		}
		
		return false;
		
	}

}