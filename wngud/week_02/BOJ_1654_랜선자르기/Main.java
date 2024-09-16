import java.util.Arrays;
import java.util.StringTokenizer;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {

    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    // 입력 받기
    // 최대 높이값도 같이 구하기
    StringTokenizer st = new StringTokenizer(br.readLine());
    int K = Integer.parseInt(st.nextToken());
    int N = Integer.parseInt(st.nextToken());

    // 배열 받기
    int[] arr = new int[N];
    int max_height = 0;
    for (int i = 0; i < K; i++) {
    	int num = Integer.parseInt(br.readLine());
    	arr[i] = num;
    	
    	if (max_height < num) max_height = num;
	}
    
    // 이진 탐색 시작
    long answer = binarySearch(arr, N, max_height);
    System.out.println(answer);
    }

    

    static long binarySearch(int[] arr, int key, int length) {
        long pl = 1; // 반례 #1
        long pr = length; // 반례 #2
        long count = 0;

        while (pl < pr) {

            long pc = ((pl + pr) / 2)+1; // 높이

            // pc가 높아지면, count는 줄어듦
            count = 0;
            for (int i = 0; i < arr.length; i++) {
                count += arr[i] / pc; // 자동 버림 
            }

            if (key <= count) {
                pl = pc;

            } else {
                pr = pc-1;
            }

        }
        return pl;
    }


}


/*반례 찾기
 * 1. 탐색을 0부터 시작할 경우 divByZero 발생 가능 + 랜선을 분할하는 최장 길이는 0이 될 수 없음
 * 
 * 1 1
 * 1
 * 
 * 2. K개의 길이값과 최장 길이값이 모두 같은 경우
 * 
 * 5 5
 * 2
 * 2
 * 2
 * 2
 * 2
 * 
 */
