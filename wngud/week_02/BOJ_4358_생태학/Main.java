import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        Map<String, Integer> map = new HashMap<>();
        List<String> list = new ArrayList<>();

        // 1. 리스트 받기
        String input = "";
        while((input = br.readLine()) != null) {
            list.add(input);
        }

        // 2. 리스트 정렬하기
        Collections.sort(list);

        // 3. 리스트 출력하기
        double total = list.size();
//        System.out.println(list.toString());
        double count = 1;

        for (int i = 0; i < total; i++) {
            // 마지막 예외처리
            if (i == total -1) {
                System.out.printf("%s %.4f\n", list.get(i), (count * 100 / total));
            }

            else if (!list.get(i).equals(list.get(i+1))) {
                System.out.printf("%s %.4f\n", list.get(i), (count * 100 / total));
                count = 0;
            }
            count++;
        }



    }

}
