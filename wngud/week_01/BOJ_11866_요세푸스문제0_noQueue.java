import java.util.Scanner;

public class BOJ_11866_요세푸스문제0_noQueue {
    public static void main(String[] args) {

        /*설계 아이디어_noQueue
        * 1. 원과 같은 순환형 인덱스가 필요
        * 보통 이럴 때는 `%`를 떠올리기 마련임
        *
        * 2. 이전에 방문한 사람들을 제외하고 K까지 count를 해야 함
        * 그리고 그 자료를 제거
        * 이를 구현하기 위해 리스트에서 그 사람을 제외할지 살려야 할지 고민을 함
        * 제외를 해버리면 idx가 꼬여서 더 어려워진다고 판단
        * 따라서, 리스트에서 제외 시키지 않고 `visited` 개념(기록법)과 조건을 추가하여
        * 리스트를 삭제하지 않고 유지할 수 있었음
        *
        * 3. visited 개념
        * 리스트가 반복적으로 돌아갈 때, 이미 선택된 사람은 건너뛰는 것에 count를 하면 안됨
        * 따라서 visited == 1로 이미 선택된 사람을 기록하고,
        * 이 사람을 지나가면 count에 계산을 넣지 않도록 함*/

        // 입력 받기
        Scanner sc = new Scanner(System.in);

        int numberOfPeople  = sc.nextInt();
        int eliminationOrder = sc.nextInt();

        // 1. 리스트 세팅하기
        int[] circle = new int[numberOfPeople];
        int[] visited = new int[numberOfPeople]; // 선택된 사람 표시 용도
        int[] answers = new int[numberOfPeople]; // 선택된 사람 정답 추가 용도

        // 원 채우기 (1부터)
        for (int i = 1; i <= numberOfPeople; i++) {
            circle[i-1] = i;
        }


        // 2. 원 리스트 순회하기 [핵심]
        int idx = 0; // 인덱스를 계속 쌓대, `%`를 이용하여 원 순회 가능하도록 함
        int idxOfAnswer = 0;
        for (int i = 0; i < circle.length; i++) {
            int count = 0; // 제거 숫자가 되기 위함

            // 순회 한 번 시작
            // 제거 순서(ex. 3)이 될 때까지 계속 반복
            while (count != eliminationOrder) {

                // 정답으로 처리된 사람이 아니면 count가 유효하여 count++
                if (visited[idx%numberOfPeople] == 0) {
                    count++;
                }

                // count++가 쌓여서 제거 순서까지 온 경우
                // 정답에 추가를 하고
                // 정답에 추가된 인덱스는 visited로 추가 (이후에 count 계산에 제외되도록)
                if (count == eliminationOrder) {
                    answers[idxOfAnswer] = circle[idx%numberOfPeople];
                    idxOfAnswer++;

                    visited[idx%numberOfPeople] = 1;
                }

                idx++;

            } // 순회 한 번 끝

        }

        // 3. 정답 출력하기
        // 꺽새를 추가해야 되어서 귀찮게 출력했음
        System.out.print("<");
        for (int i = 0; i < answers.length; i++) {
            if (i == answers.length - 1) {
                System.out.print(answers[i] + ">");
            } else {
                System.out.print(answers[i] + ", ");
            }

        }

    }
}