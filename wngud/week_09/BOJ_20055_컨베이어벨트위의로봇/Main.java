public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());

		// 입력 받기
		int[] arr = new int[n * 2];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n * 2; i++) {
			int num = Integer.parseInt(st.nextToken());
			arr[i] = num;
		}

		// 필요한 변수
		int stage = 1; // 단계를 파악하기 위한 변수
		int startIdx = 0; // 배열에서 로봇을 올리는 위치의 인덱스를 위한 변수
		int endIdx = n - 1; // 배열에서 로봇을 내리는 위치의 인덱스를 위한 변수

		int[] visited = new int[n * 2]; // 로봇

		// 벨트 회전 한번 시작
		while (true) {

			// 1. 벨트 회전
			startIdx = (startIdx - 1 + n * 2) % (n * 2); // 인덱스 뒤로 한칸
			endIdx = (endIdx - 1 + n * 2) % (n * 2); // 인덱스 뒤로 한칸
			// endIdx에 있는 로봇은 사라짐
			visited[endIdx] = 0;

			// 2. 로봇 움직이기
			// 예외: 컨테이너 끝에 로봇이 있는 경우
			// 컨베이어 벨트 위 로봇이 있는지 확인
			int count = n - 1; // 컨베이어 벨트 개수만큼 돌기 --> 마지막 제외
			int search = endIdx; // while 시작할 때 바로 뒤로가기를 해서 여기는 endIdx로 시작함

			while (startIdx != search) {
				// search 뒤로가기
				search = (search - 1 + n * 2) % (n * 2); // 앞으로 빼야함!!
				
				// 예외: 끝은 없앰
				if (visited[endIdx] == 1) {
					visited[endIdx] = 0;
				}

				// search에 로봇이 있는지 확인 -> 없으면 넘어가
				if (visited[search] != 1)
					continue;

				// 로봇이 있다면 앞을 확인 -> 로봇이 없고 내구도 1이상
				if (visited[(search + 1) % (n * 2)] == 1 || arr[(search + 1) % (n * 2)] <= 0)
					continue;

				// 로봇 이동 이동
				arr[(search + 1) % (n * 2)] -= 1; // 내구성 줄이기 1
				visited[search] = 0;
				visited[(search + 1) % (n * 2)] = 1;

				// 예외: 끝은 없앰
				if (visited[endIdx] == 1) {
					visited[endIdx] = 0;
				}

			}

			// 3. 로봇 올리기
			if (arr[startIdx] >= 1) {
				visited[startIdx] = 1;
				arr[startIdx] -= 1; // 내구성 줄이기 2
			}

			// 한 회전 끝
			// 4. 0의 개수 찾기
			int countZero = 0;
			for (int i = 0; i < arr.length; i++) {
				if (arr[i] == 0)
					countZero++;
			}

			// 0의 개수가 크거나 같으면
			if (countZero >= k) {
				System.out.println(stage);
				break;
			} else {
				stage++;
			}
		}

	}
}