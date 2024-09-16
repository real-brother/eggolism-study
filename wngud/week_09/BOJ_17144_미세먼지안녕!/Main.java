public class Main {

    /*설계 방식
     * 1. 미세먼지는 설명대로
     * 2. 공기청정기의 이동이 문제
     * - 이것도 매트릭스 하나 만들자.*/

    static int n, m, t;

    static int[][] matrix;
    static int[][] newMarix; // 공기청정기를 위한 매트릭스
    //    static Queue<int[]> dustIdx;
    static int[] airCleanerDown; // 아래 부분 좌표만 저장

    static int[] dr = {0, 0, -1, 1};
    static int[] dc = {1, -1, 0, 0};

    static int answer;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        t = Integer.parseInt(st.nextToken());

        matrix = new int[n][m];
        airCleanerDown = new int[2];
        // 입력 받기
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                int num = Integer.parseInt(st.nextToken());
                matrix[i][j] = num;

                // 공기청정기 좌표 저장 -> 2개 중 마지막인 아래로 저장됨
                if (num == -1) {
                    airCleanerDown[0] = i;
                    airCleanerDown[1] = j;
                }
            }
        }

        // t만큼 반복
        while (t-- > 0) {
            // 1. 미세먼지 확산하기
            dusting();

            // 2. 공기청정기 작동
            // 위 아래로 2번 작동해야 함
            // set 0은 위 방향 공기청정기, set 1은 아래 방향 공기청정기
            airFresh(airCleanerDown[0] - 1, airCleanerDown[1], 0);
            airFresh(airCleanerDown[0], airCleanerDown[1], 1);
        }

        // 최종 미세먼지 양
        // 공기청정기 포함X
        matrix[airCleanerDown[0] - 1][airCleanerDown[1]] = 0;
        matrix[airCleanerDown[0]][airCleanerDown[1]] = 0;

        // 3. 미세먼지 수 구하기
        answer = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                answer += matrix[i][j];
            }
        }
        System.out.println(answer);

    }


    // 동시성 문제 -> 좌표를 저장하는 방식 -> 불가능
    // -> 새로운 메트릭스 할당
    public static void dusting() {

        // 새로운 매트릭스 할당
        int[][] newMarix2 = new int[n][m];
        for (int i = 0; i < n; i++) {
            newMarix2[i] = matrix[i].clone();
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {

                if (matrix[i][j] <= 0) continue;
                int[] cur = new int[]{i, j};


                int count = 0; // 마지막에 남은 먼지를 계산하기 위한 변수

                // 먼지 확산
                for (int s = 0; s < 4; s++) {
                    int nr = cur[0] + dr[s];
                    int nc = cur[1] + dc[s];

                    // 공기청정기인 경우에만 확산하지 못함 (먼지가 있었던 곳도 가능)
                    if (nr < 0 || nr >= n || nc < 0 || nc >= m || matrix[nr][nc] == -1) continue;

                    newMarix2[nr][nc] += matrix[cur[0]][cur[1]] / 5;
                    count++;
                }

                // 한칸 끝. 남은 먼지 계산 -> 최신화를 잘해야 함!!!!! (동시성 문제)
                // -> 뉴메트릭스에다가 빼야함
                newMarix2[cur[0]][cur[1]] = (newMarix2[cur[0]][cur[1]] - ((matrix[cur[0]][cur[1]] / 5) * count));
            }
        } // 반복 끝

        matrix = newMarix2;

    }

    public static void airFresh(int airR, int airC, int set) {

        // 새로운 매트릭스 할당
        newMarix = new int[n][m];
        for (int i = 0; i < n; i++) {
            newMarix[i] = matrix[i].clone();
        }

        // 오른쪽으로 쭉
        // 시작에 열 한칸 이동
        // 끝에 도착하면 멈추기
//        System.out.println("오른쪽 이동");
        airC++;
        while (airC != m - 1) {
            move(airR, airC, 3);
            airC++;
        }

        // 1) 위로 쭉
        if (set == 0) {
//            System.out.println("위로 이동");
            while (airR != 0) {
                move(airR, airC, 0);
                airR--;
            }
        }
        // 2) 아래로 쭉
        else {
//            System.out.println("아래로 이동");
            while (airR != n - 1) {
                move(airR, airC, 1);
                airR++;
            }
        }

        // 왼쪽으로 쭉
//        System.out.println("왼쪽 이동");
        while (airC != 0) {
            move(airR, airC, 2);
            airC--;
        }

        // 여기서 공기청정기랑 마주침!
        // 1) 위로 쭉 - 세팅 1로 바꿈
        if (set == 1) {
//            System.out.println("위로 이동");
            while (airR != 0) {
                if (matrix[airR - 1][airC] == -1) break; // 공기 청정기를 전에 만나면 미리 종료
                move(airR, airC, 0);
                airR--;
            }
        }
        // 2) 아래로 쭉 - 세팅 0으로 바꿈
        else {
//            System.out.println("아래로 이동");
            while (airR != n - 1) {
                if (matrix[airR + 1][airC] == -1) break; // 공기 청정기를 전에 만나면 미리 종료
                move(airR, airC, 1);
                airR++;
            }
        }

        // 공기청정기 바로 옆 먼지 제거 (맨 처음 덮어쓰기 안됨)
        if (set == 0) {
            newMarix[airCleanerDown[0] - 1][airCleanerDown[1]+1] = 0;
        } else {
            newMarix[airCleanerDown[0]][airCleanerDown[1]+1] = 0;
        }

        // 순회 끝
        matrix = newMarix;

    }

    // 방향: 상하좌우
    static void move(int r, int c, int set) {

        if (set == 0) {
            newMarix[r - 1][c] = matrix[r][c];
        } else if (set == 1) {
            newMarix[r + 1][c] = matrix[r][c];
        } else if (set == 2) {
            newMarix[r][c - 1] = matrix[r][c];
        } else if (set == 3) {
            newMarix[r][c + 1] = matrix[r][c];
        }
    }

}
