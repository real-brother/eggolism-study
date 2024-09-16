import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Stack;

public class BOJ_10828_스택 {

    public static void main(String[] args) throws IOException {
//        Scanner sc = new Scanner(System.in);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

//        int total = sc.nextInt();
//        sc.nextLine();
        int total = Integer.parseInt(br.readLine());

        // 스택 생성
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < total; i++) {

//            String input = sc.nextLine();
            String input = br.readLine();

            // 1. push
            // contain
            // input의 띄어쓰기 뒷부분을 숫자취급
            if (input.contains("push")) {
                String[] temp = input.split(" ");
                int num = Integer.parseInt(temp[1]);
                stack.push(num);
            }

            // 2. top
            // try-catch 사용
            else if (input.equals("top")) {
                try {
                    System.out.println(stack.peek());
                } catch (Exception e) {
                    System.out.println(-1);
                }

            }

            // 3. size
            else if (input.equals("size")) {
                System.out.println(stack.size());
            }

            // 4. empty
            else if (input.equals("empty")) {
                if (!stack.isEmpty()) {
                    System.out.println(0);
                } else {
                    System.out.println(1);
                }
            }
            // 5. pop
            // try-catch 사용
            else if (input.equals("pop")) {
                try {
                    System.out.println(stack.pop());
                } catch (Exception e) {
                    System.out.println(-1);
                }
            }

        }




    }
}