import java.util.Scanner;
import java.util.LinkedList;
import java.util.Queue;

public class BOJ_11866_요세푸스문제0 {
    public static void main(String[] args) {

        /*설계 아이디어
        * 1. 원은 끊임없는 반복임
        * 1부터 7이 있다고 했을 때, 이것이 반복되는 원을 상상할 수도 있지만,
        * `123457 1234567 1234567...`이 반복되는 상황도 고려해볼 수 있음
        * 따라서, 이 구조는 `1234`가 있을 때 `12`를 무시하고 싶다면
        * `12`를 자료 뒤로 보내는 방식으로 원형의 원리를 유지할 수 있음
        * 이는 큐를 이용하여 구현가능
        */


        // 입력 받기
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        int M = sc.nextInt();

        // 세팅하기
        StringBuilder result = new StringBuilder(); // 결과 출력 용도


        // 1. 큐 세팅하기
        // N만큼의 숫자를 큐에 넣기
        Queue<Integer> queue = new LinkedList<>();

        for(int i = 1; i <= N; i++) {
            queue.offer(i); // offer == 넣기!
        }

        // 2. 큐 실행하기
        // 원래는 N개 숫자가 나와야하니 N번 반복
        // 마지막 한 개는 >와 함께 출력하기 위해 N-1까지만 진행
        result.append("<");
        for(int i = 0; i < N-1; i++){
            // 건너뛰느라 선택되지 않는 M-1개는 빼고 다시 뒤에 넣기
            for(int j = 0; j < M-1; j++) {
                queue.offer(queue.poll()); // poll & offer == 빼고 뒤로 넣는 방법!
            }
            // 선택된 M번째는 빼고 결과에 추가하기
            result.append(queue.poll()+", ");
        }

        result.append(queue.poll()+">"); // 마지막 한 개

        // 3. 결과 출력하기
        System.out.println(result);

        sc.close();
    }
}