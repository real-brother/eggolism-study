import java.util.Scanner;

public class BOJ_14890_경사로 {
    public static void main(String[] args) {

        // 입력 받기
        Scanner sc = new Scanner(System.in);

        int answer = 0;

        int line = sc.nextInt();
        int L = sc.nextInt();

        // 매트릭스 만들기
        // 2번만 확인하도록 로직을 만듦
        // line*2로 해서 인덱스가 일자로 쭉 돌도록 만듦
        int[][] matrix = new int[line*2][line];

        // 라인 만들기
        // 라인 만들기 (세로)
        for (int i = 0; i < line; i++) {
            for (int j = 0; j < line; j++) {
                int num = sc.nextInt();
                matrix[i][j] = num;
            }
        }

        // 라인 만들기 (가로 추가)
        for (int i = 0; i < line; i++) {
            for (int j = 0; j < line; j++) {
                matrix[i+line][j] = matrix[j][i];
            }
        }


         // 2. 경사로 판별 시작

/*         L로 인한 예외 처리하기
         내리막길 -> 더하기 반복, 앞 체크 추가적으로 반복
         오르막길 -> 뒤 체크 더 추가적으로 반복

         인덱스 에러 대비하기

        [3, 3, 2, 2, 3, 3] [3, 2, 2, 2, 3, 3] 겹쳐서 경사로 되는 문제
        visited 넣기 -> 내리막길에서 넣어주는게 중요 -> 판별은 오르막길만 쓰임

         L = 1이 빡셈
        [3, 2, 2, 1, 2, 3]*/

        for (int n = 0; n < line*2; n++) {
            int[] visited = new int[line];
            int start = 0;
            // 라인 만들기
            int[] line6 = matrix[n];

            // 탐색 시작
            while (start < line6.length - 1) {
                // 1. 일반적으로 통과하는 상황
//                System.out.println(start + "에서 " + (start + 1) + "로 통과 시작");
                if (line6[start] == line6[start + 1]) {
//                    System.out.println(start + "에서 " + (start + 1) + "로 통과");

                    // 2. 내리막길 -> L 가능한지 체크
                } else if (line6[start] == line6[start + 1] + 1) {
//                    System.out.println("내리막길");

                    // 더해서 점검
                    try {
                        boolean available_down = false;
                        for (int i = 1; i <= L; i++) {
                            if (line6[start] == (line6[start + i] + 1)) { //start는 고정
                                available_down = true;
//                                System.out.println((start + i - 1) + "에서 " + (start + i) + "로 통과");
                            } else {
                                available_down = false;
//                                System.out.println((start + i - 1) + "에서 " + (start + i) + "로 통과실패!!!");
                                break;
                            }
                        }

                        if (available_down == false) {
                            break;
                        } else {
                            // L=1 예외
                            if (L == 1) {
                                visited[start+1] = 1; // 여러번 해도 됨
                            } else {
                                for (int i = 1; i < L; i++) { // 한번만 덜 더하기
                                    start++;
                                    visited[start] = 1; // 여러번 해도 됨
                                    visited[start+1] = 1; // 여러번 해도 됨
                                }
                            }

                        }
                    } catch (ArrayIndexOutOfBoundsException e) {
//                        System.out.println("오류로 종료");
                        break;
                    }

                    // 3. 오르막길
                } else if (line6[start] == line6[start + 1] - 1) {
//                    System.out.println("오르막길");

                    // 빼서 점검 (L만큼)
                    try {
                        boolean available_up = false;
                        if (L != 1) {
                            for (int i = 1; i < L; i++) {
                                if (line6[start - i] == line6[start] && visited[start-i] == 0 && visited[start] == 0 ) {
                                    available_up = true;
                                } else {
//                                    System.out.println("통과에 실패하였습니다!!!");
                                    available_up = false;
                                    break;
                                }
                            }
                        } else if (L == 1 && visited[start] == 0) {
                            available_up = true;
                        }

                        if (!available_up) {
                            break;
                        } else {
//                            System.out.println("오르막길 경사로 가능");
//                            System.out.println(start + "에서 " + (start + 1) + "로 통과");
                        }
                    } catch (ArrayIndexOutOfBoundsException e) {
//                        System.out.println("오류로 종료합니다.");
                        break;
                    }


                } else {
//                    System.out.println("통과에 실패하였습니다!!!");
                    break;
                }
//                System.out.println("visited: " + Arrays.toString(visited));
                // 한번 체크 끝
                start++;
            } // 한 탐색 끝

            if (start == line6.length - 1) {
//                System.out.println(">>> 전부 통과하였습니다.");
                answer++;
            } else {
//                System.out.println(">>> 전부 통과 실패입니다.");
            } // 한줄 아예 끝

        }

        System.out.println(answer);

    }
}