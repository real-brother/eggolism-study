import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    /*설계 핵심
     * 1. 어떻게 하루하루를 표현할 것인가
     * - 특히 다양한 위치에서 발생할 수 있는 상황*/
	
	/*설계 아이디어
	 * 1. 매트릭스에 1이 있는 좌표를 저장한다
	 * 2. 1이 있는 좌표를 사방탐색하여 -1이 아닌 경우를 제외하고 1로 만든다.
	 * 3. 1로 만든 좌표를 저장하여 이후 큐를 반복적으로 돌린다.
	 * 
	 * 4. 종료 조건1은 익어야 하는 개수와 익은 개수가 같은 경우이다.
	 * 5. 종료 조건2는 1로 바뀐 좌표를 계속 저장하는데
	 * 만약 저장하는 곳이 비워져 있다면 더 이상 아무 변화가 불가능한 것임으로 종료한다
	 * 6. 익어야 하는 개수와 익은 개수가 차이가 있다면 불가능한 경우이니 -1을 출력한다.
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

        // 매트릭스 받기
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
                
                // 벽도 포함 하자
                if (num == 1) {
                    ripen += 1;
                    target.add(new int[]{i, j});
                }
                // 벽은 total에서 제외
                if (num == -1) {
                	total -= 1;
                }
            }
        }

        // 출력해보기
//        for (int i = 0; i < n; i++) {
//            System.out.println(Arrays.toString(matrix[i]));
//        }

        // bfs
        answer = 0;
        bfs();

        // 정답 출력하기
        if (ripen == total) {
            System.out.println(answer);
        } else {
            System.out.println(-1);
        }


    }

    public static void bfs() {

        // 종료 조건
        // bfs 될 것은 다한 경우
        if (target.isEmpty() || total == ripen) {
            return;
        }

        int cir = target.size(); // 고정
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

        // 큐 전부 돌기 완료
        answer++; // 하루 추가
        
        // 출력해보기
//        System.out.println("하루 종료");
//        for (int i = 0; i < n; i++) {
//            System.out.println(Arrays.toString(matrix[i]));
//        }
        
        // 다음 날짜로 이동
        bfs(); 

    }
}