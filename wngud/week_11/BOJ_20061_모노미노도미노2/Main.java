import java.util.*;

public class Main {

    /*디버깅
    * 1. 옅은 부분과 진한 부분에서 동시에 사라지는 조건이 만족할 때
    * 진한 부분을 먼저하라고 하는 것 까지는 문제에 따랐는데,
    * 진한 부분을 먼저 없앨 때 옅은 부분은 유지한채 진한 부분끼리만 내려가도록함
    * -> 옅은 부분 포함해서 내려가도록 해야 했음*/

    static int[][] GreenMatrix;
    static int[][] BlueMatrix;

    static int answer;

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();

        int[][] info = new int[N][3];
        for (int i = 0; i < N; i++) {
            int t = sc.nextInt();
            int r = sc.nextInt();
            int c = sc.nextInt();

            info[i][0] = t;
            info[i][1] = r;
            info[i][2] = c;
        }

        // 게임 시작하기 -> N 번
        answer = 0;
        GreenMatrix = new int[6][4];
        BlueMatrix = new int[4][6];

        for (int i = 0; i < N; i++) {

            // 1. 놓을 위치 정하기
            int redSize = info[i][0];
            int redR = info[i][1];
            int redC = info[i][2];

            // 2. 그린(0)/블루(1)에 블록 놓기
            // size = 1(1x1), 2(세워진 것), 3(누워진 것)
            moveBlock(redSize, redR, redC, 0);
            moveBlock(redSize, redR, redC, 1);

            // 3. 점수 가능 여부와 라인 옮기기
            getPoint(0);
            getPoint(1);

            // 4. 경계값 처리하기
            removeBorder(0);
            removeBorder(1);

        } // N번 끝

        // 정답 출력하기
        System.out.println(answer);

        int count = 0;
        for (int i = 0; i < GreenMatrix.length; i++) {
            for (int j = 0; j < GreenMatrix[0].length; j++) {
                count += GreenMatrix[i][j]; // 1이니까 더하도록
            }
        }
        for (int i = 0; i < BlueMatrix.length; i++) {
            for (int j = 0; j < BlueMatrix[0].length; j++) {
                count += BlueMatrix[i][j]; // 1이니까 더하도록
            }
        }

