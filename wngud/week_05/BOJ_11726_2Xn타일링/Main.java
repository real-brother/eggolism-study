import java.util.*;

public class Main {
    public static void main(String[] args) {

        /*
        * 문제 설명
        * 1. 1*2와 2*1의 타일이 있다.
        * 2. 2*N의 직사각형을 채우는 방법을 찾아야 한다.
        * */

        /*
        * 설계 방식
        * 1. n이 한개 씩 늘어날 때마다, 이전의 타일 방식을 유지한 채 추가하는 것을 알 수 있다.*/

        /*
        * 쉬운 풀이
        * 1. 그냥 점화식을 나열하다 보면 f(n) = f(n-1) + f(n-2)의 관계임을 알 수 있다.
        * */

        /*
        * 예외 처리
        * 1. 1000까지 계속 가다 보면 숫자가 매우 크게 증가한다.
        * 2. 단순히 답에다가 %10007을 하면 안된다.
        * 3. 순간순간 마다 %10007로 업데이트를 해줘야 한다.
        * */

        Scanner sc = new Scanner(System.in);
        int target =sc.nextInt();

        // 끝까지 하고 찾아도 됨
        long[] arr = new long[1001];

        arr[1] = 1;
        arr[2] = 2;

        for (int i = 3; i < arr.length; i++) {
            arr[i] = (arr[i-1] + arr[i-2]) % 10007;
        }

        System.out.println(arr[target]);

    }
}