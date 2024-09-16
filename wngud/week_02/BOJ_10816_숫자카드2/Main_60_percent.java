package BOJ_10816_숫자카드;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_60_percent {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        // 1. N 받기 (자신이 가지고 있는 카드)
        int N = Integer.parseInt(br.readLine());
        int[] listForN = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < N; i++) {
            listForN[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(listForN); // 오름차순 정렬 (이진탐색은 정렬해줘야 함)


        // 2. M 받기 (개수를 세어야 할 카드)
        int M = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine(), " ");

        for (int i = 0; i < M; i++) {
            int num = Integer.parseInt(st.nextToken());
            sb.append(binarySearch(listForN, num, 0, listForN.length-1)).append(" ");

        }

        System.out.println(sb);

    }


    static int binarySearch(int[] list, int key, int low, int high) {
        int mid;
        while (low <= high) {
            mid = (high + low) / 2;

            // 1) 성공 -> 인덱스 외 개수를 찾기 위해 추가 작업
            if (key == list[mid]) {
                int count = 1; // 1개로 시작
                int lft = mid-1;
                int rgt = mid+1;

                while (true) {
                    if (lft >= 0 && key == list[lft]) {
                        count++;
                        lft--;
                    } else break;
                }
                while (true) {
                    if (rgt < list.length && key == list[rgt]) {
                        count++;
                        rgt++;
                    } else break;
                }

                return count;
            }

            // 2) 왼쪽
            else if (key < list[mid]) high = mid -1;
                // 3) 오른쪽
            else if (key > list[mid]) low = mid + 1;

        }
        return 0; // else -> 인덱스를 못 찾으면 0개임
    }

}