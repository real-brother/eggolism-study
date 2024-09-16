import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/*���� ���
 * 1. ��ȸ�ϴ� �׸�ĭ�� ã�ƾ��ϴ� Ÿ���� ������ dfs�� 4���� ������ ��
 * 2. �� �׸�ĭ�� ã�ƾ��ϴ� Ÿ���� ������ dfs�� 4���� ��
 * 3. �� �׸�ĭ�� ã�ƾ��ϴ� Ÿ���� ���ٸ� �׸�ĭ ������ŭ count �߰� �� ���� -> �ٽ�
 * 4. �̸� �ݺ��ϴٰ� ���ϴ� ��ǥ�� ������ ��*/

public class Main {

    static int[][] matrix;
    static int count;
    static int targetR, targetC;
    static boolean is_on;

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int len = (int) Math.pow(2, n);
        targetR = sc.nextInt();
        targetC = sc.nextInt();

        // dfs
        is_on = true;
        count = 0;
        dfs(len, 0, 0);

    }

    public static void dfs(int length, int r, int c) {

        // ��������
        if(r == targetR && c == targetC) {
            System.out.println(count);
            is_on = false;
            return;
        }
        
        // �� �� ã���� �߰������� ���� �ʰ� is_on�� �߰���
        if (is_on) {
            // ĭ �ȿ� targetR/C�� ������ 4���� ������ Ž��
            if (r <= targetR && targetR < r + length && c <= targetC && targetC < c + length) {
                for (int i = 0; i < 2; i++) {
                    for (int j = 0; j < 2; j++) {
                        dfs(length / 2, r + length / 2 * i, c + length / 2 * j);
                    }
                }
            } else {
                // ĭ �ȿ� ���� ��� �׸�ĭ ��ŭ ������ �߰��ϰ� �Ѿ
                count += length * length;
            }
        }


    }
}