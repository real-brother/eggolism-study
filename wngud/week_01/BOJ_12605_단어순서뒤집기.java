import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BOJ_12605_단어순서뒤집기 {
    public static void main(String[] args) {

        /*설계 아이디어
        * 1. FILO 구조이니 스택 구조 사용 가능*/

        // 입력 받기
        Scanner sc = new Scanner(System.in);

        int total = sc.nextInt(); // 전체 Case 수(N)
        sc.nextLine();

        // 1. 각각 한줄 line을 리스트에 저장
        // 필요한 과정은 아니지만, 문제를 깔끔하게 풀기위해 저장
        List<String> totalLine = new ArrayList<>();

        for (int i = 0; i < total; i++) {
            String line = sc.nextLine();
            totalLine.add(line);
        }

        // 2. 리스트 출력
        for (int i = 0; i < totalLine.size(); i++) {
            // 한 줄 세팅
            // 띄어쓰기를 기준으로 각각 단어를 만들고
            // words 배열에 저장
            String[] words =  totalLine.get(i).split("\\s");

            // 한 줄 출력
            System.out.print("Case #" + (i+1) + ":"); // 출력 형식
            // 인덱스 뒤에서부터 출력
            for (int j = words.length-1; j >= 0 ; j--) {
                System.out.print(" " + words[j]);
            }

            // 마지막의 경우가 아니라면 (일반적인 상황이면)
            // 엔터침 (크게 중요X)
            if (i != totalLine.size()-1) {
                System.out.println();
            }
        }

    }
}