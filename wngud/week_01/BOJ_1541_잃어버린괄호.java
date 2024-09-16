import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class BOJ_1541_잃어버린괄호 {
    public static void main(String[] args) {

        /*설계 아이디어
        * 1. 최소값을 만들어야 함
        * -가 있으면 괄호를 시작하여 계속 +해주면, -를 극대화할 수 있음
        * 중간에 -가 있으면 괄호를 닫고 다시 시작해야 괄호 안이 극대화할 수 있음
        * 2. -를 기준으로 리스트를 만들고, 이 리스트의 원소 하나하나를 더해서
        * sum에서 빼면 됨
        * 3. 예외
        * 대신, 첫 인덱스는 무조건 +이기 때문에 이러한 예외는 sum에서 더해줘야 함*/

        // 입력 받기
        Scanner sc = new Scanner(System.in);

        String input = sc.nextLine();
        int sum = 0;

        // 1. 입력값을 - 기준으로 나눠서 리스트 (검색 도움 받음)
        List<String> numberList = Arrays.asList(input.split("[\\-]"));


        // 2. 최소화 만들기
        // -가 있으면 그 다음 -가 있을 때까지 더하기
        // -를 기준으로 나눠진 리스트를 하나하나 순회하면서 진행
        for (int i = 0; i < numberList.size(); i++) {
            // -를 기준으로 나눴으니, numberList의 한 원소는 +밖에 없음
            // 이것들을 +로 다시 나눠준다음 sum에서 빼주면 됨
            List<String> calList = Arrays.asList(numberList.get(i).split("[\\+]"));

            // 예외
            // 1) 대신, 처음(인덱스0)은 무조건 +이고, 이 묶음은 sum에 더해줘야 함
            if (i == 0) {
                for (int j = 0; j < calList.size(); j++) {
                    sum += Integer.parseInt(calList.get(j));
                }
                // 2) 그 외 나머지 상황에서는 list를 다 합쳐서 sum에서 빼줌
            } else {
                for (int j = 0; j < calList.size(); j++) {
                    sum -= Integer.parseInt(calList.get(j));
                }
            }

        }

        // 3. 정답 출력하기
        System.out.println(sum);


    }
}