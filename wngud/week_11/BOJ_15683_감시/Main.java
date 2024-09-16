import java.util.*;

public class Main {

    /*백트래킹
     * 1. 2번은 두가지 경우의 수만, 5번은 한가지 경우의 수만 존재
     */


    static int[][] matrix;
    static int[][] matrix2;
    static int[][] visited;
    static int[] sel;
    static List<int[]> cctvIdx;
    static List<Integer> cctvIdxFive;
    static List<Integer> cctvIdxTwo;
    static int N, M, cctvTotal, targetCover, cover, answer;

    static int[] dr = {-1, 0, 1, 0}; // 12시부터 시계 방향
    static int[] dc = {0, 1, 0, -1};

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        M = sc.nextInt();

        // 매트릭스 입력 받기
        targetCover = 0;

        cctvIdx = new ArrayList<int[]>();
        cctvIdxFive = new ArrayList<Integer>();
        cctvIdxTwo = new ArrayList<Integer>();

        matrix = new int[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                int num = sc.nextInt();
                matrix[i][j] = num;

                if (num >= 1 && num <= 5) {
                    cctvIdx.add(new int[]{i, j, num}); // CCTV 종류도 추가
                    if (num == 5) {
                        cctvIdxFive.add(cctvIdx.size() - 1); // 사이즈를 통해 인덱스 파악
                    } else if (num == 2) {
                        cctvIdxTwo.add(cctvIdx.size() - 1); // 사이즈를 통해 인덱스 파악
                    }
                } else if (num == 0) {
                    targetCover++; // 0의 개수 미리 세기 (답: 전체 0의 개수 - 커버 개수 = 사각지대)
                }
            }
        }

        // CCTV 돌리기
        answer = 987654321;

        cctvTotal = cctvIdx.size();
        sel = new int[cctvTotal];

        perm(0);

        // 정답 출력하기
        System.out.println(answer);

    }

    // 중복 순열
    public static void perm(int idx) {

        if (idx == cctvTotal) {
            // 선택 완료

            // 조합을 가지고 돌려보기
            visited = new int[N][M];
            rotation();

            return;
        }

        // 회전을 하는데 있어 4가지의 경우의 수가 있음
        for (int i = 0; i < 4; i++) {
            // 넣으려는 인덱스가 5가 있는 경우 0만 넣는다. -> 백트래킹
            // 넣으려는 인덱스가 2가 있는 경우 0,1만 넣는다. -> 백트래킹
            if (cctvIdxFive.contains(idx)) {
                if (i != 0) continue;
            } else if (cctvIdxTwo.contains(idx)) {
                if (i == 2 || i == 3) continue;
            }

            sel[idx] = i;
            perm(idx + 1);
        }

    }

    public static void rotation() {

        cover = 0; // CCTV가 커버한 지역의 횟수

        // 한번의 경우에 새로운 매트릭스 필요
        matrix2 = new int[N][M];
        for (int i = 0; i < N; i++) {
            matrix2[i] = matrix[i].clone();
        }

        // CCTV 개수만큼 진행 (sel.length())
        for (int i = 0; i < cctvTotal; i++) {

            int r = cctvIdx.get(i)[0];
            int c = cctvIdx.get(i)[1];
            int type = cctvIdx.get(i)[2];
            int status = sel[i];

            // 타입에 따라 숫자를 모듈러로 돌릴 수 있었다.
            if (type == 1) {
                cctvOne(r, c, status);
            } else if (type == 2) {
                cctvTwo(r, c, status);
            } else if (type == 3) {
                cctvThree(r, c, status);
            } else if (type == 4) {
                cctvFour(r, c, status);
            } else if (type == 5) {
                cctvFive(r, c, status);
            }
        }

        // 사각지대 구하기
        answer = Math.min(answer, targetCover - cover);
    }





    public static void goStraight(int r, int c, int dir) {

        int nr = r + dr[dir];
        int nc = c + dc[dir];

        if (nr < 0 || nr >= N || nc < 0 || nc >= M || matrix2[nr][nc] == 6) return;

        // 점수 획득은 처음은 경우, 0인 경우
        if (matrix2[nr][nc] == 0 && visited[nr][nc] == 0) {
            visited[nr][nc] = 1;
            cover++;
        }

        // 지나가는 것은 CCTV랑 visited는 상관없음
        goStraight(nr, nc, dir);
    }


    public static void cctvOne(int r, int c, int status) {

        // 0
        if (status == 0) goStraight(r, c, 0);

        // 1
        else if (status == 1) goStraight(r, c, 1);

        // 2
        else if (status == 2) goStraight(r, c, 2);

        // 3
        else if (status == 3) goStraight(r, c, 3);


    }

    public static void cctvTwo(int r, int c, int status) {

        // 0
        if (status == 0) {
            goStraight(r, c, 0);
            goStraight(r, c, 2);
        }

        // 1
        else if (status == 1) {
            goStraight(r, c, 1);
            goStraight(r, c, 3);
        }

    }

    public static void cctvThree(int r, int c, int status) {

        // 0
        if (status == 0) {
            goStraight(r, c, 0);
            goStraight(r, c, 1);
        }

        // 1
        else if (status == 1) {
            goStraight(r, c, 1);
            goStraight(r, c, 2);
        }

        // 2
        else if (status == 2) {
            goStraight(r, c, 2);
            goStraight(r, c, 3);
        }

        // 3
        else if (status == 3) {
            goStraight(r, c, 3);
            goStraight(r, c, 0);
        }
    }

    public static void cctvFour(int r, int c, int status) {

        // 0
        if (status == 0) {
            goStraight(r, c, 0);
            goStraight(r, c, 1);
            goStraight(r, c, 2);
        }

        // 1
        else if (status == 1) {
            goStraight(r, c, 1);
            goStraight(r, c, 2);
            goStraight(r, c, 3);
        }

        // 2
        else if (status == 2) {
            goStraight(r, c, 2);
            goStraight(r, c, 3);
            goStraight(r, c, 0);
        }

        // 3
        else if (status == 3) {
            goStraight(r, c, 3);
            goStraight(r, c, 0);
            goStraight(r, c, 1);
        }
    }

    public static void cctvFive(int r, int c, int status) {
        goStraight(r, c, 0);
        goStraight(r, c, 1);
        goStraight(r, c, 2);
        goStraight(r, c, 3);
    }


}