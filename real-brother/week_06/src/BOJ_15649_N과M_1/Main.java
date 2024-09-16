package BOJ_15649_N과M_1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int n;
    static int m;
    static int[] permutation;
    static boolean[] visited;
    
    // 이거 그대로 scanner 쓰면 시간초과남
    public static void main(String[] args) throws IOException {
        String[] input = br.readLine().split(" ");
        n = Integer.parseInt(input[0]);
        m = Integer.parseInt(input[1]);
        permutation = new int[m];
        visited = new boolean[n + 1];

        generatePermutations(0);

        bw.flush();
        bw.close();
        br.close();
    }
    
    // 백트래킹을 활용한 순열 구하기
    private static void generatePermutations(int depth) throws IOException {
        if (depth == m) {
            printPerm();
            return;
        }

        for (int i = 1; i <= n; i++) {
            if (!visited[i]) {
                visited[i] = true;
                permutation[depth] = i;
                generatePermutations(depth + 1);
                visited[i] = false;
            }
        }
    }

    private static void printPerm() throws IOException {
        for (int i = 0; i < m; i++) {
            bw.write(permutation[i] + " ");
        }
        bw.write("\n");
    }
}
