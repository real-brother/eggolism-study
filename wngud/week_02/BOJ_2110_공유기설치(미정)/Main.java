import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 입력 받기
        // 첫째줄
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());

        // 연속

        long[] arr = new long[N];
        for (int i = 0; i < N; i++) {
            long num = Long.parseLong(br.readLine());
            arr[i] = num;
        }

        Arrays.sort(arr);

        // 이진 탐색 시작
        long answer = binarySearch(arr);
        System.out.println("정답: " + answer);

    }

    // 인덱스 반환
    static long binarySearch(long[] arr) {
        int pl = 0;
        int pr = arr.length - 1;

        while (pl < pr) {
            long lft_diff = arr[pl] - arr[0];
            long rgt_diff = arr[arr.length - 1] - arr[pr];

            System.out.println("좌표: " + pl + " " + pr);
            System.out.println(lft_diff);
            System.out.println(rgt_diff);

            // 왼쪽이 우선으로 움직이게
            if (lft_diff <= rgt_diff) {
                pl++;
            } else {
                pr--;
            }

        } // while 끝

        // 판별
        if (arr[pl] - arr[0] <= arr[arr.length - 1] - arr[pr]) {
            return arr[pl] - arr[0];
        }  else {
            return arr[arr.length - 1] - arr[pr];
        }

    }

}
