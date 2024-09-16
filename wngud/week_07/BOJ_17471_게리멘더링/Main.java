public class Main {

	/*
	 * ������ 
	 * 1. �κ��������� vistied�� �����. 
	 * 2. ���� visited�� ������ �� ��° vitied�� 0�� ��쿡�� ����Ǵ��� Ȯ���Ѵ�. (1�� ��)
	 * 3. ���� visited�� ������ ù ��° visited�� 1�� ��쿡�� ������ �Ѵ�. (0�� ��)
	 * 4. ���� �� ���Ҵ��� �� 
	 * - �� �湮�� ���� ���Ͽ� ��ü ��ȯ�� ���Ѵ�. -> �� �� �� ���ƾ� �ϱ� ������ ��ȿ�� 
	 * - N�� 10�� ���Ϲۿ� �ȵǴϱ�, visited�� ���� 0 �Ǵ� 1�� �Ǿ������� �Ǵ� -> ��Ʈ��ŷ ����
	 */
	
	/*���� ó��
	 * 1. ���� �� ���� ���� �� ���
	 * 2. ��ĭ �� ũ�� ����⿡ ������ ����
	 */

	static ArrayList<ArrayList<Integer>> graph;
	static int[] population;

	static int[] visited;

	static int n;
	static int total, count1, count2; // �ʱ�ȭ ����
	static int answer;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		n = Integer.parseInt(br.readLine());

		// �α� �Է� �ޱ�
		population = new int[n + 1];
		total = 0;

		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= n; i++) {
			int num = Integer.parseInt(st.nextToken());
			population[i] = num;
			total += num;
		}

		// �׷��� �Է� �ޱ�
		graph = new ArrayList<>();
		for (int i = 0; i < n + 1; i++) {
			graph.add(new ArrayList<>());
		}

		for (int i = 1; i <= n; i++) {
			String[] inputs = br.readLine().split(" ");
			for (int j = 1; j < inputs.length; j++) {
				int num = Integer.parseInt(inputs[j]);
				graph.get(i).add(num);
			}
		}

		// BFS ������
		answer = Integer.MAX_VALUE;
		visited = new int[n + 1];
		comb(1);

		// ���� ����ϱ�
		// �ƹ� ��ȭ�� ������ -1�̴�.
		if (answer == Integer.MAX_VALUE) {
			System.out.println(-1);
		} else {
			System.out.println(answer);
		}


	}

	public static void comb(int idx) {
		
		// �κ����� ����Ⱑ ���� ���
		if (idx == n+1) {
		
			count1 = 0;
			count2 = 0;
			
			// ù��° bfs ������
			boolean check1 = bfs(1, 0);
			if (!check1) return;
			// �ι�° bfs ������
			boolean check2 = bfs(0, 1);
			if (!check2) return;
			
	
			// ��Ż�� �� �߿� �ϳ��� ����������, �� ���� ���� �� �� ���̴� ���ܽ��Ѿ� ��
			if (total == count1 || total == count2) return;
			
			// ���� answer ���� �۴ٸ� �ֽ�ȭ
			answer = Math.min(answer, Math.abs(count1 - count2));

			return;
		}
		
		// comb�����	
		if (visited[idx] != 1) {
			visited[idx] = 1;
			comb(idx+1);
			visited[idx] = 0;
			comb(idx+1);
		}

	}

	// �湮�� ���ڿ� �湮���� �ʴ� ���� ����
	public static boolean bfs(int vi, int notVi) {

		// ���ο� visited�� �����.
		// ������� visited�� �ջ��Ű�� �ȵȴ�.
		int[] visited2 = Arrays.copyOf(visited, visited.length);

		// BFS�� ���� ť
		Queue<Integer> queue = new LinkedList<>();

		// �� �������� ���� ���� ���δ� ��ȯ�� �Ǵ��� Ȯ��
		for (int i = 1; i <= n; i++) {
			if (visited2[i] == notVi) {

				// �ѹ��� �湮���� �ʴ� ������ ����
				queue.add(i);
				visited2[i] = vi;
				if (vi == 1) count1 += population[i]; // ���� �߰�
				else if (vi == 0) count2 += population[i]; // ���� �߰�

				while (!queue.isEmpty()) {
					int cur = queue.poll();

					for (int j = 0; j < graph.get(cur).size(); j++) {
						int tmp = graph.get(cur).get(j);
						if (visited2[tmp] == notVi) {
							queue.add(tmp);

							visited2[tmp] = vi;
							if (vi == 1) count1 += population[tmp]; // ���� �߰�
							else if (vi == 0) count2 += population[tmp]; // ���� �߰�
						}
					}
				} // while ��

				// ���ο� ť �ϳ��� �ϰ� ������
				break;
			}
		}
	
		
		// ��ü ��ȸ �Ǻ��ϱ�
		// ���� 0�̰ų� ���� 1�̸� �湮�� ������ ����
		int tmp = 0;
		for (int i = 1; i <= n; i++) {
			tmp += visited2[i];
		}
		
		if (tmp == n || tmp == 0) return true;
		else return false;
		
	}

}