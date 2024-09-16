package BOJ_9375_패션왕신해빈;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {

        /*문제 설명
         * 1. 옷을 입을 수 있는 경우의 수를 구하는 문제
         * 2. 옷의 종류와 이름이 나오는데 같은 조합으로 옷을 입지 않음
         * 3. 따라서, 이름이 전부 달라져야 하고, 상식적으로 옷의 종류는 겹치지 않아야 함
         * (이름이 다르더라도 바지를 두번 입을 수 없음)
         * 4. A B C의 옷을 입은 상태에서 종류가 겹치지 않는 D만 추가해도 다른 경우의 수가 되고
         * 5. A만 입어도 다른 경우의 수가 됨
         * 6. 대신 알몸인 상태만 아니면 됨*/

        /*설계 아이디어
         * 1. 알몸만 아닌 상태인 것에서 힌트를 얻음
         * 2. 각 옷의 카테고리별로 경우의 수를 곱해서 조합을 구할 수 있음
         * 3. 단 각 카테고리별의 경우의 수에 +1을 하여 카테고리에서 아무것도 입지 않는 경우에 수도 포함
         * 4. 대신, 마지막에 전체 값에 -1을 하여 알몸인 경우를 제외 시킴*/

        /*풀이 과정
         * 1. 입력 값에 따라 HashMap을 채움
         * 2. 추가적으로 HashSet을 이용하여 순수한 category를 구하려고 함
         * -> 기능이 따로 있나?
         * 3. 순수한 카테고리 별로 HashMap의 요소들을 순회하며, 카테고리별 옷의 개수를 구함
         * 4. (카테고리별 옷의 개수+1)의 곱하기를 반복하여 경우의 수를 만듦
         * 5. 최종적인 answer--;를 하여 답 도출 */

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int Test = Integer.parseInt(br.readLine());

        // 테스트 시작
        for (int t = 0; t < Test; t++) {

            // 1. HashMap 채우기
            // 처음에 K는 category, V는 name이 나오도록했는데, key가 동일해서 아예 추가를 시키지 않음
            // -> 결국 K는 name, V는 category로 함
            // category의 종류를 구하기 위해 set이용
            int total = Integer.parseInt(br.readLine());

            HashMap<String, String> map = new HashMap<>();
            Set<String> set = new HashSet<>();

            for (int i = 0; i < total; i++) {
                String[] inputs = br.readLine().split(" ");
                map.put(inputs[0], inputs[1]);

                set.add(inputs[1]);
            }

//            System.out.println(map); // map은 그냥 출력이 가능하다.

            // 2. Value별로 key의 개수 구하기
            // 경우의 수를 생각하면 각 Category(value)별로 + 1 하고 곱한다음
            // 마지막 1만 빼면 됨 (아예 선택되지 않는 경우. 즉, 알몸인 경우)
            int answer = 1;

            for (String category : set) {
                int count = 1;
                for (Map.Entry<String, String> item : map.entrySet()) {
                    if (item.getValue().equals(category)) {
                        count++;
                    }
                } // 한 category 끝
                answer *= count;

            }
            sb.append(answer -1).append("\n");
        } // 테스트 끝

        System.out.println(sb);
    }
}
