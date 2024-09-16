import java.util.*;

public class Main {

    static List<ArrayList<Integer>> arr;
    static int[][] matrix;

    static int N, M, H, answer;
    static boolean avail;

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        M = sc.nextInt(); // 있는 가로선
        H = sc.nextInt();

        arr = new ArrayList<>();
        matrix = new int[H + 1][N + 2]; // 경계선 방지

        // 실제 숫자만큼 리스트 세팅 (N-1까지만 세팅)
        for (int i = 0; i <= N - 1; i++) {
            arr.add(new ArrayList<>());
        }

        // 있는 가로선 추가
        for (int i = 0; i < M; i++) {
            int a = sc.nextInt(); // 높이
            int b = sc.nextInt();

            arr.get(b).add(a);
            matrix[a][b] = 1; // 오른쪽
            matrix[a][b + 1] = 2; // 왼쪽
        }

        // 출력해보기
//        for (int i = 0; i < arr.size(); i++) {
//            System.out.println(arr.get(i).toString());
//        }
//
//        for (int i = 0; i < matrix.length; i++) {
//            System.out.println(Arrays.toString(matrix[i]));
//        }

        // 사다리 내려가기
        avail = true;
        for (int start = 1; start <= N; start++) {
            check(start, 1, start);
            if (!avail) break;
        }

        // 아무것도 넣지 않고 가능하면 0으로 출력
        answer = Integer.MAX_VALUE;
        if (avail) {
            answer = 0;
            System.out.println(answer);
        }

        if (!avail) {
            // 사다리 넣어보기
            ladder(1, 0);

            // 정답 출력하기
            if (answer == Integer.MAX_VALUE) {
                System.out.println(-1);
            } else {
                System.out.println(answer);
            }

        }


    }

    public static void ladder(int idx, int count) {

        // 3개 초과면 의미없음
        if (count == 4) return;

        // 한 케이스 완성
        // N-1까지만 넣기
        if (idx == N) {

//            System.out.println("하나 완성 ---------------");
//            for (int i = 0; i < matrix.length; i++) {
//                System.out.println(Arrays.toString(matrix[i]));
//            }

            for (int start = 1; start <= N; start++) {
                check(start, 1, start);
                if (!avail) break;
            }

            if (avail) {
                answer = Math.min(count, answer);
            }

            return;
        }

        // 사다리 하나 넣기
        // H까지 가능성 있는 것 모두 넣기
        for (int i = 1; i <= H; i++) {
            if (matrix[i][idx - 1] == 1 || matrix[i][idx + 1] != 0) continue;
            if (matrix[i][idx - 1] == 3 || matrix[i][idx + 1] != 0) continue;

            matrix[i][idx] = 3;
            matrix[i][idx + 1] = 4;
            ladder(idx + 1, count + 1);
            matrix[i][idx] = 0;
            matrix[i][idx + 1] = 0;
        }
        ladder(idx + 1, count);


    }

    public static void check(int idx, int height, int start) {

        // 끝까지 내려감
        if (height == H + 1) {

            if (idx == start) {
//                System.out.println(start + "는 가능합니다.");
                avail = true;
                return;
            }
            avail = false;
            return;
        }

        // 우로가기
        if (matrix[height][idx] == 1 || matrix[height][idx] == 3) {
            idx++;
            height++;
        } else if (matrix[height][idx] == 2 || matrix[height][idx] == 4) {
            // 좌로가기
            idx--;
            height++;
        } else {
            height++;
        }

        check(idx, height, start);

    }


}
