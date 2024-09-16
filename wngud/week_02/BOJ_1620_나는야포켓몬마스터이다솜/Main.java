import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        // 입력 받기
        // 첫째줄
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        // 도감 만들기(N)
        // get()이 빠르기 때문에 두개의 맵을 넣었다... -> 검색 참고
        Map<String, Integer> map = new HashMap<>();
        Map<Integer, String> map2 = new HashMap<>();
        for (int i = 1; i <= N; i++) {
            String input = br.readLine();
            map.put(input, i);
            map2.put(i, input);
        }
//        System.out.println(map);


        // 비교하며 출력하기 (M)
        // 문자로 받고, 아니면 숫자로 받기
        // --> 에러가 안떠서 parseint 먼저
        // --> 그냥 뺐다...
        for (int i = 0; i < M; i++) {
            String input = br.readLine();

            if (isNum(input)) {
                int input_int = Integer.parseInt(input);
                sb.append(map2.get(input_int)).append("\n");
            } else {
                sb.append(map.get(input)).append("\n");
            }
        }

        System.out.println(sb);
    }


public static boolean isNum(String str) {
    if (!Character.isDigit(str.charAt(0))) {
        return false;
    }
    return true;
}

}
