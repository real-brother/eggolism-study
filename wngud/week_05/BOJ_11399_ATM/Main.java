import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        /*설계 방식
        * 1. 앞에 숫자가 가장 적은 숫자가 되어야 인출하는데 걸리는 시간이 가장 적게 됨
        * 2. 정렬 이용*/

        Scanner sc = new Scanner(System.in);

        int total = sc.nextInt();

        int[] arr = new int[total];
        for (int i = 0; i < total; i++) {
            arr[i] = sc.nextInt();
        }

        Arrays.sort(arr);

        int sum = 0;
        for (int i = total; i >= 1; i--) {

            sum += arr[(total - i)] * i;

        }

        System.out.println(sum);

    }
}
