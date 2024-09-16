import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/*설계방식
 * 1. 조합을 통해 양쪽 팀을 구한다.
 * 2. 조합에 해당하는 인덱스는 sum1에 저장한다.
 * 3. 조합에 해당하지 않는 인덱스는 sum2에 저장한다.
 * - 매트릭스 하나를 뽑고, 조합에 해당하는 인덱스가 하나라도 있다면 포함하지 않는다.
 * 4. 두합의 차이를 업데이트한다.
 */

public class Main {
    static int[][] matrix;
    static int[] sel;

    static int n;
    static int answer;

    public static void main(String[] args) throws NumberFormatException, IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 입력 받기
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());

        // 매트릭스 채우기
        matrix = new int[n][n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                matrix[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 조합 고르기
        sel = new int[n/2]; // 한 팀만 고르면 된다.
        answer = Integer.MAX_VALUE;

        comb(0, 0);

        // 정답 출력하기
        System.out.println(answer);
    }


    // 조합 메서드
    static void comb(int idx, int sidx) {

        if (sidx == n/2) {
            // 점수 계산
            cal();
            return;
        }

        if (idx == n) {
            return;
        }

        // 재귀
        sel[sidx] = idx; // 인덱스 정확하게
        comb(idx+1, sidx+1);

        comb(idx+1, sidx);

    }

    static void cal() {
        int sum1 = 0;
        int sum2 = 0;

		// 첫번째 팀 매트릭스 추가
        for (int i1 : sel) {
            for (int i2 : sel) {
                sum1 += matrix[i1][i2];
            }
        }
				
		// 첫번째 팀이 아닌 경우 매트릭스 추가
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
	            // 하나하나 첫번째 팀과 동일한지 파악해야 함 -> set 사용?
                boolean is_off = false;
                for (int k : sel) {
                    if (i == k || j == k) {
                        is_off = true;
                    }
                }
                // 첫번째 팀과 연관된 것이 하나라도 없으면 다른 팀으로 더함
                if(!is_off) sum2 += matrix[i][j];
            }
        }

        // 결과
        answer = Math.min(answer, Math.abs(sum1 - sum2));
    }

}