import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main2 {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 입력 받기
        int total = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] arr = new int[total];
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < total; i++) {
            int num = Integer.parseInt(st.nextToken());
            arr[i] = num;

            if (max < num) max = num;
            if (min > num) min = num;
        }

        Arrays.sort(arr);

        // 이진 탐색 시작
        int[] answers = {0, 0};

//        System.out.println(Arrays.toString(arr));
        long answer = binarySearch(arr, min, max, answers);
//        System.out.println("최소값: " + answer);
        System.out.println(arr[answers[0]] + " " + arr[answers[1]]);
    }


    static long binarySearch(int[] arr, int min, int max, int[] answers) {
        int pl = 0;
        int pr = arr.length - 1;
        long minSum = Integer.MAX_VALUE;
        long previousSum = 0;
        long currentSum = 0;

        while (pl != arr.length - 1) {
            pr = arr.length - 1;
            previousSum = 0;

            while (pl < pr) {
//                System.out.println("현재 좌표: (" + pl + ", " + pr + ")");
                currentSum = Math.abs(arr[pr] + arr[pl]);

//                System.out.println("현재값: " + currentSum);
                if (minSum > currentSum) {
//                    System.out.println("최소값 갱신입니다.");
                    minSum = currentSum;
//                    System.out.println("최소값: " + minSum);
                    answers[0] = pl;
                    answers[1] = pr;
                }

                previousSum = currentSum;

                if (currentSum > previousSum) {
//                    System.out.println("값이 커져서 종료합니다.");
                    break;
                }


                pr--;
            } // 한개 탐색 끝
            pl++;
        }


        return minSum;
    }


}