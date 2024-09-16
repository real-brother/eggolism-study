import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int[][] matrix;

    static int n;
    static int answer;

    static int[] dr = {0, 1, 1};
    static int[] dc = {1, 1, 0};

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());

        // ��Ʈ���� �ޱ�
        StringTokenizer st;
        matrix = new int[n][n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                int num = Integer.parseInt(st.nextToken());
                matrix[i][j] = num;
            }
        }

        // dfs
        answer = 0;
        dfs(0, 1, 0);

        // ���� ����ϱ�
        System.out.println(answer);
    }

		// status 0 ���� 1 �밢�� 2 ����
    public static void dfs(int postR, int postC, int status) {

				// ��ǥ ������ ���� ����ó��
        if (postR == n-1 && postC == n-1) {
            answer++;
        }

        // �밢���� ������ ���
        boolean is_on = true;
        for (int i = 0; i < 3; i++) {
            if (postR+1>=n || postC+1>=n || matrix[postR+dr[i]][postC+dc[i]] == 1) {
                is_on = false;
                break;
            }
        }

        // ������ ���
        if (status == 0 && postC+1 < n) {
            if (matrix[postR][postC+1]==0)  dfs(postR, postC+1 , 0);
            if (is_on) dfs(postR+1, postC+1 , 1);
        }

        // ������ ���
        else if (status == 2 && postR+1 < n) {
            if (matrix[postR+1][postC]==0) dfs(postR+1, postC, 2);
            if (is_on) dfs(postR+1, postC+1 , 1);
        }

        // �밢���� ���
        else if (status == 1) {
            if (is_on) dfs(postR+1, postC+1 , 1);
            if (postC+1 < n && matrix[postR][postC+1]==0) dfs(postR, postC+1 , 0);
            if (postR+1 < n && matrix[postR+1][postC]==0) dfs(postR+1, postC , 2);
        }

    }
}