import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    /*���� �ٽ�
     * 1. ��� �Ϸ��Ϸ縦 ǥ���� ���ΰ�
     * - Ư�� �پ��� ��ġ���� �߻��� �� �ִ� ��Ȳ*/
	
	/*���� ���̵��
	 * 1. ��Ʈ������ 1�� �ִ� ��ǥ�� �����Ѵ�
	 * 2. 1�� �ִ� ��ǥ�� ���Ž���Ͽ� -1�� �ƴ� ��츦 �����ϰ� 1�� �����.
	 * 3. 1�� ���� ��ǥ�� �����Ͽ� ���� ť�� �ݺ������� ������.
	 * 
	 * 4. ���� ����1�� �;�� �ϴ� ������ ���� ������ ���� ����̴�.
	 * 5. ���� ����2�� 1�� �ٲ� ��ǥ�� ��� �����ϴµ�
	 * ���� �����ϴ� ���� ����� �ִٸ� �� �̻� �ƹ� ��ȭ�� �Ұ����� �������� �����Ѵ�
	 * 6. �;�� �ϴ� ������ ���� ������ ���̰� �ִٸ� �Ұ����� ����̴� -1�� ����Ѵ�.
	 */

    static int[][] matrix;
    static int[][] visited;
    static Queue<int[]> target;

    static int n, m;
    static int total, ripen;
    static int answer;

    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());

        // ��Ʈ���� �ޱ�
        total = m * n;
        ripen = 0;
        matrix = new int[n][m];
        visited = new int[n][m];
        target = new LinkedList<>();
        
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                int num = Integer.parseInt(st.nextToken());
                matrix[i][j] = num;
                
                // ���� ���� ����
                if (num == 1) {
                    ripen += 1;
                    target.add(new int[]{i, j});
                }
                // ���� total���� ����
                if (num == -1) {
                	total -= 1;
                }
            }
        }

        // ����غ���
//        for (int i = 0; i < n; i++) {
//            System.out.println(Arrays.toString(matrix[i]));
//        }

        // bfs
        answer = 0;
        bfs();

        // ���� ����ϱ�
        if (ripen == total) {
            System.out.println(answer);
        } else {
            System.out.println(-1);
        }


    }

    public static void bfs() {

        // ���� ����
        // bfs �� ���� ���� ���
        if (target.isEmpty() || total == ripen) {
            return;
        }

        int cir = target.size(); // ����
        for (int q = 0; q < cir; q++) {
            if (ripen == total) break;
            int[] now = target.poll();

            for (int i = 0; i < 4; i++) {
                int nr = now[0] + dr[i];
                int nc = now[1] + dc[i];

                if (nr >= 0 && nr < n && nc >= 0 && nc < m && matrix[nr][nc] == 0) {
                    target.add(new int[]{nr, nc});
                    matrix[nr][nc] = 1;
                    ripen += 1;
                }
            }
        }

        // ť ���� ���� �Ϸ�
        answer++; // �Ϸ� �߰�
        
        // ����غ���
//        System.out.println("�Ϸ� ����");
//        for (int i = 0; i < n; i++) {
//            System.out.println(Arrays.toString(matrix[i]));
//        }
        
        // ���� ��¥�� �̵�
        bfs(); 

    }
}