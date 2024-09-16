import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 입력 받기
        int total = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] arr = new int[total];
        for (int i = 0; i < total; i++) {
            int num = Integer.parseInt(st.nextToken());
            arr[i] = num;
        }

        Arrays.sort(arr);

        // 이진 탐색 시작
        int[] answers = {0, 0};

        binarySearch(arr, answers);
        System.out.println(arr[answers[0]] + " " + arr[answers[1]]);
    }


    static void binarySearch(int[] arr, int[] answers) {
        int pl = 0;
        int pr = arr.length - 1;
        int minSum = Math.abs(arr[pl] + arr[pr]); // answers도 맞춰줘야 함 (모두 지나가서 할당X 경우 방지)
//        int minSum = Integer.MAX_VALUE;

        answers[0] = 0;
        answers[1] = arr.length - 1;

        while (pl < pr) {

            int newSum = arr[pl] + arr[pr];

            // 당연
            if (minSum > Math.abs(newSum)) {
                minSum = Math.abs(newSum);
                answers[0] = pl;
                answers[1] = pr;
            }

            // 핵심 조건
            if (newSum > 0) {
                pr--;
            } else {
                pl++;
            }

        }
    }

}


/*반례
* 5
-100 1 2 3 4*/