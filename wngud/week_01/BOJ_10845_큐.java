import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class BOJ_10845_큐 {
    public static void main(String[] args) throws IOException {

//        Scanner sc = new Scanner(System.in);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

//        int total = sc.nextInt();
        int total = Integer.parseInt(br.readLine());
        Queue<Integer> queue = new LinkedList<>();

        // 시작
        for (int i = 0; i < total; i++) {
//            String input = sc.nextLine();
            String input = br.readLine();

            // 1
            if (input.contains("push")) {
                String[] temp = input.split(" ");
                queue.offer(Integer.parseInt(temp[1]));
            }
            // 2
            // try-catch 썼는데 poll()는 없으면 null값을 반환하여(에러X) 불가
            else if (input.equals("pop")) {
                if (!queue.isEmpty()) {
                    System.out.println(queue.poll()); // 삭제 및 반환 (없으면 null이라도 반환)
                } else {
                    System.out.println(-1);
                }
            }
            // 3
            else if (input.equals("size")) {
                System.out.println(queue.size());
            }

            // 4
            else if (input.equals("empty")) {
                if (!queue.isEmpty()) {
                    System.out.println(0);
                } else {
                    System.out.println(1);
                }
            }
            // 5
            // 이것 또한 null 반환 이유로 try-catch 사용 불가
            else if (input.equals("front")) {
                if (!queue.isEmpty()) {
                    System.out.println(queue.peek()); // 반환
                } else {
                    System.out.println(-1);
                }
            }

            // 6
            // 큐는 인덱스가 없기 때문에 수작업이 필요함
            // 큐를 받고 큐의 오브젝트를 받아서,
            // 형변환하여 정수를 출력
            else if (input.equals("back")) {
                if (queue.isEmpty()) {
                    System.out.println(-1);
                } else {
                    System.out.println(lastOne(queue));
                }

            }

        }

    }

    // Object를 해보긴 했는데
    // int를 반환하게 할 수는 없을까?
    static int lastOne(Queue queue) {
        int x = 0;
        for (Object o : queue) {
            x = (int) o; // 계속 덮어쓰기해서 마지막의 x가 진짜 x가 됨
        }
        return x;
    }

}