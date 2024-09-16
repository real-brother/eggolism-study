import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int[][] matrix;
    static int[][] cloudMatrix;
    static ArrayList<int[]> cloud;

    static int[][] move;
    static int n, m;
    static int cloudSize;

    static int[] dr = {0, -1, -1, -1, 0, 1, 1, 1};
    static int[] dc = {-1, -1, 0, 1, 1, 1, 0, -1};
    static int[] ddr = {-1, -1, 1, 1};
    static int[] ddc = {-1, 1, 1, -1};

    /*�ð� �ʰ�
     * �ܼ��� ������ ��ǥ�� ���� -> ������ ���� �����ϴ� �������� ��ȿ��
     * -> ��Ʈ������ �߰� 
     * 
     * �ᱹ, ������ ��ǥ�� List��, ��ȸ�� �� visited ����� �Ǿ���.
     * */

    /*�ε��� ����
    * ��ⷯ �� -> n�� ���ߴµ� n�� ������ ����. 50�����ϱ� �˳��� 100�� �켱 ������.
    * */

    public static void main(String[] args) throws NumberFormatException, IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // �Է� �ޱ�
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        // ��Ʈ���� ä���
        matrix = new int[n][n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                int num = Integer.parseInt(st.nextToken());
                matrix[i][j] = num;
            }
        }

        // ������ �Է� �ޱ�
        // �ε��� ó���� ���Ͽ� ������ -1
        move = new int[m][2];
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            move[i][0] = Integer.parseInt(st.nextToken()) - 1;
            move[i][1] = Integer.parseInt(st.nextToken());
        }

        // 0. ���� �����ϱ�
        cloud = new ArrayList<>();
        cloud.add(new int[]{n - 1, 0});
        cloud.add(new int[]{n - 2, 0});
        cloud.add(new int[]{n - 1, 1});
        cloud.add(new int[]{n - 2, 1});
        cloudSize = cloud.size();

        cloudMatrix = new int[n][n];

        // �ݺ� ����
        for (int mv = 0; mv < move.length; mv++) {

            // 1. ���� �̵� ��Ű��
            // 2. ���� ��ġ +1
            // �ε��� ����?
            int move_d = move[mv][0];
            int move_s = move[mv][1];

            for (int i = 0; i < cloudSize; i++) {
                int nr = (cloud.get(i)[0] + dr[move_d] * move_s + 100 * n) % n; // ��ⷯ ���� ���̳ʽ� ���� -> �ָ��ϴ�.
                int nc = (cloud.get(i)[1] + dc[move_d] * move_s + 100 * n) % n;

                matrix[nr][nc] += 1;
                cloud.add(new int[]{nr, nc});

                cloudMatrix[nr][nc] = 1; // ��Ʈ���� �߰�
            }

            // ���� �ֽ�ȭ
            for (int i = 0; i < cloudSize; i++) {
                cloud.remove(0); // �պ��� ����
            }

            // 3. ���� ��ġ���� �ٱ��� ������ŭ ���ϱ�
            for (int i = 0; i < cloud.size(); i++) {
                int nr = cloud.get(i)[0];
                int nc = cloud.get(i)[1];

                for (int j = 0; j < 4; j++) {
                    int nnr = nr + ddr[j];
                    int nnc = nc + ddc[j];

                    if (nnr < 0 || nnr >= n || nnc < 0 || nnc >= n || matrix[nnr][nnc] == 0) continue;
                    matrix[nr][nc] += 1;
                }
            }

            // 4. ���� �����ϱ�
            // ������ ��Ʈ������ ������ ���� ������ ���⼭ �巯����
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {

                    if (matrix[i][j] < 2 || cloudMatrix[i][j] == 1) {
                        cloudMatrix[i][j] = 0; // ��Ʈ���� �ʱ�ȭ
                        continue;
                    }

                    // 2 �̻��̰�, ���� cloud�� �ƴ� ��쿡��
                    // -2�ϰ� Ŭ���忡 �߰�
                    matrix[i][j] -= 2;
                    cloud.add(new int[]{i, j});

                }
            }

            // ���� ���� �����ϱ�
            for (int i = 0; i < cloudSize; i++) {
                cloud.remove(0); // �պ��� ����
            }

            // ���� ������ �ֽ�ȭ
            cloudSize = cloud.size();

        }

        // ���� ��� ����ϱ�
        int sum = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                sum += matrix[i][j];
            }
        }

        System.out.println(sum);

    }
}