        System.out.println(count);

    }

    public static void removeBorder(int matrixColor) {

        if (matrixColor == 0) {

            // 몇번 갱신해야하는지 탐색
            int temp = 0;
            for (int i = 0; i < 2; i++) {

                for (int j = 0; j < 4; j++) {
                    if (GreenMatrix[i][j] == 1) {
                        temp++;
                        break;
                    }
                } // 한개 행 탐색 끝
            }

            // 갱신 개수 파악 완료
            // 앞으로 다 당기고, 뒤는 초기화
            if (temp == 2) {
                // (5,3) (4,2) (3,1) (2,0)
                for (int i = 5; i >= 2; i--) {
                    for (int j = 0; j < 4; j++) {
                        GreenMatrix[i][j] = GreenMatrix[i - 2][j];
                    }
                }
            } else if (temp == 1) {
                // (5,4) (4,3) (3,2) (2,1)
                for (int i = 5; i >= 2; i--) {
                    for (int j = 0; j < 4; j++) {
                        GreenMatrix[i][j] = GreenMatrix[i - 1][j];
                    }
                }
            }

            for (int i = 0; i < 2; i++) {
                for (int j = 0; j < GreenMatrix[0].length; j++) {
                    GreenMatrix[i][j] = 0;
                }
            }
        }

        else {

            // 몇번 갱신해야하는지 탐색
            int temp = 0;
            for (int i = 0; i < 2; i++) {

                for (int j = 0; j < 4; j++) {
                    if (BlueMatrix[j][i] == 1) {
                        temp++;
                        break;
                    }
                } // 한개 열 탐색 끝
            }

            // 갱신 개수 파악 완료
            // 앞으로 다 당기고, 뒤는 초기화
            if (temp == 2) {
                // (5,3) (4,2) (3,1) (2,0)
                for (int i = 5; i >= 2; i--) {
                    for (int j = 0; j < 4; j++) {
                        BlueMatrix[j][i] = BlueMatrix[j][i - 2];
                    }
                }
            } else if (temp == 1) {
                // (5,4) (4,3) (3,2) (2,1)
                for (int i = 5; i >= 2; i--) {
                    for (int j = 0; j < 4; j++) {
                        BlueMatrix[j][i] = BlueMatrix[j][i - 1];
                    }
                }
            }

            for (int i = 0; i < 2; i++) {
                for (int j = 0; j < BlueMatrix.length; j++) {
                    BlueMatrix[j][i] = 0;
                }
            }

        }

    }

    public static void getPoint(int matrixColor) {

        // 그린인 경우
        if (matrixColor == 0) {
            // 4행 탐색(2~6)
            for (int i = 2; i < 6; i++) {

                // i행 4칸 탐색
                int temp = 0;
                for (int j = 0; j < 4; j++) {
                    if (GreenMatrix[i][j] != 1)
                        break;
                    temp++;
                } // 한개 행 탐색 끝

                // 한 행 검사
                // i 칸 위를 복사하고, 2번째 행은 0으로 초기화한다. -> 동시에 있으면 연한 것도 내려 보내야 하나? -> 맞음
                // (i = i - 1) .. (3, 2) ... (1, 0) -> 0행을 없애야 함!!
                if (temp == 4) {
                    answer++;
                    for (int k = i; k >= 1; k--) {
                        for (int j2 = 0; j2 < 4; j2++) {
                            GreenMatrix[k][j2] = GreenMatrix[k - 1][j2];
                        }
                    }
                    for (int j2 = 0; j2 < 4; j2++) {
                        GreenMatrix[0][j2] = 0;
                    }
                }

            }

        }

        // 블루인 경우
        else {
            // 4열 탐색(2~6)
            for (int i = 2; i < 6; i++) {

                // i열 4칸 탐색
                int temp = 0;
                for (int j = 0; j < 4; j++) {
                    if (BlueMatrix[j][i] != 1)
                        break;
                    temp++;
                } // 한개 행 탐색 끝

                // 한 행 검사
                // i 칸 위를 복사하고, 2번째 행은 0으로 초기화한다.
                if (temp == 4) {
                    answer++;
                    for (int k = i; k >= 1; k--) {
                        for (int j2 = 0; j2 < 4; j2++) {
                            BlueMatrix[j2][k] = BlueMatrix[j2][k - 1];
                        }
                    }
                    for (int j2 = 0; j2 < 4; j2++) {
                        BlueMatrix[j2][0] = 0;
                    }
                }

            }

        }

    }

    public static void moveBlock(int size, int r, int c, int matrixColor) {

        List<int[]> blockList = new ArrayList<>();

        // 그린인 경우
        if (matrixColor == 0) {

            // 블록 시작위치 정하기 -> 1단계 경계값부터 가능, 사이즈별로 다르게 처리
            if (size == 1) {
                blockList.add(new int[] { 1, c });
            } else if (size == 2) {
                blockList.add(new int[] { 1, c });
                blockList.add(new int[] { 1, c + 1 });
            } else if (size == 3) {
                blockList.add(new int[] { 1, c });
                blockList.add(new int[] { 0, c });
            }

            // 블록 움직이기 -> 그린은 아래로, 블록 칸의 개수만큼 유동적이게
            for (int i = 0; i < blockList.size(); i++) {

                // 블록 한개 시작
                while (true) {
                    int nr = blockList.get(i)[0] + 1;
                    int nc = blockList.get(i)[1]; // 의미없음

                    // 경계값을 넘거나 다른 블록이 있으면 이동 못함 -> 한개라도 겹치면 끝
                    if (nr < 0 || nr >= 6 || nc < 0 || nc >= 4 || GreenMatrix[nr][nc] == 1) {
                        break;
                    }

                    blockList.get(i)[0] = nr; // 계속 내리기
                } // 블록 한개 끝

            }

            // 블록 위치 확정하기 -> 그린은 행을 기준으로
            // size가 2인 경우 둘의 최소값으로 갱신 (입력값대로 1부터 시작)
            // size가 3인 경우 기준점에서 한칸 올리기
            if (size == 2) {
                for (int i = 0; i < blockList.size(); i++) {
                    blockList.get(i)[0] = Math.min(blockList.get(0)[0], blockList.get(1)[0]);
                }
            } else if (size == 3) {
                blockList.get(1)[0] = blockList.get(0)[0] - 1;
            }

//			System.out.println("블록 리스트");
//			for (int j = 0; j < blockList.size(); j++) {
//				System.out.println(Arrays.toString(blockList.get(j)));
//			}

            for (int i = 0; i < blockList.size(); i++) {
                GreenMatrix[blockList.get(i)[0]][blockList.get(i)[1]] = 1;
            }

        }

        // 블루인 경우
        else {

            // 블록 시작위치 정하기 -> 1단계 경계값부터 가능, 사이즈별로 다르게 처리
            if (size == 1) {
                blockList.add(new int[] { r, 1 });
            } else if (size == 2) {
                blockList.add(new int[] { r, 1 });
                blockList.add(new int[] { r, 0 });
            } else if (size == 3) {
                blockList.add(new int[] { r, 1 });
                blockList.add(new int[] { r + 1, 1 });
            }

            // 블록 움직이기 -> 그린은 아래로, 블록 칸의 개수만큼 유동적이게
            for (int i = 0; i < blockList.size(); i++) {

                // 블록 한개 시작
                while (true) {
                    int nr = blockList.get(i)[0];
                    int nc = blockList.get(i)[1] + 1;
                    // 경계값을 넘거나 다른 블록이 있으면 이동 못함 -> 한개라도 겹치면 끝
                    if (nr < 0 || nr >= 4 || nc < 0 || nc >= 6 || BlueMatrix[nr][nc] == 1) {
                        break;
                    }

                    blockList.get(i)[1] = nc; // 계속 내리기
                } // 블록 한개 끝

            }

            // 블록 위치 확정하기 -> 그린은 행을 기준으로
            // size가 2인 경우 둘의 최소값으로 갱신 (입력값대로 1부터 시작)
            // size가 3인 경우 기준점에서 한칸 올리기
            if (size == 2) {
                blockList.get(1)[1] = blockList.get(0)[1] - 1;
            } else if (size == 3) {
                for (int i = 0; i < blockList.size(); i++) {
                    blockList.get(i)[1] = Math.min(blockList.get(0)[1], blockList.get(1)[1]);
                }
            }

//			System.out.println("블록 리스트");
//			for (int j = 0; j < blockList.size(); j++) {
//				System.out.println(Arrays.toString(blockList.get(j)));
//			}

            for (int i = 0; i < blockList.size(); i++) {
                BlueMatrix[blockList.get(i)[0]][blockList.get(i)[1]] = 1;
            }

        }

    }

}