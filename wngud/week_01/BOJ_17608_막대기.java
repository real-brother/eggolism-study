import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class BOJ_17608_막대기 {
    public static void main(String[] args) {

        // 입력 받기
        Scanner sc = new Scanner(System.in);

        int total = sc.nextInt();

        // 1. 빌딩 높이를 배열에 넣기
        List<Integer> arr = new ArrayList<>(total);
        for (int i = 0; i < total; i++) {
            int num = sc.nextInt();
            arr.add(num);
        }

        // 2. 보이는 막대 확인하기
        // 뒤에서부터 보이는 높이 저장
        // 이것보다 큰 것이 있다면 count++하고 보이는 높이 초기화
        int count = 0;
        int sight = 0;
        for (int i = total-1; i >= 0; i--) {
            if (arr.get(i) > sight) {
                sight = arr.get(i);
                count++;
            }

        }

        // 3. 결과 출력하기
        System.out.println(count);

    }
}