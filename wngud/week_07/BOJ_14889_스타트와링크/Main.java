import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/*������
 * 1. ������ ���� ���� ���� ���Ѵ�.
 * 2. ���տ� �ش��ϴ� �ε����� sum1�� �����Ѵ�.
 * 3. ���տ� �ش����� �ʴ� �ε����� sum2�� �����Ѵ�.
 * - ��Ʈ���� �ϳ��� �̰�, ���տ� �ش��ϴ� �ε����� �ϳ��� �ִٸ� �������� �ʴ´�.
 * 4. ������ ���̸� ������Ʈ�Ѵ�.
 */

public class Main {
    static int[][] matrix;
    static int[] sel;

    static int n;
    static int answer;

    public static void main(String[] args) throws NumberFormatException, IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // �Է� �ޱ�
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());

        // ��Ʈ���� ä���
        matrix = new int[n][n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                matrix[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // ���� ����
        sel = new int[n/2]; // �� ���� ���� �ȴ�.
        answer = Integer.MAX_VALUE;

        comb(0, 0);

        // ���� ����ϱ�
        System.out.println(answer);
    }


    // ���� �޼���
    static void comb(int idx, int sidx) {

        if (sidx == n/2) {
            // ���� ���
            cal();
            return;
        }

        if (idx == n) {
            return;
        }

        // ���
        sel[sidx] = idx; // �ε��� ��Ȯ�ϰ�
        comb(idx+1, sidx+1);

        comb(idx+1, sidx);

    }

    static void cal() {
        int sum1 = 0;
        int sum2 = 0;

		// ù��° �� ��Ʈ���� �߰�
        for (int i1 : sel) {
            for (int i2 : sel) {
                sum1 += matrix[i1][i2];
            }
        }
				
		// ù��° ���� �ƴ� ��� ��Ʈ���� �߰�
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
	            // �ϳ��ϳ� ù��° ���� �������� �ľ��ؾ� �� -> set ���?
                boolean is_off = false;
                for (int k : sel) {
                    if (i == k || j == k) {
                        is_off = true;
                    }
                }
                // ù��° ���� ������ ���� �ϳ��� ������ �ٸ� ������ ����
                if(!is_off) sum2 += matrix[i][j];
            }
        }

        // ���
        answer = Math.min(answer, Math.abs(sum1 - sum2));
    }

}