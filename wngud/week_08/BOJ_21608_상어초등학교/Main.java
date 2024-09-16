public class Main {
    static int n;

    static int[][] matrix;
    static int[][] sitMatrix;
    static int[][] countMatrix;

    static int[][] sitInfo;
    static Map<Integer, ArrayList<Integer>> favorInfo;

    static int[] dr = {1, -1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {


        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        int studentNum = n * n;
        matrix = new int[n*n][5];
        sitMatrix = new int[n][n];
        sitInfo = new int[n*n+1][2];
        favorInfo = new HashMap<>();

        // favorInfo
        for (int i = 1; i <= n*n; i++) {
            favorInfo.put(i, new ArrayList<>());
        }

        int[] visited = new int[n*n+1];
        List<Integer> students = new ArrayList<>();

        // 입력 받기
        StringTokenizer st;
        for (int i = 0; i < studentNum; i++) {
            st = new StringTokenizer(br.readLine());
            int tmp = Integer.parseInt(st.nextToken());
            matrix[i][0] = tmp;
            for (int j = 1; j < 5; j++) {
                int tmp2 = Integer.parseInt(st.nextToken());
                matrix[i][j] = tmp2;
                favorInfo.get(tmp).add(tmp2);
            }
        }

        // 순회 시작
        for (int stu = 0; stu < studentNum; stu++) {
            // 한 학생 자리 정하기 시작
            countMatrix = new int[n][n];
            // 1. 좋아하는 학생의 번호의 위치를 이전에 저장했는지 확인
            int targetStu = matrix[stu][0];
            for (int favorStu = 1; favorStu < 5; favorStu++) { // 인덱스 주의
                int tmpStu = matrix[stu][favorStu];

                // 좋아하는 학생의 위치가 없다면 넘어감
                if (visited[tmpStu] == 0) continue;

                // 있다면 메트릭스에 점수 추가. 4방향
                for (int k = 0; k < 4; k++) {
                    int nr = sitInfo[tmpStu][0] + dr[k];
                    int nc = sitInfo[tmpStu][1] + dc[k];

                    if (nr<0||nr>=n||nc<0||nc>=n) continue;

                    countMatrix[nr][nc] += 1;
                }
            }

            // 2. 순회하면서 cnt, empty, index 정보 구하기
            int[] sit = findSeat(targetStu);

            // 3. 자리 위치시키기
            // visited에 추가하고 위치 저장하기
            sitMatrix[sit[0]][sit[1]] = targetStu;
            visited[targetStu] = 1;
            sitInfo[targetStu][0] = sit[0];
            sitInfo[targetStu][1] = sit[1];

        } // 모든 학생 자리 위치 끝

        // 4. 정답 찾기
        int answer = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int favorCount = 0;

                int tmp = sitMatrix[i][j];
                ArrayList<Integer> tmp2 = favorInfo.getOrDefault(tmp, new ArrayList<>());;

                for (int k = 0; k < 4; k++) {
                    int nr = i + dr[k];
                    int nc = j + dc[k];

                    if (nr<0||nr>=n||nc<0||nc>=n) continue;

                    if (tmp2.contains(sitMatrix[nr][nc]))  {
                        favorCount++;
                    }
                }
                int tmp3 = (int) Math.pow(10, favorCount-1);
                if (tmp3 < 1) continue; // 0은 제외
                answer += tmp3;
            }
        }
        System.out.println(answer);
    }

    public static int[] findSeat(int targetStu) {
        int maxCnt = -1;
        int maxEmpty = 0;
        int minR = -1;
        int minC = -1;

        // 첫번째만 (2,2)에 넣어주자
        if (targetStu == matrix[0][0]) {
            return new int[]{1, 1};
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {

                if (sitMatrix[i][j] >= 1) continue; // 있는 자리 패스

                if (maxCnt < countMatrix[i][j]) {
                    // 1
                    minR = i;
                    minC = j;
                    // 2
                    maxCnt = countMatrix[i][j];
                    // 3
                    maxEmpty = 0;
                    for (int k = 0; k < 4; k++) {
                        int nr = i + dr[k];
                        int nc = j + dc[k];

                        if (nr < 0 || nr >= n || nc < 0 || nc >= n || sitMatrix[nr][nc] >= 1) continue;

                        maxEmpty += 1;
                    }
                } else if (maxCnt == countMatrix[i][j]) {
                    // 3
                    int tmpEmpty = 0;
                    for (int k = 0; k < 4; k++) {
                        int nr = i + dr[k];
                        int nc = j + dc[k];

                        if (nr < 0 || nr >= n || nc < 0 || nc >= n || sitMatrix[nr][nc] >= 1) continue;

                        tmpEmpty += 1;
                    }
                    if (tmpEmpty > maxEmpty) {
                        // 3
                        maxEmpty = tmpEmpty;
                        // 1
                        minR = i;
                        minC = j;
                    } else if (tmpEmpty == maxEmpty) {
                        if (i < minR) minR = i;
                        else if (i == minR) {
                            if (j < minC) minC = j;
                        }
                    }
                }
                // 순회 한개 끝

            }
        } // 모든 순회 끝
        return new int[]{minR, minC};


    }

}