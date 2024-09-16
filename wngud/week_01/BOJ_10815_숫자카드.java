import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;

public class BOJ_10815_숫자카드 {
    public static void main(String[] args) {

        // 입력 받기
        Scanner sc = new Scanner(System.in);

        // 1. 가지고 있는 리스트 만들기
        // 가지고 있는 것은 리스트가 필요없어서 Set 사용
        int mine_total = sc.nextInt();

        HashSet<Integer> mine = new HashSet<>();
        for (int i = 0; i < mine_total; i++) {
            int num = sc.nextInt();
            mine.add(num);
        }

        // 2. 체크해야 하는 리스트
        // set은 get 메서드가 없어서 리스트를 만듦
        int check_total = sc.nextInt();

        List<Integer> check = new ArrayList<>();
        for (int i = 0; i < check_total; i++) {
            int num = sc.nextInt();
            check.add(num);
        }

        // 3. 순회하면서 내가 가지고 있는 것과 비교
        // contain 사용
        for (int i = 0; i < check.size(); i++) {
            if (mine.contains(check.get(i))) {
                System.out.print(1 + " ");
            } else {
                System.out.print(0 + " ");
            }

        }


    }
}