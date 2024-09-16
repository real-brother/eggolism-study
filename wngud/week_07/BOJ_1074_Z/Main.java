import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/*설계 방식
 * 1. 조회하는 네모칸에 찾아야하는 타겟이 있으면 dfs로 4개로 나눠서 들어감
 * 2. 들어간 네모칸에 찾아야하는 타겟이 있으면 dfs로 4개로 들어감
 * 3. 들어간 네모칸에 찾아야하는 타겟이 없다면 네모칸 개수만큼 count 추가 후 나감 -> 핵심
 * 4. 이를 반복하다가 원하는 좌표가 나오면 끝*/

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

        // 기저조건
        if(r == targetR && c == targetC) {
            System.out.println(count);
            is_on = false;
            return;
        }
        
        // 한 번 찾으면 추가적으로 돌지 않게 is_on을 추가함
        if (is_on) {
            // 칸 안에 targetR/C가 있으면 4개로 나눠서 탐색
            if (r <= targetR && targetR < r + length && c <= targetC && targetC < c + length) {
                for (int i = 0; i < 2; i++) {
                    for (int j = 0; j < 2; j++) {
                        dfs(length / 2, r + length / 2 * i, c + length / 2 * j);
                    }
                }
            } else {
                // 칸 안에 없을 경우 네모칸 만큼 점수를 추가하고 넘어감
                count += length * length;
            }
        }


    }
}