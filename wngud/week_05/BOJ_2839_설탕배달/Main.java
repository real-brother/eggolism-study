import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        /*
        * 설계방식
        * 1. 최대한 많은 5kg 봉지를 사용하도록 한다.
        * 2. 정확한 숫자가 되어야 하므로 5kg를 많은 수대로 3kg를 늘려 확인한다.
        * 3. 순회를 위해 최고로 많이 필요한 5kg의 개수와 3kg 개수를 각각 구한다.
        **/

        Scanner sc = new Scanner(System.in);

        int input = sc.nextInt();

        int max_five = ((input / 5) + 1);
        int max_three = ((input / 3) + 1);

        int answer = -1; // 5와 3으로 떨어지지 않는다면 자동으로 -1 출력

        // 계속 덮어씌우도록 하여 가장 많은 개수를 출력하도록 함
        for (int i = 0; i < max_five; i++) {
            for (int j = 0; j < max_three; j++) {
                if (input - 5*i - 3*j == 0) {
                    answer = i+j;
                }
            }
        }
        System.out.println(answer);
    }

}