import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {

    /*설계방식
    * 1. 방향이 이전 선분들의 역순으로 반시계 방향으로 바뀜
    * 2. FILO인 스택을 이용하여 이전까지 선분들의 역순으로 출력되도록 함
    * 3. 역순으로 나왔으면 끝점을 방향만큼 이동시킴
    * */

    static int[][] matrix;
    static int[][] dragonInfo;

    static int N;

    static int[] dr = {0, -1, 0, 1}; // 우상좌하
    static int[] dc = {1, 0, -1, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());

        // 매트릭스 받기
        matrix = new int[101][101];
        dragonInfo = new int[N][4];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 4; j++) {
                int num = Integer.parseInt(st.nextToken());
                dragonInfo[i][j] = num;
            }
        }

        // 1. 드래곤 커브 만들기
        for (int i = 0; i < N; i++) {
            drawDragon(dragonInfo[i][0], dragonInfo[i][1], dragonInfo[i][2], dragonInfo[i][3]);
        }

        // 출력해보기
//        System.out.println("드래곤 커브 완성 10");
//        for (int i = 0; i < 10; i++) {
//            System.out.println(Arrays.toString(matrix[i]));
//        }

        // 2. 정답 출력하기
        // 일반적인 방법으로 전체 순회하며 4개가 해당하는 경우의 개수 찾기
        // (연결되어 있는 선분이 중요한게 아니라 꼭지점만 찾기 때문에 좌표 표시가 쉬운 문제인 편)
        // 좌표 조심 (100까지 해야함)
        int answer = 0;
        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < 100; j++) {
                 if (matrix[i][j] == 1 && matrix[i+1][j] == 1 && matrix[i][j+1] == 1 && matrix[i+1][j+1] == 1) {
                     answer++;
                 }
            }
        }

        System.out.println(answer);
    }

    public static void drawDragon(int startC, int startR, int dir, int gen) {


        matrix[startR][startC] = 1; // 0세대 시작점
        Stack<Integer> stack = new Stack<>(); // 전체 스택을 쌓기 위한 스택

        // 0세대 상태 저장
        int endR = startR + dr[dir];
        int endC = startC + dc[dir];
        matrix[endR][endC] = 1;
        stack.push(dir);

        // 세대수 만큼 Q 돌리기
        while (gen-- > 0) {
            Stack<Integer> stack2 = (Stack<Integer>) stack.clone(); // 이번 세대를 위한 스택 복사

            while (!stack2.isEmpty()) {

                // 매트릭스에 반영
                int curDir = stack2.pop();
                endR = endR + dr[(curDir+1)%4]; // 끝점
                endC = endC + dc[(curDir+1)%4];
                matrix[endR][endC] = 1;

                // 다음 세대를 위해 계속 이전 스택 추가
                stack.push((curDir+1)%4); // 반시계로 이동

            } // 한 세대 이동 끝

        } // while 끝

    }
}