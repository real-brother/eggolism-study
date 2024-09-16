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
        String input = br.readLine();

        // key는 중복 제거
        Map<String, Integer> map = new HashMap<>();
        for (int i = 0; i < input.length(); i++) {
            for (int j = i; j < input.length(); j++) {
                map.put(input.substring(i,j+1), 1);
            }
        }

        System.out.println(map.size());
    }

}
