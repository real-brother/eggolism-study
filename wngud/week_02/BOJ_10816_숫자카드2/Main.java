package BOJ_10816_숫자카드;

import java.util.Arrays;
import java.util.StringTokenizer;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {

        /*문제 설명
        * */

        /*문제가 어려웠던 점
         * 1. 단순히 존재 여부를 넘어서 개수를 세어주어야 함 (존재 여부만 찾는다면 contains)
         * -> 이전 스터디에서 set으로 풀었던 <숫자 카드>
         * 2. 일반적으로 사용했던 카운팅 리스트를 만들려고 하는데, 음수가 포함되어 있음
         * -> 개별적으로 출력하는 방식으로 진행*/

        /*문제의 여러가지 풀이
        * 1. 선형 탐색으로 동일하면 count++를 하는 방식 -> 시간 초과
        * 2. indexOf를 통해서 먼저 인덱스의 가운데를 찾고 양쪽을 탐색하여 count++ 방식 --> 시간 초과
        * **3. 이진탐색을 통해서 먼저 인덱스의 가운데를 찾고 양쪽을 탐색하여 count++ 방식 --> 60퍼 통과
        * **4. 양쪽의 인덱스를 이진탐색으로 찾고, 인덱스의 차이를 구하는 방식 --> 통과 */

        /*설계 방식
        * 1. M의 원소 하나를 뽑은 다음(타겟 숫자) N을 순회하며 갯수를 찾는 방식
        * 2. 빠르게 찾기 위해 먼저 정렬을 한 뒤, 타겟 숫자의 처음 인덱스와 끝 인덱스를 찾기
        * 3. 가운데를 기준으로 아래와 */

        /*번외) 시간 초과를 대하는 입력의 기본 자세
         * 1. 출력을 할 때, Stringbuilder는 무조건 쓴다.
         * 2. Buffered를 쓰면 빠르나, 한줄을 통째로 받아오니 이후에 나누는 작업 필요
         * 따라서, StringTokenizer가 필수
         * 3. Scanner는 개행문자가 잘 되어있는 상황에서 좋음. 나눌 필요없이 리스트에 추가 가능
         *
         * 결론,
         * 1. StringBuilder는 무조건 쓴다.
         * 2. 개행문자가 잘 되어 있어있고 리스트에 추가하는 경우에는 Scanner를 고려한다.
         * 3. 아니면, Buffered와 StringTokenizer를 빠르게 습득하여 백준에서 살아남는다.*/



        // 입력 받기
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 1. N값 배열 채우기
        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        // N관련 리스트는 이진탐색을 위해 정렬 필수
        Arrays.sort(arr);


        // 2. M값 배열 채우기
        int M = Integer.parseInt(br.readLine());

        st = new StringTokenizer(br.readLine(), " ");
        StringBuilder sb = new StringBuilder();

        // for문 최소하하기 위해
        // M값의 원소 하나를 뽑아서 배열에 넣지 않고, 메서드로 바로 넣어서 결과를 찾기
        for (int i = 0; i < M; i++) {
            int key = Integer.parseInt(st.nextToken());

            // upperBound와 lowerBound의 차이 값 구하기
            sb.append(upperBound(arr, key) - lowerBound(arr, key)).append(' ');
        }
        System.out.println(sb);
    }


    // 똑같은 상황에서 어떻게 움직일지가 중복의 인덱스를 찾는데 핵심
    // 똑같을 때는 rgt를 감소시키고
    // mid 값이 더 작아다면, lo를 키운다.
    private static int lowerBound(int[] arr, int key) {
        int lft = 0;
        int rgt = arr.length; // 숫자 조심

        // 조건 조심: lft == rgt 될 때 종료
        while (lft < rgt) {
            int mid = (lft + rgt) / 2;

            // key 값이 중간 위치의 값보다 작거나 같을 경우
            // 중복 원소에 대해 왼쪽으로 탐색하도록 오른쪽이 이동
            if (key <= arr[mid]) {
                rgt = mid;
            }
            else {
                lft = mid + 1;
            }

//            if (key == arr[mid]) rgt = mid;
//            else if (key < arr[mid]) rgt = mid;
//            else if (key > arr[mid]) lft = mid+1;
        }

        return lft;

    }

    // 똑같을 때는 lft를 증가시키고
    // mid 값이 더 커졌다면, hi를 줄인다.

    private static int upperBound(int[] arr, int key) {
        int lft = 0;
        int rgt = arr.length;

        while (lft < rgt) {
            int mid = (lft + rgt) / 2;

            // key값이 중간 위치의 값보다 작을 경우
            if (key < arr[mid]) {
                rgt = mid;
            }
            // 중복원소의 경우 else에서 처리된다.
            else {
                lft = mid + 1;
            }

//            if (key == arr[mid]) lft = mid+1;
//            else if (key < arr[mid]) rgt = mid;
//            else if (key > arr[mid]) lft = mid+1;
        }

        return lft;
    }


}