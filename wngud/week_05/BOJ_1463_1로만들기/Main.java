import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        /*
        * 문제 설명
        * 1. 3으로 나누기, 2로 나누기, 1로 빼기 순으로 이득처럼 보인다.
        * 2. 하지만, 반례 10처럼 먼저 2로 나누지 않고 1로 빼는 경우가 좋을 수 있다.
        * 3. 주어진 숫자에서 내려가는 방식이 매우 많아 보이긴 함*/

        // 전체를 다 저장하고 뽑아쓰는게 오히려 효율적일 수가 있구나

        Scanner sc = new Scanner(System.in);
        int target =sc.nextInt();

        int[] arr = new int[(int) Math.pow(10,6)+1];

        arr[1] = 0;
        arr[2] = 1;
        arr[3] = 1;

        for (int i = 4; i < arr.length; i++) {
            int temp1 = Integer.MAX_VALUE;
            int temp2 = Integer.MAX_VALUE;
            int temp3 = Integer.MAX_VALUE;
            // 경우의 수 1
            if (i % 3 == 0) {
                temp1 = arr[i/3] + 1;
            }
            // 경우의 수 2
            if (i % 2 == 0) {
                temp2 = arr[i/2] + 1;
            }
            // 경우의 수 3
            temp3 = arr[i-1] + 1;

            arr[i] = Math.min(Math.min(temp1,temp2), temp3);

        }

        System.out.println(arr[target]);

    }
}
