package BOJ_12605;

import java.util.*;
import java.io.*;
public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int tc = Integer.parseInt(br.readLine());

        for (int i = 0; i < tc; i++) {
            String str = br.readLine();
            StringTokenizer st = new StringTokenizer(str, " ");

            List<String> tokens = new ArrayList<>();
            while (st.hasMoreTokens()) {
                tokens.add(st.nextToken());
            }

            // 리스트를 역순으로 정렬
            Collections.reverse(tokens);

            // 역순으로 정렬된 리스트를 출력
            bw.write("Case #" + (i+1) + ": ");
            for (String token : tokens) {
                bw.write(token + " ");
            }
            bw.flush(); // BufferedWriter의 내용을 출력하고, 버퍼 비우기
            bw.newLine();
        }

        
        bw.close(); // BufferedWriter 닫기
        br.close(); // BufferedReader 닫기
		
	}
	
}
	

