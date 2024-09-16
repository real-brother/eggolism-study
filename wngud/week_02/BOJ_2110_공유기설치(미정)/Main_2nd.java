import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_2nd {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 입력 받기
        // 첫째줄
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());

        // 연속

        long[] arr = new long[N];
        for (int i = 0; i < N; i++) {
            long num = Long.parseLong(br.readLine());
            arr[i] = num;
        }

        // 정렬
        Arrays.sort(arr);

        // 이진 탐색 시작
        long answer = binarySearch(arr, C);
        System.out.println(answer);

    }

    // 예외1: 끝과 끝-1이 정답이 될 경우
    // -> 왼쪽이 기준이라서
    
    
    // 인덱스 반환
    static long binarySearch(long[] arr, int target) {
        int pl = 0;
        int pr = 1;
        int end_idx = arr.length-1;
        
        long diff = 0;
        long min_diff = arr[end_idx] - arr[0]; // 아무것도 업데이트 안되면
        
        long ideal = (long) Math.floor((arr[end_idx] - arr[0]) / (target-1)); // 섬세하게
//        System.out.println("이상: " + ideal);

        int count = 0;
        while (pl <= pr && pr <= end_idx) {
            
        	diff = arr[pr] - arr[pl];
//            System.out.println("좌표: " + pl + " " + pr);
//            System.out.println("차이: " + diff);

            // 오른쪽이 우선으로 움직이게
            // 딱 커지는 순간은 의미 없음 -> 커지는 순간 전까지가 의미
            // 찾는 것은 딱 커지는 순간!
            // 작거나 같아야 함 -> 근데 여러 포인트라서 이진 탐색으로 불가
            // 이상값: 같은 것이랑 넘어간 것이랑 같은 취급.
            
            if (ideal > diff) {
            	pr++;
            } else {
            	// 최신화 -> 이전값이랑 비교
            	// 2개인 예외 막기
            	if ((min_diff > arr[pr-1] - arr[pl]) && (pl != pr-1)) {
//            		System.out.println("이 이전값으로 고고!");
            		min_diff = arr[pr-1] - arr[pl];
            	}
            	// 마지막 순간
            	if ((count == target-2) && arr[pr-1] - arr[pl] < arr[end_idx] - arr[pr-1]) {
//            		System.out.println("예외!");
            		min_diff = arr[end_idx] - arr[pr-1];
            	}
            	
            	// 끝부분 예외 막기
//            	if (min_diff > arr[pr-1] - arr[pl]) && (pl != pr-1)
            	
            	pl = pr-1; // 건너뛰면 됨 (pr 더해주면 끝 쪽에서 예외 발생)
            	pr++;
                count++;
            }
            


        } // while 끝

        // 판별
        return min_diff;
    }

}
