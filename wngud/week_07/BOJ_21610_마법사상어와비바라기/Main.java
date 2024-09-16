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

    /*시간 초과
     * 단순히 구름의 좌표만 저장 -> 마지막 구름 생성하는 과정에서 비효율
     * -> 매트릭스도 추가 
     * 
     * 결국, 구름의 좌표는 List고, 순회할 때 visited 만들면 되었음.
     * */

    /*인덱스 에러
    * 모듈러 쪽 -> n을 곱했는데 n이 작으면 문제. 50까지니까 넉넉히 100을 우선 곱해줌.
    * */

    public static void main(String[] args) throws NumberFormatException, IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 입력 받기
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        // 매트릭스 채우기
        matrix = new int[n][n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                int num = Integer.parseInt(st.nextToken());
                matrix[i][j] = num;
            }
        }

        // 움직임 입력 받기
        // 인덱스 처리를 위하여 방향은 -1
        move = new int[m][2];
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            move[i][0] = Integer.parseInt(st.nextToken()) - 1;
            move[i][1] = Integer.parseInt(st.nextToken());
        }

        // 0. 구름 세팅하기
        cloud = new ArrayList<>();
        cloud.add(new int[]{n - 1, 0});
        cloud.add(new int[]{n - 2, 0});
        cloud.add(new int[]{n - 1, 1});
        cloud.add(new int[]{n - 2, 1});
        cloudSize = cloud.size();

        cloudMatrix = new int[n][n];

        // 반복 시작
        for (int mv = 0; mv < move.length; mv++) {

            // 1. 구름 이동 시키기
            // 2. 구름 위치 +1
            // 인덱스 오류?
            int move_d = move[mv][0];
            int move_s = move[mv][1];

            for (int i = 0; i < cloudSize; i++) {
                int nr = (cloud.get(i)[0] + dr[move_d] * move_s + 100 * n) % n; // 모듈러 연산 마이너스 조심 -> 애매하다.
                int nc = (cloud.get(i)[1] + dc[move_d] * move_s + 100 * n) % n;

                matrix[nr][nc] += 1;
                cloud.add(new int[]{nr, nc});

                cloudMatrix[nr][nc] = 1; // 매트릭스 추가
            }

            // 구름 최신화
            for (int i = 0; i < cloudSize; i++) {
                cloud.remove(0); // 앞부터 제거
            }

            // 3. 구름 위치에서 바구니 개수만큼 더하기
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

            // 4. 구름 생성하기
            // 구름을 매트릭스로 만들지 않은 단점이 여기서 드러나네
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {

                    if (matrix[i][j] < 2 || cloudMatrix[i][j] == 1) {
                        cloudMatrix[i][j] = 0; // 매트릭스 초기화
                        continue;
                    }

                    // 2 이상이고, 이전 cloud가 아닌 경우에만
                    // -2하고 클라우드에 추가
                    matrix[i][j] -= 2;
                    cloud.add(new int[]{i, j});

                }
            }

            // 이전 구름 삭제하기
            for (int i = 0; i < cloudSize; i++) {
                cloud.remove(0); // 앞부터 제거
            }

            // 구름 사이즈 최신화
            cloudSize = cloud.size();

        }

        // 최종 결과 출력하기
        int sum = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                sum += matrix[i][j];
            }
        }

        System.out.println(sum);

    }
}